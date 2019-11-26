package utiles;

import java.io.Serializable;
import java.util.ArrayList;

public class Respuesta implements Serializable{
    private static final long serialVersionUID = 1L;
    private boolean exito;
    private ArrayList<Persona> arrayPersonas;
    
    public Respuesta(boolean exito, ArrayList<Persona> arrayPersonas){
        this.exito = exito;
        this.arrayPersonas = arrayPersonas;
    }

    /**
     * @return the exito
     */
    public boolean isExito() {
        return exito;
    }

    /**
     * @param exito the exito to set
     */
    public void setExito(boolean exito) {
        this.exito = exito;
    }

    /**
     * @return the arrayPersonas
     */
    public ArrayList<Persona> getArrayPersonas() {
        return arrayPersonas;
    }

    /**
     * @param arrayPersonas the arrayPersonas to set
     */
    public void setArrayPersonas(ArrayList<Persona> arrayPersonas) {
        this.arrayPersonas = arrayPersonas;
    }
    
}
