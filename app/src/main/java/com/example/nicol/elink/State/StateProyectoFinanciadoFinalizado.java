package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class StateProyectoFinanciadoFinalizado extends StateProyecto {

    public StateProyectoFinanciadoFinalizado(ProyectoFinanciable p) {
        super(p);
        this.nombreEstado = "Financiado y Finalizado";
    }

    @Override
    public String financiar(int monto) {
        return "No es posible financiar el proyecto porque esta finalizado";
    }

    @Override
    public void finalizarProyecto() {
    }
}
