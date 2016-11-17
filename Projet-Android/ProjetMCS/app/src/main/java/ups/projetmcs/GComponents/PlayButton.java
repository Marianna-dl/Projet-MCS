package ups.projetmcs.GComponents;

/**
 * Created by Marianna on 09/11/2016.
 */

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;

import ups.projetmcs.Activities.AccueilActivity;
import ups.projetmcs.R;


/**
 * Created by Marianna on 07/11/2016.
 */
public class PlayButton extends Button {
    private static final String LOG_TAG = "PlayButton";
    boolean mStartPlaying = true;
    private MediaPlayer   mPlayer = null;
    RadioGroup groupCommands = null;
    final Context context;
    private static String corpusFolder;
    private String namefile;
    private  MediaPlayer mPlayerBeeDoSound = null;

    public PlayButton(Context context) {
        super(context);
        this.context = context;
        setOnClickListener(clicker);
    }

    public PlayButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOnClickListener(clicker);
    }

    public PlayButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setOnClickListener(clicker);
    }

    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onPlay(mStartPlaying);
            if (mStartPlaying) {
                setBackgroundResource(R.drawable.stop);
            } else {
                setBackgroundResource(R.drawable.play);
            }
            mStartPlaying = !mStartPlaying;
        }
    };

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
        } else {
            stopPlaying();
        }
    }

    private void startPlaying() {
        stopBeeDoSound();
        Log.v(LOG_TAG, corpusFolder);
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(corpusFolder+"/"+namefile);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }

    public void stopBeeDoSound() {
        if (mPlayerBeeDoSound != null) {
            mPlayerBeeDoSound.release();
            mPlayerBeeDoSound = null;
        }
    }

    public MediaPlayer getmPlayer() {
        return mPlayer;
    }

    public void setmPlayer(MediaPlayer mPlayer) {
        this.mPlayer = mPlayer;
    }

    public void setCorpusFolder(String corpus){
        this.corpusFolder = corpus;
    }

    public void setNameFile(String nameFile){
        this.namefile = nameFile;
    }

    public void setPlayBeeDoSound(MediaPlayer mPlayerBeeDooSound) { this.mPlayerBeeDoSound = mPlayerBeeDooSound;}
}