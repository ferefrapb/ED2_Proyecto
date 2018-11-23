package com.example.carlos.ed2_proyecto;

import java.util.List;

public class Conversation {
    List<contacto> participantes;
    List<Message> mensajes;

    public Conversation(List<contacto> participantes, List<Message> mensajes) {
        this.participantes = participantes;
        this.mensajes = mensajes;
    }

    public List<contacto> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<contacto> participantes) {
        this.participantes = participantes;
    }

    public List<Message> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Message> mensajes) {
        this.mensajes = mensajes;
    }
}
