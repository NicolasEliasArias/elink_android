package com.example.nicol.elink.Activitys;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Fragments.AboutFragment;
import com.example.nicol.elink.UI.MenuHandler.ElinkMenuHandler;
import com.example.nicol.elink.Usuario.Usuario;

public abstract class ActivityUser extends AppCompatActivity{

    private DrawerLayout drawer;
    private TextView userEmailTextView;
    private TextView userNameTextView;
    private NavigationView navView;
    private ElinkMenuHandler menuHandler;
    private Usuario user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        navView = findViewById(R.id.nav_view);
        prepareDrawer();
        prepareHeader();
        prepareMenu();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();
            navView.setCheckedItem(R.id.about_item);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    /**
     * Prepara el drawer de la aplicacion
     */
    protected void prepareDrawer(){
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * Prepara el header de la aplicacion
     */
    protected void prepareHeader(){
        userEmailTextView = navView.getHeaderView(0).findViewById(R.id.text_view_user_email);
        userNameTextView = navView.getHeaderView(0).findViewById(R.id.text_view_user_name);
    }

    /**
     * prepara el menu handler de la aplicacion con sus items
     */
    protected void prepareMenu(){
        menuHandler = new ElinkMenuHandler(this);
    }

    /**
     * prepara el usuario que inicio sesion y todos los datos necesarios
     */
    protected abstract void prepareUser();

    /**
     *Prepara todos los items del menu necesarios para el usuario correspondiente
     */
    protected abstract void prepareMenuItemSections();

    //Getters y Setters-----------------------------------------
    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public DrawerLayout getDrawer() {
        return drawer;
    }

    public void setDrawer(DrawerLayout drawer) {
        this.drawer = drawer;
    }

    public TextView getUserEmailTextView() {
        return userEmailTextView;
    }

    public void setUserEmailTextView(TextView userEmailTextView) {
        this.userEmailTextView = userEmailTextView;
    }

    public TextView getUserNameTextView() {
        return userNameTextView;
    }

    public void setUserNameTextView(TextView userNameTextView) {
        this.userNameTextView = userNameTextView;
    }

    public NavigationView getNavView() {
        return navView;
    }

    public void setNavView(NavigationView navView) {
        this.navView = navView;
    }

    public ElinkMenuHandler getMenuHandler() {
        return menuHandler;
    }

    public void setMenuHandler(ElinkMenuHandler menuHandler) {
        this.menuHandler = menuHandler;
    }

}
