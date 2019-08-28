package com.example.nicol.elink.Proyecto;

import com.example.nicol.elink.Proyecto.ContenidoProyecto.ContenidoProyecto;
import com.example.nicol.elink.Usuario.Emprendedor;

public abstract class Proyecto {

    private  String emprendedorId;
    protected long id;
    protected ContenidoProyecto contenido;

    public Proyecto(){}

    public Proyecto(long id, String emprendedorId){
        this.id = id;
        this.emprendedorId = emprendedorId;
        this.contenido = new ContenidoProyecto("<Titulo>","<descripcion>","<cuerpo>");
    }

    //Setters y getters

    public void setContenido(ContenidoProyecto contenido) {
        this.contenido = contenido;
    }
    public void setId(long id) {
        this.id = id;
    }
    public ContenidoProyecto getContenido() {
        return contenido;
    }
    public long getId() {
        return id;
    }
    public String getEmprendedorId() {
        return emprendedorId;
    }
    public void setEmprendedorId(String emprendedorId) {
        this.emprendedorId = emprendedorId;
    }
}
