package org.mp3.Controllers;

import java.io.*;

import org.json.simple.JSONObject;
import org.xml.sax.SAXException;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;

class Id {
    public static long id = 1;
}
public class Mp3Controller {
    public long id;
    private String buf;
    public BodyContentHandler handler;
    public JSONObject js;
    public Metadata metadata;
    public FileInputStream inputstream;
    public ParseContext pcontext;
    public Mp3Parser  Mp3Parser;
    public LyricsHandler lyrics;
    public String[] metadataNames;

    public Mp3Controller() throws TikaException, IOException, SAXException {
        this.id = Id.id;
        Id.id++;
        this.handler = new BodyContentHandler();
        this.js = new JSONObject();
        this.metadata = new Metadata();
        try {
            this.inputstream = new FileInputStream(new File("files/raw/example.mp3"));
            this.pcontext = new ParseContext();
            this.Mp3Parser = new Mp3Parser();
            this.Mp3Parser.parse(inputstream, handler, metadata, pcontext);
            this.lyrics = new LyricsHandler(inputstream, handler);
            this.metadataNames = metadata.names();
            this.inputstream.close();
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public Mp3Controller(String name) throws TikaException, IOException, SAXException {
        this.id = Id.id;
        Id.id++;
        this.handler = new BodyContentHandler();
        this.js = new JSONObject();
        this.metadata = new Metadata();
        try {
            this.buf = "files/raw/" + name;
            this.inputstream = new FileInputStream(new File(name));
            this.pcontext = new ParseContext();
            this.Mp3Parser = new Mp3Parser();
            this.Mp3Parser.parse(inputstream, handler, metadata, pcontext);
            this.lyrics = new LyricsHandler(inputstream, handler);
            this.metadataNames = metadata.names();
            this.inputstream.close();
        } catch (Exception e) {
            this.buf = "files/raw/" + name;
            System.out.println(buf);
            System.out.println(e);
        }
    }

    public String data2js() throws IOException {
        js.put("File id",this.id);
        js.put("Name",metadata.get(metadataNames[9]));
        js.put("Artist",metadata.get(metadataNames[6]));
        js.put("Album",metadata.get(metadataNames[1]));
        js.put("Duration",metadata.get(metadataNames[17]));
        js.put("Genre",metadata.get(metadataNames[0]));
        js.put("Release date",metadata.get(metadataNames[4]));
        js.put("File name",this.buf);
        System.out.println(js);
        String filename = (String) "files/Parsed/" +js.get("File id")+'-'+js.get("Name")+'-'+js.get("Artist")+".txt";
        FileWriter file = new FileWriter(filename);
        file.write(js.toJSONString());
        file.close();
        return ("Saved file in "+filename);
    }
}
