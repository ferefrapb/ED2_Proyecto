package com.example.carlos.ed2_proyecto;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class mensajesfragment extends Fragment {
    RecyclerView myrview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            final View view = inflater.inflate(R.layout.fragment_mensajes, container, false);
        return inflater.inflate(R.layout.fragment_mensajes, container, false);
    }
}
