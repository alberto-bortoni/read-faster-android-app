package bortoni.mx.read_faster;

import android.app.Application;

import java.io.File;

public class myGlobalVars extends Application {
///////////////////////////////////////////////////////////////////
//                       CLASS VARIABLES                         //
//---------------------------------------------------------------//
    private static final String BOOK_DIR = "bookFilesDir";
    private File bookDirectory;
    private File targetBook;
    private double progPc;
    private String targetBookName;
    private int speed;
    private int wordDisplay;

///////////////////////////////////////////////////////////////////
//                CONSTRUCT, SETS, AND GETS                      //
//---------------------------------------------------------------//

    //--------sets and gets--------//
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
