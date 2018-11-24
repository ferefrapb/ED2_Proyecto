package com.example.carlos.ed2_proyecto;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
    List<String> participantes;
    List<Message> mensajes;

    public Conversation(List<String> participantes, List<Message> mensajes) {
        this.participantes = participantes;
        this.mensajes = mensajes;
    }
    public Conversation(List<String> participantes) {
        this.participantes = participantes;
        this.mensajes = new ArrayList<>();
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
