package com.daypaytechnologies.documentscanner.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.daypaytechnologies.documentscanner.R;

public class TakePictureFragment extends BaseFragment {

    public static TakePictureFragment newInstance(String aTitle) {
        TakePictureFragment cameraFragment = new TakePictureFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", aTitle);
        cameraFragment.setArguments(bundle);
        return cameraFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_take_picture, container, false);
        chooseFragment();
        return view;
    }

    public void chooseFragment() {
        Fragment idCardScanFragment = IDCardScannerFragment.newInstance("");
        switchFragment(idCardScanFragment, true);
    }

    public void switchFragment(Fragment fragment, boolean aAddtoBackstack) {
        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        String backStateName = ft.getClass().getName();
        ft.replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName());
        if (aAddtoBackstack)
            ft.addToBackStack(backStateName);
        ft.commit();
    }
}
