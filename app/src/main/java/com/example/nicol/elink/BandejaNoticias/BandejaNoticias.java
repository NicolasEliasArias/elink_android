package com.example.nicol.elink.BandejaNoticias;

import com.example.nicol.elink.Novedades.Novedades;
import com.example.nicol.elink.Observer.Observer;

import java.util.List;


public class BandejaNoticias implements Observer {

    protected List<String> nuevasNoticias;
    protected List<Novedades> listaNovedadesSeguidas;

    public BandejaNoticias(){}

    public void update(String s){
        this.nuevasNoticias.add(s);
    }

    /**
     * Elimina todas las noticias
     */
    public void eliminarNoticias(){
        this.nuevasNoticias.clear();
    }

    /**
     * Sigue a las novedades de un proyecto para "escuchar" cada vez que se cree una noticia nueva de este
     * @param n
     */
    public void seguir(Novedades n){
        this.listaNovedadesSeguidas.add(n);
    }

    /**
     * Deja de seguir las novedades del proyecto
     * @param n
     */
    public void dejarDeSeguir(Novedades n){
        n.unregister(this);
        this.listaNovedadesSeguidas.remove(n);
    }

    //Getters y Setters -----------------------------------------------

    public List<String> getNuevasNoticias() {
        return nuevasNoticias;
    }

    public void setNuevasNoticias(List<String> nuevasNoticias) {
        this.nuevasNoticias = nuevasNoticias;
    }

    public List<Novedades> getListaNovedadesSeguidas() {
        return listaNovedadesSeguidas;
    }

    public void setListaNovedadesSeguidas(List<Novedades> listaNovedadesSeguidas) {
        this.listaNovedadesSeguidas = listaNovedadesSeguidas;
    }
}
