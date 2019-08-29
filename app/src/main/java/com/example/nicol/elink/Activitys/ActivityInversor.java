package com.example.nicol.elink.Activitys;
import android.os.Bundle;
import com.example.nicol.elink.CallBacks.InversorCallback;
import com.example.nicol.elink.DatabaseElinkManager.ElinkUserDatabaseManager;
import com.example.nicol.elink.Fragments.AllProjectsFragment;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Usuario.Inversor;

public class ActivityInversor extends ActivityUser {

    private Inversor inversor;
    private AllProjectsFragment allProjectsFragment;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allProjectsFragment = new AllProjectsFragment();
        prepareUser();
        prepareSections();

    }

    @Override
    public void prepareUser() {
        ElinkUserDatabaseManager.getInstance(this).getInversor(getFirebaseAuth().getCurrentUser().getUid(), new InversorCallback() {
            @Override
            public void onCallback(Inversor inv) {
               inversor = inv;
                getUserEmailTextView().setText(inversor.getEmail());
                getUserNameTextView().setText(inversor.getNombreUsuario());
            }
        });
    }

    private void prepareSections(){
        getMenuHandler().createAndAddItem("Panel de Proyectos", R.drawable.ic_myprojects, allProjectsFragment);
    }

    //Getters y Setters ---------------------------------------------
    public Inversor getInversor() {
        return inversor;
    }

    public void setInversor(Inversor inversor) {
        this.inversor = inversor;
    }

    public AllProjectsFragment getAllProjectsFragment() {
        return allProjectsFragment;
    }

    public void setAllProjectsFragment(AllProjectsFragment allProjectsFragment) {
        this.allProjectsFragment = allProjectsFragment;
    }
}
