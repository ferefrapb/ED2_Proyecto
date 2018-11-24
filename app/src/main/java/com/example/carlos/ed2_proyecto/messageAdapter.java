package com.example.carlos.ed2_proyecto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.carlos.ed2_proyecto.Algorithm.SDES;

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
        SDES sdes = new SDES();
        sdes.GenerateKeys(conversation.key);
            String mensaje = conversation.mensajes.get(position).getMensaje();
            StringBuilder builder = new StringBuilder();
            byte[] bytes = mensaje.getBytes();
            for(int i: bytes){
                builder.append(sdes.descipher(i));
            }
        TextView emisor = Viewemisor.findViewById(R.id.emisortxt);
        TextView receptor = Viewreceptor.findViewById(R.id.receptortxt);

        if(usuarioLoged.equals(conversation.mensajes.get(position).getEmisor())){
            emisor.setText(builder.toString());
            return Viewemisor;
        }else{
            receptor.setText(builder.toString());
            return Viewreceptor;
        }
    }
}
