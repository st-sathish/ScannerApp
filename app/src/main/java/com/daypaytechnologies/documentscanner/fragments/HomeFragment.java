package com.daypaytechnologies.documentscanner.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daypaytechnologies.documentscanner.R;
import com.daypaytechnologies.documentscanner.adapters.FileListAdapter;

import java.io.File;

import static com.daypaytechnologies.documentscanner.LandingPageActivity.CAMERA_FRAGMENT;
import static com.daypaytechnologies.documentscanner.LandingPageActivity.TAKE_PICTURE_FRAGMENT;

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
        loadFiles();
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.ic_camera) {
            switchFragment(TAKE_PICTURE_FRAGMENT, "Camera", true);
        }
    }

    private void loadFiles() {
        File folder = new File(Environment.getExternalStorageDirectory()+File.separator+"DOCUMENT_SCANNER");
        File[] files = folder.listFiles();
        //declare adapter
        FileListAdapter adapter = new FileListAdapter();
        adapter.refresh(files);
    }
}
