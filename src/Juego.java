
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
 * @author Kerux
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
