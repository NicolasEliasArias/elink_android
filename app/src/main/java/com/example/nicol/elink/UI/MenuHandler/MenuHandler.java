package com.example.nicol.elink.UI.MenuHandler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.view.Menu;
import android.view.MenuItem;
import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.R;
import java.util.AbstractMap;
import java.util.ArrayList;

public abstract class MenuHandler {

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
        int newItemId = getItems().size();
        MenuItem newItem = getMenu().add(R.id.menu_group_dynamic, newItemId,0, title).setIcon(iconId);
        newItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                getContext().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                getContext().getDrawer().closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    /**
     * Prepara los items en comun que tendran todos los usuarios (sin importar si son inversores o emprendedores)
     */
    protected abstract void setStaticItemsListeners();

    //Getters y Setters -----------------------------------------------------
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public ArrayList<AbstractMap.SimpleEntry<MenuItem, Fragment>> getItems() {
        return items;
    }

    public void setItems(ArrayList<AbstractMap.SimpleEntry<MenuItem, Fragment>> items) {
        this.items = items;
    }

    public ActivityUser getContext() {
        return context;
    }

    public void setContext(ActivityUser context) {
        this.context = context;
    }
}
