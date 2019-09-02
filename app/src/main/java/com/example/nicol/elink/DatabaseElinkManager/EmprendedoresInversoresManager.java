package com.example.nicol.elink.DatabaseElinkManager;
import com.example.nicol.elink.CallBacks.EmprendedorCallback;
import com.example.nicol.elink.CallBacks.InversorCallback;

public interface EmprendedoresInversoresManager {

    /**
     * Registra un nuevo emprendedor en la base de datos
     * @param email email del nuevo emprendedor
     */
    public void registerNewEmprendedor(String email);

    /**
     * Registra un nuevo inversor en la base de datos
     * @param email email del nuevo inversor
     */
    public void registerNewInvestor(String email);

    /**
     * Obtiene la instancia del emprendedor de la id correspondiente y lo devuelve en el callback
     * @param id id del emprendedor a obtener
     * @param emprendedorCallback callback que devuelve la instancia del emprendedor
     */
    public void getEmprendedor(String id, final EmprendedorCallback emprendedorCallback);
    /**
     * Obtiene la instancia del inversor de la id correspondiente y lo devuelve en el callback
     * @param id id del inversor a obtener
     * @param inversorCallback callback que devuelve la instancia del inversor
     */
    public void getInversor(String id, final InversorCallback inversorCallback);
}
