package com.example.nicol.elink.Novedades;
import com.example.nicol.elink.Subject.Subject;
import java.util.ArrayList;

public abstract class Novedades extends Subject {
    private ArrayList<String> noticias;
    private String ultimaNoticia;

    public Novedades(){
        this.noticias = new ArrayList<String>();
        this.ultimaNoticia = null;
        this.notificar();
    }

    /**
     * Crea una nueva noticia
     * @param noticia texto de la nueva noticia
     */
    public abstract void nuevaNoticia(String noticia);

    /**
     * Elimina una noticia
     * @param index index de la noticia
     */
    public void eliminarNoticia(int index){
        if(this.noticias.get(index)!= null){
            this.noticias.remove(this.noticias.get(index));
        }
    }

    @Override
    public void notificar() {
        for(int i = 0; i < observers.size(); i++){
            this.observers.get(i).update(this.ultimaNoticia);
        }
    }

    //Getters y Setters --------------------------------------
    public ArrayList<String> getNoticias() {
        return noticias;
    }

    public void setNoticias(ArrayList<String> noticias) {
        this.noticias = noticias;
    }

    public String getUltimaNoticia() {
        return ultimaNoticia;
    }

    public void setUltimaNoticia(String ultimaNoticia) {
        this.ultimaNoticia = ultimaNoticia;
    }
}
