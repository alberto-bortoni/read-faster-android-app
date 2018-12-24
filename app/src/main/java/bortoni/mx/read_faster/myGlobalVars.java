package bortoni.mx.read_faster;

import android.app.Application;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class myGlobalVars extends Application {
///////////////////////////////////////////////////////////////////
//                       CLASS VARIABLES                         //
//---------------------------------------------------------------//
    private static final String BOOK_DIR = "bookFilesDir";
    private static final String TAG = "globalVars";

    private File bookDirectory;
    private File targetBook;
    private double progPc;
    private String targetBookName;
    private int speed;
    private int wordDisplay;
    BufferedReader bufferR = null;

///////////////////////////////////////////////////////////////////
//                CONSTRUCT, SETS, AND GETS                      //
//---------------------------------------------------------------//

    //-------- sets --------//
    public void setBookDirectory(File bookDirectory) {
        this.bookDirectory = bookDirectory;
    }

    public void setTargetBook(File targetBook) {
        this.targetBook = targetBook;
    }

    public void setProgPc(double progPc) {
        this.progPc = progPc;
    }

    public void setTargetBookName(String targetBookName) {
        this.targetBookName = targetBookName;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setWordDisplay(int wordDisplay) {
        this.wordDisplay = wordDisplay;
    }

    public void setBufferStream(){
        String brPath = getBookDirectory().getAbsolutePath().concat("/"+getTargetBookName());

        try{
            bufferR = new BufferedReader(new FileReader(brPath));
        }catch (Exception e){
            e.printStackTrace();
            Log.d(TAG, "setBufferStream: file issue");
        }
    }


    //-------- gets --------//
    public File getBookDirectory() {
        return bookDirectory;
    }

    public File getTargetBook() {
        return targetBook;
    }

    public double getProgPc() {
        return progPc;
    }

    public String getTargetBookName() {
        return targetBookName;
    }

    public String getDirectoryName() {
        return BOOK_DIR;
    }

    public int getSpeed() {
        return speed;
    }

    public int getWordDisplay() {
        return wordDisplay;
    }
}//EOF
