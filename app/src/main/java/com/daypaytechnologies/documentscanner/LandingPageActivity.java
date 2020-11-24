package com.daypaytechnologies.documentscanner;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.daypaytechnologies.documentscanner.fragments.CameraFragment;
import com.daypaytechnologies.documentscanner.fragments.DocumentsListFragment;
import com.daypaytechnologies.documentscanner.fragments.HomeFragment;

public class LandingPageActivity extends BaseAppCompatActivity {

    public static final int HOME_FRAGMENT = 1;

    public static final int CAMERA_FRAGMENT = 2;

    public static final int DOCUMENT_FRAGMENT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_landing_page);
        displayView(2, "Home", true);
    }

    public void displayView(int fragmentNo, String aTitle, boolean addToBackstack) {
        Fragment fragment = null;
        switch (fragmentNo) {
            case CAMERA_FRAGMENT:
                fragment = CameraFragment.newInstance("Camera");
                break;
            case HOME_FRAGMENT:
                fragment = HomeFragment.newInstance("Home");
                break;
            case DOCUMENT_FRAGMENT:
                fragment = DocumentsListFragment.newInstance("Scanned Document");
                break;
            default:
                break;
        }
        switchFragment(fragment, addToBackstack);
    }

    public void switchFragment(Fragment fragment, boolean aAddtoBackstack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        String backStateName = ft.getClass().getName();
        ft.replace(R.id.frame, fragment, fragment.getClass().getSimpleName());
        if (aAddtoBackstack)
            ft.addToBackStack(backStateName);
        ft.commit();
    }
}