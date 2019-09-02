package com.example.nicol.elink.CallBacks;

import com.example.nicol.elink.Usuario.Inversor;

public interface InversorCallback {
    void onCallback(Inversor Inversor);
    void onCallbackFailed(String errorMessage);
}
