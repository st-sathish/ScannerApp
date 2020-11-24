package com.daypaytechnologies.documentscanner;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import androidx.appcompat.app.AppCompatActivity;

public class BaseAppCompatActivity extends AppCompatActivity {

    private ProgressDialog mProgressDialog;

    public void showLoading() {
        hideLoading();
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.show();
        if (mProgressDialog.getWindow() != null) {
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mProgressDialog.setContentView(R.layout.progress_dialog);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setCanceledOnTouchOutside(false);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
}
