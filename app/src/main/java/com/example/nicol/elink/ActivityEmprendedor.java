package com.example.nicol.elink;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;

import com.example.nicol.elink.UI.AboutFragment;
import com.example.nicol.elink.Usuario.Emprendedor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityEmprendedor extends ActivityUser {

    MenuItem myProyects;
    Emprendedor emprendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prepareMenuEmprendedor();
        prepareUser();
    }

    @Override
    public void prepareUser(){
        emprendedor = new Emprendedor();
        emprendedor.setId(getFirebaseAuth().getCurrentUser().getUid());
        emprendedor.setEmail(getFirebaseAuth().getCurrentUser().getEmail());
        setReference(FirebaseDatabase.getInstance().getReference("users/emprendedores/" + emprendedor.getId()+ "/nombreUsuario"));
        getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombreUsuario = dataSnapshot.getValue(String.class);
                emprendedor.setNombreUsuario(nombreUsuario);
                String e = emprendedor.getEmail();
                getUserEmailTextView().setText(emprendedor.getEmail());
                getUserNameTextView().setText(emprendedor.getNombreUsuario());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void prepareMenu() {
        super.prepareMenu();
        getNavView().setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.about_item:
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                        break;
                    case R.id.cerrar_sesion_item:
                        break;
                }
                getDrawer().closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    public void prepareMenuEmprendedor(){
        myProyects = getNavView().getMenu().add(R.id.menu_group_dynamic,Menu.NONE,0,"Mis Proyectos").setIcon(R.drawable.ic_myprojects);
    }

    public MenuItem getMyProyects() {
        return myProyects;
    }

    public void setMyProyects(MenuItem myProyects) {
        this.myProyects = myProyects;
    }

    public Emprendedor getEmprendedor() {
        return emprendedor;
    }

    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }
}


