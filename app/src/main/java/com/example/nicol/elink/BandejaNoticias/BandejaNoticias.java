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

    public void eliminarNoticias(){
        this.nuevasNoticias.clear();
    }

    //Setters y Getters
    public List<String> getNuevasNoticias() {
        return nuevasNoticias;
    }

    public void seguir(Novedades n){
        this.listaNovedadesSeguidas.add(n);
    }
    public void dejarDeSeguir(Novedades n){
        n.unregister(this);
        this.listaNovedadesSeguidas.remove(n);
    }

}
