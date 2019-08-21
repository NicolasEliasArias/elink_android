package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class StateProyectoCreado extends StateProyecto {

    public StateProyectoCreado(ProyectoFinanciable p) {
        super(p);
        this.nombreEstado = "Creado";
    }

    @Override
    public String financiar(int monto) {
        this.proyecto.setFinanciacionActual(this.proyecto.getFinanciacionActual() + monto);
        if(this.proyecto.getFinanciacionActual() >= this.proyecto.getFinanciacionNecesaria()){
            this.proyecto.setEstado(new StateProyectoFinanciado(this.proyecto));
        }
        return "Financiado correctamente";
    }

    @Override
    public void finalizarProyecto() {
        this.proyecto.setEstado(new StateProyectoCancelado(this.proyecto));
    }
}
