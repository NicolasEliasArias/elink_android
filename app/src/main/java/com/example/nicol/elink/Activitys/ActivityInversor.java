package com.example.nicol.elink.Activitys;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nicol.elink.R;
import com.example.nicol.elink.Usuario.Inversor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityInversor extends ActivityUser {

    Inversor inversor;
    MenuItem panelProyectos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareUser();
        prepareMenuInversor();

    }

    @Override
    public void prepareUser() {
        inversor = new Inversor();
        inversor.setId(getFirebaseAuth().getCurrentUser().getUid());
        inversor.setEmail(getFirebaseAuth().getCurrentUser().getEmail());
        setReference(FirebaseDatabase.getInstance().getReference("users/inversores/" + inversor.getId()+ "/nombreUsuario"));

        getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombreUsuario = dataSnapshot.getValue(String.class);
                inversor.setNombreUsuario(nombreUsuario);
                String e = inversor.getEmail();
                getUserEmailTextView().setText(inversor.getEmail());
                getUserNameTextView().setText(inversor.getNombreUsuario());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void prepareMenuInversor(){
        panelProyectos = getNavView().getMenu().add(R.id.menu_group_dynamic, Menu.NONE, 0, "Panel de Proyectos").setIcon(R.drawable.ic_myprojects);
    }

    public Inversor getInversor() {
        return inversor;
    }

    public void setInversor(Inversor inversor) {
        this.inversor = inversor;
    }
}
