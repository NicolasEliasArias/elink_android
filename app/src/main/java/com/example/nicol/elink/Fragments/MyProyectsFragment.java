package com.example.nicol.elink.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.nicol.elink.Activitys.ActivityEmprendedor;
import com.example.nicol.elink.Builder.MyProyectViewHolderBuilder;
import com.example.nicol.elink.DatabaseElinkManager.ElinkProjectsDatabaseManager;
import com.example.nicol.elink.Director.DirectorViewHolder;
import com.example.nicol.elink.CallBacks.FirebaseCallback;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;
import java.util.ArrayList;

public class MyProyectsFragment extends Fragment {

    private ActivityEmprendedor context;
    private ArrayList<ProyectoFinanciable> proyectos;
    private View rootview;
    private LinearLayout fragmentLayout;
    private LinearLayout myprojectsListLayout;
    private DirectorViewHolder director;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_myproyects, container,false);
        context = (ActivityEmprendedor) getActivity();
        fragmentLayout = rootview.findViewById(R.id.fragment_myproyects);
        myprojectsListLayout = rootview.findViewById(R.id.project_list_layout);
        director = new DirectorViewHolder();
        prepareProyects();
        prepareNewProjectsButton();
        return rootview;
    }

    public void prepareNewProjectsButton(){
        Button btn = new Button(getActivity());
        btn.setText("Nuevo Proyecto");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityEmprendedor activityEmprendedor = (ActivityEmprendedor) getActivity();
                ProyectoFinanciable proyectoFinanciable = ElinkProjectsDatabaseManager.getInstance(getActivity()).createNewProyect(activityEmprendedor.getEmprendedor());
                director.setBuilder(new MyProyectViewHolderBuilder(proyectoFinanciable));
                ViewHolder vh = director.createViewHolder(getActivity(), proyectoFinanciable.getContenido().getTitulo());
                myprojectsListLayout.addView(vh,0);
            }
        });
        fragmentLayout.addView(btn);
    }

    public void prepareProyects(){
        ElinkProjectsDatabaseManager.getInstance(getActivity()).getEmprendedorProyects(context.getEmprendedor(), new FirebaseCallback() {
            @Override
            public void onCallback(ArrayList<ProyectoFinanciable> proyectos) {
                myprojectsListLayout.removeAllViews();
                setProyectos(proyectos);
                for (ProyectoFinanciable proy : proyectos){
                    MyProyectViewHolderBuilder buider = new MyProyectViewHolderBuilder(proy);
                    director.setBuilder(buider);
                    ViewHolder vh = director.createViewHolder(getActivity(),proy.getContenido().getTitulo());
                    myprojectsListLayout.addView(vh,0);
                }
            }
        });
    }

    public ArrayList<ProyectoFinanciable> getProyectos() {
        return proyectos;
    }
    public void setProyectos(ArrayList<ProyectoFinanciable> proyectos) {
        this.proyectos = proyectos;
    }

}


