package com.example.nicol.elink.Usuario;
import com.example.nicol.elink.Proyecto.Proyecto;
import java.util.ArrayList;

public class Emprendedor extends Usuario {

    private ArrayList<Proyecto> proyectos;

    public Emprendedor(){
    }
    public Emprendedor(String id, String nombreUsuario, String email) {
        super(id, nombreUsuario, email);
        this.proyectos = new ArrayList<Proyecto>();
    }

    /**
     * se le asigna un proyecto al inversor y al proyecto se le añade la id del emprendedor
     * @param proyecto
     */
    public void asignarProyecto(Proyecto proyecto){
        this.proyectos.add(proyecto);
        proyecto.setEmprendedorId(getId());
    }

    //Getters y Setters ---------------------------------------------
    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
}
