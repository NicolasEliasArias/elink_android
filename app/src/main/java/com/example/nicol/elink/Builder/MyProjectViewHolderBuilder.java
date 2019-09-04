package com.example.nicol.elink.Builder;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.example.nicol.elink.Activitys.ActivityEmprendedor;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.ActualProyectFragment;

public class MyProjectViewHolderBuilder extends ProjectViewHolderBuilder{

    public MyProjectViewHolderBuilder() {
    }
    public MyProjectViewHolderBuilder(ProyectoFinanciable proyectoFinanciable) {
        super(proyectoFinanciable);
    }

    @Override
    public void createButtons() {
        Button btnVer = getViewHolder().addButton();
        btnVer.setText("Ver Proyecto");
        Button btnBorrar = getViewHolder().addButton();
        btnBorrar.setText("Borrar");
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TTT", "Boton Ver presionado");
                ActivityEmprendedor act = (ActivityEmprendedor) getViewHolder().getContext();
                ActualProyectFragment fragment = new ActualProyectFragment();
                fragment.setProyecto(getViewHolder().getProyectoFinanciable());
                act.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commitNow();
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

}
