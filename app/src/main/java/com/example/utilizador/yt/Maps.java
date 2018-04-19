package com.example.utilizador.yt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.utilizador.yt.Custom.CustomAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import com.example.utilizador.yt.Coordenadas;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class Maps extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMapClickListener {

    public Maps() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private SupportMapFragment mapFragment;

    LatLng latLng;
    GoogleMap map;
    ArrayList<Coordenadas> coordernadas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_maps, container, false);

        getJSON("http://192.168.1.127/YTAPP/GetLocations.php");


        SupportMapFragment mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);


        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();

        return root;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        try{

            for(int i = 0;i < coordernadas.size();++i){
                System.out.println("" + coordernadas.get(i).getLong());
                LatLng TutorialsPoint = new LatLng(coordernadas.get(i).getLat(), coordernadas.get(i).getLong());
                map.addMarker(new MarkerOptions().position(TutorialsPoint));

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(TutorialsPoint)      // Sets the center of the map to Mountain View
                        .zoom(1)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }

        }catch (NullPointerException e){
            e.printStackTrace();
        }
        googleMap.setOnMarkerClickListener(this);
        googleMap.setOnMapClickListener(this);
    }


    @Override
    public void onMapClick(LatLng arg0) {

        try{
                LatLng place = new LatLng(38.7436057,-9.2302432);
                map.animateCamera(CameraUpdateFactory.zoomIn());
                map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

                map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(place)      // Sets the center of the map to Mountain View
                        .zoom(2)                   // Sets the zoom
                        .bearing(90)                // Sets the orientation of the camera to east
                        .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }catch (NullPointerException e){
            e.printStackTrace();
        }

    }

    //receber dados do site
    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }


            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();
    }
    /*****************************************************************************/
    String lat = "";
    String Long = "";
    int flags[] = {R.drawable.ico};
    Coordenadas coordenadas;
    private void loadIntoListView(String json) throws JSONException {
        coordernadas = new ArrayList<Coordenadas>();
        JSONArray jsonArray = new JSONArray(json);
        final String[] heroes = new String[jsonArray.length()];
        final ArrayList<Video> arrayList = new ArrayList<Video>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            lat = heroes[i] = obj.getString("latitude");
            Long = heroes[i] = obj.getString("logitude");
            //Coordenadas coordenadas2 = new Coordenadas(22.3,.4);
            coordenadas = new Coordenadas(Double.parseDouble(lat),Double.parseDouble(Long));
            coordernadas.add(coordenadas);

        }
    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

       map.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(marker.getPosition())      // Sets the center of the map to Mountain View
                .zoom(14)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        return true;
    }
}
