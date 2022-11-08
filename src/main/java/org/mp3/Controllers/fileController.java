package org.mp3.Controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class fileController {

    public static ArrayList getFilenames(){
        File[] files = new File("/Users/oganesbozoan/progs/mp3Parser/files/raw").listFiles();
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
        for(String i: res){
            if(i.endsWith("mp3")){
                filenames.add(i);
            }
        }
       return (ArrayList) filenames;
    }
}
