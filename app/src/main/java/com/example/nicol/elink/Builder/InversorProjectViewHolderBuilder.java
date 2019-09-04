package com.example.nicol.elink.Builder;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.nicol.elink.Activitys.ActivityInversor;
import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.DatabaseElinkManager.ElinkProjectsDatabaseManager;
import com.example.nicol.elink.DatabaseElinkManager.ElinkUserDatabaseManager;
import com.example.nicol.elink.Fragments.ProjectFragment;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Usuario.Inversor;

public class InversorProjectViewHolderBuilder extends ProjectViewHolderBuilder{

    private Inversor inversor;

    public InversorProjectViewHolderBuilder( Inversor inversor) {
        this.inversor = inversor;
    }
    public InversorProjectViewHolderBuilder(ProyectoFinanciable proyectoFinanciable, Inversor inversor) {
        super(proyectoFinanciable);
        this.inversor = inversor;
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
                final AlertDialog.Builder builder = new AlertDialog.Builder(getViewHolder().getContext());
                builder.setTitle("Ingresa el monto que quieres inverir");
                final EditText input = new EditText(getViewHolder().getContext());
                builder.setView(input);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityUser activityInversor = (ActivityUser) getViewHolder().getContext();
                        Inversor inv = (Inversor) activityInversor.getUser();
                        inv.invertir(getProyectoFinanciable(),Double.parseDouble(input.getText().toString()));
                        ElinkUserDatabaseManager.getInstance((ActivityUser) getViewHolder().getContext()).saveInversor(inv);
                        ElinkProjectsDatabaseManager.getInstance((ActivityUser) getViewHolder().getContext()).saveProyect(getProyectoFinanciable());
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
    }

}
