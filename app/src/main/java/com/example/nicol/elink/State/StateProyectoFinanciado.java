//package com.example.nicol.elink.State;
//
//import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
//
//public class StateProyectoFinanciado extends StateProyecto {
//
//    public StateProyectoFinanciado(ProyectoFinanciable p) {
//        super(p);
//        this.nombreEstado = "Financiado";
//    }
//
//    @Override
//    public String financiar(int monto) {
//        //si el proyecto esta financiado se le cobra una comision de 5% del monto ingresado al inversor
//        this.proyecto.setFinanciacionActual(this.proyecto.getFinanciacionActual() * 0.95 + monto);
//        return "Financiado correctamente (comision cobrada)";
//    }
//
//    @Override
//    public void finalizarProyecto() {
//        this.proyecto.setEstado(new StateProyectoFinanciadoFinalizado(this.proyecto));
//    }
//}
