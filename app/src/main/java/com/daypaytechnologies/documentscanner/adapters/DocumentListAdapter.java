package com.daypaytechnologies.documentscanner.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daypaytechnologies.documentscanner.R;
import com.daypaytechnologies.documentscanner.vo.FileVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.ScannedDocumentViewHolder> {

    private List<FileVO> data = new ArrayList<>();

    public DocumentListAdapter(){

    }

    public void refresh(List<FileVO> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DocumentListAdapter.ScannedDocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View deviceView = inflater.inflate(R.layout.item_document, parent, false);
        return new DocumentListAdapter.ScannedDocumentViewHolder(deviceView);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentListAdapter.ScannedDocumentViewHolder holder, int position) {
        FileVO fileVO = data.get(position);
        File file = fileVO.getFile();
        holder.document.setImageBitmap(fileVO.getBitmap());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ScannedDocumentViewHolder extends RecyclerView.ViewHolder  {
        private ImageView document;

        public ScannedDocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            document = itemView.findViewById(R.id.item_document);
        }
    }
}
