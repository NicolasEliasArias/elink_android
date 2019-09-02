package com.example.nicol.elink.DatabaseElinkManager;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.nicol.elink.CallBacks.Callback;
import com.example.nicol.elink.CallBacks.EmprendedorCallback;
import com.example.nicol.elink.CallBacks.InversorCallback;
import com.example.nicol.elink.Factory.FactoryEmprendedor;
import com.example.nicol.elink.Factory.FactoryInversor;
import com.example.nicol.elink.Factory.FactoryUser;
import com.example.nicol.elink.R;
import com.example.nicol.elink.Usuario.Emprendedor;
import com.example.nicol.elink.Usuario.Inversor;
import com.example.nicol.elink.Usuario.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ElinkUserDatabaseManager implements AuthManager,EmprendedoresInversoresManager {

    private static ElinkUserDatabaseManager instance;
    private Activity context;
    private FirebaseDatabase database;

    private ElinkUserDatabaseManager(Activity context){
        this.context = context;
        database = FirebaseDatabase.getInstance();
    }


    @Override
    public void logIn(String email, String password,final Callback callback) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            callback.onCallback();
                        }else{
                            callback.onCallbackFailed(task.getException().getMessage());
                        }
                    }
                });
    }


    /**
     * Intenta iniciar sesion chequeando el tipo de usuario seleccionado, si el email corresponde con un usuario del tipo correcto en la base de datos entonces inicia sesion normalmente.
     * @param email email del usuario que quiere iniciar sesion
     * @param password contraseña del usuario que quiere iniciar sesion
     * @param callback metodos (objeto que lo contiene) a ejecutar una vez iniciada la sesion o finalizada por otro motivo
     * @param userType tipo del usuario que inicia sesion
     */
    public void logIn(String email, String password, final Callback callback, final String userType){
        logIn(email, password, new Callback() {//Logeamos con FirebaseAuth (metodo arriba de este)
            @Override
            public void onCallback() { //Pasamos este callback para que se ejecute ni bien termine de loguear con FirebaseAuth
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                if(userType.equals(getContext().getResources().getString(R.string.TIPOEMPRENDEDOR))){//Si se esta tratando de loguear como emprendedor ...
                    getEmprendedor(firebaseAuth.getCurrentUser().getUid(), new EmprendedorCallback() {
                        @Override
                        public void onCallback(Emprendedor emprendedor) {
                            if(emprendedor == null){
                                callback.onCallbackFailed(" No existe ese emprendedor");
                            }else{
                                callback.onCallback();
                            }
                        }
                        @Override
                        public void onCallbackFailed(String errorMessage) {
                            callback.onCallbackFailed(errorMessage);
                        }
                    });
                }else
                    if(userType.equals(getContext().getResources().getString(R.string.TIPOINVERSOR))){//Si se esta tratando de loguear como inversor ...
                        getInversor(firebaseAuth.getCurrentUser().getUid(), new InversorCallback() {
                            @Override
                            public void onCallback(Inversor inversor) {
                                if(inversor == null){
                                    callback.onCallbackFailed(" No existe ese inversor");
                                }else{
                                    callback.onCallback();
                                }
                            }
                            @Override
                            public void onCallbackFailed(String errorMessage) {
                                callback.onCallbackFailed(errorMessage);
                            }
                        });
                    }
            }
            @Override
            public void onCallbackFailed(String errorMessage) {
                callback.onCallbackFailed(errorMessage);
            }
        });

    }


    @Override
    public void registerUser(String email, String password,final Callback callback) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            callback.onCallback();
                        }else{
                            callback.onCallbackFailed(task.getException().getMessage());
                        }
                    }
                });
    }

    @Override
    public void registerNewEmprendedor(String email) {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String userid = "" + currentFirebaseUser.getUid();
        FactoryUser factory = new FactoryEmprendedor();
        Usuario emprendedorNuevo = factory.createUser(userid, "Mauro",email);
        DatabaseReference databaseRef = database.getReference("users/emprendedores/" + userid);
        databaseRef.setValue(emprendedorNuevo);
    }

    @Override
    public void registerNewInvestor(String email) {
        FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FactoryUser factory = new FactoryInversor();
        String userid = "" + currentFirebaseUser.getUid();
        Usuario inversorNuevo = factory.createUser(userid,"Pepe",email);
        DatabaseReference databaseRef = database.getReference("users/inversores/" + userid);
        databaseRef.setValue(inversorNuevo);
    }

    @Override
    public void getEmprendedor(String id, final EmprendedorCallback emprendedorCallback) {
        FactoryUser factory = new FactoryEmprendedor();
        final Emprendedor emprendedor = (Emprendedor) factory.createUser();//Se que no tiene mucho sentido pero por lo menos esondo la logica
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users/emprendedores/" + id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    emprendedorCallback.onCallback(null);
                }
                else{
                    String nombreUsuario = dataSnapshot.child("nombreUsuario").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String id = dataSnapshot.child("id").getValue(String.class);
                    emprendedor.setNombreUsuario(nombreUsuario);
                    emprendedor.setId(id);
                    emprendedor.setEmail(email);
                    emprendedorCallback.onCallback(emprendedor);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                emprendedorCallback.onCallback(null);
            }
        });
    }

    @Override
    public void getInversor(String id,final InversorCallback inversorCallback) {
        FactoryUser factory = new FactoryInversor();
        final Inversor inversor = (Inversor) factory.createUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users/inversores/" + id);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.getValue() == null){
                    inversorCallback.onCallback(null);
                }else{
                    String nombreUsuario = dataSnapshot.child("nombreUsuario").getValue(String.class);
                    String email = dataSnapshot.child("email").getValue(String.class);
                    String id = dataSnapshot.child("id").getValue(String.class);
                    int dinero = dataSnapshot.child("dinero").getValue(Integer.class);
                    inversor.setNombreUsuario(nombreUsuario);
                    inversor.setId(id);
                    inversor.setEmail(email);
                    inversor.setDinero(dinero);
                    inversorCallback.onCallback(inversor);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                inversorCallback.onCallback(null);
            }
        });
    }

    public Activity getContext() {
        return context;
    }
    public void setContext(Activity context) {
        this.context = context;
    }

    public static ElinkUserDatabaseManager getInstance(Activity context){
        if(instance == null){
            instance = new ElinkUserDatabaseManager(context);
        }
        else {
            instance.setContext(context);
        }
        return instance;
    }
}
