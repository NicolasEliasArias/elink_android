package com.example.nicol.elink.Activitys;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.MyProyectsFragment;
import com.example.nicol.elink.Usuario.Emprendedor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityEmprendedor extends ActivityUser {

    private Emprendedor emprendedor;
    private final ActivityEmprendedor context = this;
    MyProyectsFragment myProyectsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myProyectsFragment = new MyProyectsFragment();
        prepareUser();
        prepareSections();
    }

    @Override
    public void prepareUser(){
        emprendedor = new Emprendedor();
        emprendedor.setId(getFirebaseAuth().getCurrentUser().getUid());
        emprendedor.setEmail(getFirebaseAuth().getCurrentUser().getEmail());
        this.setReference(FirebaseDatabase.getInstance().getReference("users/emprendedores/" + emprendedor.getId()+ "/nombreUsuario"));
        this.getReference().addValueEventListener(new ValueEventListener() {
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

    private void prepareSections(){
        getMenuHandler().createAndAddItem("Mis Proyectos", R.drawable.ic_myprojects, myProyectsFragment);
    }

    public Emprendedor getEmprendedor() {
        return emprendedor;
    }
    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }
}


