package com.yunqilai.delivery.ui.view;

import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.yunqilai.delivery.R;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 选择图片
 */
public class SelectImgDialog extends DialogFragment implements View.OnClickListener {
    private TextView takePhotoTV;
    private TextView albumTV;
    private Button btn_cancel;
    private OnImgSelect onImgSelect;

    private String imageFilePath;

    public interface OnImgSelect {

        void onImgSelected(Intent data);
        void onImgIntentNull(String path);

    }

    public void setOnImgSelect(OnImgSelect onImgSelect) {
        this.onImgSelect = onImgSelect;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View v = inflater.inflate(R.layout.dialog_select_img, container, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        initView(v);
        initEvent();
        return v;
    }

    private void initEvent() {
        takePhotoTV.setOnClickListener(this);
        albumTV.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    private void initView(View v) {
        takePhotoTV = (TextView) v.findViewById(R.id.tv_take_photo);
        albumTV = (TextView) v.findViewById(R.id.tv_album);
        btn_cancel = (Button) v.findViewById(R.id.btn_cancel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_take_photo:
                try {
                    //拍照我们用Action为MediaStore.ACTION_IMAGE_CAPTURE，
                    //有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
                    SimpleDateFormat timeStampFormat = new SimpleDateFormat(
                            "yyyy_MM_dd_HH_mm_ss");

                    imageFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures/" + timeStampFormat.format(new Date()) + ".jpg";
                    File temp = new File(imageFilePath);
                    Uri imageFileUri = Uri.fromFile(temp);//获取文件的Uri

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageFileUri);
                    startActivityForResult(intent, 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case R.id.tv_album:

                try {
                    //选择照片的时候也一样，我们用Action为Intent.ACTION_GET_CONTENT，
                    //有些人使用其他的Action但我发现在有些机子中会出问题，所以优先选择这个
                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
//                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 2);

                } catch (ActivityNotFoundException e) {
                }
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            default:
                break;


        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        if (onImgSelect != null) {
            //data.putExtra("pic",imageFilePath);
            if(null != data) {
                onImgSelect.onImgSelected(data);
            }else{
                onImgSelect.onImgIntentNull(imageFilePath);
            }
        }
        dismiss();
    }
}
