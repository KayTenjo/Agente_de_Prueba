
import ComandosAgente.*;
import ComandosAgente.ACCEPT;
import ComandosAgente.ArgumentsRegister;
import ComandosAgente.ArgumentsSessionStart;
import ComandosAgente.REGISTER;
import ComandosAgente.REG_SUCESS;
import ComandosAgente.RULES;
import ComandosAgente.SESSION_START;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kabot-Zoe
 */
public class Juego {
    
//reglas y características del tablero
    
    public static String id; // id asignado al cliente durante el proceso de registro
    public static int height; // cantidad de filas del tablero (x)
    public static int width; // cantidad de columnas del tablero (y)
    public static int maxBoardReqs; // cant de solicitud de info del tablero por partida
    public static boolean timedTurn =  false; // variable que indica si existe duracion máxima de tiempo para cada turno
    public static int turnDuration; // tiempo que duran los turnos, solo si timedTurn es verdadero
    public static boolean inmediateTurn = false;
    public static int maxIdleTime;
    public static int maxRoundTime; //limite de duracion de un round
    public static int maxMachTime; // limite absoluto de la duracion del match (0 segundos desactiva el limite)
    public static int roundsPerMatch; // numero de rondas a jugar por cada enfrentamiento entre jugadores distintos
    public static boolean noConnect3 = true; //evitar conectar 3 piezas antes que 4
    public static boolean tournament = false; //especifica si se esta llevando a cabo un torneo
    public static List penalizeIllegalMoves; // indica si se pueden realizar jugadas ilegales
    public static boolean illegalMoveLose = false; //indica si el jugador perdio por realizar un movimiento ilegal
    public static boolean wrongPosLose = false; //booleando que si un jugador perdio por colocar una pieza en una zona ya jugada con anterioridad
    public static boolean noPass = false; //booleando que indica si un jugador puede saltarse turnos hasta maxPasses
    public static int maxPasses; // cantidad máxima de turnos que un jugador puede pasar
    public static boolean timeoutLose = false; //indica si existe un limite de veces que un usuario puede perder turnos por tiempo
    public static int timeoutsForLose; // cantidad de turnos que puede perder un usuario por no ocupar el tiempo del turno
    public static boolean enable = true;// activa o desactiva el performance
    public static int minRounds = 10; //cantidad minima de rondas que deben completarse antes de finalizar el Match
    public static float efficencyLimit = (float) 0.75;
    
    /*Tablero*/
    
    public static int[][] tablero;
    public static boolean jugando4 = true;
    
    /*Nombre y características del Agente*/
    
    public static String nombreAgente = "Agente 1";
    public static String passAgente = "TresTristesTigres";
    public static String tipoAgente = "IA";
    public static int turno = 3;//1 = primero, 2 = segundo, 3 = aun no asignado (no es necesario el 3 pero se quedara así mientras).
    
    /*nombre y caracteristicas del adversario*/
    public static String nombreAdversario;
    public static String idAdversario;
    public static String nombrePartida;
    
    public static float cantTurnos = 0;
    
    
    
