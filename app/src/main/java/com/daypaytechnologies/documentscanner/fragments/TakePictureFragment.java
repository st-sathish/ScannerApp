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

import java.util.List;

public class TakePictureFragment extends BaseFragment implements View.OnClickListener {

    IDCardScannerFragment idCardScanFragment;

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
        view.findViewById(R.id.ic_capture).setOnClickListener(this);
        chooseFragment();
        return view;
    }

    public void chooseFragment() {
        idCardScanFragment = IDCardScannerFragment.newInstance("");
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

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.ic_capture:
                idCardScanFragment.captureImage();
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getChildFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            if (fragment != null && fragment.isVisible()) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
            }
        }
    }
}
