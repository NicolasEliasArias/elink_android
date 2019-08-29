package com.example.nicol.elink.Factory;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.Usuario.Emprendedor;

public class FactoryProyectoFinanciable {

    public ProyectoFinanciable crearProyectoFinanciable(long id, String emprendedorId){
        ProyectoFinanciable proyectoFinanciable = new ProyectoFinanciable(id,emprendedorId);
        return proyectoFinanciable;
    }
}
