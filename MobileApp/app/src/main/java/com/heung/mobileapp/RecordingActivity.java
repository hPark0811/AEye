package com.heung.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Camera;
import android.os.Bundle;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class RecordingActivity extends AppCompatActivity {
    private Camera camera;
    private CameraPreview camPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);
        getSupportActionBar().setTitle("Record");
        getSupportActionBar().show();

        if (camera != null) {
            camera.release();
        }
        camera = null;
        try {
            camera = Camera.open(); // attempt to get a Camera instance
            System.out.println("Got camera!");
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Camera.Parameters parameters = camera.getParameters();
        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

        if(display.getRotation() == Surface.ROTATION_0) {
            camera.setDisplayOrientation(90);
        }

        if(display.getRotation() == Surface.ROTATION_90) {
        }

        if(display.getRotation() == Surface.ROTATION_180) {
        }

        if(display.getRotation() == Surface.ROTATION_270) {
            camera.setDisplayOrientation(180);
        }
        camPreview = new CameraPreview(this, camera);
        FrameLayout preview = findViewById(R.id.cam_preview);
        preview.addView(camPreview);
    }
}
