package org.mp3;

import org.apache.tika.exception.TikaException;
import org.mp3.Controllers.Mp3Controller;
import org.mp3.Controllers.fileController;
import org.mp3.Controllers.threadController;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.exit;
import static java.lang.Thread.sleep;

/*
tz:
Пользователь загружает в определенный каталог файлы формата «.mp3»,
после чего система должна их «подхватить» – получить из каждого файла информацию
о названии, альбоме, жанре и т. п. с дальнейшей загрузкой информации в базу данных.
Дополнительно необходимо реализовать запрос для получения списка последних 10 загруженных файлов.
 */

public class App {
    static String path = "files/raw/";
    public static void main(String[] args) throws TikaException, IOException, SAXException, InterruptedException {
        Scanner myObj = new Scanner(System.in);
        threadController thread = new threadController();
        thread.start();
        while (true) {
            System.out.println("==========|Parse(Y)| Update(U)| To get last ten files(10)| Q - exit|==========");
            switch (myObj.nextLine().toUpperCase(Locale.ROOT)) {
                case ("Y"):
                    for (Object i : thread.files) {
                        Mp3Controller mp3 = new Mp3Controller(path + (String) i);
                        mp3.data2js();
                    }
                    break;
                case ("U"):
                    System.out.println("Updated... new files: "+thread.files);
                    break;
                case ("10"):
                    fileController.get10files();
                    break;
                case ("Q"):
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Wrong operation");
                    break;
            }
        }
    }
}