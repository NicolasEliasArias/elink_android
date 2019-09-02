package com.example.nicol.elink.Usuario;

public abstract class Usuario {

    private String nombreUsuario;
    private String id;
    private String email;

    public Usuario(){

    }
    public Usuario(String id, String nombreUsuario,String email){
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    //Getters y Setters -------------------------------------------------------
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

