package com.example.watchman;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.watchman.R.id.buttonMain;

public class fragMain extends Fragment {

    final int MAX_NUMBER__SMS_SENT = 2;

    TextView textView;
    EditText editTextPhone;
    EditText editTextMessage;
    Button button;

    private String phonNum, msg;  // SMS, holds data
    SmsManager smsMgrTwo = null;  // SMS, used to pass data
    int count = 0;                // SMS, keeps track of message sent button and number putton pused.

    LocationManager locationManager;  // GPS
    String mapProvider;               // GPS

    Double longCoor = 0.0;            // GPS  -long  use to pass
    Double lattCoor = 0.0;            // Gps   Lat use to pass

    String dataOfCoordNameConvertor;  // data of coordinate and name to send via SMS




    public fragMain() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        //
        chckPermission();


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


    // GPS / SMS enables. .
    public void chckPermission() {

        //check permission if it is off, then it will prompt user to enable permission.
        // Checks Location and SMS permission at the same time.
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) +
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            // Allow access to GPS Location, location = 0b111,
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.SEND_SMS}, 123);

//            Log.d("This App", "Permission is not granted, requesting");  // SMS = 123.  123 for this High Tech
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
//            //  button.setEnabled(false);
        }
        else {
            Log.d("This App", "Permission is granted");
            // postCenterToast("Permission to send SMS");.
        }
    }




}// end



