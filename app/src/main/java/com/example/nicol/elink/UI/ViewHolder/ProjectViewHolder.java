package com.example.nicol.elink.UI.ViewHolder;
import android.app.Activity;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class ProjectViewHolder extends ViewHolder {
    private ProyectoFinanciable proyectoFinanciable;
    public ProjectViewHolder(Activity context) {
        super(context);
    }

    public ProjectViewHolder(Activity context, ProyectoFinanciable proyecto){
        super(context);
        proyectoFinanciable = proyecto;
    }

    //Getters y Setters -----------------------------------------------------
    public ProyectoFinanciable getProyectoFinanciable() {
        return proyectoFinanciable;
    }

    public void setProyectoFinanciable(ProyectoFinanciable proyectoFinanciable) {
        this.proyectoFinanciable = proyectoFinanciable;
    }
}
