package com.daypaytechnologies.documentscanner.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daypaytechnologies.documentscanner.R;
import com.daypaytechnologies.documentscanner.vo.FileVO;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.FileViewHolder> {

    private File[] data;

    public FileListAdapter(){

    }

    public void refresh(File[] data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FileListAdapter.FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View deviceView = inflater.inflate(R.layout.item_file, parent, false);
        return new FileListAdapter.FileViewHolder(deviceView);
    }

    @Override
    public void onBindViewHolder(@NonNull FileListAdapter.FileViewHolder holder, int position) {
        File file = data[position];
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        holder.document.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder  {
        private ImageView document;

        public FileViewHolder(@NonNull View itemView) {
            super(itemView);
            document = itemView.findViewById(R.id.item_file);
        }
    }
}
