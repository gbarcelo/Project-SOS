package com.example.watchman;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.watchman.R.id.textView_Saved_Name1;
import static com.example.watchman.R.id.textView_Saved_Num1;


public class fragSettings extends android.support.v4.app.Fragment {




    public fragSettings() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment

        //final MainActivity mainA = (MainActivity)getActivity();  // get the Mainactivity
        //final String msg = mainA.contName;

        final View rootView = inflater.inflate(R.layout.fragment_frag_settings, container, false);

        Button btnSaveString = (Button) rootView.findViewById(R.id.buttonSave_Contact);        // this is SAVE button

        btnSaveString.setOnClickListener(new View.OnClickListener() {  // btnSnd


            @Override
            public void onClick(View view) {
                EditText edTxt_Input_Num = (EditText) getView().findViewById(R.id.editText_Input_Num);  // display
                EditText edTxt_Input_Name = (EditText) getView().findViewById(R.id.editText_Input_Name);  // display
                try {

                    int cntctAmt = ((MainActivity)getActivity()).getContactAmt();
                    if (cntctAmt<3) {
                        ((MainActivity) getActivity()).setPhonNum(edTxt_Input_Num.getText().toString()); //phonNum = edTxt_Input_Num.getText().toString();
                        ((MainActivity) getActivity()).setContactName(edTxt_Input_Name.getText().toString()); //contName = edTxt_Input_Name.getText().toString();
                    }
                    else{
                        MessageBox("Maximum Contacts Reached");
                    }
                    writeTextViews();

                    edTxt_Input_Num.setText("");
                    edTxt_Input_Name.setText("");

                    //	Toast.makeText(MainActivity.this, "SMS being drop", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "ERROR!!",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
                ((MainActivity) getActivity()).saveToLocalFile();
            }//end Onclick
        });  //nothing in between.


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        writeTextViews();
    }

    public void writeTextViews() {
        int id = ((MainActivity) getActivity()).arrayPHone.size() - 1;
        if (id < 0) {
            id = 0;
        } else {
            TextView tvName1 = (TextView)getView().findViewById(textView_Saved_Name1);
            TextView tvNum1 = (TextView)getView().findViewById(textView_Saved_Num1);
            tvNum1.setText(((MainActivity) getActivity()).getPhonNum(id));
            tvName1.setText(((MainActivity) getActivity()).getContactName(id));

        }
    }

    public void MessageBox(String message)
    {
        Toast.makeText(getActivity(),message, Toast.LENGTH_SHORT).show();  //debugging msgbox
    }


}