    public void aplicacion()throws IOException{
    
        /*creamos las variables necesatias para la comunicacion*/
        
        Socket socket = new Socket("localhost",7075);
    
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

        BufferedReader  entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
          
        /*Comunicacion generada*/
        
        /*Comenzando proceso de registro*/
        
        Conversion conversion = new Conversion();
        
        /*iniciando nuevo registro*/
        
        REGISTER nuevoRegistro = new REGISTER();
        nuevoRegistro = conversion.creaRegistro("Agente1", "abcdef", "IA"); // creando json de registro
        Gson gson = new Gson();
        String jsonRegistro = gson.toJson(nuevoRegistro); // creando string json
            
        /*enviando registro al GM*/
        salida.writeUTF(jsonRegistro); // enviando registro al gm
        
        String resRegistro = entrada.readLine(); // recibindo respuesta
        
       
        
        if(resRegistro.equals("{\"command\":\"ERR_UNKOWN_COMMAND\",\"arguments\":()")){
        
            System.out.println("Error 1: Registro Invalido");
        
        }
        else{
        /*zona de registro correcto*/
            System.out.println(resRegistro);
            
            REG_SUCESS registroValido = new REG_SUCESS();
            
            registroValido = conversion.registroValido(resRegistro);
            
            System.out.println(registroValido.getCommand());
            
            /* // ciclo para continuar proceso de registro infinitamente
            while(!registroValido.getCommand().equals("REG_SUCESS")){
            
                    salida.writeUTF(jsonRegistro); // enviando registro al gm
        
                    resRegistro = entrada.readLine();
            
            }
            */
            
            id = registroValido.getArguments().getId(); // obteniendo la id enviada por el GM
            
            /*Iniciando nueva sesion*/
            SESSION_START nuevaSesion = new SESSION_START();
            nuevaSesion = conversion.inicioSesion(id);
            String jsonSesionStart = gson.toJson(nuevaSesion);
            
            //System.out.println(jsonSesionStart);
            salida.writeUTF(jsonSesionStart);
            String recSessionSucess = entrada.readLine();
            
            //System.out.println(recSessionSucess);
            /*recibiendo reglas*/
            RULES reglas = new RULES();
            reglas = conversion.reglas(recSessionSucess);
            
          //  System.out.println(reglas.getCommand());
            
            /*casteando reglas*/
            
            height = Integer.parseInt(reglas.getArguments().getBoard().getHeight());
            width = Integer.parseInt(reglas.getArguments().getBoard().getWidth()); 
            maxBoardReqs = Integer.parseInt(reglas.getArguments().getBoard().getMaxBoardReqs()); 
            timedTurn = convStrABool(reglas.getArguments().getTime().getTimedTurn()); 
            turnDuration = Integer.parseInt(reglas.getArguments().getTime().getTurnDuration()); 
            inmediateTurn = convStrABool(reglas.getArguments().getTime().getImmediateTurn());
            maxIdleTime = Integer.parseInt(reglas.getArguments().getTime().getMaxIdleTime());
            maxRoundTime = Integer.parseInt(reglas.getArguments().getTime().getMaxRoundTime());
            maxMachTime = Integer.parseInt(reglas.getArguments().getTime().getMaxMatchTime()); 
            roundsPerMatch = Integer.parseInt(reglas.getArguments().getGame().getRoundsPerMatch()); 
            noConnect3 = convStrABool(reglas.getArguments().getGame().getNoConnect3()); 
            tournament = convStrABool(reglas.getArguments().getGame().getTournament()); 
            penalizeIllegalMoves = reglas.getArguments().getGame().getPenalizeIllegalMoves(); // esto es una lista de dos elementos (cantidad de turnos fallidos y penalizacion)
            illegalMoveLose =   convStrABool(reglas.getArguments().getGame().getIllegalMoveLose()); 
            wrongPosLose = convStrABool(reglas.getArguments().getGame().getWrongPosLose());
            noPass = convStrABool(reglas.getArguments().getGame().getNoPass()); 
            maxPasses = Integer.parseInt(reglas.getArguments().getGame().getMaxPasses()); 
            timeoutLose = convStrABool(reglas.getArguments().getGame().getTimeoutLose()); 
            timeoutsForLose = Integer.parseInt(reglas.getArguments().getGame().getTimeoutsForLose()); 
          //enable = convStrABool(reglas.getArguments().getMatchEficiencyLimit().getEnabled());
          //minRounds = Integer.parseInt(reglas.getArguments().getMatchEficiencyLimit().getMinRounds()); 
        //  efficencyLimit = Float.parseFloat(reglas.getArguments().getMatchEficiencyLimit().getEfficiencyLimit());
            
            
            /*Aceptando las reglas*/
            
            ACCEPT aceptando = new ACCEPT();
            aceptando = conversion.aceptando(id);  
            String aceptandoStr = gson.toJson(aceptando);
            
            salida.writeUTF(aceptandoStr);
            
            String aceptandoACKStr = entrada.readLine();
           
           // System.out.println(aceptandoACKStr);
            
            if(!aceptandoACKStr.equals("\"sin definir\"")){
            
                System.out.println("Error 2: mensaje desde GM \"sin definir\" no llego => ack no llego ");
                
            }
            else{
                
              /*ack de aceptacion de reglas logrado*/
                MATCH_LOOK_UP matchUP = new MATCH_LOOK_UP();
                matchUP = conversion.matchLookup(nombreAgente, id);
                String matchUPStr = gson.toJson(matchUP);
                
               // System.out.println(matchUPStr);
                
                salida.writeUTF(matchUPStr);
                
                String matchOK = entrada.readLine();
                
              //  System.out.println(matchOK);
                
                /*match Look UP OK*/
                RULES msjMatchOK = new RULES();
                msjMatchOK = conversion.reglas(matchOK);
                
               
                if(!msjMatchOK.getCommand().equals("MATCH_LOOKUP_OK")){
                
                    System.out.println("Error 3: se esta repitiendo el proceso de matchLookup\n o hubo un error en el envio del comando");
                    
                }
                else{
                    
                    /*iniciando proceso de notificacion (Match Notify)*/

                    String notifys = entrada.readLine();
                    MATCH_NOTIFY matchNotify = new MATCH_NOTIFY();
                    matchNotify = conversion.notificacion(notifys);
                    
                    /*sacando datos del json matchNotify*/
                    nombreAdversario = matchNotify.getArguments().getAdvName(); // nombre del adversario actual
                    idAdversario = matchNotify.getArguments().getAdvId(); // id del adversario actual
                    nombrePartida = matchNotify.getArguments().getMatchName(); // nombre de la partida actual
                
                    /*enviando aceptacion de match*/
                    MATCH_READY iniciando = new MATCH_READY();
                    iniciando = conversion.matchListo(id);
                    String matchReadyStr = gson.toJson(iniciando);
                    
                    salida.writeUTF(matchReadyStr);
                    
                    String OK = entrada.readLine(); // donde recibo un ok
                    
                    if(!OK.equals("OK")){
                    
                    System.out.println("Error 4: Ok no recibido");
                    }
                    else{
                    
                    /*Comienzo del match*/
                        
                        String roundStartStr = entrada.readLine();
                        
                        ROUND_START roundStart = new ROUND_START();
                        roundStart = conversion.roundStart(roundStartStr);
                        
                        if(convStrABool(roundStart.getArguments().getFirstMove())==true){
                       
                            turno = 1;
                        }
                        else{
                
                            turno = 2;
                        }
                                
                        ROUND_START_ACK roundSAck = new ROUND_START_ACK();
                        roundSAck = conversion.roundStartACK(id);
                        String rsAckStr = gson.toJson(roundSAck);
                        
                        salida.writeUTF(rsAckStr);
                        
                        /*comienza el envio de turns*/
                
                        String strTurn = entrada.readLine();
                        
                        TURN turnData = new TURN();
                        
                
                        turnData = conversion.turno(strTurn);
                
                        cantTurnos = cantTurnos+1;
                
                        //int jugada1 = 1;
                        int x,y;
                        /*Comenzando el juego*/
                        while(jugando4){
                    
                            // if(jugada1 == 1 && turno == 1){
                            //  if(turno == 1 && turnData.getArguments().getAdvMove().getMove().equals("FIRST")){   
                            if(turno == 1 && cantTurnos%2 !=0){    
                         
                                x = randomico(height);
                                y = randomico(width);
                          
                                PUT move = new PUT();
                                move = conversion.put(x, y, id);
                                strTurn = gson.toJson(move);
                          
                          
                                 salida.writeUTF(strTurn);
                          //     tablero[x][y] = 1;
                          
                          
                            //   jugada1 = 0;  
                                }
                            else{
                    
                            //if(turno == 2 && turnData.getArguments().getAdvMove().getMove().equals("WAIT") ){
                            if(turno==2 && cantTurnos%2==0){
                        
                        
                                x = randomico(height);
                                y = randomico(width);
                          
                                PUT move = new PUT();
                                move = conversion.put(x, y, id);
                                strTurn = gson.toJson(move);
                          
                                salida.writeUTF(strTurn);
                   //           tablero[x][y] = 1;
                        
                    
                            //jugada1 = 0;
                                }
                            else{
                    
                             /*espero por nuevo turno*/
                             strTurn = entrada.readLine();
                        
                        
                                }
                    
                            }
                
                
                    
                    
                }// fin while del juego
                        
                    
                    } // recepcion del ok después del match Ready
                    
                } // match lock up ok
                
            } // ACK aceptado (mensaje sin definir desde GM)
        }// comando invalido en inicio de registro
    }
    
