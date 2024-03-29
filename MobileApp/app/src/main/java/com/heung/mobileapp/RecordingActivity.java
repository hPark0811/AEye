package com.heung.mobileapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.YuvImage;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.heung.mobileapp.service.APIManager;
import com.heung.mobileapp.service.SpeechRecognitionAssistance;
import com.heung.mobileapp.service.TextToSpeechAssistance;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class RecordingActivity extends AppCompatActivity {
    private Camera camera;
    private CameraPreview camPreview;
    private int x = 0;
    private String selected;
    private TextToSpeechAssistance myTTS;
    private SpeechRecognitionAssistance SRA;
    public static boolean isListening = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        getSupportActionBar().setTitle("Record");
        getSupportActionBar().show();

        selected = getIntent().getExtras().getString("selected");
        ((TextView)findViewById(R.id.search)).setText(selected);
        FrameLayout preview = findViewById(R.id.cam_preview);

        if (camera != null) {
            camera.release();
        }
        camera = null;
        Camera.PreviewCallback previewCallback = (data, camera) -> gatherFrames(data, camera);
        try {
            camera = Camera.open(); // attempt to get a Camera instance
            camera.setPreviewCallback(previewCallback);
            System.out.println("Got camera!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        camera.setDisplayOrientation(90);
        camPreview = new CameraPreview(this, camera);
        preview.addView(camPreview);
        myTTS = new TextToSpeechAssistance(this);
        SRA = new SpeechRecognitionAssistance(this);
    }

    public void promptUser(View view){
        try {
            if (!isListening){
                isListening = true;
                myTTS.speak("Seems like you're having trouble finding " + selected + ". Would you like to request help to your primary contacts?");
                myTTS.listenToResponseAfter(SRA);
            }
        } catch (Exception e){
            isListening = false;
        }

    }

    private void saveImage(Bitmap finalBitmap, String image_name) {
        String root = Environment.getExternalStorageDirectory().toString();
        File myDir = new File(root);
        myDir.mkdirs();
        String fname = "Image.jpeg";
        File file = new File(myDir, fname);
        if (file.exists()) file.delete();
        try {
            FileOutputStream out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
            APIManager.getVisualRecognitionData(file, selected);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gatherFrames(byte[] data, Camera camera){
        try {
            Parameters parameters = camera.getParameters();
            x++;
            if (parameters.getPreviewFormat() == ImageFormat.NV21 && x%30 == 0)
            {
                Camera.Size size = parameters.getPreviewSize();
                YuvImage yuvimage = new YuvImage(data, ImageFormat.NV21, size.width, size.height, null);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                yuvimage.compressToJpeg(new Rect(0, 0, size.width, size.height), 80, baos);
                byte[] jdata = baos.toByteArray();
                Bitmap bmp = BitmapFactory.decodeByteArray(jdata, 0, jdata.length);
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                saveImage(Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), matrix, true), "name");
                x = 0;
            }
        } catch (Exception Ignore) { }
    }
}
