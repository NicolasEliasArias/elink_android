package com.example.nicol.elink.Proyecto.ContenidoProyecto;

public class ContenidoProyecto {

    private String titulo;
    private String descripcionBreve;
    private String cuerpo;

    public ContenidoProyecto(String titulo, String descripcionBreve, String cuerpo){
        this.titulo = titulo;
        this.descripcionBreve = descripcionBreve;
        this.cuerpo = cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public void setDescripcionBreve(String descripcionBreve) {
        this.descripcionBreve = descripcionBreve;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public String getDescripcionBreve() {
        return descripcionBreve;
    }

    public String getTitulo() {
        return titulo;
    }
}
