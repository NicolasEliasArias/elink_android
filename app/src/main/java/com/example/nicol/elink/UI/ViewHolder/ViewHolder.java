package com.example.nicol.elink.UI.ViewHolder;
import android.app.Activity;
import android.support.v7.widget.AppCompatTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ViewHolder extends LinearLayout {

    private TextView titleTextView;
    private List<TextView> allContent;
    private Activity context;
    private LinearLayout buttonsLayout;
    private LinearLayout contentLayout;
    private List<Button> buttons;

    public ViewHolder(Activity context) {
        super(context);
        this.context = context;
        titleTextView = new TextView(context);
        this.addView(titleTextView,0);
        prepareLayouts();
    }

    /**
     * Agrega un boton nuevo
     * @return boton creado
     */
    public Button addButton(){
        Button btn = new Button(getContext());
        getButtons().add(btn);
        buttonsLayout.addView(btn);
        return  btn;
    }

    /**
     * Agrega un TextView nuevo
     * @param textView textView a agregar
     */
    public void addTextView(TextView textView){
        allContent.add(textView);
        contentLayout.addView(textView);
    }

    /**
     * prepara los layouts del view holder
     */
    protected void prepareLayouts(){
        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        this.setOrientation(VERTICAL);
        contentLayout = new LinearLayout(context);
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        contentLayout.setOrientation(VERTICAL);
        buttonsLayout = new LinearLayout(context);
        buttonsLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
        allContent = new ArrayList<>();
        buttons = new ArrayList<>();
        this.addView(contentLayout);
        this.addView(buttonsLayout);
    }
    //Getters y Setters -----------------------------------------------------------

    public List<TextView> getAllContent() {
        return allContent;
    }

    public void setAllContent(List<TextView> allContent) {
        this.allContent = allContent;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public LinearLayout getButtonsLayout() {
        return buttonsLayout;
    }

    public void setButtonsLayout(LinearLayout buttonsLayout) {
        this.buttonsLayout = buttonsLayout;
    }

    public List<Button> getButtons() {
        return buttons;
    }

    public void setButtons(List<Button> buttons) {
        this.buttons = buttons;
    }

    public TextView getTitleTextView() {
        return titleTextView;
    }

    public void setTitleTextView(TextView titleTextView) {
        this.titleTextView = titleTextView;
//        this.removeViewAt(0);
        this.addView(this.titleTextView,0);
    }

    public LinearLayout getContentLayout() {
        return contentLayout;
    }

    public void setContentLayout(LinearLayout contentLayout) {
        this.contentLayout = contentLayout;
    }

    public void setTitle(String text){
        titleTextView.setText(text);
    }

    public String getTitle(){
        return  titleTextView.getText().toString();
    }
}
