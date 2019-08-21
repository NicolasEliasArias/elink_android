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

    public void setId(String id) {
        this.id = id;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getId() {
        return id;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public String getEmail() {return email; }
}

