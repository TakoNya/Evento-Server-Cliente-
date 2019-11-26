package cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import utiles.Persona;
import utiles.Peticion;
import utiles.Respuesta;

public class ConectorCliente {

    static ObjectOutputStream outObjeto = null;
    static ObjectInputStream inObjeto = null;

    private static void comunicarse() throws IOException, ClassNotFoundException {
        Scanner keyboard = new Scanner(System.in);
        boolean conectados = true;
        while (conectados) {
            System.out.println("¿Que deseas hacer:\n1)Listar las personas insritas?"
                    + "\n2)Inscribir a una nueva persona"
                    + "\n3)Salir.");
            switch (keyboard.nextInt()) {
                case 1:
                    obtenerListado();
                    break;
                case 2:
                    inscribirPersona();
                    break;
                case 3:
                    conectados = false;
                    break;
                default:
                    break;
            }
        }
    }

    public static void obtenerListado() throws IOException, ClassNotFoundException {
        Peticion peticion = new Peticion(0, null);
        outObjeto.writeObject(peticion);
        Respuesta respuesta = (Respuesta) inObjeto.readObject();
        if (respuesta.isExito()) {
            System.out.println("Solicitud de listado completada.\n"
                    + "El listado de personas es el siguiente:");
            System.out.println(respuesta.getArrayPersonas());
        } else {
            System.out.println("Error al pedir el listado.Error en la parte servidor");
        }
    }
    public static void inscribirPersona() throws IOException, ClassNotFoundException{
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Introduzca el nombre de la persona: ");
        String nombre = keyboard.nextLine();
        System.out.println("Introduzca la direccion de la persona: ");
        String direccion = keyboard.nextLine();
        boolean edadCorrecta = false;
        int edad = 10;
        while (edadCorrecta == false){
            System.out.println("Introduzca la edad de la persona: ");
            try{
                edad = Integer.parseInt(keyboard.nextLine());
                edadCorrecta = true;
            }catch (Exception e){;
        }
    }
    System.out.println("¿Casado? (Y/N): ");
    boolean casado = keyboard.nextLine().equalsIgnoreCase("Y");
    Persona personaInscribir = new Persona(nombre, direccion,edad,casado);
    Peticion peticion = new Peticion(1, personaInscribir);
    outObjeto.writeObject(peticion);
    Respuesta respuesta = (Respuesta) inObjeto.readObject();
    if(respuesta.isExito()){
        System.out.println("Persona iinscripta correctamente.\n"
        + "el listado de personas es el siguiente:");
        System.out.println(respuesta.getArrayPersonas());
    }else{
        System.out.println("error al inscribir persona. Error en la parte servidor");
    }
    }
    

    public static void main(String[] args) {
        String Host = "localhost";
        int Puerto = 8000;
        Socket Cliente = null;
        try{
            Cliente = new Socket(Host, Puerto);
        }catch (UnknownHostException e1){
            e1.printStackTrace();
        }catch(IOException e1){
            e1.printStackTrace();
        }
        try{
            outObjeto = new ObjectOutputStream(Cliente.getOutputStream());
        }catch(IOException e2){
            e2.printStackTrace();
        }
        try{
            inObjeto = new ObjectInputStream(Cliente.getInputStream());
        }catch (IOException e2){
            e2.printStackTrace();
        }
        try{
            comunicarse();
        }catch(IOException e2){
            e2.printStackTrace();
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            inObjeto.close();
            outObjeto.close();
            Cliente.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
