package com.example.nicol.elink.Factory;
import com.example.nicol.elink.Usuario.Usuario;

public interface FactoryUser {
    public Usuario createUser();
    public Usuario createUser(String type, String name, String id);
}
