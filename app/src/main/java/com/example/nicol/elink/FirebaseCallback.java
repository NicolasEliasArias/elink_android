package com.example.nicol.elink;
import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

import java.util.ArrayList;

public interface FirebaseCallback  {
    void onCallback(ActivityUser context, ArrayList<ProyectoFinanciable> proyectos);
}
