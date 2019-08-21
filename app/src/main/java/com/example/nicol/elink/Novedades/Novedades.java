package com.example.nicol.elink.Novedades;

import com.example.nicol.elink.Subject.Subject;

import java.util.ArrayList;

public abstract class Novedades extends Subject {
    protected ArrayList<String> noticias;
    protected String ultimaNoticia;

    public Novedades(){
        this.noticias = new ArrayList<String>();
        this.ultimaNoticia = null;
        this.notificar();
    }

    public abstract void nuevaNoticia(String noticia);

    public void eliminarNoticia(int index){
        if(this.noticias.get(index)!= null){
            this.noticias.remove(this.noticias.get(index));
        }
    }

    public ArrayList<String> getNoticias() {
        return noticias;
    }

    @Override
    public void notificar() {
        for(int i = 0; i < observers.size(); i++){
            this.observers.get(i).update(this.ultimaNoticia);
        }
    }
}
