package com.yunqilai.consumer.luckyapp.Common.Presenter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.squareup.okhttp.Request;
import com.yunqilai.consumer.R;
import com.yunqilai.consumer.luckyapp.Common.RxBus;
import com.yunqilai.consumer.luckyapp.Common.Utils.OkHttpClientManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.camera.CameraManager;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.camera.PreviewFrameShotListener;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.camera.Size;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.decode.DecodeListener;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.decode.DecodeThread;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.decode.LuminanceSource;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.decode.PlanarYUVLuminanceSource;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.decode.RGBLuminanceSource;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.util.DocumentUtil;
import com.yunqilai.consumer.luckyapp.Common.Utils.RQCode.view.CaptureView;
import com.yunqilai.consumer.luckyapp.Common.Utils.ResponseCodeUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.SharedPreferencesUtils;
import com.yunqilai.consumer.luckyapp.Common.Utils.UrlUtils;
import com.yunqilai.consumer.luckyapp.HomePage.Model.BottleInfoBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ScanCodeBean;
import com.yunqilai.consumer.luckyapp.HomePage.Model.ScanCodeResultData;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.BottleInfoActivity;
import com.yunqilai.consumer.luckyapp.HomePage.Presenter.EditCodeActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CaptureActivity extends Activity implements SurfaceHolder.Callback ,PreviewFrameShotListener, DecodeListener,
        OnCheckedChangeListener, OnClickListener {

    private static final long VIBRATE_DURATION = 200L;
    private static final int REQUEST_CODE_ALBUM = 0;
    public static final String EXTRA_RESULT = "result";
    public static final String EXTRA_BITMAP = "bitmap";

    private SurfaceView previewSv;
    private CaptureView captureView;
    private CheckBox flashCb;
    private ImageButton backBtn;
    private Button albumBtn;

    BottleInfoBean bottleInfoBean = null ;

    private CameraManager mCameraManager;
    private DecodeThread mDecodeThread;
    private Rect previewFrameRect = null;
    private boolean isDecoding = false;

    private TextView tv_scan ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        previewSv = (SurfaceView) findViewById(R.id.sv_preview);
        captureView = (CaptureView) findViewById(R.id.cv_capture);
        flashCb = (CheckBox) findViewById(R.id.cb_capture_flash);
        flashCb.setOnCheckedChangeListener(this);
        flashCb.setEnabled(false);
        backBtn = (ImageButton) findViewById(R.id.btn_back);
        backBtn.setOnClickListener(this);
        albumBtn = (Button) findViewById(R.id.btn_album);
        albumBtn.setOnClickListener(this);

        tv_scan = (TextView) findViewById(R.id.activity_capture_tv);
        tv_scan.setOnClickListener(this);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            albumBtn.setVisibility(View.GONE);
        }
        previewSv.getHolder().addCallback(this);
        mCameraManager = new CameraManager(this);
        mCameraManager.setPreviewFrameShotListener(this);

    }

    /**
     * 利用正则表达式判断字符串是否是数字
     * @param str
     * @return
     */
    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mCameraManager.initCamera(holder);
        if (!mCameraManager.isCameraAvailable()) {
            Toast.makeText(CaptureActivity.this, R.string.capture_camera_failed, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        if (mCameraManager.isFlashlightAvailable()) {
            flashCb.setEnabled(true);
        }
        mCameraManager.startPreview();
        if (!isDecoding) {
            mCameraManager.requestPreviewFrameShot();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mCameraManager.stopPreview();
        if (mDecodeThread != null) {
            mDecodeThread.cancel();
        }
        mCameraManager.release();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPreviewFrame(byte[] data, Size dataSize) {
        if (mDecodeThread != null) {
            mDecodeThread.cancel();
        }
        if (previewFrameRect == null) {
            previewFrameRect = mCameraManager.getPreviewFrameRect(captureView.getFrameRect());
        }
        PlanarYUVLuminanceSource luminanceSource = new PlanarYUVLuminanceSource(data, dataSize, previewFrameRect);
        mDecodeThread = new DecodeThread(luminanceSource, CaptureActivity.this);
        isDecoding = true;
        mDecodeThread.execute();
    }

    @Override
    public void onDecodeSuccess(Result result, LuminanceSource source, Bitmap bitmap) {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(VIBRATE_DURATION);
        isDecoding = false;
        if(bitmap.getWidth()>100||bitmap.getHeight()>100){
            Matrix matrix = new Matrix();
            matrix.postScale(100f/bitmap.getWidth(),100f/bitmap.getHeight());
            Bitmap resizeBmp = Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            bitmap.recycle();
            bitmap = resizeBmp;
        }
//        Intent resultData = new Intent();
//        resultData.putExtra(EXTRA_RESULT, result.getText());
//        resultData.putExtra(EXTRA_BITMAP, bitmap);
//        setResult(RESULT_OK, resultData);
        getIsUserfulCode(result.getText());
//        finish();
    }

    private void getIsUserfulCode(String text) {
        String new_str = null ;
        if (text.length()>=10) {
            new_str = text.substring(text.length() - 10);
            if (isNumeric(new_str)) {
                final Gson gson = new Gson();
                String accesstoken = (String) SharedPreferencesUtils.getParam(CaptureActivity.this,SharedPreferencesUtils.access_token,"");
                String type = gson.toJson(new ScanCodeBean(accesstoken,new_str));
                OkHttpClientManager.postAsynJson(type, UrlUtils.SCAN_CODER_URL, new OkHttpClientManager.StringCallback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            ScanCodeResultData data = gson.fromJson(response,ScanCodeResultData.class);
                            if (data.getCode().equals(ResponseCodeUtils.OK)){
                                Intent intent = new Intent(CaptureActivity.this, BottleInfoActivity.class);
                                bottleInfoBean = data.getData();
                                startActivity(intent);
                            }else {
                                Toast.makeText(CaptureActivity.this,data.getMsg(),Toast.LENGTH_SHORT).show();
                            }
                        }catch (Exception e){

                        }
                    }
                });
            }
        }
        //Toast.makeText(CaptureActivity.this,text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDecodeFailed(LuminanceSource source) {
        if (source instanceof RGBLuminanceSource) {
            Toast.makeText(CaptureActivity.this, R.string.capture_decode_failed, Toast.LENGTH_SHORT).show();
        }
        isDecoding = false;
        mCameraManager.requestPreviewFrameShot();
    }

    @Override
    public void foundPossibleResultPoint(ResultPoint point) {
        captureView.addPossibleResultPoint(point);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            mCameraManager.enableFlashlight();
        } else {
            mCameraManager.disableFlashlight();
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_album:
                Intent intent = null;
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                    intent = new Intent(Intent.ACTION_GET_CONTENT);
                } else {
                    intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                }
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.putExtra("return-data", true);
                startActivityForResult(intent, REQUEST_CODE_ALBUM);
                break;

            case R.id.activity_capture_tv:
                 startActivity(new Intent(CaptureActivity.this, EditCodeActivity.class));
                finish();
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_ALBUM && resultCode == RESULT_OK && data != null) {
            Bitmap cameraBitmap = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String path = DocumentUtil.getPath(CaptureActivity.this, data.getData());
                cameraBitmap = DocumentUtil.getBitmap(path);
            } else {
                // Not supported in SDK lower that KitKat
            }
            if (cameraBitmap != null) {
                if (mDecodeThread != null) {
                    mDecodeThread.cancel();
                }
                int width = cameraBitmap.getWidth();
                int height = cameraBitmap.getHeight();
                int[] pixels = new int[width * height];
                cameraBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
                RGBLuminanceSource luminanceSource = new RGBLuminanceSource(pixels, new Size(width, height));
                mDecodeThread = new DecodeThread(luminanceSource, CaptureActivity.this);
                isDecoding = true;
                mDecodeThread.execute();
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (bottleInfoBean!=null) {
            EventBus.getDefault().post(bottleInfoBean);
        }
    }
}
