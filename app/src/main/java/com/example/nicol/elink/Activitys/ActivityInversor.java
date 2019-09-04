package com.example.nicol.elink.Activitys;
import android.os.Bundle;
import com.example.nicol.elink.CallBacks.InversorCallback;
import com.example.nicol.elink.DatabaseElinkManager.ElinkUserDatabaseManager;
import com.example.nicol.elink.Fragments.AllProjectsFragment;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Usuario.Inversor;
import com.google.firebase.auth.FirebaseAuth;

public class ActivityInversor extends ActivityUser {

    private AllProjectsFragment allProjectsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        allProjectsFragment = new AllProjectsFragment();
        prepareUser();
        prepareMenuItemSections();

    }

    @Override
    protected void prepareUser() {
        ElinkUserDatabaseManager.getInstance(this).getInversor(FirebaseAuth.getInstance().getCurrentUser().getUid(), new InversorCallback() {
            @Override
            public void onCallback(Inversor inv) {
                setUser(inv);
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
        getMenuHandler().createAndAddItem("Panel de Proyectos", R.drawable.ic_myprojects, allProjectsFragment);
    }

    //Getters y Setters ---------------------------------------------
    public AllProjectsFragment getAllProjectsFragment() {
        return allProjectsFragment;
    }

    public void setAllProjectsFragment(AllProjectsFragment allProjectsFragment) {
        this.allProjectsFragment = allProjectsFragment;
    }
}
