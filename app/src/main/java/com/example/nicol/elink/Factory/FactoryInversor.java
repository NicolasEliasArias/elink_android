package com.example.nicol.elink.Factory;
import com.example.nicol.elink.Usuario.Inversor;
import com.example.nicol.elink.Usuario.Usuario;

public class FactoryInversor  implements FactoryUser{

    @Override
    public Usuario createUser() {
        return new Inversor();
    }

    @Override
    public Usuario createUser(String id, String name, String email){
        return new Inversor(id,name,email);
    }
}
