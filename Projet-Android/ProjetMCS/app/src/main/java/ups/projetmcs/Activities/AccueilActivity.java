package ups.projetmcs.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import java.io.File;

import ups.projetmcs.R;

/**
 * Created by utilisateur on 16/03/2016.
 */

public class AccueilActivity extends Activity {

    Button btnValide;
    public static final String CORPUS_BRUITE = Environment.getExternalStorageDirectory()+"/corpus/dronevolant_bruite";
    public static final String CORPUS_NON_BRUITE = Environment.getExternalStorageDirectory()+"/corpus/dronevolant_nonbruite";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_layout);
        btnValide = (Button) findViewById(R.id.btnValide);

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
                }
            }
        });
    }
}

