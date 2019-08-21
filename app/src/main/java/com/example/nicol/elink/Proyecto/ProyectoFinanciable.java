package com.example.nicol.elink.Proyecto;

import com.example.nicol.elink.Novedades.Novedades;
import com.example.nicol.elink.Novedades.NovedadesProyecto;
import com.example.nicol.elink.State.StateProyecto;
import com.example.nicol.elink.State.StateProyectoCreado;

public class ProyectoFinanciable extends Proyecto {

    private StateProyecto estadoActual;
    private Novedades novedades;
    private double financiacionActual;
    private double financiacionNecesaria;

    public ProyectoFinanciable(int financiacionNecesaria){
        this.financiacionNecesaria = financiacionNecesaria;
        this.financiacionActual = 0;
        this.estadoActual = new StateProyectoCreado(this);
        this.novedades = new NovedadesProyecto(this);
    }

    public void financiar(int monto){
        this.estadoActual.financiar(monto);
    }

    public void finalizarProyecto(){
        this.estadoActual.finalizarProyecto();
    }

    public void nuevaNoticia(String noticia){
        this.novedades.nuevaNoticia(noticia);
    }
    //Setters y Getters

    public void setEstado(StateProyecto s){
        this.estadoActual = s;
        this.nuevaNoticia("El proyecto " + this.contenido.getTitulo() + " fue " + this.estadoActual.getNombreEstado());
    }
    public void setFinanciacionNecesaria(double financiacionNecesaria) {
        this.financiacionNecesaria = financiacionNecesaria;
    }
    public void setFinanciacionActual(double financiacionActual) {
        this.financiacionActual = financiacionActual;
    }
    public double getFinanciacionNecesaria() {
        return financiacionNecesaria;
    }
    public double getFinanciacionActual() {
        return financiacionActual;
    }
    public StateProyecto getEstadoActual() {
        return estadoActual;
    }
}
