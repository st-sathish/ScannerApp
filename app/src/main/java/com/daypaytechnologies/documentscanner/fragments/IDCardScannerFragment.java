package com.daypaytechnologies.documentscanner.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daypaytechnologies.documentscanner.R;
import com.daypaytechnologies.documentscanner.camera.CameraController;
import com.daypaytechnologies.documentscanner.camera.CameraType;

public class IDCardScannerFragment extends AbstractScannerFragment {

    TextureView textureView;
    CameraController cameraController;
    FrameLayout cameraLayout;

    public static IDCardScannerFragment newInstance(String aTitle) {
        IDCardScannerFragment cameraFragment = new IDCardScannerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", aTitle);
        cameraFragment.setArguments(bundle);
        return cameraFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_id_card_scanner, container, false);
        textureView = view.findViewById(R.id.texture_view);
        cameraController = new CameraController(getActivity(), textureView);
        cameraLayout = view.findViewById(R.id.camera_layout);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPermissionGranted() {
        Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_LONG).show();
        openCamera();
    }

    private void openCamera() {
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams)cameraLayout.getLayoutParams();
//        layoutParams.setMargins(20, 0, 20, 0);
//        layoutParams.height = 600;
//        cameraLayout.setLayoutParams(layoutParams);
//        cameraLayout.setBackgroundResource(R.drawable.round_cornor);
        cameraController.startCamera();
    }
}
