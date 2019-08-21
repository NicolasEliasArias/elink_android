package com.example.nicol.elink.Proyecto;
import com.example.nicol.elink.Proyecto.ContenidoProyecto.ContenidoProyecto;
import com.example.nicol.elink.Usuario.Emprendedor;

public abstract class Proyecto {

    protected Emprendedor emprendedor;
    protected int id;
    protected ContenidoProyecto contenido;

    public Proyecto(){}
    public Proyecto(int id, Emprendedor emprendedor) {
        this.id = id;
        this.emprendedor = emprendedor;
        this.contenido = new ContenidoProyecto("<Titulo>","<descripcion>","<cuerpo>");
    }

    //Setters y getters


    public void setContenido(ContenidoProyecto contenido) {
        this.contenido = contenido;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }
    public ContenidoProyecto getContenido() {
        return contenido;
    }
    public Emprendedor getEmprendedor() {
        return emprendedor;
    }
    public int getId() {
        return id;
    }
}
