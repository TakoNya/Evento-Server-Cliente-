package utiles;

import java.io.Serializable;

public class Peticion implements Serializable{
    private static final long serialVersionUID = 1L;
    private int peticion;
    private Persona persona;
    
    public Peticion(int peticion, Persona persona){
        this.peticion = peticion;
        this.persona = persona;
    }

    /**
     * @return the peticion
     */
    public int getPeticion() {
        return peticion;
    }

    /**
     * @param peticion the peticion to set
     */
    public void setPeticion(int peticion) {
        this.peticion = peticion;
    }

    /**
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * @param persona the persona to set
     */
    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    
}
