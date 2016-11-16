package ups.projetmcs.GComponents;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaRecorder;
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
public class RecordButton extends Button {
    boolean mStartRecording = true;
    private MediaRecorder mRecorder = null;
    private static final String LOG_TAG = "RecordButton";
    private static String corpusFolder;
    private String namefile;
    final Context context;

    public RecordButton(Context context) {
        super(context);
        this.context = context;
        setOnClickListener(clicker);
    }

    public RecordButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        setOnClickListener(clicker);
    }

    public RecordButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        setOnClickListener(clicker);
    }
    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onRecord(mStartRecording);
            if (mStartRecording) {
                setText("Arrêter l'enregistrement");
                setBackgroundResource(R.drawable.button_recordstate);
            } else {
                setText("Enregistrer l'instruction");
                setBackgroundResource(R.drawable.button_selector);
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
        Log.v(LOG_TAG, corpusFolder);

        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mRecorder.setOutputFile(corpusFolder+"/"+namefile);
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

    public void setCorpusFolder(String corpus){
        this.corpusFolder = corpus;
    }

    public void setNameFile(String nameFile){
        this.namefile = nameFile;
    }
}