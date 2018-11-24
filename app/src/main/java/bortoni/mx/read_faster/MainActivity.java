package bortoni.mx.read_faster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;

//public class MainActivity extends AppCompatActivity {
public class MainActivity extends Activity {
///////////////////////////////////////////////////////////////////
//                      MAIN ACTIVITY CLASS                      //
//---------------------------------------------------------------//
    //--------init class variables--------//
    private static final String TAG = "MainActivity";
    public static final String EXTRA_WORDSPERMIN = "bortoni.mx.read_faster.EXTRA_WORDSPERMIN";
    public static final String EXTRA_WORDSPERDIPS = "bortoni.mx.read_faster.EXTRA_WORDSPERDIPS";
    public bookFiles myBooks;

    //--------init layout context--------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: started");

    //--------init book content--------//
        File tmp = new File("");


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
        EditText wordsPerDisplay = (EditText) findViewById(R.id.words_per_display);
        int intWordsPerDisplay;
        int intWordsPerMinute;

        try{
            intWordsPerDisplay = Integer.parseInt(wordsPerDisplay.getText().toString());
        }catch(NumberFormatException e){
            intWordsPerDisplay = 1;
            Log.d(TAG, "openReadPane: default to value 1, no user input");
        }

        try{
            intWordsPerMinute = Integer.parseInt(wordsPerMinute.getText().toString());
        }catch(NumberFormatException e){
            intWordsPerMinute = 400;
            Log.d(TAG, "openReadPane: default to value 400, no user input");
        }

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

/*

    private void copyAssets() {
        AssetManager bookAssets = getApplicationContext().getAssets();
        String[] files = null;
        Log.i("tag", "copyAssets: started");
        try {
            files = bookAssets.list("");
        }
        catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) for (String filename : files) {
            InputStream in = null;
            OutputStream out = null;
            try {
                in = bookAssets.open(filename);
                //File outFile = new File(getExternalFilesDir(null), filename);
                File outFile = new File(getFilesDir(), filename);
                //File outFile = new File(bookDir.getAbsoluteFile(), filename);
                out = new FileOutputStream(outFile);
                copyFile(in, out);
                Log.i("tag", "success copy location " + outFile.getAbsolutePath());
            }
            catch(IOException e) {
                Log.e("tag", "Failed to copy asset file: " + filename, e);
            }
            finally {
                if (in != null) {
                    try {in.close(); Log.i("tag", "closed file in:" + filename);}
                    catch (IOException e) {Log.e("tag", "CANT CLOSE IN FILE");}
                }
                if (out != null) {
                    try {out.close(); Log.i("tag", "closed file out:" + filename);}
                    catch (IOException e) {Log.e("tag", "CANT CLOSE OUT FILE");}
                }
            }//finally
        }//if-for
    }
    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

*/



}
