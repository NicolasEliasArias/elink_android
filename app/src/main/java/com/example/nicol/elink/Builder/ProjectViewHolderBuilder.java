package com.example.nicol.elink.Builder;
import android.app.Activity;
import android.widget.TextView;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.UI.ViewHolder.ProjectViewHolder;

public abstract class ProjectViewHolderBuilder implements IViewHolderBuilder {

    private ProjectViewHolder viewHolder;
    private ProyectoFinanciable proyectoFinanciable;

    public ProjectViewHolderBuilder( ){
    }
    public ProjectViewHolderBuilder(ProyectoFinanciable proyectoFinanciable){
        this.proyectoFinanciable = proyectoFinanciable;
    }

    @Override
    public void createViewHolder(Activity context){
        this.viewHolder = new ProjectViewHolder(context);
        this.viewHolder.setProyectoFinanciable(this.proyectoFinanciable);
    }

    @Override
    public void createText(String text) {
        viewHolder.setTitle(text);
        TextView financiacionNecesariaTextView = new TextView(viewHolder.getContext());
        financiacionNecesariaTextView.setText(viewHolder.getContext().getResources().getString(R.string.financiacionNecesaria) + proyectoFinanciable.getFinanciacionNecesaria());
        TextView financiacionActualTextView = new TextView(viewHolder.getContext());
        financiacionActualTextView.setText(viewHolder.getContext().getResources().getString(R.string.financiacionActual) + proyectoFinanciable.getFinanciacionActual());
        viewHolder.addTextView(financiacionNecesariaTextView);
        viewHolder.addTextView(financiacionActualTextView);
        TextView projectState = new TextView(viewHolder.getContext());
        projectState.setText("Estado del proyecto: " + proyectoFinanciable.getEstadoActual().getNombreEstado());
        viewHolder.addTextView(projectState);
    }

    @Override
    public ProjectViewHolder getViewHolder() {
        return viewHolder;
    }


    //Getters y Setters ---------------------------------------------------
    public void setProyectoFinanciable(ProyectoFinanciable proyectoFinanciable){
        this.proyectoFinanciable = proyectoFinanciable;
    }

    public void setViewHolder(ProjectViewHolder viewHolder) {
        this.viewHolder = viewHolder;
    }

    public ProyectoFinanciable getProyectoFinanciable() {
        return proyectoFinanciable;
    }
}
