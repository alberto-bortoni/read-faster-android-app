package bortoni.mx.read_faster;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;


public class bookFiles extends AppCompatActivity {

    //public static File bookFiles = Environment.getExternalStoragePublicDirectory(En)
    //public AssetManager bookAssets = getApplicationContext().getAssets();

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
        Log.i(TAG, "onCreate: Started bookfiles");
        //Log.i(TAG, "file location: " + bookDir.getAbsolutePath());


        //--------get files
        copyAssets(); //TODO -- this copies the files every time
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
        //File[] bookFiles = getExternalFilesDir(null);

        Field[] bookFiles = R.raw.class.getFields();

        mTitles.add(bookFiles[0].getName());
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add(bookFiles[1].getName());
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add(bookFiles[2].getName());
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        mTitles.add(getExternalFilesDir(null).getPath());
        mImages.add("@android:drawable/ic_menu_sort_by_size");

        String[] what = new String[0];
        try {
            what = getApplicationContext().getAssets().list("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTitles.add(what[0]);
        mImages.add("@android:drawable/ic_menu_sort_by_size");
        

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


//TODO -- is storing in the sd card appropiate? do internal memory
    private void copyAssets() {
        AssetManager bookAssets = getApplicationContext().getAssets();
        String[] files = null;
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
                File outFile = new File(getExternalFilesDir(null), filename);
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
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while((read = in.read(buffer)) != -1){
            out.write(buffer, 0, read);
        }
    }


}//EOF