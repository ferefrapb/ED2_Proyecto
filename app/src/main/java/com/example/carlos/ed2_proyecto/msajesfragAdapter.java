package com.example.carlos.ed2_proyecto;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class msajesfragAdapter extends RecyclerView.Adapter<msajesfragAdapter.MesajesFragViewHolder>{
    private OnItemClickListener mlistener;
    private List<String> users;

    public interface OnItemClickListener{
        void onItemClicked(int position);
    }
    public void setOnclickListener(OnItemClickListener listener){
        mlistener = listener;
    }
    private Context myContext;

    public msajesfragAdapter(Context context, List<String> users) {
        myContext = context;
        this.users = users;
    }
    @NonNull
    @Override
    public MesajesFragViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(myContext);
        View view = inflator.inflate(R.layout.cviewlist,null);
        MesajesFragViewHolder holder = new MesajesFragViewHolder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MesajesFragViewHolder holder, int position) {
        String user = users.get(position);
        holder.converseuser.setText(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    class MesajesFragViewHolder extends RecyclerView.ViewHolder{
        TextView converseuser;
        ImageView img;
        public MesajesFragViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            converseuser =itemView.findViewById(R.id.txtView1);
            img = itemView.findViewById(R.id.imgview3);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClicked(position);
                        }
                    }
                }
            });

        }
    }
}
