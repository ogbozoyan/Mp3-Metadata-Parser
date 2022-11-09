package org.mp3.Controllers;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class fileController {
    private static List<String> prevfilenames = new ArrayList<>();

    public static void get10files(){
        File[] files = new File("files/parsed").listFiles();
        List<String> filenames = new ArrayList<String>();
        if(files == null) {
            for (File file : files) {
                if (file.isFile()) {
                    filenames.add(file.getName());
                }
            }
            System.out.println(filenames);
        } else{
                for (File file : files) {
                    if (file.isFile()) {
                        filenames.add(file.getName());
                    }
                }
                for(int i = 0;i<10;i++){
                    try{
                        System.out.println(filenames.get(i));
                    }catch (Exception e){
                        break;
                    }
                }
            }
        }

    public static @NotNull ArrayList getFilenames(){
        int j = 0;
        File[] files = new File("files/raw").listFiles();
        List<String> filenames = new ArrayList<String>();
        assert files != null;
        for (File file : files) {
            if (file.isFile()) {
                filenames.add(file.getName());
            }
        }
        String[] res = new String[filenames.size()];
        res = filenames.toArray(new String[0]);
        filenames.clear();
        for(String i: res) {
            if (j < 1) {
                if (i.endsWith(".mp3")) {
                    filenames.add(i);
                    j++;
                    prevfilenames = filenames;
                }
            }else{
                if (i.endsWith(".mp3") && (!prevfilenames.contains(i))) {
                    filenames.add(i);
                    prevfilenames = filenames;
                }
            }
        }
       return (ArrayList) filenames;
    }
}
