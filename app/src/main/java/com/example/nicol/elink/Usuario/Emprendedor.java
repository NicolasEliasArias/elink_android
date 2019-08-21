package com.example.nicol.elink.Usuario;
import com.example.nicol.elink.Proyecto.Proyecto;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import java.util.ArrayList;

public class Emprendedor extends Usuario {

    private ArrayList<Proyecto> proyectos;

    public Emprendedor(){
    }
    public Emprendedor(String id, String nombreUsuario, String email) {
        super(id, nombreUsuario, email);
        this.proyectos = new ArrayList<Proyecto>();
    }


    public void asignarProyecto(ProyectoFinanciable proyecto){
        this.proyectos.add(proyecto);
        proyecto.setEmprendedor(this);
    }

    public ProyectoFinanciable crearProyectoFinanciable(int id, double financiacionNecesaria){
        ProyectoFinanciable proyecto = new ProyectoFinanciable(id);
        proyecto.setFinanciacionNecesaria(financiacionNecesaria);
        return proyecto;
    }
}
