package ups.projetmcs.GComponents;

/**
 * Created by Marianna on 09/11/2016.
 */

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.IOException;

import ups.projetmcs.Activities.RecordActivity;
import ups.projetmcs.R;


/**
 * Created by Marianna on 07/11/2016.
 */
public class PlayButton extends Button {
    private static final String LOG_TAG = "PlayButton";
    boolean mStartPlaying = true;
    private MediaPlayer   mPlayer = null;
    private static String mFileName = RecordActivity.CORPUS_NON_BRUITE;
    RadioGroup groupCommands = null;
    final Context context;

    public PlayButton(Context context) {
        super(context);
        this.context = context;
        setText("Start playing");
        setOnClickListener(clicker);
        groupCommands = (RadioGroup) findViewById(R.id.groupCommands);
    }

    public PlayButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setText("Start playing");
        setOnClickListener(clicker);
        groupCommands = (RadioGroup) findViewById(R.id.groupCommands);
    }

    public PlayButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setText("Start playing");
        setOnClickListener(clicker);
        groupCommands = (RadioGroup) findViewById(R.id.groupCommands);
    }

    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onPlay(mStartPlaying);
            if (mStartPlaying) {
                setText("Stop playing");
            } else {
                setText("Start playing");
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
        mPlayer = new MediaPlayer();
        int idButtonChecked = groupCommands.getCheckedRadioButtonId();
        RadioButton radioChecked = (RadioButton) findViewById(idButtonChecked);
        String nameFile = radioChecked.getText().toString().trim();
        try {
            mPlayer.setDataSource(mFileName+"/"+nameFile+".wav");
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

    public MediaPlayer getmPlayer() {
        return mPlayer;
    }

    public void setmPlayer(MediaPlayer mPlayer) {
        this.mPlayer = mPlayer;
    }
}