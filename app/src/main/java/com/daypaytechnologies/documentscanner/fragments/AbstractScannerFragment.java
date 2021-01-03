package com.daypaytechnologies.documentscanner.fragments;

import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class AbstractScannerFragment extends BaseFragment {

    private static final int REQUEST_CODE_PERMISSIONS = 101;
    private static final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        checkPermission();
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if(allPermissionsGranted()){
                onPermissionGranted();
            } else {
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
            }
        } else {
            onPermissionGranted();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_PERMISSIONS){
            if(allPermissionsGranted()){
                onPermissionGranted();
            } else{
                Toast.makeText(getActivity(), "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
                ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
            }
        }
    }

    private boolean allPermissionsGranted(){
        for(String permission : REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(getActivity(), permission) != PackageManager.PERMISSION_GRANTED){
                return false;
            }
        }
        return true;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }


    protected File saveImage(byte[] bytes) {
        FileOutputStream outStream;
        try {
            File folder = new File(Environment.getExternalStorageDirectory()+File.separator+"DOCUMENT_SCANNER");
            if(!folder.exists()) {
                folder.mkdirs();
            }
            String fileName = "DC_" + System.currentTimeMillis() + ".jpg";
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            outStream = new FileOutputStream(file);
            outStream.write(bytes);
            outStream.close();
            Toast.makeText(getActivity(), "Picture Saved: " + fileName, Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), "saved path: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
            return file;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract void onPermissionGranted();
}
