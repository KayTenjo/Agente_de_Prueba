
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



/**
 *
 * @author Kerux
 */
public class Juego {
    
//reglas y características del tablero
    
    public static String id; // id asignado al cliente durante el proceso de registro
    public static int turnDuration; // tiempo que duran los turnos, solo si timedTurn es verdadero
    public static int maxRoundTime; //limite de duracion de un round
    public static int roundsPerMatch; // numero de rondas a jugar por cada enfrentamiento entre jugadores distintos
    public static int boardSize;

    
    public static int[][] tablero;
    public static boolean jugando4 = true;
    
    /*Nombre y características del Agente*/
    
    public static String nombreAgente = "Agente 2";
    public static String passAgente = "Tdsfasdfasdfsafdfas";
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
        nuevoRegistro = conversion.creaRegistro("Agente1", "asdfasdfasdfasdfasfs", "IA"); // creando json de registro
        Gson gson = new Gson();
        String jsonRegistro = gson.toJson(nuevoRegistro); // creando string json
        
        
        /*enviando registro al GM*/
        salida.writeUTF(jsonRegistro); // enviando registro al gm
       // System.out.println(jsonRegistro);
        String resRegistro = entrada.readLine(); // recibindo respuesta
        System.out.println("Registro enviado");
       
        
        if(resRegistro.equals("{\"command\":\"ERR_UNKOWN_COMMAND\",\"arguments\":()")){
        
            System.out.println("Error 1: Registro Invalido");
        
        }
        else{

        /*zona de registro correcto*/
           // System.out.println(resRegistro);
            
            REG_SUCESS registroValido = new REG_SUCESS();
            
            registroValido = conversion.registroValido(resRegistro);
       
      if(!registroValido.getCommand().equals("REG_SUCESS")){
        
          System.out.println("Error 2: comando reg_sucess no llego");
          System.out.println("respuesta del GM ante register es : "+registroValido.getCommand());
        
      }    
        else{
            
            System.out.println("Registro aprobado");
            id = registroValido.getId(); // obteniendo la id enviada por el GM
            
            /*Iniciando nueva sesion*/
            SESSION_START nuevaSesion = new SESSION_START();
            nuevaSesion = conversion.inicioSesion(id,"Connect 4");
            String jsonSesionStart = gson.toJson(nuevaSesion);
            
            //System.out.println(jsonSesionStart);
            salida.writeUTF(jsonSesionStart);
            String strRule = entrada.readLine();
            
            System.out.println("comando reglas llegando"+ strRule);
            /*recibiendo reglas*/
            RULES reglas = new RULES();
            reglas = conversion.reglas(strRule);
            
          //  System.out.println(reglas.getCommand());
            
            /*casteando reglas*/
            boardSize = Integer.parseInt(reglas.getBoardSize()); 
            turnDuration = Integer.parseInt(reglas.getTurnDuration()); 
            maxRoundTime = Integer.parseInt(reglas.getMaxRoundTime()); 
            roundsPerMatch = Integer.parseInt(reglas.getRoundsPerMatch()); 
            
            
            /*Aceptando las reglas*/
            
            ACCEPT aceptando = new ACCEPT();
            aceptando = conversion.aceptando(id);  
            String aceptandoStr = gson.toJson(aceptando);
            
            salida.writeUTF(aceptandoStr);
            
            String aceptandoACKStr = entrada.readLine();
            ACCEPT_ACK ack = new  ACCEPT_ACK();
            ack = conversion.acceptAck(aceptandoACKStr);
            
           // System.out.println(aceptandoACKStr);

            if(!ack.getCommand().equals("ACCEPT_ACK")){
                
                System.out.println("Error 2: No llego Accept Ack");
            }
            else{
                
              /*ack de aceptacion de reglas logrado*/
                MATCH_LOOK_UP matchUP = new MATCH_LOOK_UP();
                matchUP = conversion.matchLookup(nombreAgente, id,"AI","10","Agente 2");
                String matchUPStr = gson.toJson(matchUP);
                
               // System.out.println(matchUPStr);
                
                salida.writeUTF(matchUPStr);
                
                String matchOK = entrada.readLine();
                
                //System.out.println("Esta llegando un " + matchOK + "antes del matchLookUp ");
                
                /*match Look UP OK*/
                RULES msjMatchOK = new RULES();
                msjMatchOK = conversion.reglas(matchOK);
                
                System.out.println(msjMatchOK.getCommand());
                if(msjMatchOK.getCommand().equals("MATCH_LOOKUP_OK")){
                    System.out.println("Entre al match look up ok");
                    matchOK = entrada.readLine();
                    System.out.println("estoyp or salir del  match look up ok");
                }
               
                    System.out.println("sali del  match look up ok");
                    /*iniciando proceso de notificacion (Match Notify)*/

                    String notifys = matchOK;
                    MATCH_NOTIFY matchNotify = new MATCH_NOTIFY();
                    matchNotify = conversion.notificacion(notifys);
                    
                    /*sacando datos del json matchNotify*/
                    nombreAdversario = matchNotify.getAdvName(); // nombre del adversario actual
                    idAdversario = matchNotify.getAdvId(); // id del adversario actual
                    nombrePartida = matchNotify.getMatchName(); // nombre de la partida actual
                
                    while(true){
                        
                    /*enviando aceptacion de match*/
                    MATCH_READY iniciando = new MATCH_READY();
                    iniciando = conversion.matchListo(id);
                    String matchReadyStr = gson.toJson(iniciando);
                    
                    salida.writeUTF(matchReadyStr);
                    
                    String OK = entrada.readLine(); // donde recibo un ok
                    
                    TURN oks = new TURN();
                    oks = conversion.turno(OK);

                     System.out.println("el comando que muere es "+ oks.getCommand());          
                     if(oks.getCommand().equals("OK")){

                    
                        OK = entrada.readLine();
                    
                    }

                        String roundStartStr = OK;
                        
                        ROUND_START roundStart = new ROUND_START();
                        roundStart = conversion.roundStart(roundStartStr);
                              
                        ROUND_START_ACK roundSAck = new ROUND_START_ACK();
                        roundSAck = conversion.roundStartACK(id);
                        String rsAckStr = gson.toJson(roundSAck);
                        
                        salida.writeUTF(rsAckStr);
                        
                        /*comienza el envio de turns*/
                
                        String strTurnOK = entrada.readLine();                        
                        oks = conversion.turno(strTurnOK);
                        
                        if(oks.getCommand().equals("OK")){
                        
                            strTurnOK = entrada.readLine();
                        
                        }
   
                        String strTurn = strTurnOK;                      
                        TURN turnData = new TURN();
                        turnData = conversion.turno(strTurn);

                        //int jugada1 = 1;
                        
                        ////Comenzando el juego/////
                        while(jugando4){
                            
                
                            turnData = conversion.turno(strTurn);
                            System.out.println(turnData.getCommand());
                            if(turnData.getCommand().equals("ROUND_END")){
                                System.out.println("Roun end yey!");
                                break;
                            }
                            else{
                           //convStrABool(turnData.getYourTurn())== true)
                            if((turnData.getCommand().equals("TURN")&& turnData.getYourTurn().equals("true"))|| turnData.getCommand().equals("ERR_WRONG_POS")){
                            
                                 
                                Random rand = new Random();

                                int x= rand.nextInt(boardSize)+1;
                                int y= rand.nextInt(boardSize)+1;
                                //x=x-1;
                                //y=y-1;
                                System.out.println("X es " + x);
                                System.out.println("Y es " + y);
                                PUT move = new PUT();
                                move = conversion.put(x, y, id);
                                strTurn = gson.toJson(move);
                                
                                salida.writeUTF(strTurn);
                                
                               // strTurn = entrada.readLine();
                               // turnData = conversion.turno(strTurn);
                                
                               /* while(turnData.getCommand().equals("ERR_WRONG_POS")){
                                
                                x = randomico(boardSize);
                                y = randomico(boardSize);
                                System.out.println("X es " + x);
                                System.out.println("Y es " + y);

                                while(turnData.getCommand().equals("ERR_WRONG_POS")){
                                
                                x = randomico(boardSize);
                                y = randomico(boardSize);
                                    
                                move = conversion.put(x, y, id);
                                strTurn = gson.toJson(move);
                                
                                salida.writeUTF(strTurn);
                                
                                strTurn = entrada.readLine();
                                turnData = conversion.turno(strTurn);
                                */
                                }
                                
                            }
                           
                        strTurn = entrada.readLine();
                        
                        
                    
                         }// fin while del juego
                        

                 
                        ROUND_END fin = new ROUND_END();
                        fin = conversion.roundEnd(strTurn);
                        if(fin.getCommand().equals("ROUND_END")&& convStrABool(fin.getNextGame())== false){
                            break;
                        }
                        
            }//para multiples matches   

            } // ACK aceptado (mensaje sin definir desde GM)
        }
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
