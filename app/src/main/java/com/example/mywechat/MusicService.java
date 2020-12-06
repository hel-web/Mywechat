package com.example.mywechat;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MusicService extends Service
{
    MyReceiver serviceReceiver;
    AssetManager am;
    String[] musics = new String[] { "legend.mp3", "prom.mp3",
            "beau.mp3" };
    MediaPlayer mPlayer;
    // 当前的状态，0代表没有播放；1代表正在播放；
    int status = 0;
    // 记录当前正在播放的音乐
    int current = 0;
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        am = getAssets();

        // 创建BroadcastReceiver
        serviceReceiver = new MyReceiver();
        // 创建IntentFilter
        IntentFilter filter = new IntentFilter();
        filter.addAction(settingFragment.CTL_ACTION);
        registerReceiver(serviceReceiver, filter);


        // 创建MediaPlayer
        mPlayer = new MediaPlayer();
        // 为MediaPlayer播放完成事件绑定监听器
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() // ①
        {
            @Override
            public void onCompletion(MediaPlayer mp)
            {
                current++;
                if (current >= 3)
                {
                    current = 0;
                }
                //发送广播通知Activity更改文本框
                Intent sendIntent = new Intent(settingFragment.UPDATE_ACTION);
                sendIntent.putExtra("current", current);
                // 发送广播，将被Activity组件中的BroadcastReceiver接收到
                sendBroadcast(sendIntent);
                // 准备并播放音乐
                prepareAndPlay(musics[current]);
            }
        });
    }

    public class MyReceiver extends BroadcastReceiver
    {
        @Override
        public void onReceive(final Context context, Intent intent)
        {
            int control = intent.getIntExtra("control", -1);
            System.out.println(control);
            switch (control)
            {
                // 播放或暂停
                case 0:
                    // 原来处于没有播放状态
                    if (current == 0)
                    {
                        // 准备并播放音乐
                        prepareAndPlay(musics[current]);
                        status = 1;
                    }
                    // 原来处于播放状态
                    else {
                        // 暂停
                        current=current-1;
                        prepareAndPlay(musics[current]);
                        status = 1;
                    }
                    break;
                // 停止声音
                case 1:
                    // 如果原来正在播放或暂停
                    // 停止播放
                    mPlayer.pause();
                    status = 0;
                    break;
                case 2:
                    if (current >=2)
                    {
                        current=0;

                        prepareAndPlay(musics[current]);
                        status = 1;
                    }
                    // 原来处于播放状态
                    else {
                        // 暂停
                        current=current+1;
                        prepareAndPlay(musics[current]);
                        status = 1;
                    }
                    break;

            }
            // 广播通知Activity更改图标、文本框
            Intent sendIntent = new Intent(settingFragment.UPDATE_ACTION);
            sendIntent.putExtra("update", status);
            sendIntent.putExtra("current", current);
            // 发送广播，将被Activity组件中的BroadcastReceiver接收到
            sendBroadcast(sendIntent);
        }
    }

    private void prepareAndPlay(String music)
    {
        try
        {
            // 打开指定音乐文件
            AssetFileDescriptor afd = am.openFd(music);
            mPlayer.reset();
            // 使用MediaPlayer加载指定的声音文件。
            mPlayer.setDataSource(afd.getFileDescriptor(),
                    afd.getStartOffset(), afd.getLength());
            // 准备声音
            mPlayer.prepare();
            // 播放
            mPlayer.start();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
