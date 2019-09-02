package com.example.nicol.elink.DatabaseElinkManager;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.nicol.elink.CallBacks.Callback;
import com.example.nicol.elink.CallBacks.FirebaseCallback;
import com.example.nicol.elink.Factory.FactoryProyectoFinanciable;
import com.example.nicol.elink.Proyecto.ContenidoProyecto.Contenido;
import com.example.nicol.elink.Proyecto.ContenidoProyecto.ContenidoProyecto;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ElinkProjectsDatabaseManager implements  ProjectsDatabaseManager {

    private static ElinkProjectsDatabaseManager instance;
    private Activity context;
    private FirebaseDatabase database;
    private long proyectsLastId;

    public ElinkProjectsDatabaseManager(Activity context){
        this.context = context;
        database = FirebaseDatabase.getInstance();

        prepareProjectsIdCounter();
    }

    @Override
    public void getAllProyects(final FirebaseCallback firebaseCallback) {
        DatabaseReference reference =FirebaseDatabase.getInstance().getReference("/proyectos");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ProyectoFinanciable> proyectos = new ArrayList<>();
                if(dataSnapshot.exists()){
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for(DataSnapshot proyectSnapshot : children){
                        FactoryProyectoFinanciable factory = new FactoryProyectoFinanciable();
                        String idEmprendedor = proyectSnapshot.child("emprendedorId").getValue(String.class);
                        ProyectoFinanciable proyecto = factory.crearProyectoFinanciable(Long.parseLong(proyectSnapshot.getKey()),idEmprendedor);
                        proyectos.add(proyecto);
                        int financiacionNecesaria = Integer.valueOf(proyectSnapshot.child("financiacionNecesaria").getValue(Integer.class));
                        int financiacionActual = Integer.valueOf(proyectSnapshot.child("financiacionActual").getValue(Integer.class));
                        proyecto.setFinanciacionNecesaria(financiacionNecesaria);
                        proyecto.setFinanciacionActual(financiacionActual);
                        DataSnapshot contenidoProyectoSnapshot = proyectSnapshot.child("contenido");
                        String titulo = contenidoProyectoSnapshot.child("titulo").getValue(String.class);
                        String descripcion = contenidoProyectoSnapshot.child("descripcionBreve").getValue(String.class);
                        String cuerpo = contenidoProyectoSnapshot.child("cuerpo").getValue(String.class);
                        Contenido contenido = new ContenidoProyecto(titulo,descripcion,cuerpo);
                        proyecto.setContenido(contenido);
                    }

                }else{
                    Toast.makeText(context,"No hay proyectos",Toast.LENGTH_LONG).show();
                }
                firebaseCallback.onCallback(proyectos);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void saveProyect(ProyectoFinanciable proyectoFinanciable) {
        DatabaseReference refProyects = database.getReference("/proyectos/" + proyectoFinanciable.getId());
        DatabaseReference refEmprendedorProyects = database.getReference("users/emprendedores/" + proyectoFinanciable.getEmprendedorId() + "/proyectos/" + proyectoFinanciable.getId() );
        this.sendProyect(proyectoFinanciable,refEmprendedorProyects);
        this.sendProyect(proyectoFinanciable,refProyects);
    }

    private void sendProyect(ProyectoFinanciable proyectoFinanciable, DatabaseReference ref){
        ref.child("contenido").setValue(proyectoFinanciable.getContenido());//guardamos el contenido
        ref.child("emprendedorId").setValue(proyectoFinanciable.getEmprendedorId());//guardamos la id del emprendedor
        ref.child("id").setValue(proyectoFinanciable.getId());//guardamos la id del proyecto
        ref.child("financiacionActual").setValue(proyectoFinanciable.getFinanciacionActual());//guardamos la financiacion actual
        ref.child("financiacionNecesaria").setValue(proyectoFinanciable.getFinanciacionNecesaria());//guardamos la financiacion necesaria
    }

    @Override
    public ProyectoFinanciable createNewProyect(String emprendedor) {
        long newProyectID = proyectsLastId + 1;
        DatabaseReference refProyects = database.getReference("/proyectos/" + newProyectID);
        DatabaseReference refEmprendedorProyects = database.getReference("users/emprendedores/" + emprendedor + "/proyectos/" + newProyectID );
        FactoryProyectoFinanciable factory = new FactoryProyectoFinanciable();
        ProyectoFinanciable newProyect = factory.crearProyectoFinanciable(newProyectID, emprendedor);
        sendProyect(newProyect,refProyects);
        sendProyect(newProyect,refEmprendedorProyects);
        return newProyect;
    }

    @Override
    public void getEmprendedorProyects(final String emprendedor,final FirebaseCallback firebaseCallback) {
        DatabaseReference reference =FirebaseDatabase.getInstance().getReference("users/emprendedores/" + emprendedor+ "/proyectos");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ProyectoFinanciable> proyectos = new ArrayList<>();
                if(dataSnapshot.exists()){
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for(DataSnapshot proyectSnapshot : children){
                        FactoryProyectoFinanciable factory = new FactoryProyectoFinanciable();
                        ProyectoFinanciable proyecto = factory.crearProyectoFinanciable(Long.parseLong(proyectSnapshot.getKey()),emprendedor);
                        proyectos.add(proyecto);
                        int financiacionNecesaria = Integer.valueOf(proyectSnapshot.child("financiacionNecesaria").getValue(Integer.class));
                        int financiacionActual = Integer.valueOf(proyectSnapshot.child("financiacionActual").getValue(Integer.class));
                        proyecto.setFinanciacionNecesaria(financiacionNecesaria);
                        proyecto.setFinanciacionActual(financiacionActual);
                        DataSnapshot contenidoProyectoSnapshot = proyectSnapshot.child("contenido");
                        String titulo = contenidoProyectoSnapshot.child("titulo").getValue(String.class);
                        String descripcion = contenidoProyectoSnapshot.child("descripcionBreve").getValue(String.class);
                        String cuerpo = contenidoProyectoSnapshot.child("cuerpo").getValue(String.class);
                        Contenido contenido = new ContenidoProyecto(titulo,descripcion,cuerpo);
                        proyecto.setContenido(contenido);
                    }

                }else{
                    Toast.makeText(context,"No hay proyectos",Toast.LENGTH_SHORT).show();
                }
                firebaseCallback.onCallback(proyectos);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    private void prepareProjectsIdCounter(){
        proyectsLastId = 0;
        DatabaseReference refProyects = database.getReference().child("proyectos");
        refProyects.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    proyectsLastId = (dataSnapshot.getChildrenCount());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static ElinkProjectsDatabaseManager getInstance(Activity context){
        if(instance == null){
            instance = new ElinkProjectsDatabaseManager(context);
        }
        else {
            instance.setContext(context);
        }
        return instance;
    }

    public Activity getContext() {
        return context;
    }
    public void setContext(Activity context) {
        this.context = context;
    }
}
