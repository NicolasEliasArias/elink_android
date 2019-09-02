package com.example.nicol.elink.State;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class StateProyectoFinanciadoFinalizado extends StateProyecto {

    public StateProyectoFinanciadoFinalizado(ProyectoFinanciable p) {
        super(p);
        this.nombreEstado = "Financiado y Finalizado";
    }

    @Override
    public String financiar(int monto) {
        return "El proyecto ya esta ha concluido, no se puede financiar";
    }

    @Override
    public void finalizarProyecto() {
    }
}
