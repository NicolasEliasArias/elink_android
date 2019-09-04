package com.example.nicol.elink.Proyecto;
import com.example.nicol.elink.State.StateProyecto;
import com.example.nicol.elink.State.StateProyectoCreado;
import com.example.nicol.elink.State.StateProyectoFinanciado;

public class ProyectoFinanciable extends Proyecto {

    //    private Novedades novedades;
    private StateProyecto estadoActual;
    private double financiacionActual;
    private double financiacionNecesaria;

    public ProyectoFinanciable(){
        super();
        this.financiacionNecesaria = 0;
        this.financiacionActual = 0;
        this.estadoActual = new StateProyectoCreado(this);
        //        this.novedades = new NovedadesProyecto(this);
    }

    public ProyectoFinanciable(long id, String emprendedorId ){
        super(id,emprendedorId);
        this.financiacionNecesaria = 0;
        this.financiacionActual = 0;
        this.estadoActual = new StateProyectoCreado(this);
//        this.novedades = new NovedadesProyecto(this);
    }
    public ProyectoFinanciable(long id, String emprendedorId, int financiacionNecesaria){
        super(id,emprendedorId);
        this.financiacionNecesaria = financiacionNecesaria;
        this.financiacionActual = 0;
        this.estadoActual = new StateProyectoCreado(this);
//        this.novedades = new NovedadesProyecto(this);
    }

    /**
     * Aumenta la financiacion actual
     * @param monto
     */
    public void financiar(double monto){
        this.estadoActual.financiar(monto);
    }

    /**
     * Finaliza el proyecto
     */
    public void finalizarProyecto(){
        this.estadoActual.finalizarProyecto();
    }

//    public void nuevaNoticia(String noticia){
//        this.novedades.nuevaNoticia(noticia);
//    }

    /**
     * Analiza si la financiacion necesaria fue cumplida y cambia el estado al correspondiente
     */
    private void checkActualState(){
        if(financiacionActual >= financiacionNecesaria){
            this.setEstadoActual(new StateProyectoFinanciado(this));
        }
    }

    //Getters y Setters ----------------------------------------

    public double getFinanciacionActual() {
        return financiacionActual;
    }

    public void setFinanciacionActual(double financiacionActual) {
        this.financiacionActual = financiacionActual;
        checkActualState();
    }

    public double getFinanciacionNecesaria() {
        return financiacionNecesaria;
    }

    public void setFinanciacionNecesaria(double financiacionNecesaria) {
        this.financiacionNecesaria = financiacionNecesaria;
        checkActualState();
    }

    public StateProyecto getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(StateProyecto nuevoestado) {
        this.estadoActual = nuevoestado;
    }

//    public Novedades getNovedades() {
//        return novedades;
//    }

//    public void setNovedades(Novedades novedades) {
//        this.novedades = novedades;
//    }
}
