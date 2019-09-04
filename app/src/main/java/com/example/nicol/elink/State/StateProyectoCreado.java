package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class StateProyectoCreado extends StateProyecto {

    public StateProyectoCreado(ProyectoFinanciable proyecto) {
        super(proyecto);
        this.nombreEstado = "Creado";
        if(proyecto.getFinanciacionActual() >= proyecto.getFinanciacionNecesaria()){
            proyecto.setEstadoActual(new StateProyectoFinanciado(proyecto));
        }
    }

    @Override
    public String financiar(double monto) {
        this.proyecto.setFinanciacionActual(this.proyecto.getFinanciacionActual() + monto);
        if((this.proyecto.getFinanciacionActual() >= this.proyecto.getFinanciacionNecesaria())){
            this.proyecto.setEstadoActual(new StateProyectoFinanciado(this.proyecto));
        }
        return "Financiado correctamente";
    }

    @Override
    public void finalizarProyecto() {
//        this.proyecto.setEstado(new StateProyectoCancelado(this.proyecto));
    }
}
