package com.example.watchman;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Fragment fragment;
    FragmentManager fm;
    FragmentTransaction ft;

    public String phonNum = null;
    public String contName = null;

    //attempt at file saving
    public ArrayList arrayName = new ArrayList();
    public ArrayList arrayPHone = new ArrayList();
    public ArrayList  arrayCNumID = new ArrayList();
    public ArrayList  arrayCNameID = new ArrayList();




    //end attempt at file saving

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_tracking:
                    SharedPreferences shf = getSharedPreferences("KEY_VALUE_WATCHMAN", MODE_PRIVATE);
                    String str = ""+shf.getInt("contactLength",0);
                    MessageBox(str);
                    fragment = new fragMain();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_place, fragment);
                    ft.commit();
                    return true;
                case R.id.navigation_lastroute:
                    fragment = new fragLastRoute();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_place, fragment);
                    ft.commit();                    return true;
                case R.id.navigation_settings:
                    fragment = new fragSettings();
                    fm = getSupportFragmentManager();
                    ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_place, fragment);
                    ft.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = new fragMain();
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.fragment_place, fragment);
        ft.commit();
        loadLocalFile();
//        selection();

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }// end OnCreate

    public String getPhonNum(int id){
        return arrayPHone.get(id).toString();
    }

    public void setPhonNum(String str){
        phonNum = str;
        arrayPHone.add(str);
    }


    // name input
    public String getContactName(int id){
            return arrayName.get(id).toString();
    }

    // update message to be sent
    public void setContactName(String name){
           contName = name;
           arrayName.add(name);
    }

    public int getContactAmt(){
        return arrayCNumID.size();
    }



    public void saveToLocalFile(){
        final String MY_PREFS_NAME = "KEY_VALUE_WATCHMAN"; // file name
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        if (arrayPHone.size()>0) {
            //editor.putInt("contactLength",0);
            for (int i = 0; i < arrayPHone.size(); i++) {
                if (i==arrayCNumID.size())
                    arrayCNumID.add(1);
                arrayCNumID.set(i,"contactNum" + i);
                editor.putString(arrayCNumID.get(i).toString(), arrayPHone.get(i).toString());
                if (i==arrayCNameID.size())
                    arrayCNameID.add(1);
                arrayCNameID.set(i,"contactName" + i);
                editor.putString(arrayCNameID.get(i).toString(), arrayName.get(i).toString());
            }

        }
        editor.putInt("contactLength", arrayCNumID.size());
        editor.commit();

    }

    public void loadLocalFile(){

        SharedPreferences shf = getSharedPreferences("KEY_VALUE_WATCHMAN", MODE_PRIVATE);
        File f = new File(
                "/data/data/com.example.watchman/shared_prefs/KEY_VALUE_WATCHMAN.xml");
        if (f.exists()){
            // TODO: if file exists, load contents
            int length = shf.getInt("contactLength",0);
            for(int i = 0; i < length; i++){
//                if (i==arrayCNumID.size())
//                    arrayCNumID.add(1);
                String contNumID = "contactNum"+i;
                arrayCNumID.add(contNumID);
                arrayPHone.add(shf.getString(contNumID,"No Number Found"));
//                if (i==arrayCNameID.size())
//                    arrayCNameID.add(1);
                String contNameID = "contactName"+i;
                arrayCNameID.add(contNameID);
                arrayName.add(shf.getString(contNameID,"No Name Found"));
            }

        }
    }

    public void clearfile(View view){
        final String MY_PREFS_NAME = "KEY_VALUE_WATCHMAN"; // file name
        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        Toast.makeText(this, "Cleared Successful!!",
                Toast.LENGTH_LONG).show();

        arrayCNameID.clear();
        arrayCNumID.clear();
        arrayPHone.clear();;
        arrayName.clear();


        editor.clear();
        editor.commit();
    }

    public String ListAllCNums(){
        String str = arrayPHone.toString();
        return str;
    }



    public void selection(){

//        SharedPreferences sharedPref = getPreferences(Context,MODE_PRIVATE);

        SharedPreferences shf = getSharedPreferences("KEY_VALUE_WATCHMAN", MODE_PRIVATE);
        String strPref = shf.getString(null, "test");

        if(strPref != null) {
            // do some thing
            Toast.makeText(this, "NOT empty!!",
                    Toast.LENGTH_LONG).show();

        }
        else{
            Toast.makeText(this, "ERROR!!",
                    Toast.LENGTH_LONG).show();

           // TextView tv = (TextView)getView().findViewById(textView_Saved_Num);
           // TextView checkArr =  (TextView) findViewById(textView);

          // checkArr.setText(shf.getString(String.valueOf(arrayCNameID.get(0)), ""));
        }




//        if(sharedPref.contains(NAME_KEY)) {
//            txtViewName.setText(sharedpreferences.getString(NAME_KEY, ""));
//        })
//




//        // whatever is displayed in etName ---->> stored in txtViewName
//        txtViewName = (TextView) findViewById(R.id.etName);
//        txtPhoneNum = (TextView) findViewById(R.id.etEmail);

        // NOT NECESSARY SINCE IT S ALREADY AT THE TOP
        // sharedpreferences = getSharedPreferences(MYPREFERENCE, Context.MODE_PRIVATE);


//
//        // CHECK IF IT IS NOT NULL.
//        if (sharedpreferences.contains(NAME_KEY)) {
//            txtViewName.setText(sharedpreferences.getString(NAME_KEY, ""));
//        }
//        if (sharedpreferences.contains(PHONE_KEY)) {
//            txtPhoneNum.setText(sharedpreferences.getString(PHONE_KEY, ""));
//        }










    }
    public void MessageBox(String message)
    {
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();  //debugging msgbox
    }


}

