package com.example.nicol.elink.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.nicol.elink.Activitys.ActivityEmprendedor;
import com.example.nicol.elink.Builder.MyProjectViewHolderBuilder;
import com.example.nicol.elink.DatabaseElinkManager.ElinkProjectsDatabaseManager;
import com.example.nicol.elink.Director.DirectorViewHolder;
import com.example.nicol.elink.CallBacks.FirebaseCallback;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;
import java.util.ArrayList;

public class MyProyectsFragment extends ProjectsFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRootview(super.onCreateView(inflater,container,savedInstanceState));
        prepareNewProjectsButton();
        return getRootview();
    }

    @Override
    public void prepareProyects(){
        ElinkProjectsDatabaseManager.getInstance(getActivity()).getEmprendedorProyects(getActivityUser().getUser().getId(), new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<ProyectoFinanciable> proyectos) {
                displayProjects(proyectos, new MyProjectViewHolderBuilder());
            }
        });
    }

    /**
     * Prepara el boon para crear nuevos proyectos
     */
    protected void prepareNewProjectsButton(){
        Button btn = new Button(getActivity());
        btn.setText("Nuevo Proyecto");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DirectorViewHolder director = new DirectorViewHolder();
                ActivityEmprendedor activityEmprendedor = (ActivityEmprendedor) getActivity();
                ProyectoFinanciable proyectoFinanciable = ElinkProjectsDatabaseManager.getInstance(getActivity()).createNewProyect(activityEmprendedor.getUser().getId());
                director.setBuilder(new MyProjectViewHolderBuilder(proyectoFinanciable));
                ViewHolder vh = director.createViewHolder(getActivity(), proyectoFinanciable.getContenido().getTitulo());
                getProjectsListLayout().addView(vh,0);
            }
        });
        getFragmentLayout().addView(btn);

    }

}


