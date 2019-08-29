package com.example.nicol.elink.Activitys;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
    private ProgressDialog progressDialog;
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

    private void iniciarSesion(String email, String password){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando Sesion, por favor espere...");
        progressDialog.show();
        userManager.logIn(email, password, new Callback() {
            @Override
            public void onCallback() {
                Toast.makeText(ActivityAuthentication.this, "Logueado exitosamente", Toast.LENGTH_SHORT).show();
                if(buttonEmprendedor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
                    userType = getResources().getString(R.string.TIPOEMPRENDEDOR);
                }else if(buttonInversor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
                    userType = getResources().getString(R.string.TIPOINVERSOR);
                }
                finishAuth();
                progressDialog.dismiss();
            }

            @Override
            public void onCallbackFailed() {
                Toast.makeText(ActivityAuthentication.this, "No se pudo loguear :(", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void registrarse(final String email, final String password){
        progressDialog = new ProgressDialog(this);
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
            public void onCallbackFailed() {
                Toast.makeText(ActivityAuthentication.this, "No se pudo registrar :(", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
    }

    private void registrarEmprendedor(String  email){
        userManager.registerNewEmprendedor(email);
        userType = getResources().getString(R.string.TIPOEMPRENDEDOR);
    }

    private void registrarInversor(String email){
        userManager.registerNewInvestor(email);
        userType = getResources().getString(R.string.TIPOINVERSOR);
    }

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

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public void setProgressDialog(ProgressDialog progressDialog) {
        this.progressDialog = progressDialog;
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
