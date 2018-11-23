package com.example.carlos.ed2_proyecto.Clases;

public class mensaje {
    public mensaje(String userEnvia, String userRecibe, String mensaje, boolean hasAttach, String pathAttach, boolean peteneceaUsuario)
    {
        User_Envia = userEnvia;
        User_Recibe = userRecibe;
        Mensaje = mensaje;
        HasAttachment = hasAttach;
        PathAttachment = pathAttach;

    }

    private String User_Envia;
    private String User_Recibe;
    private String Mensaje;
    private boolean HasAttachment;
    private boolean PerteneceaUsuario;
    private String PathAttachment;

    public String getUserEnvia() {
        return User_Envia;
    }
    public void setUserEnvia(String userEnvia) {
        User_Envia = userEnvia;
    }

    public String getUserRecibe() {
        return User_Recibe;
    }
    public void setUserRecibe(String userRecibe) {
        User_Recibe = userRecibe;
    }

    public String getMensaje() {
        return Mensaje;
    }
    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public boolean getHasAttachment() {
        return HasAttachment;
    }
    public void setHasAttachment(boolean hasAttach) {
        HasAttachment = hasAttach;
    }

    public String getPathAttachment() {
        return PathAttachment;
    }
    public void setPathAttachment(String pathAttach) {
        PathAttachment = pathAttach;
    }

    public boolean EsNuestroMensaje() {
        return PerteneceaUsuario;
    }
}
