package com.example.user.gjsd.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

public class SplashActivity extends Activity {
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        callPermision();

        try{
            Thread.sleep(1000);
            if(!isPermission()){
                finish();
            }
            //marketExplorer 생성
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

//    private final int PERMISSIONS_ACCESS_FINE_LOCATION = 1000;
//    private final int PERMISSIONS_ACCESS_COARSE_LOCATION = 1001;
//    private boolean isAccessFineLocation = false;
//    private boolean isAccessCoarseLocation = false;
//    private boolean isPermission = false;
//
//    public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                                           int[] grantResults) {
//        if (requestCode == PERMISSIONS_ACCESS_FINE_LOCATION
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//
//            isAccessFineLocation = true;
//
//        } else if (requestCode == PERMISSIONS_ACCESS_COARSE_LOCATION
//                && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//
//            isAccessCoarseLocation = true;
//        }
//
//        if (isAccessFineLocation && isAccessCoarseLocation) {
//            isPermission = true;
//        }
//    }
//
//    private void callPermission() {
//        // Check the SDK version and whether the permission is already granted or not.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            requestPermissions(
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_ACCESS_FINE_LOCATION);
//
//        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
//                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED){
//
//            requestPermissions(
//                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
//                    PERMISSIONS_ACCESS_COARSE_LOCATION);
//        } else {
//            isPermission = true;
//        }
//    }

    public boolean isPermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return false;
        }else{
            return true;
        }
    }

    public void callPermision(){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
    }
}
