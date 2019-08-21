package com.example.nicol.elink;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView txt1;

    private String returnedUserType; // el valor va a ser retornado por ActivityAuthentication (puede ser "emprendedor" o "inversor")

    private final int REQUEST_CODE_AUTH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1 = findViewById(R.id.txt1);
        //int numero = Integer.parseInt(mEditTextNumber.getText().toString()
        this.openActivityAuthentication();
    }

    public void openActivityAuthentication(){
        Intent intentAuthentication = new Intent(this, ActivityAuthentication.class);
        intentAuthentication.putExtra(getResources().getString(R.string.RETURNED_USERTYPE), returnedUserType);
        startActivityForResult(intentAuthentication, REQUEST_CODE_AUTH_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE_AUTH_ACTIVITY){//esto es para saber exactamente que "startActivityForResult" esta retornando
            if(resultCode == RESULT_OK){
                returnedUserType = data.getStringExtra(getResources().getString(R.string.RESULT_USERTYPE));
                firebaseAuth = FirebaseAuth.getInstance();
                if(firebaseAuth.getCurrentUser() != null){
                    if(returnedUserType.equals(getResources().getString(R.string.TIPOEMPRENDEDOR))){
                        openActivityEmprendedor();
                    }else if(returnedUserType.equals(getResources().getString(R.string.TIPOINVERSOR)) ){
                        openActivityInversor();
                    }
                }else {
                    Toast.makeText(this,"current user = null",Toast.LENGTH_LONG).show();
                }
            }
            if(resultCode == RESULT_CANCELED){
               Toast.makeText(this,"nada seleccionado",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void openActivityEmprendedor(){
        Intent intentAuthentication = new Intent(this, ActivityEmprendedor.class);
        startActivity(intentAuthentication);
    }
    public void openActivityInversor(){
        Intent intentAuthentication = new Intent(this, ActivityInversor.class);
        startActivity(intentAuthentication);
    }
}
