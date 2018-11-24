package bortoni.mx.read_faster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

public class readPane extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_pane);

        Intent intent = getIntent();
        //TODO -- this default value seems not to work
        int speed =  intent.getIntExtra(MainActivity.EXTRA_WORDSPERMIN, 400);
        int displaySize = intent.getIntExtra(MainActivity.EXTRA_WORDSPERDIPS, 1);

        //--------init layout context--------//
        //--------instructions
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


    }


///////////////////////////////////////////////////////////////////
//                      OTHER FUNCTIONS                          //
//---------------------------------------------------------------//

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











}