package com.daypaytechnologies.documentscanner.camera.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

public class CameraTextureView extends TextureView {

    public CameraTextureView(Context context) {
        this(context, null);
    }

    public CameraTextureView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CameraTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}
