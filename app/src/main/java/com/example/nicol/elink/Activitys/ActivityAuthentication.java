package com.example.nicol.elink.Activitys;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.nicol.elink.CallBacks.Callback;
import com.example.nicol.elink.DatabaseElinkManager.ElinkUserDatabaseManager;
import com.example.nicol.elink.R;

public class ActivityAuthentication extends Activity {

    private EditText emailEditText;
    private EditText passwordEditText;
    private Button buttonIniciarSesion;
    private Button buttonRegistrarse;
    private RadioGroup grupoEmprendedorInversor;
    private RadioButton buttonEmprendedor;
    private RadioButton buttonInversor;
    private RadioButton buttonChecked;
    private String userType;
    private ElinkUserDatabaseManager userManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        userManager = ElinkUserDatabaseManager.getInstance(this);

        grupoEmprendedorInversor = findViewById(R.id.radiogroup_emprendedorinversor);
        buttonEmprendedor = findViewById(R.id.button_emprendedor);
        buttonInversor = findViewById(R.id.button_inversor);
        buttonEmprendedor.setText(getResources().getString(R.string.TIPOEMPRENDEDOR));
        buttonInversor.setText(getResources().getString(R.string.TIPOINVERSOR));
        userType = getResources().getString(R.string.TIPOEMPRENDEDOR);

        emailEditText = findViewById(R.id.input_edit_text_email);
        passwordEditText = findViewById(R.id.input_edit_text_password);
        buttonIniciarSesion = findViewById(R.id.button_iniciar_sesion);
        buttonRegistrarse = findViewById(R.id.button_registrarse);
    }

    /**
     * Inicia sesion o registra al usuario con los datos almacenados en los inputs
     * @param v
     */
    public void onAuthButtonClicked(View v){
        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        int radioButtonID = grupoEmprendedorInversor.getCheckedRadioButtonId();
        buttonChecked = findViewById(radioButtonID);
        if(this.validarEmailPassword(email,password)){
            if(v == buttonRegistrarse){
                registrarse(email,password);
            }else{
                iniciarSesion(email,password);
            }
        }
    }

    /**
     * inicia sesion con los datos correspondientes del usuario
     * @param email email del usuario
     * @param password contraseña del usuario
     */
    public void iniciarSesion(String email, String password){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando Sesion, por favor espere...");
        progressDialog.show();
        if(buttonEmprendedor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
            userType = getResources().getString(R.string.TIPOEMPRENDEDOR);
        }else if(buttonInversor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
            userType = getResources().getString(R.string.TIPOINVERSOR);
        }
        Callback newCallback = new Callback() {
            @Override
            public void onCallback() {
                Toast.makeText(ActivityAuthentication.this, "Logueado exitosamente", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
                finishAuth();
            }

            @Override
            public void onCallbackFailed(String errorMessage) {
                Toast.makeText(ActivityAuthentication.this, "No se pudo loguear :(" + errorMessage, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        };
        userManager.logIn(email, password, newCallback, userType);
    }

    /**
     * registra al usuario con los datos correspondientes
     * @param email email del usuario
     * @param password contraseña del usuario
     */
    public void registrarse(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando, por favor espere...");
        progressDialog.show();
        userManager.registerUser(email, password, new Callback() {
            @Override
            public void onCallback() {
                if(buttonEmprendedor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
                    registrarEmprendedor(email);
                }else if(buttonInversor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
                    registrarInversor(email);
                }
                progressDialog.dismiss();
                finishAuth();
            }

            @Override
            public void onCallbackFailed(String errorMessage) {
                Toast.makeText(ActivityAuthentication.this, "No se pudo registrar :(" + errorMessage, Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    /**
     * Almacena un nuevo emprendedor en la base de datos
     * @param email
     */
    private void registrarEmprendedor(String  email){
        userManager.registerNewEmprendedor(email);
        userType = getResources().getString(R.string.TIPOEMPRENDEDOR);
    }

    /**
     * Almacena un nuevo inversor en la base de datos
     * @param email
     */
    private void registrarInversor(String email){
        userManager.registerNewInvestor(email);
        userType = getResources().getString(R.string.TIPOINVERSOR);
    }

    /**
     * Retorna true si el email y la contraseña son validas. Retorna false en caso contrario
     * @param email email ingresado
     * @param password contraseña ingresada
     * @return
     */
    private boolean validarEmailPassword(String email, String password){
        boolean valido = true;
        if(TextUtils.isEmpty(email)){
            //email vacio
            Toast.makeText(this,"Por faveor ingresar email",Toast.LENGTH_SHORT).show();
            valido = false;
        }else
            if(TextUtils.isEmpty(password)){
                //contraseña vacia}
                Toast.makeText(this,"Por faveor ingresar contraseña",Toast.LENGTH_SHORT).show();
                valido = false;
            }
        return valido;
    }

    /**
     * Finaliza la actividad retornando a la Actividad anterior el tipo de usuario que inicia sesion
     */
    public void finishAuth(){
        Intent resultIntent = new Intent();
        resultIntent.putExtra(getResources().getString(R.string.RESULT_USERTYPE), userType);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    //Getters y Setters------------------------------------------------

    public EditText getEmailEditText() {
        return emailEditText;
    }

    public void setEmailEditText(EditText emailEditText) {
        this.emailEditText = emailEditText;
    }

    public EditText getPasswordEditText() {
        return passwordEditText;
    }

    public void setPasswordEditText(EditText passwordEditText) {
        this.passwordEditText = passwordEditText;
    }

    public Button getButtonIniciarSesion() {
        return buttonIniciarSesion;
    }

    public void setButtonIniciarSesion(Button buttonIniciarSesion) {
        this.buttonIniciarSesion = buttonIniciarSesion;
    }

    public Button getButtonRegistrarse() {
        return buttonRegistrarse;
    }

    public void setButtonRegistrarse(Button buttonRegistrarse) {
        this.buttonRegistrarse = buttonRegistrarse;
    }

    public RadioGroup getGrupoEmprendedorInversor() {
        return grupoEmprendedorInversor;
    }

    public void setGrupoEmprendedorInversor(RadioGroup grupoEmprendedorInversor) {
        this.grupoEmprendedorInversor = grupoEmprendedorInversor;
    }

    public RadioButton getButtonEmprendedor() {
        return buttonEmprendedor;
    }

    public void setButtonEmprendedor(RadioButton buttonEmprendedor) {
        this.buttonEmprendedor = buttonEmprendedor;
    }

    public RadioButton getButtonInversor() {
        return buttonInversor;
    }

    public void setButtonInversor(RadioButton buttonInversor) {
        this.buttonInversor = buttonInversor;
    }

    public RadioButton getButtonChecked() {
        return buttonChecked;
    }

    public void setButtonChecked(RadioButton buttonChecked) {
        this.buttonChecked = buttonChecked;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public ElinkUserDatabaseManager getUserManager() {
        return userManager;
    }

    public void setUserManager(ElinkUserDatabaseManager userManager) {
        this.userManager = userManager;
    }
}
