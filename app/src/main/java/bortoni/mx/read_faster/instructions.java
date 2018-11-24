package bortoni.mx.read_faster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class instructions extends AppCompatActivity {

    public TextView readInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instructions);

        readInstructions = (TextView) findViewById(R.id.read_me);
        readInstructions.setMovementMethod(new ScrollingMovementMethod());

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