package com.example.nicol.elink.Builder;
import android.support.v4.view.GravityCompat;
import android.view.View;
import android.widget.Button;
import com.example.nicol.elink.Activitys.ActivityInversor;
import com.example.nicol.elink.Fragments.ProjectFragment;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;

public class InversorProjectViewHolderBuilder extends ProjectViewHolderBuilder{

    public InversorProjectViewHolderBuilder() {
    }
    public InversorProjectViewHolderBuilder(ProyectoFinanciable proyectoFinanciable) {
        super(proyectoFinanciable);
    }

    @Override
    public void createButtons() {
        Button btnVer = getViewHolder().addButton();
        btnVer.setText("Ver Proyecto");
        Button btnInvertir = getViewHolder().addButton();
        btnInvertir.setText("Invertir");
        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityInversor act = (ActivityInversor) getViewHolder().getContext();
                ProjectFragment fragment = new ProjectFragment();
                fragment.setProyecto(getViewHolder().getProyectoFinanciable());
                act.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commitNow();
                act.getDrawer().closeDrawer(GravityCompat.START);
            }
        });
        btnInvertir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
