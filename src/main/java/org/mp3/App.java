package org.mp3;

import org.apache.tika.exception.TikaException;
import org.mp3.Controllers.Mp3Controller;
import org.mp3.Controllers.fileController;
import org.xml.sax.SAXException;

import java.io.IOException;

/*
tz:
Пользователь загружает в определенный каталог файлы формата «.mp3»,
после чего система должна их «подхватить» – получить из каждого файла информацию
о названии, альбоме, жанре и т. п. с дальнейшей загрузкой информации в базу данных.
Дополнительно необходимо реализовать запрос для получения списка последних 10 загруженных файлов.
 */

public class App 
{
    static String path = "/Users/oganesbozoan/progs/mp3Parser/files/raw/";
    public static void main( String[] args ) throws TikaException, IOException, SAXException {
        var fls = fileController.getFilenames();
        for(Object i: fls){
            Mp3Controller mp3 = new Mp3Controller(path +(String) i);
            mp3.data2js();
        }
    }
}