    public void jugando() throws IOException{
   
        Comunicacion canal = new Comunicacion();
        
        System.out.println("Iniciando conexion al servidor");
        
        try{
       
            
            System.out.println("Socket habilitado. Iniciando registro");
            
             /*guardando datos de registro*/
            
            String registro_fail = "REGISTER_FAIL"; 
            
            while(registro_fail.equals("REGISTER_FAIL")){
            
            REGISTER nuevoRegistro = new REGISTER();
            ArgumentsRegister argRegistro = new ArgumentsRegister();
            argRegistro.setClientName(nombreAgente);
            argRegistro.setClientPass(passAgente);
            argRegistro.setClientType(tipoAgente);
            nuevoRegistro.setArguments(argRegistro);
            nuevoRegistro.setCommand("REGISTER");
            
            /*Enviando datos de registro*/
            
            Gson gsonRegistro = new Gson();
            String jsonRegistro = gsonRegistro.toJson(nuevoRegistro);
            System.out.println(jsonRegistro); // comprobamos que se este enviando
            
            System.out.println("datos de registro enviados");
                       
            String jsonRegSucess = canal.mensajeJuego(jsonRegistro,"REGISTER");
            
            System.out.println("Respuesta del servidor: ");
            System.out.println(jsonRegSucess);
            
            /*convirtiendo la respuesta en objeto reg_sucess*/
            REG_SUCESS  respuestaRegSucess = new REG_SUCESS();
            
            Gson regRespuesta = new Gson();
            respuestaRegSucess = regRespuesta.fromJson(jsonRegSucess,respuestaRegSucess.getClass());
            
            System.out.println("Comando es: "+respuestaRegSucess.getCommand()+ ", "
                    +respuestaRegSucess.getArguments().getClientName()+", "+respuestaRegSucess.getArguments().getId());
            
            if(respuestaRegSucess.getCommand().equals("REG_SUCESS")){
                
                id = respuestaRegSucess.getArguments().getId();
                registro_fail = "REG_SUCESS";
            
            }
            else{
            
                System.out.println("Se ha fallado en el proceso de registro");
            
            }
            
            
            }// fin while register
            
            /*Iniciando comando Session start*/
            System.out.println("Iniciando comando Session start");
            
            SESSION_START inSesion = new SESSION_START();
            ArgumentsSessionStart inSesionArg = new ArgumentsSessionStart();
            inSesionArg.setGameName("Connect 4");
            inSesionArg.setId(id);
            inSesion.setCommand("SESSION_START");
            inSesion.setArguments(inSesionArg);
            
            Gson gsonSesionStart = new Gson();
            String jsonSesionStart = gsonSesionStart.toJson(inSesion);
            System.out.println(jsonSesionStart); // comprobamos que se este enviando
            
            
            /*Recibiendo datos del tablero en el comando RULE*/
            
            String startFail = "error";
            
            String jsonRulesStr = canal.mensajeJuego(jsonSesionStart,"SESSION_START");
            
            
            System.out.println("Respuesta del servidor: ");
            System.out.println(jsonRulesStr);
            
            /*convirtiendo la respuesta en objeto reg_sucess*/
            
            
            
            RULES  rules = new RULES();
            Gson regRules = new Gson();
            rules = regRules.fromJson(jsonRulesStr,rules.getClass());
            
            
            
            
            System.out.println("Algunas reglas son: "+ rules.getArguments().getGame().getIllegalMoveLose());
            
            /*obteniendo características del tablero y datos del match*/
            
            height = Integer.parseInt(rules.getArguments().getBoard().getHeight());
            width = Integer.parseInt(rules.getArguments().getBoard().getWidth()); 
            maxBoardReqs = Integer.parseInt(rules.getArguments().getBoard().getMaxBoardReqs()); 
            timedTurn = convStrABool(rules.getArguments().getTime().getTimedTurn()); 
            turnDuration = Integer.parseInt(rules.getArguments().getTime().getTurnDuration()); 
            inmediateTurn = convStrABool(rules.getArguments().getTime().getImmediateTurn());
            maxIdleTime = Integer.parseInt(rules.getArguments().getTime().getMaxIdleTime());
            maxRoundTime = Integer.parseInt(rules.getArguments().getTime().getMaxRoundTime());
            maxMachTime = Integer.parseInt(rules.getArguments().getTime().getMaxMatchTime()); 
            roundsPerMatch = Integer.parseInt(rules.getArguments().getGame().getRoundsPerMatch()); 
            noConnect3 = convStrABool(rules.getArguments().getGame().getNoConnect3()); 
            tournament = convStrABool(rules.getArguments().getGame().getTournament()); 
            penalizeIllegalMoves = rules.getArguments().getGame().getPenalizeIllegalMoves(); // esto es una lista de dos elementos (cantidad de turnos fallidos y penalizacion)
            illegalMoveLose =   convStrABool(rules.getArguments().getGame().getIllegalMoveLose()); 
            wrongPosLose = convStrABool(rules.getArguments().getGame().getWrongPosLose());
            noPass = convStrABool(rules.getArguments().getGame().getNoPass()); 
            maxPasses = Integer.parseInt(rules.getArguments().getGame().getMaxPasses()); 
            timeoutLose = convStrABool(rules.getArguments().getGame().getTimeoutLose()); 
            timeoutsForLose = Integer.parseInt(rules.getArguments().getGame().getTimeoutsForLose()); 
          //enable = convStrABool(rules.getArguments().getMatchEficiencyLimit().getEnabled());
          //minRounds = Integer.parseInt(rules.getArguments().getMatchEficiencyLimit().getMinRounds()); 
        //  efficencyLimit = Float.parseFloat(rules.getArguments().getMatchEficiencyLimit().getEfficiencyLimit());
            
            
            
            
            /*Aceptando las reglas*/
            
            
            ACCEPT acepRules = new ACCEPT();
            ArgumentsAccept acepArg = new ArgumentsAccept();
            acepArg.setId(id);
            acepRules.setArgumentsAccept(acepArg);
            acepRules.setCommand("ACCEPT");
            
            Gson acepta = new Gson();
            String acepACK = acepta.toJson(acepRules);
            System.out.println(acepACK);
            String ACK = canal.mensajeJuego(acepACK,"ACCEPT");    
            System.out.println(ACK);
            
       
        
        
        
            ACCEPT_ACK valida = new ACCEPT_ACK();
            Gson validaACK = new Gson();
            
         // valida = validaACK.fromJson(ACK, valida.getClass()); // convierto el json a un formato que puedo usar
            
            /*si hay que realizar alguna aceptacion antes debe hacerse en este
            
            
            espacio
            */
            
//            if(!valida.getCommand().equals("ACCEPT_ACK")){
            
  //              System.out.println("accept_ack no llego");
            
    //        }
      //      else{
          
                /*Creando el tablero*/
  //              tablero = new int[height][width];
//                tablero = iniciarMatriz(tablero,height,width);
                
                /*enviando mensaje de busqueda de partidas*/
                MATCH_LOOK_UP buscandoPartida = new MATCH_LOOK_UP();
                ArgumentsMatchLookUp matchArg = new ArgumentsMatchLookUp();
                matchArg.setAdvName(nombreAgente);
                matchArg.setId(id);
                matchArg.setMatchName("AI");
                matchArg.setTimeout("10");
                matchArg.setAdvName("Agente 2");
                buscandoPartida.setCommand("MATCH_LOOKUP");
                buscandoPartida.setArguments(matchArg);
                
                Gson gsonMatch = new Gson();
                
                String rptaMatch = gsonMatch.toJson(buscandoPartida);
                System.out.println(rptaMatch);
                String notify = canal.mensajeJuego(rptaMatch,"MATCH_LOOKUP"); // string del match notify que indica que se puede jugar
                System.out.println(notify);
                
                /*Sacando los datos del mensaje notify*/
                
                MATCH_NOTIFY matchNotifys = new MATCH_NOTIFY();
                Gson notf = new Gson();
                matchNotifys = notf.fromJson(notify, matchNotifys.getClass());
                
                nombreAdversario = matchNotifys.getArguments().getAdvName(); // nombre del adversario actual
                idAdversario = matchNotifys.getArguments().getAdvId(); // id del adversario actual
                nombrePartida = matchNotifys.getArguments().getMatchName(); // nombre de la partida actual
                
                
                /*Enviando mensaje de ready para la match actual*/
                              
                
                MATCH_READY  ready = new MATCH_READY();
                ArgumentsMatchReady readyArg = new ArgumentsMatchReady();
                readyArg.setId(id);
                ready.setCommand("MATCH_READY");
                ready.setArguments(readyArg);
                
                Gson gsonReady = new Gson();
                
                String strReady = gsonReady.toJson(ready);
                System.out.println(strReady);
                String strRoundStart = canal.mensajeJuego(strReady,"MATCH_READY");
                
                System.out.println(strRoundStart); // en este mensaje puede llegar un ok, esto ocurre cuando solo existe un agente en el servidor
                
                ROUND_START start = new ROUND_START();
                Gson startRound = new Gson();
                
                start = startRound.fromJson(strRoundStart,start.getClass());
               
            if(convStrABool(start.getArguments().getFirstMove())==true){
                       
                    turno = 1;
                }
                else{
                
                    turno = 2;
                }
            
            /*Enviando Rounds Start ACK para validar la jugada.*/
                
                ROUND_START_ACK rStartAck = new ROUND_START_ACK();
                ArgumentsRoundStartAck rSArg = new ArgumentsRoundStartAck();
                rSArg.setId(id);
                rStartAck.setArguments(rSArg);
                rStartAck.setCommand("ROUND_START_ACK");
                
                Gson gsonrsAck = new Gson();
                String strRsACK = gsonrsAck.toJson(rStartAck);
                System.out.println(strRsACK);
                
                String strTurn = canal.mensajeJuego(strRsACK, "ROUND_START_ACK");
                
                TURN turnData = new TURN();
                Gson gsonTurn = new Gson();
                
                turnData = gsonTurn.fromJson(strTurn, turnData.getClass());
                
                cantTurnos = cantTurnos+1;
                
                //int jugada1 = 1;
                int x,y;
                /*Comenzando el juego*/
                while(jugando4){
                    
                   // if(jugada1 == 1 && turno == 1){
                  //  if(turno == 1 && turnData.getArguments().getAdvMove().getMove().equals("FIRST")){   
                    if(turno == 1 && cantTurnos%2 !=0){    
                         
                        x = randomico(height);
                          y = randomico(width);
                          
                          PUT move = new PUT();
                          ArgumentsPut putArgs = new ArgumentsPut();
                          putArgs.setId(id);

                          putArgs.setXPos(Integer.toString(x));
                          putArgs.setYPos(Integer.toString(y));
                          move.setCommand("PUT");
                          move.setArguments(putArgs);
                          
                          
                     //     tablero[x][y] = 1;
                          
                          /*enviando jugada*/
                          Gson gsonMove = new Gson();
                          String tmove = gsonMove.toJson(move);
                          strTurn = canal.mensajeJuego(tmove, "PUT");
                          
                       //   jugada1 = 0;  
                    }
                    else{
                    
                    //if(turno == 2 && turnData.getArguments().getAdvMove().getMove().equals("WAIT") ){
                    if(turno==2 && cantTurnos%2==0){
                        
                        
                          x = randomico(height);
                          y = randomico(width);
                          
                          PUT move = new PUT();
                          ArgumentsPut putArgs = new ArgumentsPut();
                          putArgs.setId(id);

                          putArgs.setXPos(Integer.toString(x));
                          putArgs.setYPos(Integer.toString(y));
                          move.setCommand("PUT");
                          move.setArguments(putArgs);
                          
                          
                   //       tablero[x][y] = 1;
                          
                          /*enviando jugada*/
                          Gson gsonMove = new Gson();
                          String tmove = gsonMove.toJson(move);
                          strTurn = canal.mensajeJuego(tmove, "PUT");
                        
                    
                    //jugada1 = 0;
                    }
                    else{
                    
                        /*espero por nuevo turno*/
         strTurn = canal.mensajeJuego("omitir", "PUT");
                        
                        
                    }
                    
                    }
                
                
                    
                    
                }// fin while del juego
            
            
          //  }
          }
        catch(SocketException e){
            
        
            
            System.out.println(e);
        
            
        }
        
            
    }
    
    public boolean convStrABool(String x){
    if(x.equals("true")){
    
        return true;
    }
        
    return false;
    }
    /*gemera numero random entre 0 y el largo, se utilizara para ubicar posiciones en el tablero*/
    public int randomico(int largo){
        
        Random x = new Random();
    
    return x.nextInt(largo+1);
    }
    


    private int[][] iniciarMatriz(int[][] m, int x, int y) {
              for(int i=0;0<x;i++){
            for(int j=0;j<y;j++){
                m[x][y]=0;
            }
        
        }
        
    return m;//To change body of generated methods, choose Tools | Templates.
    }
    
}
