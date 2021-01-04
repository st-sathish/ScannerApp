package com.daypaytechnologies.documentscanner.helpers;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.widget.Toast;

import com.daypaytechnologies.documentscanner.vo.FileVO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DocumentStorageHelper {

    private Activity mActivity;

    private static final String ROOT_DIRECTORY = "DOCUMENT_SCANNER";

    public DocumentStorageHelper(Activity activity) {
        this.mActivity = activity;
    }

    public File createNewFile() {
        File rootDir = getRootDirectoryPath();
        if(!rootDir.exists()) {
            boolean isCreated = rootDir.mkdir();
            if(!isCreated) {
                Toast.makeText(mActivity, "Couldn't create root directory", Toast.LENGTH_LONG).show();
                return new File(Environment.getExternalStorageDirectory() + File.pathSeparator + System.currentTimeMillis() + ".png");
            }
        }
        return new File(rootDir + File.pathSeparator + System.currentTimeMillis() + ".png");
    }

    public File getRootDirectoryPath() {
        return new File(Environment.getExternalStorageDirectory() + File.separator + ROOT_DIRECTORY);
    }

    public List<FileVO> findAllScannedDocuments() {
        File directory = getRootDirectoryPath();
        File[] list = directory.listFiles();

        List<FileVO> fileVOList = new ArrayList<>();
        if(list == null || list.length == 0) {
            return fileVOList;
        }
        for(File file: list) {
            FileVO fileVO = new FileVO();
            fileVO.setFile(file);
            fileVOList.add(fileVO);
        }
        return fileVOList;
    }
}
