package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class StateProyectoCancelado extends StateProyecto {

    public StateProyectoCancelado(ProyectoFinanciable p) {
        super(p);
        this.nombreEstado = "Cancelado";
    }

    @Override
    public String financiar(double monto) {
        return "El proyecto ah sido cancelado, no es posible financiar";
    }

    @Override
    public void finalizarProyecto() {}
}
