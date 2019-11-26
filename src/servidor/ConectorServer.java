package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import utiles.Persona;
import utiles.Peticion;
import utiles.Respuesta;

public class ConectorServer {
    static ObjectOutputStream outObjeto = null;
    static ObjectInputStream inObjeto= null;
    static ArrayList<Persona> personasApuntadas = new ArrayList<Persona>();
    
    private static void comunicarse() throws IOException, ClassNotFoundException {
        while (true){
            Peticion peticion = null;
            try{
                peticion = (Peticion) inObjeto.readObject();
                                
            }catch (Exception e){
                System.out.println("Fin de la comunicacion con un cliente");
                break;
            }
            switch (peticion.getPeticion()){
                case 0:
                    devolverListado();
                    break;
                case 1:
                    introducirPersona(peticion.getPersona());
                    break;
                default:
                    break;
            }
        }
    }

    public static void devolverListado() throws IOException{
        Respuesta respuesta = new Respuesta(true, personasApuntadas);
        outObjeto.writeObject(respuesta);
        outObjeto.reset();
    }
    public static void introducirPersona(Persona nuevoInscripto) throws IOException{
        personasApuntadas.add(nuevoInscripto);
        System.out.println("\nEl servidor inscribe a " + nuevoInscripto.getNombre());
        Respuesta respuesta = new Respuesta(true, personasApuntadas);
        //System.out.println(respuesta.getArrayPersonas());
        outObjeto.writeObject(respuesta);
        outObjeto.reset();
        outObjeto.flush();
    }

    public static void main(String[] args) throws IOException{
        int Puerto = 8000;
        ServerSocket Servidor = new ServerSocket(Puerto);
        System.out.println("Escuchando en " + Servidor.getLocalPort());
        while (true){
            Socket clienteConectado = Servidor.accept();
            try{
                outObjeto = new ObjectOutputStream(clienteConectado.getOutputStream());
                outObjeto.flush();
            }catch(IOException e2){
                e2.printStackTrace();
            }
            try{
                inObjeto = new ObjectInputStream(clienteConectado.getInputStream());
            }catch (IOException e2){
                e2.printStackTrace();
            }
            try{
                comunicarse();
            }catch (ClassNotFoundException e1){
                e1.printStackTrace();
            }
            try{
                inObjeto.close();
                outObjeto.close();
                clienteConectado.close();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        
    }
    
}
