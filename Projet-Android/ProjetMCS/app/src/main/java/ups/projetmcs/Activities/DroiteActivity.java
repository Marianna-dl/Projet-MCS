package ups.projetmcs.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.File;

import ups.projetmcs.GComponents.PlayButton;
import ups.projetmcs.GComponents.RecordButton;
import ups.projetmcs.R;

public class DroiteActivity extends Activity {

    private static final String LOG_TAG = "DroiteActivity";
    private static final String NAME_FILE = "droite.wav";
    RecordButton mRecordButton = null;
    PlayButton mPlayButton = null;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        //A modifier avec la vue qui convient
        setContentView(R.layout.avance_layout);
        //---
        mRecordButton = (RecordButton) findViewById(R.id.btnRecord);
        mPlayButton = (PlayButton) findViewById(R.id.btnPlay);
        //A modifier avec la vue qui convient
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupAvance);
        //---

        mRecordButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
        mPlayButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
        mRecordButton.setNameFile(NAME_FILE);
        mPlayButton.setNameFile(NAME_FILE);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                RadioButton radioChecked = (RadioButton) findViewById(checkedId);
                switch (checkedId){
                    case R.id.radioBruite:
                        Log.d(LOG_TAG, "bruite");
                        mRecordButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
                        mPlayButton.setCorpusFolder(AccueilActivity.CORPUS_BRUITE);
                        break;
                    case R.id.radioNonBruite:
                        Log.d(LOG_TAG, "non bruite");
                        mRecordButton.setCorpusFolder(AccueilActivity.CORPUS_NON_BRUITE);
                        mPlayButton.setCorpusFolder(AccueilActivity.CORPUS_NON_BRUITE);
                        break;


                }

            }
        });

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
