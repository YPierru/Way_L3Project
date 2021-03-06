package com.example.drawmywaybeta2.AsyncTasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.AsyncTask;

import com.example.drawmywaybeta2.Decoder;
import com.example.drawmywaybeta2.Parcours.Downloaded.DirectionsResponse;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.maps.GeoPoint;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GettingRoute extends AsyncTask<LatLng, Void, Void> {

	private LatLng origin, destination;
	private final String URL_PATTERN = "https://maps.googleapis.com/maps/api/directions/json?sensor=true&language=fr&mode=walking&";
	//private Document myXmlDoc;
	private static DirectionsResponse myRoad;
	private String overviewPL;
	private static ArrayList<LatLng> route;

	protected Void doInBackground(LatLng... params) {
		this.origin = params[0];
		this.destination = params[1];

		URL url = null;
		try {
			url = new URL(this.URL_PATTERN + "origin=" + this.origin.latitude
					+ "," + this.origin.longitude + "&destination="
					+ this.destination.latitude + ","
					+ this.destination.longitude);
			InputStream is = url.openStream();
			String strRoad = IOUtils.toString(is);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			myRoad = gson.fromJson(strRoad, DirectionsResponse.class);
			this.overviewPL = myRoad.getRoutes().get(0).getOverview_polyline().getPoints();
			route = Decoder.decodePoly((this.overviewPL));
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<LatLng> getRoute(){
		return route;
	}
	
	public static DirectionsResponse getDR(){
		return myRoad;
	}
}
