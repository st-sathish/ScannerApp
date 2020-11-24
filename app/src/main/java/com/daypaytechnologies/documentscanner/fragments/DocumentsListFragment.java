package com.daypaytechnologies.documentscanner.fragments;

import android.os.Bundle;
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

import java.util.List;

public class DocumentsListFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private DocumentStorageHelper documentStorageHelper;

    private DocumentListAdapter adapter;

    public static DocumentsListFragment newInstance(String aTitle) {
        DocumentsListFragment cameraFragment = new DocumentsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", aTitle);
        cameraFragment.setArguments(bundle);
        return cameraFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fr_document, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new DocumentListAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        documentStorageHelper = new DocumentStorageHelper(getActivity());
        List<FileVO> files = documentStorageHelper.findAllDocuments();
        adapter.refresh(files);
    }
}
