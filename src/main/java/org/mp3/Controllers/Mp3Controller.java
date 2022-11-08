package org.mp3.Controllers;

import java.io.*;

import org.json.simple.JSONObject;
import org.xml.sax.SAXException;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
    public BodyContentHandler handler;
    public JSONObject js;
    public Metadata metadata;
    public FileInputStream inputstream;
    public ParseContext pcontext;
    public Mp3Parser  Mp3Parser;
    public LyricsHandler lyrics;

    public Mp3Controller() throws TikaException, IOException, SAXException {
        this.id = Id.id;
        Id.id++;
        this.handler = new BodyContentHandler();
        this.js = new JSONObject();
        this.metadata = new Metadata();
        this.inputstream = new FileInputStream(new File("/Users/oganesbozoan/progs/mp3Parser/files/raw/song.mp3"));
        this.pcontext   = new ParseContext();
        this.Mp3Parser =   new  Mp3Parser();
        this.Mp3Parser.parse(inputstream, handler, metadata, pcontext);
        this.lyrics  = new LyricsHandler(inputstream,handler);
        this.inputstream.close();
    }
    public Mp3Controller(String name) throws TikaException, IOException, SAXException {
        this.id = Id.id;
        Id.id++;
        this.handler = new BodyContentHandler();
        this.js = new JSONObject();
        this.metadata = new Metadata();
        try {
            String buf = "/Users/oganesbozoan/progs/mp3Parser/files/raw/" + name;
            this.inputstream = new FileInputStream(new File(name));
            this.pcontext = new ParseContext();
            this.Mp3Parser = new Mp3Parser();
            this.Mp3Parser.parse(inputstream, handler, metadata, pcontext);
            this.lyrics = new LyricsHandler(inputstream, handler);
            this.inputstream.close();
        } catch (Exception e) {
            String buf = "/Users/oganesbozoan/progs/mp3Parser/files/raw/" + name;
            System.out.println(buf);
            System.out.println(e);
        }
    }

    public void data2js() throws IOException {
        String[] metadataNames = metadata.names();
        js.put("File id",this.id);
        js.put("Name",metadata.get(metadataNames[9]));
        js.put("Artist",metadata.get(metadataNames[6]));
        js.put("Album",metadata.get(metadataNames[1]));
        js.put("Duration",metadata.get(metadataNames[17]));
        js.put("Genre",metadata.get(metadataNames[0]));
        js.put("Release date",metadata.get(metadataNames[4]));
        System.out.println(js);
        String filename = (String) "/Users/oganesbozoan/progs/mp3Parser/files/Parsed/" +js.get("File id")+'-'+js.get("Name")+'-'+js.get("Artist");
        FileWriter file = new FileWriter(filename);
        file.write(js.toJSONString());
        file.close();
        System.out.println("Saved file in "+filename);
    }
}
