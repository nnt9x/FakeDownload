package com.bkacad.nnt.fakedownload;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageView imgPreview;
    private Button btnDownload;
    private ProgressBar pbDownload;
    private TextView tvDownload;
    private Handler handler;
    private DownloadThread downloadThread;

    private int process = 0;

    private void initUI(){
        imgPreview = findViewById(R.id.img_main_preview);
        btnDownload = findViewById(R.id.btn_main_download);
        pbDownload = findViewById(R.id.pb_main_download);
        tvDownload= findViewById(R.id.tv_process_download);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        handler = new Handler(getMainLooper()){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case 0:
                        Toast.makeText(MainActivity.this, "Download thành công", Toast.LENGTH_SHORT).show();
                        pbDownload.setProgress(100);
                        tvDownload.setText("100 %");
                        break;

                    case 1:
                        process = msg.getData().getInt("number");
                        tvDownload.setText(process+" %");
                        pbDownload.setProgress(process);
                        break;
                }
            }
        };

        downloadThread = new DownloadThread(handler);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadThread.start();
            }
        });
    }
}