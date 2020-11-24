package com.daypaytechnologies.documentscanner.vo;

import android.graphics.Bitmap;

import java.io.File;

public class FileVO {

    private File file;

    private Bitmap bitmap;

    public FileVO (Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
