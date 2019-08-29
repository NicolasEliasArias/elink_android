package com.example.nicol.elink.CallBacks;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;

import java.util.ArrayList;

public interface FirebaseCallback  {
    void onCallback(ArrayList<ProyectoFinanciable> proyectos);
}
