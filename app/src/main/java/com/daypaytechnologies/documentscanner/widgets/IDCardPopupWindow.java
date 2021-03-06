package com.daypaytechnologies.documentscanner.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.daypaytechnologies.documentscanner.R;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class IDCardPopupWindow implements View.OnClickListener, PopupWindow.OnDismissListener {

    private OnPopupWindowTextClickListener mListener;

    private PopupWindow popupWindow;

    private Context mContext;

    private String selectedPosition;

    public IDCardPopupWindow(Context context, View view, OnPopupWindowTextClickListener listener) {
        this.mListener = listener;
        this.mContext = context;
        // inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        popupWindow = new PopupWindow(popupView, width, height, focusable);
        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        popupView.findViewById(R.id.front_side).setOnClickListener(this);
        popupView.findViewById(R.id.back_side).setOnClickListener(this);
        // dismiss the popup window when touched
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOnDismissListener(this);
    }

    @Override
    public void onClick(View view) {
        selectedPosition = (String)view.getTag();
        mListener.onIDCardPositionTextClicked(selectedPosition);
    }

    @Override
    public void onDismiss() {
        Toast.makeText(mContext, "Dismissed", Toast.LENGTH_LONG).show();
    }

    public void dismiss() {
        popupWindow.dismiss();
    }

    public interface OnPopupWindowTextClickListener {
        void onIDCardPositionTextClicked(String tag);
    }
}
