package bortoni.mx.read_faster;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class readPane extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_pane);

        Intent intent = getIntent();
        int speed =  intent.getIntExtra(MainActivity.EXTRA_WORDSPERMIN, 400);
        int displaySize = intent.getIntExtra(MainActivity.EXTRA_WORDSPERDIPS, 1);
    }
}