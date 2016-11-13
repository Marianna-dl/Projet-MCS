package ups.projetmcs;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class EtatUrgenceActivity extends Activity {

    private MediaPlayer mPlayer = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.etat_urgence_layout);
        playSound(R.raw.bee_do);
    }

    private void playSound(int resId) {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(this, resId);
        mPlayer.start();
    }
}
