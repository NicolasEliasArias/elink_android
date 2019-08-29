package com.example.nicol.elink.DatabaseElinkManager;
import com.example.nicol.elink.CallBacks.FirebaseCallback;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.Usuario.Emprendedor;

public interface ProjectsDatabaseManager {
    public void getAllProyects();
    public void saveProyect(final ProyectoFinanciable proyectoFinanciable);
    public ProyectoFinanciable createNewProyect(final Emprendedor emprendedor );
    public void getEmprendedorProyects(final Emprendedor emprendedor,final FirebaseCallback firebaseCallback);
}
