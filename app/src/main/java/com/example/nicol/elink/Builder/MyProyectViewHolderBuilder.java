package com.example.nicol.elink.Builder;

import android.app.Activity;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.nicol.elink.Activitys.ActivityEmprendedor;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.ActualProyectFragment;
import com.example.nicol.elink.UI.ViewHolder.ProyectTextViewHolder;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public class MyProyectViewHolderBuilder implements ViewHolderBuilder{

    private ProyectTextViewHolder viewHolder;
    private ProyectoFinanciable proyectoFinanciable;

    public MyProyectViewHolderBuilder(ProyectoFinanciable proyectoFinanciable){
        this.proyectoFinanciable = proyectoFinanciable;
    }

    @Override
    public void createViewHolder(Activity context){
        this.viewHolder = new ProyectTextViewHolder(context);
        this.viewHolder.setProyectoFinanciable(this.proyectoFinanciable);
    }

    @Override
    public void createText(String text) {
        TextView textView = new TextView(viewHolder.getContext());
        textView.setText(text);
        viewHolder.setContent(textView);
    }

    @Override
    public void createButtons() {
        Button btnVer = viewHolder.addButton();
        btnVer.setText("Ver Proyecto");
        Button btnBorrar = viewHolder.addButton();
        btnBorrar.setText("Borrar");
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TTT", "Boton Ver presionado");
                ActivityEmprendedor act = (ActivityEmprendedor) viewHolder.getContext();
                ActualProyectFragment fragment = new ActualProyectFragment();
                fragment.setProyecto(viewHolder.getProyectoFinanciable());
                act.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commitNow();
//                fragment.prepareProyect();
                act.getDrawer().closeDrawer(GravityCompat.START);

            }
        });
        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TTT", "Boton Borrar presionado");
            }
        });
    }

    @Override
    public ViewHolder getViewHolder() {
        return viewHolder;
    }

}
