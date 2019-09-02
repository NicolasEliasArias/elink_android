package com.example.nicol.elink.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.nicol.elink.Builder.InversorProjectViewHolderBuilder;
import com.example.nicol.elink.CallBacks.FirebaseCallback;
import com.example.nicol.elink.DatabaseElinkManager.ElinkProjectsDatabaseManager;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import java.util.ArrayList;

public class AllProjectsFragment extends ProjectsFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRootview(super.onCreateView(inflater,container,savedInstanceState));
        return getRootview();
    }

    @Override
    protected void prepareProyects(){
        ElinkProjectsDatabaseManager.getInstance(getActivity()).getAllProyects(new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<ProyectoFinanciable> proyectos) {
                displayProjects(proyectos, new InversorProjectViewHolderBuilder());
            }
        });
    }
}
