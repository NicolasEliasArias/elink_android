package com.example.nicol.elink.State;

public interface  State {

    /**
     * Agrega el monto al  monto de la financiacion Actual del proyecto
     * @param monto
     * @return
     */
    public String financiar(int monto);

    /**
     * Finaliza el proyecto
     */
    public void finalizarProyecto();

}
