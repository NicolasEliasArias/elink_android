package com.example.nicol.elink.DatabaseElinkManager;
import com.example.nicol.elink.CallBacks.EmprendedorCallback;
import com.example.nicol.elink.CallBacks.InversorCallback;

public interface EmprendedoresInversoresManager {
    public void registerNewEmprendedor(String email);
    public void registerNewInvestor(String email);
    public void getEmprendedor(String id, final EmprendedorCallback emprendedorCallback);
    public void getInversor(String id, final InversorCallback inversorCallback);
}
