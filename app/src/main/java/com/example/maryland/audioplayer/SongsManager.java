package com.example.maryland.audioplayer;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {
    final String MEDIA_PATH = (Environment.getExternalStorageDirectory().toString() + "/Music");
    private ArrayList<HashMap<String, String>> songsList = new ArrayList<HashMap<String, String>>();

    // Constructor
    public SongsManager() {

    }

    /**
     * Function to read all mp3 files and store the details in
     * ArrayList
     * */
    public ArrayList<HashMap<String, String>> getPlayList() {

        Log.d("ddd", "MEDIA_PATH: "+MEDIA_PATH);
        System.out.println(MEDIA_PATH);
        if (MEDIA_PATH != null) {
            File home = new File(MEDIA_PATH);
            File[] listFiles = home.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    System.out.println(file.getAbsolutePath());
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }
                }
            }
        }
        Log.d("ddd", "MethodgetPlayList: "+songsList.size());

        // return songs list array
        return songsList;
    }

    private void scanDirectory(File directory) {
        if (directory != null) {
            File[] listFiles = directory.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file : listFiles) {
                    if (file.isDirectory()) {
                        scanDirectory(file);
                    } else {
                        addSongToList(file);
                    }

                }
            }
        }
    }

    private void addSongToList(File song) {
        String mp3Pattern = ".mp3";
        if (song.getName().endsWith(mp3Pattern)) {
            HashMap<String, String> songMap = new HashMap<String, String>();
            songMap.put("songTitle",
                    song.getName().substring(0, (song.getName().length() - 4)));
            songMap.put("songPath", song.getPath());

            // Adding each song to SongList
            songsList.add(songMap);
        }
    }


}