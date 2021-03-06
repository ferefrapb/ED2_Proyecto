package com.example.carlos.ed2_proyecto;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    List<String> participantes;
    List<Message> mensajes;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    int key;

    public Conversation(List<String> participantes, List<Message> mensajes,int key) {
        this.participantes = participantes;
        this.mensajes = mensajes;
        this.key = key;
    }



    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public List<Message> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Message> mensajes) {
        this.mensajes = mensajes;
    }
}
