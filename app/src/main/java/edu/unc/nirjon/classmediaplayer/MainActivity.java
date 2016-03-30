package edu.unc.nirjon.classmediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

    MediaPlayer mp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void pauseSong(View v) {
        Log.v("MP", "pause button clicked");
        if(mp != null) {
            mp.pause();
        }
    }

    public void playSong(View v) {

        Log.v("MP", "Play button clicked");
        //the link is not availabel anymore. Just an example. Also, you can store the audio file in
        //raw folder and using it by refering the R.rar.filename.
        String str = "http://wwwx.cs.unc.edu/~nirjon/test.mp3";
        try {
            if(mp != null) {
                mp.release();
                mp = null;
            }

            mp = new MediaPlayer();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(str);
            mp.setOnPreparedListener(this);
            mp.prepareAsync();
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected  void onStop() {
        super.onStop();
        //always remember to release the resource used by media player.
        if(mp != null) {
            mp.release();
            mp = null;
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Log.v("MP", "onPrepared called");
        if (mp != null) {
            mp.start();
        }
    }
}
