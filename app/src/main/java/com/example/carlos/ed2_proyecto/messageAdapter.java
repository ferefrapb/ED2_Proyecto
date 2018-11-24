package com.example.carlos.ed2_proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carlos.ed2_proyecto.Algorithm.SDES;
import com.example.carlos.ed2_proyecto.Algorithm.ZigZag;

public class messageAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context mycontext;
    String usuarioLoged;
    Conversation conversation;

    public messageAdapter(Context mycontext, String usuarioLoged, Conversation conversation) {
        this.mycontext = mycontext;
        this.usuarioLoged = usuarioLoged;
        this.conversation = conversation;
        inflater =(LayoutInflater) mycontext.getSystemService(mycontext.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return conversation.getMensajes().size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View Viewemisor = inflater.inflate(R.layout.bubble_emisor,null);
        final View Viewreceptor = inflater.inflate(R.layout.bubble_receptor,null);
        ZigZag zz = new ZigZag();
        String message = "";

            String mensaje = conversation.mensajes.get(position).getMensaje();
        try {
            message = zz.Decryption(mensaje,conversation.key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        TextView emisor = Viewemisor.findViewById(R.id.emisortxt);
        TextView receptor = Viewreceptor.findViewById(R.id.receptortxt);

        if(usuarioLoged.equals(conversation.mensajes.get(position).getEmisor())){
            emisor.setText(message);
            return Viewemisor;
        }else{
            receptor.setText(message);
            return Viewreceptor;
        }
    }
}
