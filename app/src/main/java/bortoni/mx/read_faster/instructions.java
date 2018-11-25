package bortoni.mx.read_faster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class instructions extends AppCompatActivity {
///////////////////////////////////////////////////////////////////
//                       CLASS VARIABLES                         //
//---------------------------------------------------------------//
    public TextView readInstructions;
    private static final String TAG = "Instructions";

///////////////////////////////////////////////////////////////////
//                     ACTIVITY WORKFLOW                         //
//---------------------------------------------------------------//
    //--------on create--------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);
        Log.d(TAG, "onCreate: created instructions activity");

        //--------init global Variables--------//
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        initLayout();
        displayInstructions();

    }

    //--------on start--------//
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: started activity");
    }

    //--------on resume--------//
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: resumed activity");
    }

    //--------on pause--------//
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: activity paused");
    }

    //--------on stop--------//
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: activity stoped");
    }

    //--------on restart--------//
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: activity restarted");
    }

    //--------on destroy--------//
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: destroy activity");
    }


///////////////////////////////////////////////////////////////////
//                      OTHER FUNCTIONS                          //
//---------------------------------------------------------------//
    //--------init layout--------//
    private void initLayout() {
        readInstructions = (TextView) findViewById(R.id.read_me);
        readInstructions.setMovementMethod(new ScrollingMovementMethod());
    }

    //--------on display text--------//
    private void displayInstructions(){
        String text = "";
        StringBuffer sBuffer = new StringBuffer();
        InputStream is = this.getResources().openRawResource(R.raw.instructions);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if(is != null){
            try{
                while((text = reader.readLine()) != null){
                    sBuffer.append(text + "n");
                }
                readInstructions.setText(sBuffer);
                is.close();
            }catch (Exception e){e.printStackTrace();}
        }
    }

}