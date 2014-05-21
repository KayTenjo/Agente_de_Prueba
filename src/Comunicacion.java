


import ComandosAgente.MATCH_READY;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kabot-Zoe
 */
public class Comunicacion {
    
    public Socket socket;
    
   // public final int puerto;
  
    
    public Comunicacion(){
   
        
    
    }
    
   public String mensajeJuego(String mensaje,String Comando) throws IOException{
        
      socket = new Socket("localhost",7075);
        
      DataOutputStream salida = new DataOutputStream(socket.getOutputStream());

      BufferedReader  entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

      if(mensaje.equals("omitir")&&Comando.equals("PUT")){
               
       // escribiendo en buffer de datos
      }
      else{
      salida.writeUTF(mensaje);
      }
      
      String datosBuffer = entrada.readLine(); 

      System.out.println("\n\n"+Comando+"\n\n");
      
      if(!Comando.equals("ACCEPT")){
      MATCH_READY   test = new MATCH_READY();
      Gson ok = new Gson();
      test = ok.fromJson(datosBuffer, test.getClass());
      
      while(test.getCommand().equals("MATCH_LOOKUP_OK")){
      
          System.out.println("llego un  MATCH_LOOKUP_OK");
          
      datosBuffer = entrada.readLine();
      }
      
      while(test.getCommand().equals("OK")){
      
      System.out.println("llego un OK");
      
      datosBuffer = entrada.readLine();
      }
      
      }
      
            
      
      return datosBuffer;
      
   }
   

    
}
