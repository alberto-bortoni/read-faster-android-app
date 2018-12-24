package bortoni.mx.read_faster;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.util.ArrayList;

public class bookFiles extends AppCompatActivity {
///////////////////////////////////////////////////////////////////
//                       CLASS VARIABLES                         //
//---------------------------------------------------------------//

    private static final String TAG = "bookFiles";
    private static final String BOOK_DIR = "bookFilesDir";
    private File bookDir = new File("");
    private File targetBook = new File("");

    //--------init class variables and construct--------//
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();

///////////////////////////////////////////////////////////////////
//                CONSTRUCT, SETS, AND GETS                      //
//---------------------------------------------------------------//
    public bookFiles(){

    }

    //--------sets and gets--------//
    public void setTargetBook(String fileName){
        targetBook = new File(bookDir.getAbsolutePath(), fileName); //TODO -- hardcoded
        Log.d(TAG, "setTargetBook: and the book is "+targetBook.getPath());
    }

    public void setBookDir(){
        bookDir = getDir(BOOK_DIR,0);
    }

    public File getTargetBook(){
        return targetBook.getAbsoluteFile();
    }

    public String getTargetBookName(){
        Log.d(TAG, "getTargetBookName: " + targetBook.getName());
        return targetBook.getName();
    }

    public File getBookDir(){
        return bookDir.getAbsoluteFile();
    }


///////////////////////////////////////////////////////////////////
//                     ACTIVITY WORKFLOW                         //
//---------------------------------------------------------------//

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //--------init layout context--------//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_files);
        Log.i(TAG, "onCreate: Started bookFiles");

        //--------init global Variables--------//
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        //--------get files
        //TODO -- this probably does not belong here
        fetchFiles();

        //--------buttons
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
//                      OTHER FUNCTIONS                          //
//---------------------------------------------------------------//

    //--------switch to activities--------//
    private void initLayout() {
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

    private void fetchFiles(){
        myGlobalVars globalVars = (myGlobalVars) getApplicationContext();

        Log.d(TAG, "fetchFiles: retrieving flies from " + globalVars.getBookDirectory().getPath());
        String[] files = globalVars.getBookDirectory().list();
            mTitles.clear();
            mImages.clear();
        for (String filenames : files){
            mTitles.add(filenames);
            mImages.add("@android:drawable/ic_menu_sort_by_size");
        }

        initRecyclerView();
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
/*        File deleteFile = new File(getBookDir().getPath(), file);
        Log.d(TAG, "deleted file: " + deleteFile.getAbsolutePath());
        deleteFile.delete();*/
    }

    private void selectTargetFile(){

    }


}//EOF