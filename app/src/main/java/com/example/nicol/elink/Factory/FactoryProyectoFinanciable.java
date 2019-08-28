package com.example.nicol.elink.Factory;

import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.Usuario.Emprendedor;

public class FactoryProyectoFinanciable {

    protected static int nuevaID ;

    public FactoryProyectoFinanciable(){

    }

    public ProyectoFinanciable crearProyectoFinanciable(long id, Emprendedor emprendedor){
        ProyectoFinanciable proyectoFinanciable = new ProyectoFinanciable(id,emprendedor.getId());
        return proyectoFinanciable;
    }
}
