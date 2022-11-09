package org.mp3.Controllers;

import java.util.ArrayList;

public class threadController extends Thread{
    fileController flds;
    public ArrayList files;
    @Override
    public void run(){
       while(true)
       {
           try {
               this.files = flds.getFilenames();
               sleep(5000);
           } catch (InterruptedException e) {
               throw new RuntimeException(e);
           }
       }
    }
}
