package com.daypaytechnologies.documentscanner.fragments;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.daypaytechnologies.documentscanner.R;
import com.daypaytechnologies.documentscanner.widgets.IDCardPopupWindow;
import com.labo.kaji.relativepopupwindow.RelativePopupWindow;

import java.io.File;

import static android.content.Context.WINDOW_SERVICE;

public class IDCardScannerFragment extends AbstractScannerFragment implements SurfaceHolder.Callback, Camera.PictureCallback {

    private SurfaceView cameraView, transparentView;
    private SurfaceHolder holder, holderTransparent;
    private float RectLeft, RectTop,RectRight,RectBottom ;
    int  deviceHeight,deviceWidth;
    Camera camera;

    private static final int ID_CARD_WIDTH = 1016;
    private static final int ID_CARD_HEIGHT = 638;
    private IDCardPopupWindow popup;

    public static IDCardScannerFragment newInstance(String aTitle) {
        IDCardScannerFragment cameraFragment = new IDCardScannerFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", aTitle);
        cameraFragment.setArguments(bundle);
        return cameraFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mParentView = inflater.inflate(R.layout.fr_id_card_scanner, container, false);
        cameraView = mParentView.findViewById(R.id.camera_view);
        transparentView = mParentView.findViewById(R.id.transparent_view);
        return mParentView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpCameraView() {
        holder = cameraView.getHolder();
        holder.addCallback((SurfaceHolder.Callback) this);
        //holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        cameraView.setSecure(true);
    }

    private void openPopupWindow() {
        popup = new IDCardPopupWindow(mParentView.getContext());
        popup.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popup.showOnAnchor(mParentView, RelativePopupWindow.VerticalPosition.ALIGN_TOP, RelativePopupWindow.HorizontalPosition.RIGHT, true);
    }

    private void closePopupWindow() {
        popup.dismiss();
    }

    private void setUpTransparentView() {
        holderTransparent = transparentView.getHolder();
        holderTransparent.addCallback((SurfaceHolder.Callback) this);
        holderTransparent.setFormat(PixelFormat.TRANSLUCENT);
        transparentView.setZOrderMediaOverlay(true);
        deviceWidth = getScreenWidth();
        deviceHeight = getScreenHeight();
    }

    @Override
    public void onPermissionGranted() {
        Toast.makeText(getActivity(), "Permission granted", Toast.LENGTH_LONG).show();
        setUpCameraView();
        setUpTransparentView();
    }

    private void drawRectangleBox() {
        Canvas canvas = holderTransparent.lockCanvas(null);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
        RectLeft = 1;
        RectTop = 200 ;
        RectRight = RectLeft+ deviceWidth-100;
        RectBottom =RectTop+ 500;
        Rect rec=new Rect((int) RectLeft,(int)RectTop,(int)RectRight,(int)RectBottom);
        canvas.drawRect(rec,paint);
        holderTransparent.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        try {
            synchronized (holder) {
                drawRectangleBox(); //call a draw method
            }
            camera = Camera.open(); //open a camera
        }
        catch (Exception e) {
            return;
        }
        Camera.Parameters parameters = camera.getParameters();
        parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        //parameters.set("jpeg-quality", 70);
        //parameters.setPictureFormat(PixelFormat.JPEG);
        //parameters.setPictureSize(ID_CARD_WIDTH, ID_CARD_HEIGHT);
        Display display = ((WindowManager)getActivity().getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        if(display.getRotation() == Surface.ROTATION_0) {
            camera.setDisplayOrientation(90);
        }
        camera.setParameters(parameters);
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
            openPopupWindow();
        }
        catch (Exception e) {
            return;
        }
    }

    @Override

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        refreshCamera(); //call method for refress camera
    }

    public void refreshCamera() {
        if (holder.getSurface() == null) {
            return;
        }
        try {
            camera.stopPreview();
        }
        catch (Exception e) {
        }
        try {
            camera.setPreviewDisplay(holder);
            camera.startPreview();
        }
        catch (Exception e) {
        }
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(camera == null) {
            return;
        }
        camera.release(); //for release a camera
    }

    public void captureImage() {
        if (camera != null) {
            camera.takePicture(null, null, this);
        }
    }

    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        File file = saveImage(bytes);
        //refreshCamera();
        goBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
