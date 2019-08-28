package com.example.nicol.elink.FirebaseElinkManager;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.example.nicol.elink.Activitys.ActivityUser;
import com.example.nicol.elink.Factory.FactoryProyectoFinanciable;
import com.example.nicol.elink.FirebaseCallback;
import com.example.nicol.elink.Proyecto.ContenidoProyecto.ContenidoProyecto;
import com.example.nicol.elink.Proyecto.ProyectoFinanciable;
import com.example.nicol.elink.Usuario.Emprendedor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class FirebaseElinkManager {

    private static FirebaseElinkManager instance;
    private ActivityUser context;
    private FirebaseDatabase database;
    private long proyectsLastId;

    private FirebaseElinkManager(ActivityUser context){
        this.context = context;
        database = FirebaseDatabase.getInstance();
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

    public void getEmprendedor(){}
    public void getInversor(){}
    public void getAllProyects(){}

    public void saveProyect(final ProyectoFinanciable proyectoFinanciable){
        DatabaseReference refProyects = database.getReference("/proyectos/" + proyectoFinanciable.getId());
        DatabaseReference refEmprendedorProyects = database.getReference("users/emprendedores/" + proyectoFinanciable.getEmprendedorId() + "/proyectos/" + proyectoFinanciable.getId() );
        refProyects.setValue(proyectoFinanciable);
        refEmprendedorProyects.setValue(proyectoFinanciable);
    }

    public ProyectoFinanciable createNewProyect(final Emprendedor emprendedor ){
        long newProyectID = proyectsLastId + 1;
        DatabaseReference refProyects = database.getReference("/proyectos/" + newProyectID);
        DatabaseReference refEmprendedorProyects = database.getReference("users/emprendedores/" + emprendedor.getId() + "/proyectos/" + newProyectID );
        FactoryProyectoFinanciable factory = new FactoryProyectoFinanciable();
        ProyectoFinanciable newProyect = factory.crearProyectoFinanciable(newProyectID, emprendedor);
        refProyects.setValue(newProyect);
        refEmprendedorProyects.setValue(newProyect);
        return newProyect;
    }

    public void getEmprendedorProyects(final Emprendedor emprendedor,final FirebaseCallback firebaseCallback){
        context.setReference(FirebaseDatabase.getInstance().getReference("users/emprendedores/" + emprendedor.getId()+ "/proyectos"));
        context.getReference().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<ProyectoFinanciable> proyectos = new ArrayList<>();
                if(dataSnapshot.exists()){
                    Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                    for(DataSnapshot proyectSnapshot : children){
                        FactoryProyectoFinanciable factory = new FactoryProyectoFinanciable();
                        ProyectoFinanciable p = factory.crearProyectoFinanciable(Long.parseLong(proyectSnapshot.getKey()),emprendedor);
                        proyectos.add(p);
                        int financiacion = Integer.valueOf(proyectSnapshot.child("financiacionNecesaria").getValue(Integer.class));
                        p.setFinanciacionNecesaria(financiacion);
                        DataSnapshot contenidoProyectoSnapshot = proyectSnapshot.child("contenido");
                        String titulo = contenidoProyectoSnapshot.child("titulo").getValue(String.class);
                        String descripcion = contenidoProyectoSnapshot.child("descripcionBreve").getValue(String.class);
                        String cuerpo = contenidoProyectoSnapshot.child("cuerpo").getValue(String.class);
                        ContenidoProyecto contenido = new ContenidoProyecto(titulo,descripcion,cuerpo);
                        p.setContenido(contenido);
                    }

                }else{
                    Toast.makeText(context,"No hay proyectos",Toast.LENGTH_LONG).show();
                }
                firebaseCallback.onCallback(context, proyectos);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }

    public ActivityUser getContext() {
        return context;
    }
    public void setContext(ActivityUser context) {
        this.context = context;
    }

    public static FirebaseElinkManager getInstance(ActivityUser context){
        if(instance == null){
            return new FirebaseElinkManager(context);
        }
        else return instance;
    }

}
