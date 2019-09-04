package com.example.nicol.elink.UI.MenuHandler;
import android.support.v4.view.GravityCompat;
import android.view.MenuItem;
import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.AboutFragment;

public class ElinkMenuHandler extends  MenuHandler{
    private final int ABOUTITEM = 0;
    private final int CERRARSESIONITEM = 1;

    public ElinkMenuHandler(ActivityUser context) {
        super(context);
    }

    @Override
    protected void setStaticItemsListeners(){
        getContext().getNavView().getMenu().getItem(ABOUTITEM).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getContext().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                getContext().getDrawer().closeDrawer(GravityCompat.START);
                return true;
            }
        });

        getContext().getNavView().getMenu().getItem(CERRARSESIONITEM).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getContext().getDrawer().closeDrawer(GravityCompat.START);
                //Codigo para CERRAR SESION
                return true;
            }
        });
    }

}
