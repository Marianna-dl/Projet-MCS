package ups.projetmcs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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
                Intent intent = new Intent(AccueilActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

    }

}

