package com.example.nicol.elink.UI.ViewHolder;

import android.app.Activity;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class ProyectTextViewHolder extends ViewHolder {
    private ProyectoFinanciable proyectoFinanciable;
    public ProyectTextViewHolder(Activity context) {
        super(context);
    }

    public ProyectTextViewHolder(Activity context, ProyectoFinanciable proyecto){
        super(context);
        proyectoFinanciable = proyecto;
    }


    public ProyectoFinanciable getProyectoFinanciable() {
        return proyectoFinanciable;
    }

    public void setProyectoFinanciable(ProyectoFinanciable proyectoFinanciable) {
        this.proyectoFinanciable = proyectoFinanciable;
    }
}
