package com.example.nicol.elink.DatabaseElinkManager;
import android.app.Activity;
import android.support.annotation.NonNull;
import com.example.nicol.elink.CallBacks.Callback;
import com.example.nicol.elink.CallBacks.EmprendedorCallback;
import com.example.nicol.elink.CallBacks.InversorCallback;
import com.example.nicol.elink.Factory.FactoryEmprendedor;
import com.example.nicol.elink.Factory.FactoryInversor;
import com.example.nicol.elink.Factory.FactoryUser;
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
                            callback.onCallbackFailed();
                        }
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
                            callback.onCallbackFailed();
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
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String nombreUsuario = dataSnapshot.child("nombreUsuario").getValue(String.class);
                String email = dataSnapshot.child("email").getValue(String.class);
                String id = dataSnapshot.child("id").getValue(String.class);
                emprendedor.setNombreUsuario(nombreUsuario);
                emprendedor.setId(id);
                emprendedor.setEmail(email);
                emprendedorCallback.onCallback(emprendedor);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void getInversor(String id,final InversorCallback inversorCallback) {
        FactoryUser factory = new FactoryInversor();
        final Inversor inversor = (Inversor) factory.createUser();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users/inversores/" + id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
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
