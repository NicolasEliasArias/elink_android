package com.example.nicol.elink;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nicol.elink.Usuario.Emprendedor;
import com.example.nicol.elink.Usuario.Inversor;
import com.example.nicol.elink.Usuario.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityAuthentication extends Activity {

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference databaseRef;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

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
                //inicio de sesion
                registrarse(email,password);
            }else{
                //registro
                iniciarSesion(email,password);
            }
        }
    }

    private void registrarse(final String email, final String password){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registrando, por favor espere...");
        progressDialog.show();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //usuario se registro exitosamente
                            if(buttonEmprendedor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
                                registrarEmprendedor(email,password);
                            }else if(buttonInversor.getId() == grupoEmprendedorInversor.getCheckedRadioButtonId()){
                                registrarInversor(email,password);
                            }
                            finishAuth();
                        }else{
                            //usuario no pudo registrarse
                            Toast.makeText(ActivityAuthentication.this, "No se pudo registrar :(", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    private void iniciarSesion(String email, String password){
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Iniciando Sesion, por favor espere...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //usuario se registro exitosamente
                            Toast.makeText(ActivityAuthentication.this, "Logueado exitosamente", Toast.LENGTH_SHORT).show();
                            finishAuth();
                        }else{
                            //usuario no pudo registrarse
                            Toast.makeText(ActivityAuthentication.this, "No se pudo loguear :(", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();;
                    }
                });
    }

    private void registrarInversor(String email,String password){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = "" + currentFirebaseUser.getUid();
        Usuario inversorNuevo = new Inversor(userid,"Pepe",email);
        databaseRef = database.getReference("users/inversores/" + userid);
        databaseRef.setValue(inversorNuevo);
        userType = getResources().getString(R.string.TIPOINVERSOR);
    }

    private void registrarEmprendedor(String email,String password){
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = "" + currentFirebaseUser.getUid();
        Usuario emprendedorNuevo = new Emprendedor(userid,"Mauro", email);
        databaseRef = database.getReference("users/emprendedores/" + userid);
        databaseRef.setValue(emprendedorNuevo);
        userType = getResources().getString(R.string.TIPOEMPRENDEDOR);
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


    //Getters y Setters
    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public DatabaseReference getDatabaseRef() {
        return databaseRef;
    }

    public void setDatabaseRef(DatabaseReference databaseRef) {
        this.databaseRef = databaseRef;
    }

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
}
