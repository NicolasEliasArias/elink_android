package com.example.nicol.elink.Factory;
import com.example.nicol.elink.Usuario.Usuario;

public interface FactoryUser {
    /**
     * Crea un usuario
     * @return
     */
    public Usuario createUser();

    /**
     * Crea un usuario con los datos corresponedientes
     * @param id id del usuario
     * @param name nombre del usuario
     * @param email email del usuario
     * @return
     */
    public Usuario createUser(String id, String name, String email);
}
