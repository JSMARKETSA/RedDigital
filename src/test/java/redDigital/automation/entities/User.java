package redDigital.automation.entities;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private String nombre;
    private String password;
    private String fechaActualizada;



    public User(String nombre, String password,String fechaActualizada) {
        this.nombre = nombre;
        this.password = password;
        this.fechaActualizada = fechaActualizada;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFechaActualizada() {
        return fechaActualizada;
    }

    public void setFechaActualizada(String fechaActualizada) {
        this.fechaActualizada =  fechaActualizada;
    }



    @Override
    public String toString() {
        return "User{" +
                "nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
