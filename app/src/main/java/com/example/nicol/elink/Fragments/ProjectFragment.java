package com.example.nicol.elink.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.Builder.HorizontalViewHolderBuilder;
import com.example.nicol.elink.Builder.IViewHolderBuilder;
import com.example.nicol.elink.Builder.ViewHolderBuilder;
import com.example.nicol.elink.Director.DirectorViewHolder;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class ProjectFragment extends Fragment {

    private View rootView;
    private ActivityUser context;
    private ProyectoFinanciable proyecto;
    private ViewHolder vhTitle,vhDescripcion,vhCuerpo,vhFinanciacionNecesaria, vhFinanciacionActual;
    private LinearLayout contenidoProyectoLayout;
    private LinearLayout buttonsFragmentLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_actual_proyect, container,false);
        this.context = (ActivityUser) getActivity();
        this.contenidoProyectoLayout  = rootView.findViewById(R.id.contenido_proyect_layout);
        this.buttonsFragmentLayout = rootView.findViewById(R.id.buttons_proyect_layout);
        prepareProyect();
        return rootView;
    }


    /**
     * Prepara el proyecto
     */
    protected void prepareProyect(){
        prepareFinanciacionNecesariaViewHolder(new HorizontalViewHolderBuilder());
        prepareFinanciacionActualViewHolder(new HorizontalViewHolderBuilder());
        prepareProjectViewHolders(new ViewHolderBuilder());
    }

    /**
     * Prepara y muestra la interfaz para la financiacion necesaria del proyecto
     * @param builder builder que creat la financiacion necesaria
     */
    protected void prepareFinanciacionNecesariaViewHolder(IViewHolderBuilder builder){
        DirectorViewHolder directorViewHolder = new DirectorViewHolder(builder);
        TextView viewN = new TextView(getActivity());
        viewN .setText("- Financiacion Necesaria");
        vhFinanciacionNecesaria = directorViewHolder.createViewHolder(getActivity(), Double.toString(proyecto.getFinanciacionNecesaria()) );
        vhFinanciacionNecesaria.addTextView(viewN);
    }

    /**
     * Prepara y muestra la interfaz para la financiacion actual del proyecto
     * @param builder builder que creat la financiacion actual
     */
    protected void prepareFinanciacionActualViewHolder(IViewHolderBuilder builder){
        DirectorViewHolder directorViewHolder = new DirectorViewHolder(builder);
        vhFinanciacionActual = directorViewHolder.createViewHolder(getActivity(), Double.toString(proyecto.getFinanciacionActual()));
        TextView viewA = new TextView(getActivity());
        viewA .setText("- Financiacion Actual");
        vhFinanciacionActual.addTextView(viewA);
        contenidoProyectoLayout.addView(vhFinanciacionNecesaria);
        contenidoProyectoLayout.addView(vhFinanciacionActual);
    }

    /**
     * Prepara y muestra el contenido del proyecto
     * @param builder builder que crea la interfaz para cada seccion del proyecto (titulo, descripcion, cuerpo)
     */
    protected void prepareProjectViewHolders(IViewHolderBuilder builder){
        DirectorViewHolder director = new DirectorViewHolder(builder);
        vhTitle = director.createViewHolder(getActivity(),proyecto.getContenido().getTitulo());
        vhDescripcion = director.createViewHolder(getActivity(),proyecto.getContenido().getDescripcionBreve());
        vhCuerpo = director.createViewHolder(getActivity(),proyecto.getContenido().getCuerpo());
        contenidoProyectoLayout.addView(vhTitle);
        contenidoProyectoLayout.addView(vhDescripcion);
        contenidoProyectoLayout.addView(vhCuerpo);
    }

    //Getters y Setters ---------------------------------------------
    public View getRootView() {
        return rootView;
    }

    @Nullable
    @Override
    public ActivityUser getContext() {
        return context;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
    }

    public void setContext(ActivityUser context) {
        this.context = context;
    }

    public ProyectoFinanciable getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoFinanciable proyecto) {
        this.proyecto = proyecto;
    }

    public ViewHolder getVhTitle() {
        return vhTitle;
    }

    public void setVhTitle(ViewHolder vhTitle) {
        this.vhTitle = vhTitle;
    }

    public ViewHolder getVhDescripcion() {
        return vhDescripcion;
    }

    public void setVhDescripcion(ViewHolder vhDescripcion) {
        this.vhDescripcion = vhDescripcion;
    }

    public ViewHolder getVhCuerpo() {
        return vhCuerpo;
    }

    public void setVhCuerpo(ViewHolder vhCuerpo) {
        this.vhCuerpo = vhCuerpo;
    }

    public ViewHolder getVhFinanciacionNecesaria() {
        return vhFinanciacionNecesaria;
    }

    public void setVhFinanciacionNecesaria(ViewHolder vhFinanciacionNecesaria) {
        this.vhFinanciacionNecesaria = vhFinanciacionNecesaria;
    }

    public ViewHolder getVhFinanciacionActual() {
        return vhFinanciacionActual;
    }

    public void setVhFinanciacionActual(ViewHolder vhFinanciacionActual) {
        this.vhFinanciacionActual = vhFinanciacionActual;
    }

    public LinearLayout getContenidoProyectoLayout() {
        return contenidoProyectoLayout;
    }

    public void setContenidoProyectoLayout(LinearLayout contenidoProyectoLayout) {
        this.contenidoProyectoLayout = contenidoProyectoLayout;
    }

    public LinearLayout getFragmentLayout() {
        return buttonsFragmentLayout;
    }

    public void setFragmentLayout(LinearLayout fragmentLayout) {
        this.buttonsFragmentLayout = fragmentLayout;
    }
}
