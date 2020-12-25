package com.daypaytechnologies.documentscanner.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daypaytechnologies.documentscanner.R;

public class DocumentScannerFragment extends AbstractScannerFragment {

    public static DocumentScannerFragment newInstance(String aTitle) {
        DocumentScannerFragment cameraFragment = new DocumentScannerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", aTitle);
        cameraFragment.setArguments(bundle);
        return cameraFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_document_scanner, container, false);
        return view;
    }
}
