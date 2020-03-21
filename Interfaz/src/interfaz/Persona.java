package interfaz;

import java.io.Serializable;

public class Persona implements Serializable{
   
    private String nombre;
    private String categoria;
    private String candest;
    private String tipoedu;
  

    public Persona() {}


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCandest() {
        return candest;
    }

    public void setCandest(String candest) {
        this.candest = candest;
    }

    public String getTipoedu() {
        return tipoedu;
    }

    public void setTipoedu(String tipoedu) {
        this.tipoedu = tipoedu;
    }



}