package com.example.nicol.elink.Usuario;

import com.example.nicol.elink.BandejaNoticias.BandejaNoticias;

public class Inversor extends Usuario {

    private BandejaNoticias bandejaNoticias;
    private double dinero;

    public Inversor(){}
    public Inversor(String id, String nombreUsuario, String email) {
        super(id, nombreUsuario, email);
        bandejaNoticias = new BandejaNoticias();
    }

    public double depositar(double monto){
        this.dinero += monto;
        return this.dinero;
    }

    //Getters y Setters
    public void setDinero(double dinero) {
        this.dinero = dinero;
    }
    public double getDinero() {
        return dinero;
    }

}
