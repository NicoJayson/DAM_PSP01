/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package socketlector;

import java.net.*;
import java.io.*;
import java.util.Arrays;
/**
 *
 * @author usuario
 */
public class ordenarNumeros {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Socket canal = null; //Socket para establecer el canal de conexión con el escritor
        BufferedReader entrada = null; //Para el stream de lectura
        String valorEntrada = null; //Valores que iremos leyendo del canal        
        try{
            canal = new Socket("localhost", 12345 );
            //Pedimos establecer una conexión en el equipo local con el puerto 12345
            //donde debe estar escuchando el proceso escritor
            }catch (Exception ex){
                System.err.println("No se ha podido establecer conexión.");
                System.err.println(ex.toString());
        }
        if (canal != null)  //Si hemos podido establecer la conexión. Tenemos
                            //un canal de comunicación
            try{
               entrada = new BufferedReader(new InputStreamReader(canal.getInputStream()));
               //Obtemenos el objeto que representa el strean de entrada en el canal
               //Lector con buffer, para no perder ningún dato
               //Instanciamos un nuevo array para pasrle los valores que hay en el buffer
               int ArrayNumeros[]= new int[40];
               //Pasamos los valores del buffer a un array.
               int i=0;
               while ((valorEntrada = entrada.readLine())!= null){
                   //Mientras que haya datos que leer
            	   ArrayNumeros[i]= Integer.parseInt(valorEntrada);
                   i++;
               }
               //Ordenamos los numeros
               Arrays.sort(ArrayNumeros);
               //Pintamos los numeros en orden
               for (int k = 0; k < ArrayNumeros.length; k++){
                   //Mientras que haya datos que leer
                   System.out.println(ArrayNumeros[k]);
                   System.out.println("**");
               }
            }catch (Exception ex){
               System.err.println("No se ha podido establecer conexión.");
               System.err.println(ex.toString());
            }finally{
                //Nos aseguramos de que se cierran los recursos que estamos utilizando
                if (entrada != null)
                    try{
                        entrada.close();
                    }catch (IOException ex) {
                        System.err.println("Se ha producido un error al cerrar el InputStreamReader.");
                        System.err.println(ex.toString());
                    }
                if (canal != null)
                    try{
                        canal.close();
                    }catch (IOException ex) {
                        System.err.println("Se ha producido un error al cerrar el Socket.");
                        System.err.println(ex.toString());
                    }
            }
    }

}
