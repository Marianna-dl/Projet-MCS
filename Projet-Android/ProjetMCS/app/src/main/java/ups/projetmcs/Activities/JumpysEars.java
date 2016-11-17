package ups.projetmcs.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;

import ups.projetmcs.R;

public class JumpysEars extends Activity {

    private Intent intent;
    private long wait = 2000L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jumpys_ears);
        waitSecond();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (intent == null) {
            intent = new Intent(JumpysEars.this, AccueilActivity.class);
            startActivity(intent);
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
                    JumpysEars.this.finish();
                }
            }
        }, wait);
    }
}