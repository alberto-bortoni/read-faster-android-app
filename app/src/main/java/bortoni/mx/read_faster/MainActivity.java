package bortoni.mx.read_faster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    //variables
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: started");


       initFilterSpeed();
       initFilterDisplay();

       //setContentView(R.layout.book_files);
       //fetchFiles();
    }


///////////////////////////////////////////////////////////////////
    private void fetchFiles(){
        Log.d(TAG, "fetchFiles: retrieving flies");
        mTitles.add("one");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("two");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("three");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("four");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("five");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("six");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("seven");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("eight");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add("nine");
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        initRecyclerView();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RV");
        RecyclerView recyclerView = findViewById(R.id.file_list);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImages, mTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initFilterSpeed(){
        EditText wordSpeed = findViewById(R.id.words_per_minute);
        wordSpeed.setFilters(new InputFilter[]{new InputFilterMinMax("1", "600")});
    }
    private void initFilterDisplay(){
        EditText wordDisplay = findViewById(R.id.words_per_display);
        wordDisplay.setFilters(new InputFilter[]{new InputFilterMinMax("1", "4")});
    }
}
