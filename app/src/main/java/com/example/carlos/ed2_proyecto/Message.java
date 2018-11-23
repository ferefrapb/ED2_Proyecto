package com.example.carlos.ed2_proyecto;

public class Message {
    String emisor,receptor,mensaje,adjunto;

    public Message(String emisor, String receptor, String mensaje, String adjunto) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.mensaje = mensaje;
        this.adjunto = adjunto;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAdjunto() {
        return adjunto;
    }

    public void setAdjunto(String adjunto) {
        this.adjunto = adjunto;
    }
}
