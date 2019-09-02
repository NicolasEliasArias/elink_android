package com.example.nicol.elink.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.Builder.ProjectViewHolderBuilder;
import com.example.nicol.elink.Director.DirectorViewHolder;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;
import java.util.List;

public abstract class ProjectsFragment extends Fragment {

    private ActivityUser activityUser;
    private List<ProyectoFinanciable> proyectos;
    private View rootview;
    private LinearLayout fragmentLayout;
    private LinearLayout projectsListLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_proyects, container,false);
        activityUser = (ActivityUser) getActivity();
        fragmentLayout = rootview.findViewById(R.id.fragment_myproyects);
        projectsListLayout = rootview.findViewById(R.id.project_list_layout);
        prepareProyects();
        return rootview;
    }

    /**
     * Prepara todos los proyectos
     */
    protected abstract void prepareProyects();

    /**
     * Muestra todos los proyectos por pantalla
     * @param proyectos proyectos a mostrar
     * @param builder builder para crear la interfaz necesaria
     */
    protected void displayProjects(List<ProyectoFinanciable> proyectos, ProjectViewHolderBuilder builder){
        getProjectsListLayout().removeAllViews();
        setProyectos(proyectos);
        DirectorViewHolder director = new DirectorViewHolder();
        for (ProyectoFinanciable proy : proyectos){
            builder.setProyectoFinanciable(proy);
            director.setBuilder(builder);
            ViewHolder vh = director.createViewHolder(getActivity(),proy.getContenido().getTitulo());
            getProjectsListLayout().addView(vh, 0);
        }
    }

    //Getters y Setters -------------------------------------------------
    public ActivityUser getActivityUser() {
        return activityUser;
    }

    public void setActivityUser(ActivityUser activityUser) {
        this.activityUser = activityUser;
    }

    public List<ProyectoFinanciable> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<ProyectoFinanciable> proyectos) {
        this.proyectos = proyectos;
    }

    public View getRootview() {
        return rootview;
    }

    public void setRootview(View rootview) {
        this.rootview = rootview;
    }

    public LinearLayout getFragmentLayout() {
        return fragmentLayout;
    }

    public void setFragmentLayout(LinearLayout fragmentLayout) {
        this.fragmentLayout = fragmentLayout;
    }

    public LinearLayout getProjectsListLayout() {
        return projectsListLayout;
    }

    public void setProjectsListLayout(LinearLayout myprojectsListLayout) {
        this.projectsListLayout = myprojectsListLayout;
    }
}
