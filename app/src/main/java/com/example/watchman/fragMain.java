package com.example.watchman;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static com.example.watchman.R.id.buttonMain;

public class fragMain extends android.support.v4.app.Fragment {

    public fragMain() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_frag_main, container, false);
        Button mainb = rootView.findViewById(buttonMain);

        mainb.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                MessageBox("Hello World"); //nothing function
            }
        });
        return rootView;
    }

    public void MessageBox(String message)
    {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

}
