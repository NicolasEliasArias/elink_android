package com.example.nicol.elink.DatabaseElinkManager;
import com.example.nicol.elink.CallBacks.FirebaseCallback;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.Usuario.Emprendedor;

public interface ProjectsDatabaseManager {
    public void getAllProyects(final FirebaseCallback callback);
    public void saveProyect(final ProyectoFinanciable proyectoFinanciable);
    public ProyectoFinanciable createNewProyect(final String emprendedor );
    public void getEmprendedorProyects(final String emprendedor,final FirebaseCallback firebaseCallback);
}
