package bortoni.mx.read_faster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class readPane extends AppCompatActivity {
///////////////////////////////////////////////////////////////////
//                       CLASS VARIABLES                         //
//---------------------------------------------------------------//
    private static final String TAG = "readPane";

///////////////////////////////////////////////////////////////////
//                CONSTRUCT, SETS, AND GETS                      //
//---------------------------------------------------------------//
    public readPane(){

    }

///////////////////////////////////////////////////////////////////
//                     ACTIVITY WORKFLOW                         //
//---------------------------------------------------------------//
    //--------on create--------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_pane);
        Log.d(TAG, "onCreate: created readPane activity");

        //--------init global Variables--------//
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        initLayout();
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
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        TextView idBook = (TextView) findViewById(R.id.id_book);
        idBook.setText(globalVars.getTargetBookName());
        Log.d(TAG, "onResume: " + globalVars.getTargetBookName());

        TextView currLine = (TextView) findViewById(R.id.curr_line);

        TextView idProgress = (TextView) findViewById(R.id.id_progress);
        idProgress.setText(Double.toString(globalVars.getProgPc())+"%");
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

    //--------controls--------//
    private void play(){

    }

    private void pause(){

    }

    private void nextSentence(){

    }

    private void prevSentence(){

    }

    private void nextParagraph(){

    }

    private void prevParagraph(){

    }



}//EOF