package bortoni.mx.read_faster;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends Activity {
///////////////////////////////////////////////////////////////////
//                       CLASS VARIABLES                         //
//---------------------------------------------------------------//

    private static final String TAG = "MainActivity";


///////////////////////////////////////////////////////////////////
//                     ACTIVITY WORKFLOW                         //
//---------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Started Main Activity");
        copyAssets();

     //--------init global Variables--------//
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();
        globalVars.setProgPc(5.0);
        globalVars.setSpeed(400);
        globalVars.setWordDisplay(2);

        initLayout();

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: started activity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: resumed activity");
        //TODO -- speed and display should not keep their val at activity level but with global

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: activity paused");

        //gather the information on speed and words per display by the user
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();
        EditText wordsPerMinute = (EditText) findViewById(R.id.words_per_minute);
        EditText wordsPerDisplay = (EditText) findViewById(R.id.words_per_display);

        try{
            globalVars.setWordDisplay(Integer.parseInt(wordsPerDisplay.getText().toString()));
        }catch(NumberFormatException e){
            globalVars.setWordDisplay(2);
            Log.d(TAG, "openReadPane: default to value 1, no user input");
        }

        try{
            globalVars.setSpeed(Integer.parseInt(wordsPerMinute.getText().toString()));
        }catch(NumberFormatException e){
            globalVars.setSpeed(400);
            Log.d(TAG, "openReadPane: default to value 400, no user input");
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: activity stoped");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: activity restarted");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: destroy activity");
    }

///////////////////////////////////////////////////////////////////
//                      OTHER FUNCTIONS                          //
//---------------------------------------------------------------//

    private void initLayout() {

        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

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

        //--------edit text filters
        EditText wordSpeed = findViewById(R.id.words_per_minute);
        wordSpeed.setFilters(new InputFilter[]{new InputFilterMinMax("1", "600")});

        EditText wordDisplay = findViewById(R.id.words_per_display);
        wordDisplay.setFilters(new InputFilter[]{new InputFilterMinMax("1", "4")});

        TextView currentRead = (TextView) findViewById(R.id.t_current_read);
        currentRead.setText(globalVars.getTargetBookName());
    }

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
        Intent intent = new Intent(this, readPane.class);
        startActivity(intent);
    }

    //TODO -- i think it is storingthe data in the right spot
    private void copyAssets() {
        AssetManager bookAssets = getApplicationContext().getAssets();
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();
        String[] files = null;
        File location = getDir(globalVars.getDirectoryName(),0);//getBookDir();
        String[] copiedFiles = null;
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
                File outFile = new File(location, filename);
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

        copiedFiles = location.list();
        globalVars.setBookDirectory(location);
        globalVars.setTargetBookName(copiedFiles[1]);

    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }

}
