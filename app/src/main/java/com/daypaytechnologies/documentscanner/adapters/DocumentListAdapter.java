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
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DocumentListAdapter extends RecyclerView.Adapter<DocumentListAdapter.DocumentViewHolder> {

    private List<FileVO> data = new ArrayList<>();

    public DocumentListAdapter(){

    }

    public void refresh(List<FileVO> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DocumentListAdapter.DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View deviceView = inflater.inflate(R.layout.item_document, parent, false);
        return new DocumentListAdapter.DocumentViewHolder(deviceView);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentListAdapter.DocumentViewHolder holder, int position) {
        FileVO fileVO = data.get(position);
        File file = fileVO.getFile();
        Picasso.get().load(file).into(holder.document);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class DocumentViewHolder extends RecyclerView.ViewHolder  {
        private ImageView document;

        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);
            document = itemView.findViewById(R.id.item_document);
        }
    }
}
