package com.daypaytechnologies.documentscanner.fragments;

import androidx.fragment.app.Fragment;

import com.daypaytechnologies.documentscanner.BaseAppCompatActivity;
import com.daypaytechnologies.documentscanner.LandingPageActivity;

public class BaseFragment extends Fragment {

    public void switchFragment(int fragment, String title, boolean addToBackStack) {
        LandingPageActivity landingPageActivity = (LandingPageActivity) getActivity();
        if(null != landingPageActivity) {
            landingPageActivity.displayView(fragment, title, addToBackStack);
        }
    }

    public void showLoading() {
        BaseAppCompatActivity landingPageActivity = (BaseAppCompatActivity) getActivity();
        if(null != landingPageActivity) {
            landingPageActivity.showLoading();
        }
    }

    public void hideLoading() {
        BaseAppCompatActivity landingPageActivity = (BaseAppCompatActivity) getActivity();
        if(null != landingPageActivity) {
            landingPageActivity.hideLoading();
        }
    }

    protected void goBack() {
        LandingPageActivity landingPageActivity = (LandingPageActivity) getActivity();
        if(landingPageActivity != null) {
            getActivity().onBackPressed();
        }
    }
}
