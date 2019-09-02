package com.example.nicol.elink.Builder;
import android.app.Activity;
import com.example.nicol.elink.UI.ViewHolder.ViewHolder;

public interface IViewHolderBuilder {

    /**
     * Crea el view holder
     * @param context contexto en donde se va a crear el view holder
     */
    public void createViewHolder(Activity context);

    /**
     * Personaliza el view holder y se le añade el texto
     * @param text texto a añadir
     */
    public void createText(String text);

    /**
     * Crea los botones del view holder
     */
    public void createButtons();

    public ViewHolder getViewHolder();

}
