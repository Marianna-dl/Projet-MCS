package ups.projetmcs.GComponents;

import android.content.Context;
import android.media.AudioManager;
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
public class RecordButton extends Button {
    boolean mStartRecording = true;
    private MediaRecorder mRecorder = null;
    private static final String LOG_TAG = "RecordButton";
    private static String mFileName = RecordActivity.CORPUS_NON_BRUITE;
    RadioGroup groupCommands =  (RadioGroup) findViewById(R.id.groupCommands);
    final Context context;

    public RecordButton(Context context) {
        super(context);
        this.context = context;
        setText("Start recording");
        setOnClickListener(clicker);
        groupCommands = (RadioGroup) findViewById(R.id.groupCommands);
    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setText("Start recording");
        setOnClickListener(clicker);
        groupCommands = (RadioGroup) findViewById(R.id.groupCommands);
    }

    public RecordButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setText("Start recording");
        setOnClickListener(clicker);
        groupCommands = (RadioGroup) findViewById(R.id.groupCommands);
    }
    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onRecord(mStartRecording);
            if (mStartRecording) {
                setText("Stop recording");
            } else {
                setText("Start recording");
            }
            mStartRecording = !mStartRecording;
        }
    };

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        Log.v("FILE", mFileName);
        int idButtonChecked = groupCommands.getCheckedRadioButtonId();
        RadioButton radioChecked = (RadioButton) findViewById(idButtonChecked);
        String nameFile = radioChecked.getText().toString().trim();

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(mFileName+"/"+nameFile+".wav");
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }

    public MediaRecorder getmRecorder() {
        return mRecorder;
    }

    public void setmRecorder(MediaRecorder mRecorder) {
        this.mRecorder = mRecorder;
    }
}