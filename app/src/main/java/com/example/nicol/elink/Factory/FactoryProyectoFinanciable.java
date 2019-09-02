package com.example.nicol.elink.Factory;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.Usuario.Emprendedor;

public class FactoryProyectoFinanciable {

    /**
     * Crea un proyecto con su id y la id del emprendedor correspondiente que lo creo
     * @param id id del proyecto
     * @param emprendedorId id del emprendedor
     * @return
     */
    public ProyectoFinanciable crearProyectoFinanciable(long id, String emprendedorId){
        ProyectoFinanciable proyectoFinanciable = new ProyectoFinanciable(id,emprendedorId);
        return proyectoFinanciable;
    }
}
