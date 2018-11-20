package bortoni.mx.read_faster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class bookFiles extends AppCompatActivity {

    //public static File bookFiles = Environment.getExternalStoragePublicDirectory(En)

    private static final String TAG = "bookFiles";

    //--------init class variables--------//
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    //File bookDir = getDir("bookFileDir", MODE_PRIVATE);

    //Field[] bookFiles = R.raw.class.getFields();
    //File rawDir = new File(R.raw.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //--------init layout context--------//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_files);
        Log.d(TAG, "onCreate: Started bookfiles");





        //--------get files
        fetchFiles();
        initRecyclerView();

        //--------buttons
        Button addFile = (Button) findViewById(R.id.b_add);
        addFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBookFile();
            }
        });

        Button deleteFile = (Button) findViewById(R.id.b_delete);
        deleteFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteBookFile();
            }
        });

        Button selectFile = (Button) findViewById(R.id.b_select);
        selectFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTargetFile();
            }
        });





    }

///////////////////////////////////////////////////////////////////
//                      OTHER FUNCTIONS                          //
//---------------------------------------------------------------//

    //--------switch to activities--------//
    private void fetchFiles(){
        Log.d(TAG, "fetchFiles: retrieving flies");

        //File bookFilesa = new File("android.resource://" + getPackageName() + "/" + R.raw.class.getName());
        //File[] bookFiles = bookFilesa.listFiles();

        Field[] bookFiles = R.raw.class.getFields();


        mTitles.add(bookFiles[0].getName());
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add(bookFiles[1].getName());
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add(bookFiles[2].getName());
        mImages.add("@android:drawable/ic_menu_sort_by_size");


 /*       //String[] myList = bookDir.list();

        mTitles.add(myList[0]);
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add(myList[1]);
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add(myList[2]);
        mImages.add("@android:drawable/ic_menu_sort_by_size");
*/

  /*      mTitles.add("one");
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
        mImages.add("@android:drawable/ic_menu_sort_by_size");*/

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init RV");
        RecyclerView recyclerView = findViewById(R.id.file_list);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mImages, mTitles);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    //--------button services--------//

    private void addBookFile(){

    }

    private void deleteBookFile(){

    }

    private void selectTargetFile(){

    }


}