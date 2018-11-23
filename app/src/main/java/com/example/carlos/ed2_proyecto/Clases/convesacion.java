package com.example.carlos.ed2_proyecto.Clases;

import com.example.carlos.ed2_proyecto.user;

import java.util.List;

public class convesacion{
    public convesacion(user usuario, String userReceptor, List<mensaje> mensajes)
    {
        User = usuario;
        UserReceptor = userReceptor;
        Mensajes = mensajes;
    }
    private user User;
    private String UserReceptor;
    private List<mensaje> Mensajes;

    public user getUser() {
        return User;
    }
    public void setUser(user user) { User = user; }

    public String getUserReceptor() {
        return UserReceptor;
    }
    public void setUserReceptor(String userReceptor) {
        UserReceptor = userReceptor;
    }

    public List<mensaje> getMensajes() {
        return Mensajes;
    }
    public void setMensajes(List<mensaje> mensajes) {
        Mensajes = mensajes;
    }
}
