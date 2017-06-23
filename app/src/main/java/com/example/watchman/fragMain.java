package com.example.watchman;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
//import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.app.Fragment;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.watchman.R.id.buttonMain;
import static com.example.watchman.R.id.textView_Saved_Name1;
import static com.example.watchman.R.id.textView_Saved_Num1;

public class fragMain extends android.support.v4.app.Fragment implements LocationListener {



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

        chckPermission();

        myLocationSOS();



        MessageBox(((MainActivity) getActivity()).ListAllCNums());    //debugging MsgBox
        //



// ======== WORKING


        View rootView = inflater.inflate(R.layout.fragment_frag_main, container, false);

        final View rootViewSettings = inflater.inflate(R.layout.fragment_frag_settings, container, false);

        //============================ DONT DELETE ============================
        // this button is the medical button
        button = (Button) rootView.findViewById(R.id.buttonMain);    // button

        // sndBtn Save button
        // dsblButton  // need save button

//        final Button disable = (Button) findViewById(R.id.dsblButton);       // dslbButton


//        // Reset when SOS after it has been disabled.
//        disable.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                count = 0;
//            }
//        });



        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                // count how many text has been sent and after 2 sent. After 2 message sent, no
                // longer need button.
                if (count < MAX_NUMBER__SMS_SENT) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        // increaseSize();

                        //postCenterToast("***** HOLDING BUTTON ****** .");

                    } else {

                 //       postCenterToast("S.O.S SENT!!!");

                        try {
                            //*****    NOTE:  THIS IS TO SEND SMS, future tech    **************

                            //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {

                            smsMgrTwo = SmsManager.getDefault();


                            //   }
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.DONUT) {

                                // http://maps.google.com/maps?q=24.197611,120.780512 show PIC DROP

                                // adding all coordinates and message to be sent as dataOfCoordNameConvertor

                                String testMsg = "help test";

                                dataOfCoordNameConvertor =  testMsg + "  " + "http://www.google.com/maps?q="+dataOfCoordNameConvertor;

                                String number = "5554";
                                smsMgrTwo.sendTextMessage(number, null, dataOfCoordNameConvertor, null, null);


                                count++;  // count how many sms sent. . MAX 1
                                //    if (count == MAX_NUMBER__SMS_SENT) {

//                                final Handler handler = new Handler();
//                                handler.postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        //Do something after after 3000 seconds
//                                        //  btnSOS.setEnabled(false);  // disabled button
//                                    }
//                                }, 3000);  // 2 seconds

                         //       postCenterToast("Button Disabled.");

                                //     }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                }// end 1st if statement ---> counting message sent


                else {
           //         postCenterToast("You have already sent TWO MESSAGES.");
                }







                return false;
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




//=============== GPS ============================

    //  1 of 2:implement GPS
    public void myLocationSOS() {

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        mapProvider = locationManager.getBestProvider(criteria, false);

        if (mapProvider != null && !mapProvider.equals("")) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            Location location = locationManager.getLastKnownLocation(mapProvider);
            locationManager.requestLocationUpdates(mapProvider, 15000, 1, this);

            // convert decimal to string format
            dataOfCoordNameConvertor = locationDecToString(location);

            //creating location in string to pass
            longCoor = location.getLongitude();
            lattCoor = location.getLatitude();

            if (location != null)
                onLocationChanged(location);
            else
               Toast.makeText(getActivity(), "Location not Found.", Toast.LENGTH_SHORT).show();
        }

    } // end of implement GPS



    // 2 of 2 implement GPS
    @Override
    public void onLocationChanged(Location location) {


//        TextView longitude = (TextView) findViewById(R.id.longView);
//        TextView latitude = (TextView) findViewById(R.id.latView);

//        longitude.setText("Current Longitude:\n" + location.getLongitude());
//        latitude.setText("Current Latitude:" + location.getLatitude());

        //  String sfd = longitude.toString();  // this ts to convert. .



        // send location to MSN

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }



    // Converts to GPS decimal to string format and returns a string
    public static String locationDecToString(final Location location) {
        return Location.convert(location.getLatitude(), Location.FORMAT_DEGREES) +
                "," + Location.convert(location.getLongitude(), Location.FORMAT_DEGREES);
    }











}// end



