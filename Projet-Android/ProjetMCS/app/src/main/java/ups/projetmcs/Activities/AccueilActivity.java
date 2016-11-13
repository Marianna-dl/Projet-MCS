package ups.projetmcs.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import ups.projetmcs.AvanceActivity;
import ups.projetmcs.EtatUrgenceActivity;
import ups.projetmcs.FlipActivity;
import ups.projetmcs.GaucheActivity;
import ups.projetmcs.R;
import ups.projetmcs.ReculeActivity;
import ups.projetmcs.TourneGaucheActivity;

/**
 * Created by utilisateur on 16/03/2016.
 */

public class AccueilActivity extends Activity {

    Button btnActivityRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accueil_layout);
        btnActivityRecord = (Button) findViewById(R.id.btnActivityRecord);

        btnActivityRecord.setOnClickListener(new View.OnClickListener() {

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

