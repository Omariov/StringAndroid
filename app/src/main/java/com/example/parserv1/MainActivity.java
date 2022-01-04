package com.example.parserv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public class SimpleXmlPullApp
    {

        public void DoIt ()
                throws XmlPullParserException, IOException
        {
            //factory to let us implement the xmlpullparser
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            //to be aware of namesapces on xml tags
            factory.setNamespaceAware(true);

            //new instance
            XmlPullParser xpp = factory.newPullParser();

            //set input with a stream
            xpp.setInput( new StringReader
                                            (
                                            "<film><titre>Harry Potter</titre></film>" +
                                               "<film><titre>Indiana Jones</titre></film>"+
                                               "<film><titre>Mission Impossible</titre></film>"
                                            )
                        );

            //gets event
            int eventType = xpp.getEventType();

            ArrayList items = new ArrayList<String>();

            String item = "";

            while (eventType != XmlPullParser.END_DOCUMENT) {

                if(eventType == XmlPullParser.START_DOCUMENT)
                    System.out.println("Start document");



                if (eventType == XmlPullParser.START_TAG)
                        item = xpp.getName();

                if(eventType == XmlPullParser.TEXT)
                {
                    item += " :" + xpp.getText();
                    items.add(item);
                    item = "";
                }

                eventType = xpp.next();
            }
            System.out.println("End document");

            ListView list = (ListView) findViewById(R.id.listView);

            //create adapter for the data retrieved
            ArrayAdapter adapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, items);
            list.setAdapter(adapter);
        }
    }

    public void parse(View view) throws IOException, XmlPullParserException {
        SimpleXmlPullApp xxp = new SimpleXmlPullApp();
        xxp.DoIt();
    }


}