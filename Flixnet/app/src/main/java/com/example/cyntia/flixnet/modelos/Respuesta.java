package com.example.cyntia.flixnet.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Respuesta {
    @Expose
    @SerializedName("error")
    private boolean error;
    @Expose
    @SerializedName("message")
    private String  message;
    @Expose
    @SerializedName("data")
    private Usuario data;
    @Expose
    @SerializedName("idUsuario")
    private String idUsuario ;
    @Expose
    @SerializedName("usuario")
    private String usuario ;
    @Expose
    @SerializedName("nombre")
    private String nombre ;
    @Expose
    @SerializedName("apellidos")
    private String apellidos ;
    @Expose
    @SerializedName("email")
    private String email ;
    @Expose
    @SerializedName("telefono")
    private String telefono ;
    @Expose
    @SerializedName("alias")
    private String alias ;

    public Respuesta(boolean error, String message, Usuario data) {
        this.error = error;
        this.message = message;
        this.data = data;
    }

    public Respuesta() {
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getData() {
        return data;
    }

    public void setData(Usuario data) {
        this.data = data;
    }

}
