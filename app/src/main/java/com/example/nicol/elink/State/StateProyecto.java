package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public abstract class StateProyecto implements State {

    protected ProyectoFinanciable proyecto;
    protected String nombreEstado;

    public StateProyecto(ProyectoFinanciable proyecto){
        this.proyecto = proyecto;
    }

    public abstract String financiar(double monto);

    public abstract void finalizarProyecto();

    //Getters y Setters------------------------------------------
    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
}
