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

public class contactosAdapter extends  RecyclerView.Adapter<contactosAdapter.ContactosViewHolder> {
    private List<String> users;
    private OnItemClickListener mlistener;
    public interface OnItemClickListener{
        void onItemClicked(int position);
    }
    public void setOnclickListener(OnItemClickListener listener){
        mlistener = listener;
    }
private Context myContext;
    public contactosAdapter(Context context, List<String> users){
        this.users = users;
        myContext = context;
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflator = LayoutInflater.from(myContext);
        View view = inflator.inflate(R.layout.cviewcontact,null);
        ContactosViewHolder holder = new ContactosViewHolder(view,mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position) {
        String user = users.get(position);
        holder.contact.setText(user);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    public synchronized void refreshadapter(List<String> nusers){
        users.clear();
        users.addAll(nusers);
        notifyDataSetChanged();
    }

     class ContactosViewHolder extends RecyclerView.ViewHolder{
         TextView contact;
         ImageView img;

        public ContactosViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            contact =itemView.findViewById(R.id.txtView1);
            img = itemView.findViewById(R.id.imgview);
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
