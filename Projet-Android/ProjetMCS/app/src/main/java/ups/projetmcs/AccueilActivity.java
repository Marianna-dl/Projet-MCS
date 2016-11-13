package ups.projetmcs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
                    setContentView(R.layout.avance_layout);
                } else if (checkedRadioButtonId == R.id.radioGauche) {
                    setContentView(R.layout.gauche_layout);
                } else if (checkedRadioButtonId == R.id.radioTourneGauche) {
                    setContentView(R.layout.tourne_gauche_layout);
                } else if (checkedRadioButtonId == R.id.radioRecule) {
                    setContentView(R.layout.recule_layout);
                } else if (checkedRadioButtonId == R.id.radioUrgence) {
                    setContentView(R.layout.etat_urgence_layout);
                } else if (checkedRadioButtonId == R.id.radioFlip) {
                    setContentView(R.layout.flip_layout);
                }
            }
        });
    }
}

