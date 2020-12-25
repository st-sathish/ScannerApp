package com.daypaytechnologies.documentscanner.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daypaytechnologies.documentscanner.R;

import static com.daypaytechnologies.documentscanner.LandingPageActivity.CAMERA_FRAGMENT;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public static HomeFragment newInstance(String aTitle) {
        HomeFragment cameraFragment = new HomeFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", aTitle);
        cameraFragment.setArguments(bundle);
        return cameraFragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_home, container, false);
        view.findViewById(R.id.ic_camera).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.ic_camera) {
            switchFragment(CAMERA_FRAGMENT, "Camera", true);
        }
    }
}
