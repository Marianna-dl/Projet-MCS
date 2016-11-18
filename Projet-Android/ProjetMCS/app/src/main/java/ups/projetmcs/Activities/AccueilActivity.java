package ups.projetmcs.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;

import ups.projetmcs.R;

/**
 * Created by utilisateur on 16/03/2016.
 */

public class AccueilActivity extends Activity {

    Button btnValide;
    public static final String CORPUS_BRUITE = Environment.getExternalStorageDirectory()+"/corpus/dronevolant_bruite";
    public static final String CORPUS_NON_BRUITE = Environment.getExternalStorageDirectory()+"/corpus/dronevolant_nonbruite";
    private MediaPlayer mPlayerJumpyBienvenue;
    private boolean isPlayingSound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_layout);
        btnValide = (Button) findViewById(R.id.btnValide);

        TextView tv =(TextView) findViewById(R.id.title);
        Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Classic_Robot.otf");
        tv.setTypeface(typeFace);

        ImageView image = (ImageView) findViewById(R.id.imageView);
        image.setImageDrawable(createMarkerIcon(getResources().getDrawable(R.drawable.bulle1), "Bienvenue !", 120, 120));

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

        btnValide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                stopJumpyBienvenue();
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
                int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
                if (checkedRadioButtonId == R.id.radioAvance) {
                    Intent intent = new Intent(AccueilActivity.this, AvanceActivity.class);
                    startActivity(intent);
                } else if (checkedRadioButtonId == R.id.radioGauche) {
                    Intent intent = new Intent(AccueilActivity.this, GaucheActivity.class);
                    startActivity(intent);
                } else if (checkedRadioButtonId == R.id.radioTourneGauche) {
                    Intent intent = new Intent(AccueilActivity.this, TourneGaucheActivity.class);
                    startActivity(intent);
                } else if (checkedRadioButtonId == R.id.radioRecule) {
                    Intent intent = new Intent(AccueilActivity.this, ReculeActivity.class);
                    startActivity(intent);
                } else if (checkedRadioButtonId == R.id.radioUrgence) {
                    Intent intent = new Intent(AccueilActivity.this, EtatUrgenceActivity.class);
                    startActivity(intent);
                } else if (checkedRadioButtonId == R.id.radioFlip) {
                    Intent intent = new Intent(AccueilActivity.this, FlipActivity.class);
                    startActivity(intent);
                } else if (checkedRadioButtonId == R.id.radioArreteToi) {
                    Intent intent = new Intent(AccueilActivity.this, ArreteToiActivity.class);
                    startActivity(intent);
                } else if (checkedRadioButtonId == R.id.radioDroite) {
                    Intent intent = new Intent(AccueilActivity.this, DroiteActivity.class);
                    startActivity(intent);
              } else if (checkedRadioButtonId == R.id.radioTourneDroite) {
                Intent intent = new Intent(AccueilActivity.this, TourneDroiteActivity.class);
                startActivity(intent);
              }
            }
        });

        ImageView jumpingSumoImage = (ImageView) findViewById(R.id.imageJumpingSumo);
        jumpingSumoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPlayingSound) {
                    playJumpyBienvenue();
                } else {
                    stopJumpyBienvenue();
                }
            }
        });
    }

    private Drawable createMarkerIcon(Drawable backgroundImage, String text,
                                      int width, int height) {

        Bitmap canvasBitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        // Create a canvas, that will draw on to canvasBitmap.
        Canvas imageCanvas = new Canvas(canvasBitmap);

        // Set up the paint for use with our Canvas
        Paint imagePaint = new Paint();
        imagePaint.setTextAlign(Paint.Align.CENTER);
        imagePaint.setTextSize(16f);

        // Draw the image to our canvas
        backgroundImage.draw(imageCanvas);

        // Draw the text on top of our image
        imageCanvas.drawText(text, width / 2, height / 2, imagePaint);

        // Combine background and text to a LayerDrawable
        LayerDrawable layerDrawable = new LayerDrawable(
                new Drawable[]{backgroundImage, new BitmapDrawable(canvasBitmap)});
        return layerDrawable;
    }

    private void playJumpyBienvenue() {
        if(mPlayerJumpyBienvenue != null) {
            mPlayerJumpyBienvenue.stop();
            mPlayerJumpyBienvenue.release();
        }
        isPlayingSound = true;
        mPlayerJumpyBienvenue = MediaPlayer.create(AccueilActivity.this, R.raw.r2d2_bienvenue);
        mPlayerJumpyBienvenue.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                isPlayingSound = false;
            }
        });
        mPlayerJumpyBienvenue.start();
    }

    private void stopJumpyBienvenue() {
        if (mPlayerJumpyBienvenue != null) {
            mPlayerJumpyBienvenue.release();
            mPlayerJumpyBienvenue = null;
            isPlayingSound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopJumpyBienvenue();
    }
}

