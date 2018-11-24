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
import java.util.ArrayList;

public class bookFiles extends AppCompatActivity {


    private static final String TAG = "bookFiles";
    private static final String BOOK_DIR = "bookFilesDir";
    private File bookDir = new File("");
    private File targetBook = new File("");

    //--------init class variables and construct--------//
    private ArrayList<String> mTitles = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();


    public bookFiles(){

    }

    //--------sets and gets--------//
    public void setTargetBook(String fileName){
        targetBook = new File(bookDir.getAbsolutePath(), fileName);
    }

    public void setBookDir(){
        bookDir = getDir(BOOK_DIR,0);
    }

    public File getTargetBook(){
        return targetBook.getAbsoluteFile();
    }

    public File getBookDir(){
        return bookDir.getAbsoluteFile();
    }


    //--------on create--------//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    //--------init layout context--------//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_files);
        Log.i(TAG, "onCreate: Started bookfiles");

        //--------get files
        setBookDir();
        copyAssets(); //TODO -- this copies the files every time
        setTargetBook("second");
        fetchFiles();

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
                //deleteBookFile();
                
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
        Log.d(TAG, "fetchFiles: retrieving flies from " + getBookDir().getPath());
        String[] files = getBookDir().list();

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

    private void deleteBookFile(String file){
        File deleteFile = new File(getBookDir().getPath(), file);
        Log.d(TAG, "deleted file: " + deleteFile.getAbsolutePath());
        deleteFile.delete();
    }

    private void selectTargetFile(){

    }



//TODO -- i think it is storingthe data in the right spot
    private void copyAssets() {
        AssetManager bookAssets = getApplicationContext().getAssets();
        String[] files = null;
        File location = getBookDir();
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
                //File outFile = new File(getFilesDir(), filename);
                File outFile = new File(location, filename);
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