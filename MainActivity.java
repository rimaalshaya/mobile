package com.example.testass;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.content.ContextCompat;

public class MainActivity extends Activity {

    String TAG="0";
    private int Phone_State_Permission_Code = 1;

     TextView rima;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
     rima = (TextView) findViewById(R.id.info);
     Button myBtn = (Button) findViewById(R.id.button1);
     myBtn.setOnClickListener(new View.OnClickListener() {


     @Override
     public void onClick(View v) {
     if (ContextCompat.checkSelfPermission(MainActivity.this,//storage APIs are backed by device-protected storage.
     Manifest.permission.READ_PHONE_STATE) == //grant access to a wide cross section of sensitive data such as their device and SIM hardware IDs and the phone number of the incoming call.منح حق الوصول
             PackageManager.PERMISSION_GRANTED) {//information related to the application packages//If the app has the permission
     telephonyStuff();
     } else {
     requestReadPhonePermission();
     } }}); }



     private void requestReadPhonePermission() {//منح اذن الوصول لمعلومات الجهاز
     ActivityCompat.requestPermissions(this, new String[]
     {Manifest.permission.READ_PHONE_STATE},Phone_State_Permission_Code);//given the permission of read phone state
      }// move the things you do with the telephony in the callb



      public void onRequestPermissionsResult(int requestCode,
      String permissions[], int[] grantResults) {
      if(requestCode==Phone_State_Permission_Code){
      if(grantResults.length > 0 && grantResults[0] ==
      PackageManager.PERMISSION_GRANTED) {
      telephonyStuff();
       } }}


       public void telephonyStuff() {
       TelephonyManager tm=(TelephonyManager)
       getSystemService(Context.TELEPHONY_SERVICE);
       if(ActivityCompat.checkSelfPermission(this,Manifest.permission.READ_PHONE_STATE)
       !=PackageManager.PERMISSION_GRANTED){
       return; }




       String IMEInum="1: IMEI: "+tm.getDeviceId();



       String lineNum = "\n2: Line number: "+tm.getLine1Number();



        int callstate = tm.getCallState();
        String callstat = "";
        switch (callstate) {
            case TelephonyManager.CALL_STATE_IDLE:
                callstat = "4: Call State: Phone is idle\n";
                break;
            case TelephonyManager.CALL_STATE_OFFHOOK:
                callstat = "4: Call State: Phone is in use\n";
                break;
            case TelephonyManager.CALL_STATE_RINGING:
                callstat = "4: Call State: Phone is ringing\n";
                break;
        }



        int phoneType = tm.getPhoneType();
        String ptype = "";
        switch (phoneType) {
            case TelephonyManager.PHONE_TYPE_CDMA:
                ptype = "\n3: Phone Type: CDMA\n";
                break;
            case TelephonyManager.PHONE_TYPE_GSM:
                ptype = "\n3: Phone Type: GSM\n";
                break;
            case TelephonyManager.PHONE_TYPE_SIP:
                ptype = "\n3: Phone Type: SIP\n";
                break;
            case TelephonyManager.PHONE_TYPE_NONE:
                ptype = "\n3: Phone Type: NONE\n";
                break;
        }



        int sim=tm.getSimState();
        String sstate="";
        switch(sim)
        {
            case TelephonyManager.SIM_STATE_ABSENT:
                sstate="5: Sim State: Absent\n";
                break;
            case TelephonyManager.SIM_STATE_NETWORK_LOCKED:
                sstate="5: Sim State: Network Locked\n";
                break;
            case TelephonyManager.SIM_STATE_PIN_REQUIRED:
                sstate="5: Sim State: Pin Required\n";
                break;
            case TelephonyManager.SIM_STATE_PUK_REQUIRED:
                sstate="5: Sim State: Puk Required\n";
                break;
            case TelephonyManager.SIM_STATE_READY:
                sstate="5: Sim State: Ready\n";
                break;
            case TelephonyManager.SIM_STATE_UNKNOWN:
                sstate="5: Sim State: Unknown\n";
                break;
        }


        rima.setText(IMEInum+lineNum+ptype+callstat+sstate);
    }


}