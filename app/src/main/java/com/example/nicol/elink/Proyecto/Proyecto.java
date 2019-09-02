package com.example.nicol.elink.Proyecto;
import com.example.nicol.elink.Proyecto.ContenidoProyecto.Contenido;
import com.example.nicol.elink.Proyecto.ContenidoProyecto.ContenidoProyecto;

public abstract class Proyecto {

    private  String emprendedorId;
    private long id;
    private Contenido contenido;

    public Proyecto(){
        this.contenido = new ContenidoProyecto("<Titulo>","<descripcion>","<cuerpo>");
    }

    public Proyecto(long id, String emprendedorId){
        this.id = id;
        this.emprendedorId = emprendedorId;
        this.contenido = new ContenidoProyecto("<Titulo>","<descripcion>","<cuerpo>");
    }

    //Getters y Setters ---------------------------------------
    public String getEmprendedorId() {
        return emprendedorId;
    }

    public void setEmprendedorId(String emprendedorId) {
        this.emprendedorId = emprendedorId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Contenido getContenido() {
        return contenido;
    }

    public void setContenido(Contenido contenido) {
        this.contenido = contenido;
    }
}
