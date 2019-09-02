package com.example.nicol.elink.Activitys;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.example.nicol.elink.CallBacks.EmprendedorCallback;
import com.example.nicol.elink.DatabaseElinkManager.ElinkUserDatabaseManager;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.MyProyectsFragment;
import com.example.nicol.elink.Usuario.Emprendedor;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityEmprendedor extends ActivityUser {

    private MyProyectsFragment myProyectsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myProyectsFragment = new MyProyectsFragment();
        prepareUser();
        prepareMenuItemSections();
    }

    @Override
    protected void prepareUser(){
        ElinkUserDatabaseManager.getInstance(this).getEmprendedor(FirebaseAuth.getInstance().getCurrentUser().getUid(), new EmprendedorCallback() {
            @Override
            public void onCallback(Emprendedor emp) {
                setUser(emp);
                getUserEmailTextView().setText(getUser().getEmail());
                getUserNameTextView().setText(getUser().getNombreUsuario());
            }

            @Override
            public void onCallbackFailed(String errorMessage) {

            }
        });
    }

    @Override
    protected void prepareMenuItemSections(){
        getMenuHandler().createAndAddItem("Mis Proyectos", R.drawable.ic_myprojects, myProyectsFragment);
    }

    //Getters y Setters ------------------------------------------------

    public MyProyectsFragment getMyProyectsFragment() {
        return myProyectsFragment;
    }

    public void setMyProyectsFragment(MyProyectsFragment myProyectsFragment) {
        this.myProyectsFragment = myProyectsFragment;
    }
}


