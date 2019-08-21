package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public abstract class StateProyecto implements State {

    protected ProyectoFinanciable proyecto;
    protected String nombreEstado;

    public StateProyecto(ProyectoFinanciable p){
        this.proyecto = p;
    }

    public abstract String financiar(int monto);
    public abstract void finalizarProyecto();

    public String getNombreEstado() {
        return nombreEstado;
    }
}
