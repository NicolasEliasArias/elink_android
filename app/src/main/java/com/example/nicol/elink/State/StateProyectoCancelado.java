package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class StateProyectoCancelado extends StateProyecto {

    public StateProyectoCancelado(ProyectoFinanciable p) {
        super(p);
        this.nombreEstado = "Cancelado";
    }

    @Override
    public String financiar(int monto) {
        return "El proyecto ah sido cancelado, no es posible financiarlo";
    }

    @Override
    public void finalizarProyecto() {}
}
