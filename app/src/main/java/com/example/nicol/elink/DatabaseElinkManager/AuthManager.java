package com.example.nicol.elink.DatabaseElinkManager;

import com.example.nicol.elink.CallBacks.Callback;

public interface AuthManager {

    /**
     *Intenta iniciar sesion con el email y la contraseña seleccionada
     * @param email email del usuario que quiere iniciar sesion
     * @param password contraseña del usuario que quiere iniciar sesion
     * @param callback metodos (objeto que lo contiene) a ejecutar una vez iniciada la sesion o finalizada por otro motivo
     */
    public void logIn(String email, String password, final Callback callback);

    /**
     * Registra al usuario con el email y la contraseña seleccionada
     * @param email
     * @param password
     * @param callback
     */
    public void registerUser(final String email, final String password, final Callback callback);
}
