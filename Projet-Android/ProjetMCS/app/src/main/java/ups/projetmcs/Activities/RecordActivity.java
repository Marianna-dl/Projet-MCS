package ups.projetmcs.Activities;

/**
 * Created by Marianna on 07/11/2016.
 */
/*
 * The application needs to have the permission to write to external storage
 * if the output file is written to the external storage, and also the
 * permission to record audio. These permissions must be set in the
 * application's AndroidManifest.xml file, with something like:
 *
 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 * <uses-permission android:name="android.permission.RECORD_AUDIO" />
 *
 */

    import android.app.Activity;
    import android.os.Bundle;
    import android.os.Environment;
    import android.widget.Button;
    import android.view.View;
    import android.content.Context;
    import android.util.Log;
    import android.media.MediaRecorder;
    import android.media.MediaPlayer;

    import java.io.File;
    import java.io.IOException;

    import ups.projetmcs.GComponents.PlayButton;
    import ups.projetmcs.GComponents.RecordButton;
    import ups.projetmcs.R;


public class RecordActivity extends Activity
{
    private static final String LOG_TAG = "RecordActivity";
    RecordButton mRecordButton = null;
    PlayButton mPlayButton = null;

    public static final String CORPUS_BRUITE = Environment.getExternalStorageDirectory()+"/corpus/dronevolant_bruite";
    public static final String CORPUS_NON_BRUITE = Environment.getExternalStorageDirectory()+"/corpus/dronevolant_nonbruite";




    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        String folder_main = "corpus";
        File f = new File(Environment.getExternalStorageDirectory(), folder_main);
        if (!f.exists()) {
            f.mkdirs();
        }

        f = new File(Environment.getExternalStorageDirectory()+"/"+ folder_main, "dronevolant_bruite");
        if (!f.exists()) {
            f.mkdirs();
        }

        f = new File(Environment.getExternalStorageDirectory()+"/"+ folder_main, "dronevolant_nonbruite");
        if (!f.exists()) {
            f.mkdirs();
        }
        setContentView(R.layout.record_layout);
        mRecordButton = (RecordButton) findViewById(R.id.btnRecord);
        mPlayButton = (PlayButton) findViewById(R.id.btnPlay);

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mRecordButton.getmRecorder() != null) {
            mRecordButton.getmRecorder().release();
            mRecordButton.setmRecorder(null);
        }
        if (mPlayButton.getmPlayer() != null) {
            mPlayButton.getmPlayer().release();
            mPlayButton.setmPlayer(null);
        }
    }
}