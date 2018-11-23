package bortoni.mx.read_faster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {
///////////////////////////////////////////////////////////////////
//                      MAIN ACTIVITY CLASS                      //
//---------------------------------------------------------------//
    //--------init class variables--------//
    private static final String TAG = "MainActivity";
    public static final String EXTRA_WORDSPERMIN = "bortoni.mx.read_faster.EXTRA_WORDSPERMIN";
    public static final String EXTRA_WORDSPERDIPS = "bortoni.mx.read_faster.EXTRA_WORDSPERDIPS";

    //--------init layout context--------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

     //--------init layout context--------//
     //--------instructions
        Button instructions = (Button) findViewById(R.id.b_instr);
        instructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openInstructions();
            }
        });

     //--------my files
        Button myFiles = (Button) findViewById(R.id.b_files);
        myFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookFiles();
            }
        });

     //--------go read
        Button goRead = (Button) findViewById(R.id.b_read);
        goRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openReadPane();
            }
        });

     //--------filters
        initFilterSpeed();
        initFilterDisplay();

    }


///////////////////////////////////////////////////////////////////
//                      OTHER FUNCTIONS                          //
//---------------------------------------------------------------//

    //--------switch to activities--------//
    public void openInstructions(){
        Intent intent = new Intent(this, instructions.class);
        startActivity(intent);
    }

    public void openBookFiles(){
        Intent intent = new Intent(this, bookFiles.class);
        startActivity(intent);
    }

    public void openReadPane(){
        EditText wordsPerMinute = (EditText) findViewById(R.id.words_per_minute);
        int intWordsPerMinute = Integer.parseInt(wordsPerMinute.getText().toString());
        EditText wordsPerDisplay = (EditText) findViewById(R.id.words_per_display);
        int intWordsPerDisplay = Integer.parseInt(wordsPerDisplay.getText().toString());

        Intent intent = new Intent(this, readPane.class);
        intent.putExtra(EXTRA_WORDSPERMIN, intWordsPerMinute);
        intent.putExtra(EXTRA_WORDSPERDIPS, intWordsPerDisplay);
        startActivity(intent);
    }

    //--------extra functions--------//
    private void initFilterSpeed(){
        EditText wordSpeed = findViewById(R.id.words_per_minute);
        wordSpeed.setFilters(new InputFilter[]{new InputFilterMinMax("1", "600")});
    }
    private void initFilterDisplay(){
        EditText wordDisplay = findViewById(R.id.words_per_display);
        wordDisplay.setFilters(new InputFilter[]{new InputFilterMinMax("1", "4")});
    }
}
