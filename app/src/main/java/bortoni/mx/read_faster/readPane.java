package bortoni.mx.read_faster;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.InputStream;

public class readPane extends AppCompatActivity {
///////////////////////////////////////////////////////////////////
//                       CLASS VARIABLES                         //
//---------------------------------------------------------------//
    private static final String TAG = "readPane";

    private CountDownTimer timer;
    private long msLeft = 2147483647; //miliseconds = 24 days
    private int msInc = 500; //miliseconds
    static int i = 0;


    InputStream is;// = this.getResources().openRawResource(R.raw.instructions);
    //BufferedReader reader;// = new BufferedReader(new InputStreamReader(is));

///////////////////////////////////////////////////////////////////
//                CONSTRUCT, SETS, AND GETS                      //
//---------------------------------------------------------------//
    public readPane(){

    }

///////////////////////////////////////////////////////////////////
//                     ACTIVITY WORKFLOW                         //
//---------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_pane);
        Log.d(TAG, "onCreate: created readPane activity");

        //--------init global Variables--------//
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();
        globalVars.setBufferStream();

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
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        TextView idBook = (TextView) findViewById(R.id.id_book);
        idBook.setText(globalVars.getTargetBookName());
        Log.d(TAG, "onResume: " + globalVars.getTargetBookName());

        TextView currLine = (TextView) findViewById(R.id.curr_line);

        TextView idProgress = (TextView) findViewById(R.id.id_progress);
        idProgress.setText(Double.toString(globalVars.getProgPc())+"%");

        //retrieve all other book line and etc
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: activity paused");
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
//                    ON CLICK LISTENERS                         //
//---------------------------------------------------------------//


    private void play(){
        //TextView currLine = (TextView) findViewById(R.id.curr_line);
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        timer = new CountDownTimer(msLeft, msInc) {
            @Override
            public void onTick(long millisUntilFinished) {
                displayNext();
            }

            @Override
            public void onFinish() {
            }
        }.start();

    }

    private void pause(){
        timer.cancel();
    }

    private void nextSentence(){

    }

    private void prevSentence(){

    }

    private void nextParagraph(){

    }

    private void prevParagraph(){

    }


///////////////////////////////////////////////////////////////////
//                      OTHER FUNCTIONS                          //
//---------------------------------------------------------------//


    private void initLayout() {

        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        ImageButton play = (ImageButton) findViewById(R.id.b_play);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                play();
            }
        });

        ImageButton pause = (ImageButton) findViewById(R.id.b_pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pause();
            }
        });

        ImageButton prevParagraph = (ImageButton) findViewById(R.id.b_prev_p);
        prevParagraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevParagraph();
            }
        });

        ImageButton nextParagraph = (ImageButton) findViewById(R.id.b_next_p);
        nextParagraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextParagraph();
            }
        });

        ImageButton prevSentence = (ImageButton) findViewById(R.id.b_prev_s);
        prevSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevSentence();
            }
        });

        ImageButton nextSentence = (ImageButton) findViewById(R.id.b_next_s);
        nextSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextSentence();
            }
        });

        TextView currLine = (TextView) findViewById(R.id.curr_line);

        TextView idBook = (TextView) findViewById(R.id.id_book);
        idBook.setText(globalVars.getTargetBookName());

        TextView idProgress = (TextView) findViewById(R.id.id_progress);
        idProgress.setText(Double.toString(globalVars.getProgPc())+"%");

    }

    public long fileSizeInKb(String fileName) {
        File file = new File(fileName);
        long fileSize = file.length();
        return fileSize / 1024;
    }

    private void displayNext(){
        TextView currLine = (TextView) findViewById(R.id.curr_line);
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();




//         Toast.makeText(readPane.this, "errorreadingfifle",Toast.LENGTH_SHORT).show();

        //https://www.youtube.com/watch?v=UIIpCt2S5Ls
        String text = "";
        String newChar = "";
        int cFlag = 1;
//        StringBuffer sBuffer = new StringBuffer();
//        InputStream is = this.getResources().openRawResource(R.raw.instructions);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        //if(is != null){
            try{

                for(int i =0; i<6; i++) {
                    newChar = String.valueOf(((char) globalVars.bufferR.read()));
                    text = text.concat(newChar);
                }

                currLine.setText(text);

                //is.close();
            }catch (Exception e){e.printStackTrace();
                Log.d(TAG, "displayNext: error");}
        //}
    }

}//EOF