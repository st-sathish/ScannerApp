package com.daypaytechnologies.documentscanner.fragments;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daypaytechnologies.documentscanner.R;
import com.daypaytechnologies.documentscanner.adapters.DocumentListAdapter;
import com.daypaytechnologies.documentscanner.helpers.DocumentStorageHelper;
import com.daypaytechnologies.documentscanner.vo.FileVO;

import java.io.File;
import java.util.List;

import static com.daypaytechnologies.documentscanner.LandingPageActivity.CAMERA_FRAGMENT;
import static com.daypaytechnologies.documentscanner.LandingPageActivity.TAKE_PICTURE_FRAGMENT;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;

    private DocumentListAdapter documentListAdapter;

    private DocumentStorageHelper documentStorageHelper;

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
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        documentListAdapter = new DocumentListAdapter();
        recyclerView.setAdapter(documentListAdapter);
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
        documentStorageHelper = new DocumentStorageHelper(getActivity());
        List<FileVO> files = documentStorageHelper.findAllScannedDocuments();
        documentListAdapter.refresh(files);
    }
}
