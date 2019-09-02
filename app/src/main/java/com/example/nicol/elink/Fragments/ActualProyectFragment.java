package com.example.nicol.elink.Fragments;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.nicol.elink.Builder.EditViewHolderBuilder;
import com.example.nicol.elink.Builder.HorizontalViewHolderBuilder;
import com.example.nicol.elink.DatabaseElinkManager.ElinkProjectsDatabaseManager;
import com.example.nicol.elink.R;

public class ActualProyectFragment extends ProjectFragment {

    private Button btnEdit;
    private Button btnSave;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        prepareButtons();
        return getRootView();
    }

    /**
     * Prepara el boton para editar el proyecto actual
     */
    protected void prepareButtons(){
        btnEdit = new Button(getActivity());
        btnEdit.setText(getResources().getString(R.string.edit));
        getFragmentLayout().addView(btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onEditClicked();
            }
        });
        btnSave = new Button(getActivity());
        btnSave.setText(getResources().getString(R.string.save));
        getFragmentLayout().addView(btnSave);
        btnSave.setVisibility(View.GONE);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
    }

    /**
     * Oculta el boton de editar, hace visible el boton de guardar y modifica la interfaz para poder editar el proyecto como el usuario quiera
     */
    protected void onEditClicked(){
        btnEdit.setVisibility(View.GONE);
        btnSave.setVisibility((View.VISIBLE));
        getContenidoProyectoLayout().removeAllViews();
        prepareFinanciacionNecesariaViewHolder(new EditViewHolderBuilder());
        prepareFinanciacionActualViewHolder(new HorizontalViewHolderBuilder());
        prepareProjectViewHolders(new EditViewHolderBuilder());
    }

    /**
     * Oculta el boton de guardar, hace visible el boton de editar y guarda las modificaciones realizadas (por el usuario) al proyecto
     */
    protected void onSaveClicked(){
        btnEdit.setVisibility(View.VISIBLE);
        btnSave.setVisibility((View.GONE));
        getContenidoProyectoLayout().removeAllViews();
        this.getProyecto().getContenido().setTitulo( getVhTitle().getTitle());
        this.getProyecto().getContenido().setDescripcionBreve( getVhDescripcion().getTitle());
        this.getProyecto().getContenido().setCuerpo( getVhCuerpo().getTitle());
        this.getProyecto().setFinanciacionNecesaria(Double.parseDouble(getVhFinanciacionNecesaria().getTitle()));
        prepareProyect();
        ElinkProjectsDatabaseManager.getInstance(getActivity()).saveProyect(getProyecto());
    }


    //Getters y Setters ----------------------------------------------
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


