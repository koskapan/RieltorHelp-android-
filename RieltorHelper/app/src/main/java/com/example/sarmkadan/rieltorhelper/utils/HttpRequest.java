package com.example.sarmkadan.rieltorhelper.utils;

import android.util.Log;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by cav on 26.04.16.
 */

public class HttpRequest {
    private final static String LOG_TAG="syncHTTPRequest";

    public static String getRequest(String urlRequest){
        try{
            URL url = new URL(urlRequest);
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new InputSource(url.openStream()));
            doc.getDocumentElement().normalize();

        }catch (Exception e){
            Log.d(LOG_TAG,e.getMessage());
            return "Error: "+e.getLocalizedMessage();

        }

        return null;
    }
}
