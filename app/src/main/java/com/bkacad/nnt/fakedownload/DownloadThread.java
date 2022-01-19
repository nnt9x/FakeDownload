package com.bkacad.nnt.fakedownload;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class DownloadThread extends Thread {

    private final Handler handler;
    private int process = 0;

    public DownloadThread(Handler handler) {
        this.handler = handler;
    }

    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void run() {
        super.run();

        while (true) {

            int tmp = getRandomNumber(1, 5);
            // Công nó vào process -> nếu mà process > 100 dừng
            process += tmp;
            if (process >= 100) {
                handler.sendEmptyMessage(0);
                break;
            }
            Message msg = handler.obtainMessage(1);
            Bundle bundle = new Bundle();
            bundle.putInt("number", process);
            msg.setData(bundle);
            handler.sendMessage(msg);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
