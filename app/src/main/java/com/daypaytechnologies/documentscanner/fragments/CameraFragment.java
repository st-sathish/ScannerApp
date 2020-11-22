package com.daypaytechnologies.documentscanner.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.util.Rational;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.daypaytechnologies.documentscanner.R;
import com.daypaytechnologies.documentscanner.camera.CameraController;
import com.daypaytechnologies.documentscanner.camera.CameraType;

import java.io.File;

public class CameraFragment extends BaseFragment {

    private int REQUEST_CODE_PERMISSIONS = 101;
    private final String[] REQUIRED_PERMISSIONS = new String[]{"android.permission.CAMERA", "android.permission.WRITE_EXTERNAL_STORAGE"};
    TextureView textureView;
    ImageButton captureBtn;
    CameraController cameraController;
    FrameLayout cameraLayout;

    public static CameraFragment newInstance(String aTitle) {
        CameraFragment cameraFragment = new CameraFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", aTitle);
        cameraFragment.setArguments(bundle);
        return cameraFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cameraController = new CameraController(getActivity(), textureView);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_camera, container, false);
        textureView = view.findViewById(R.id.view_finder);
        cameraLayout = view.findViewById(R.id.camera_layout);
        captureBtn = view.findViewById(R.id.imgCapture);
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDocumentType();
//                File file = new File(Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".png");
//                cameraController.getImageCaptureInstance().takePicture(file, new ImageCapture.OnImageSavedListener() {
//                    @Override
//                    public void onImageSaved(@NonNull File file) {
//                        String msg = "Pic captured at " + file.getAbsolutePath();
//                        Toast.makeText(getActivity(), msg,Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onError(@NonNull ImageCapture.UseCaseError useCaseError, @NonNull String message, @Nullable Throwable cause) {
//                        String msg = "Pic capture failed : " + message;
//                        Toast.makeText(getActivity(), msg,Toast.LENGTH_LONG).show();
//                        if(cause != null){
//                            cause.printStackTrace();
//                        }
//                    }
//                });
            }
        });
        if(allPermissionsGranted()){
            chooseDocumentType();
        } else{
            ActivityCompat.requestPermissions(getActivity(), REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_PERMISSIONS){
            if(allPermissionsGranted()){
                chooseDocumentType();
            } else{
                Toast.makeText(getActivity(), "Permissions not granted by the user.", Toast.LENGTH_SHORT).show();
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

    private void chooseDocumentType() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.scanner_type_dialog, null);
        final RadioGroup radioGroup = dialogView.findViewById(R.id.radioGroup);
        builder.setView(dialogView);
        builder.setTitle("Select Document Type");
        builder.setCancelable(false);
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                RadioButton radioButton = getSelectedDocumentType(radioGroup);
                openCamera(radioButton.getTag().toString());
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void openCamera(String type) {
        switch (type){
            case CameraType.ID_CARD:
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)cameraLayout.getLayoutParams();
                layoutParams.setMargins(20, 0, 20, 0);
                layoutParams.height = 600;
                cameraLayout.setLayoutParams(layoutParams);
                cameraLayout.setBackgroundResource(R.drawable.round_cornor);
                break;
            case CameraType.OTHERS:
                cameraLayout.getLayoutParams().height = ViewGroup.LayoutParams.MATCH_PARENT;
                break;
        }
        cameraController.startCamera();
    }

    private RadioButton getSelectedDocumentType(RadioGroup radioGroup) {
       int count =  radioGroup.getChildCount();
       RadioButton radioButton = (RadioButton) radioGroup.getChildAt(0);
       for(int i =0;i < count;i++) {
           radioButton = (RadioButton) radioGroup.getChildAt(i);
           if(radioButton.isChecked()) {
               return radioButton;
           }
       }
       return radioButton;
    }

    @Override
    public void onDestroyView() {
        cameraController.destroy();
        super.onDestroyView();
    }
}
