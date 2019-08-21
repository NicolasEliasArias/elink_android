package com.example.nicol.elink.Novedades;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

public class NovedadesProyecto extends Novedades {

    private ProyectoFinanciable proyecto;

    public NovedadesProyecto(ProyectoFinanciable p){
        this.proyecto = p;
    }

    @Override
    public void nuevaNoticia(String noticia) {
        String titulo = this.proyecto.getContenido().getTitulo();
        this.ultimaNoticia = titulo + " " + noticia;
        this.noticias.add(ultimaNoticia);
        this.notificar();
    }
}
