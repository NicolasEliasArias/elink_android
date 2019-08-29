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
import android.widget.TextView;
import com.example.nicol.elink.Activitys.ActivityEmprendedor;
import com.example.nicol.elink.Builder.ActualProyectViewHolderBuilder;
import com.example.nicol.elink.Builder.EditActualProyectViewHolderBuilder;
import com.example.nicol.elink.DatabaseElinkManager.ElinkProjectsDatabaseManager;
import com.example.nicol.elink.Director.DirectorViewHolder;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class ActualProyectFragment extends Fragment {

    private ActivityEmprendedor context;
    private ProyectoFinanciable proyecto;
    private View rootView;
    private ViewHolder vhTitle,vhDescripcion,vhCuerpo;
    private LinearLayout contenidoProyectoLayout;
    private Button btnEdit;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_actual_proyect, container,false);
        this.context = (ActivityEmprendedor) getActivity();
        this.contenidoProyectoLayout  = rootView.findViewById(R.id.contenido_proyect_layout);
        prepareActualEmprendedor();
        prepareProyect();
        prepareButtons();
        return rootView;
    }

    private void prepareActualEmprendedor(){
        TextView emprendedorTextView = rootView.findViewById(R.id.nombre_emprendedor);
        emprendedorTextView.setText(context.getEmprendedor().getEmail());
    }

    public void prepareButtons(){
        btnEdit = rootView.findViewById(R.id.edit_button);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditClicked();
            }
        });
        btnSave = rootView.findViewById(R.id.save_button);
        btnSave.setVisibility(View.GONE);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
    }
    private void prepareProyect(){
        DirectorViewHolder director = new DirectorViewHolder(new ActualProyectViewHolderBuilder());
        vhTitle = director.createViewHolder(getActivity(),proyecto.getContenido().getTitulo());
        vhDescripcion = director.createViewHolder(getActivity(),proyecto.getContenido().getDescripcionBreve());
        vhCuerpo = director.createViewHolder(getActivity(),proyecto.getContenido().getCuerpo());
        contenidoProyectoLayout.addView(vhTitle);
        contenidoProyectoLayout.addView(vhDescripcion);
        contenidoProyectoLayout.addView(vhCuerpo);
    }

    private void onEditClicked(){
        btnEdit.setVisibility(View.GONE);
        btnSave.setVisibility((View.VISIBLE));
        contenidoProyectoLayout.removeAllViews();
        DirectorViewHolder director = new DirectorViewHolder(new EditActualProyectViewHolderBuilder());
        vhTitle = director.createViewHolder(getActivity(),proyecto.getContenido().getTitulo());
        vhDescripcion = director.createViewHolder(getActivity(),proyecto.getContenido().getDescripcionBreve());
        vhCuerpo = director.createViewHolder(getActivity(),proyecto.getContenido().getCuerpo());
        contenidoProyectoLayout.addView(vhTitle);
        contenidoProyectoLayout.addView(vhDescripcion);
        contenidoProyectoLayout.addView(vhCuerpo);
    }

    private void onSaveClicked(){
        btnEdit.setVisibility(View.VISIBLE);
        btnSave.setVisibility((View.GONE));
        contenidoProyectoLayout.removeAllViews();
        this.proyecto.getContenido().setTitulo( vhTitle.getText());
        this.proyecto.getContenido().setDescripcionBreve( vhDescripcion.getText());
        this.proyecto.getContenido().setCuerpo( vhCuerpo.getText());
        prepareProyect();
        ElinkProjectsDatabaseManager.getInstance(getActivity()).saveProyect(proyecto);
    }

    //Getters y Setters ----------------------------------------------
    public void setContext(ActivityEmprendedor context) {
        this.context = context;
    }

    public ProyectoFinanciable getProyecto() {
        return proyecto;
    }

    public void setProyecto(ProyectoFinanciable proyecto) {
        this.proyecto = proyecto;
    }

    public View getRootView() {
        return rootView;
    }

    public void setRootView(View rootView) {
        this.rootView = rootView;
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

    public LinearLayout getContenidoProyectoLayout() {
        return contenidoProyectoLayout;
    }

    public void setContenidoProyectoLayout(LinearLayout contenidoProyectoLayout) {
        this.contenidoProyectoLayout = contenidoProyectoLayout;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    public Button getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(Button btnSave) {
        this.btnSave = btnSave;
    }
}


