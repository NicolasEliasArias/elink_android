package com.example.nicol.elink.Activitys;
import android.os.Bundle;
import com.example.nicol.elink.CallBacks.EmprendedorCallback;
import com.example.nicol.elink.DatabaseElinkManager.ElinkUserDatabaseManager;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.MyProyectsFragment;
import com.example.nicol.elink.Usuario.Emprendedor;

public class ActivityEmprendedor extends ActivityUser {

    private Emprendedor emprendedor;
    private MyProyectsFragment myProyectsFragment;

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
        ElinkUserDatabaseManager.getInstance(this).getEmprendedor(getFirebaseAuth().getCurrentUser().getUid(), new EmprendedorCallback() {
            @Override
            public void onCallback(Emprendedor emp) {
                emprendedor = emp;
                getUserEmailTextView().setText(emprendedor.getEmail());
                getUserNameTextView().setText(emprendedor.getNombreUsuario());
            }
        });
    }

    private void prepareSections(){
        getMenuHandler().createAndAddItem("Mis Proyectos", R.drawable.ic_myprojects, myProyectsFragment);
    }

    //Getters y Setters ------------------------------------------------
    public Emprendedor getEmprendedor() {
        return emprendedor;
    }

    public void setEmprendedor(Emprendedor emprendedor) {
        this.emprendedor = emprendedor;
    }

    public MyProyectsFragment getMyProyectsFragment() {
        return myProyectsFragment;
    }

    public void setMyProyectsFragment(MyProyectsFragment myProyectsFragment) {
        this.myProyectsFragment = myProyectsFragment;
    }
}


