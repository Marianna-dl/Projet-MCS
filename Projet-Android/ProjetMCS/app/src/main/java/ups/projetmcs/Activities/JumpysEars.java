package ups.projetmcs.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.TextView;

import ups.projetmcs.R;

public class JumpysEars extends Activity {

    private Intent intent;
    private long wait = 2500L;
    private MediaPlayer mPlayerJumpy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpys_ears);
        TextView tv =(TextView) findViewById(R.id.textJumpysEars);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Classic_Robot.otf");
        tv.setTypeface(typeFace);
        playJumpy();
        waitSecond();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (intent == null) {
            intent = new Intent(JumpysEars.this, AccueilActivity.class);
            startActivity(intent);
            stopJumpy();
            this.finish();
        }
        return super.onTouchEvent(event);
    }

    public void waitSecond() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
            if (intent == null) {
                intent = new Intent(JumpysEars.this, AccueilActivity.class);
                startActivity(intent);
                stopJumpy();
                JumpysEars.this.finish();
            }
            }
        }, wait);
    }

    private void playJumpy() {
        if(mPlayerJumpy != null) {
            mPlayerJumpy.stop();
            mPlayerJumpy.release();
        }
        mPlayerJumpy = MediaPlayer.create(JumpysEars.this, R.raw.r2d2_jumpy);
        mPlayerJumpy.start();
    }

    private void stopJumpy() {
        if (mPlayerJumpy != null) {
            mPlayerJumpy.release();
            mPlayerJumpy = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopJumpy();
    }
}
