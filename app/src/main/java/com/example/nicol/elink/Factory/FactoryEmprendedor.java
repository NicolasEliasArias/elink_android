package com.example.nicol.elink.Factory;
import com.example.nicol.elink.Usuario.Emprendedor;
import com.example.nicol.elink.Usuario.Usuario;

public class FactoryEmprendedor implements FactoryUser{

    @Override
    public Usuario createUser() {
        return new Emprendedor();
    }

    @Override
    public Usuario createUser(String id, String name, String email){
        return new Emprendedor(id,name,email);
    }
}
