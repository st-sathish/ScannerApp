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

    private static final String ROOT_DIRECTORY = "DocumentScanner";

    public DocumentStorageHelper(Activity activity) {
        this.mActivity = activity;
    }

    public File createNewFile() {
        File rootDir = getRootDirectoryPath();
        if(!rootDir.exists()) {
            boolean isCreated = rootDir.mkdir();
            if(!isCreated) {
                Toast.makeText(mActivity, "Couldn't create root directory", Toast.LENGTH_LONG).show();
                return new File(Environment.getExternalStorageDirectory() + "/" + System.currentTimeMillis() + ".png");
            }
        }
        return new File(rootDir + "/" + System.currentTimeMillis() + ".png");
    }

    public File getRootDirectoryPath() {
        return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + ROOT_DIRECTORY);
    }

    public List<FileVO> findAllDocuments() {
        File directory = getRootDirectoryPath();
        File[] list = directory.listFiles();

        List<FileVO> fileVOList = new ArrayList<>();
        if(list == null || list.length == 0) {
            return fileVOList;
        }
        for(File file: list) {
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            FileVO fileVO = new FileVO(bitmap);
            fileVO.setFile(file);
            fileVOList.add(fileVO);
        }
        return fileVOList;
    }
}
