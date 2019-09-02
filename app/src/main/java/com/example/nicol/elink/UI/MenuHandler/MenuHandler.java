package com.example.nicol.elink.UI.MenuHandler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.AboutFragment;
import java.util.AbstractMap;
import java.util.ArrayList;

public class MenuHandler {
    private final int ABOUTITEM = 0;
    private final int CERRARSESIONITEM = 1;
    private Menu menu;
    private ArrayList<AbstractMap.SimpleEntry<MenuItem,Fragment>> items;
    private ActivityUser context;

    public MenuHandler(ActivityUser context){
        this.context = context;
        this.menu = context.getNavView().getMenu();
        items = new ArrayList<AbstractMap.SimpleEntry<MenuItem,Fragment>>();
        setStaticItemsListeners();
    }

    /**
     * Creea un item nuevo y lo agrega al menu
     * @param title Titulo del item nuevo
     * @param iconId Icono del item nuevo
     * @param fragment fragment que abrira una vez clickeado el item
     */
    public void createAndAddItem(String title, int iconId , final Fragment fragment){
        int newItemId = items.size();
        MenuItem newItem = menu.add(R.id.menu_group_dynamic, newItemId,0, title).setIcon(iconId);
        newItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                context.getDrawer().closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    /**
     * Prepara los items en comun que tendran todos los usuarios (sin importar si son inversores o emprendedores)
     */
    private void setStaticItemsListeners(){
        context.getNavView().getMenu().getItem(ABOUTITEM).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                context.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
                context.getDrawer().closeDrawer(GravityCompat.START);
                return true;
            }
        });

        context.getNavView().getMenu().getItem(CERRARSESIONITEM).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                context.getDrawer().closeDrawer(GravityCompat.START);
                //Codigo para CERRAR SESION
                return true;
            }
        });
    }

}
