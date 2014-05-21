/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import ComandosAgente.*;
import com.google.gson.Gson;
/**
 *
 * @author Kabot-Zoe
 */
public class Conversion {

    
    public Conversion(){}
    
    public REGISTER creaRegistro(String nombreAgente,String passAgente,String tipoAgente){
    
            REGISTER nuevoRegistro = new REGISTER();

            ArgumentsRegister argRegistro = new ArgumentsRegister();
            argRegistro.setClientName(nombreAgente);
            argRegistro.setClientPass(passAgente);
            argRegistro.setClientType(tipoAgente);
            nuevoRegistro.setArguments(argRegistro);
            nuevoRegistro.setCommand("REGISTER");
        
    return nuevoRegistro;
    
    
    }
    
    public String registroJson(REGISTER registro){
    
        String strJson =  "sin nada aun";
        
            Gson gson = new Gson();
            String jsonRegistro = gson.toJson(registro);
            System.out.println(strJson);
        
    
    return strJson;
    }
    
    
    public REG_SUCESS registroValido(String strjson){
    
        REG_SUCESS  RegSucess = new REG_SUCESS();
        Gson regRespuesta = new Gson();
        RegSucess = regRespuesta.fromJson(strjson,RegSucess.getClass());
    
    return RegSucess; 
    }
    
    public SESSION_START inicioSesion(String id){
    
            SESSION_START inSesion = new SESSION_START();
            ArgumentsSessionStart inSesionArg = new ArgumentsSessionStart();
            inSesionArg.setGameName("Connect 4");
            inSesionArg.setId(id);
            inSesion.setCommand("SESSION_START");
            inSesion.setArguments(inSesionArg);
    
    return inSesion;
    }
    
    public RULES reglas(String jsonRulesStr){
    
            RULES  rules = new RULES();
            Gson regRules = new Gson();
            rules = regRules.fromJson(jsonRulesStr,rules.getClass());
    
    return rules;
    }
    
    public ACCEPT aceptando (String id){
    
            ACCEPT acepRules = new ACCEPT();
            ArgumentsAccept acepArg = new ArgumentsAccept();
            acepArg.setId(id);
            acepRules.setArgumentsAccept(acepArg);
            acepRules.setCommand("ACCEPT");
    
    return acepRules;
    }
    
    public MATCH_LOOK_UP matchLookup(String nombreAgente, String id){
    
        MATCH_LOOK_UP buscandoPartida = new MATCH_LOOK_UP();
        ArgumentsMatchLookUp matchArg = new ArgumentsMatchLookUp();
        matchArg.setAdvName(nombreAgente);
        matchArg.setId(id);
        matchArg.setMatchName("AI");
        matchArg.setTimeout("10");
        matchArg.setAdvName("Agente 2");
        buscandoPartida.setCommand("MATCH_LOOKUP");
        buscandoPartida.setArguments(matchArg);
    
    return buscandoPartida;
    }
    
    
    public MATCH_NOTIFY notificacion(String notify){
    
        MATCH_NOTIFY matchNotifys = new MATCH_NOTIFY();
        Gson notf = new Gson();
        matchNotifys = notf.fromJson(notify, matchNotifys.getClass());
    
    return matchNotifys;
    }
    
    public MATCH_READY matchListo(String id){
            
                MATCH_READY  ready = new MATCH_READY();
                ArgumentsMatchReady readyArg = new ArgumentsMatchReady();
                readyArg.setId(id);
                ready.setCommand("MATCH_READY");
                ready.setArguments(readyArg);
    
    return ready;
    }
    
    public ROUND_START roundStart(String strRoundStart){
            
        ROUND_START start = new ROUND_START();
        Gson startRound = new Gson();        
        start = startRound.fromJson(strRoundStart,start.getClass());
    
    return start;
    }
    
    public ROUND_START_ACK roundStartACK(String id){
        
                ROUND_START_ACK rStartAck = new ROUND_START_ACK();
                ArgumentsRoundStartAck rSArg = new ArgumentsRoundStartAck();
                rSArg.setId(id);
                rStartAck.setArguments(rSArg);
                rStartAck.setCommand("ROUND_START_ACK");
                
    return rStartAck;
    }
    
    
    public TURN turno(String strTurn){
    
        TURN turnData = new TURN();
        Gson gsonTurn = new Gson();
                
        turnData = gsonTurn.fromJson(strTurn, turnData.getClass());
    
    return turnData;
    }
    
    public PUT put(int x, int y, String id){
    
    
        PUT move = new PUT();
        ArgumentsPut putArgs = new ArgumentsPut();
        putArgs.setId(id);

        putArgs.setXPos(Integer.toString(x));
        putArgs.setYPos(Integer.toString(y));
        move.setCommand("PUT");
        move.setArguments(putArgs);
        
    return move;
    }
    
}
