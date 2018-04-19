package com.example.utilizador.yt;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;



import com.example.utilizador.yt.Custom.CustomAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ScreenVideos extends Fragment {


    public ScreenVideos() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    ListView listView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_screen_videos, container, false);
        listView = (ListView)root.findViewById(R.id.listView);
        getJSON("http://192.168.1.127/YTAPP/getVideos.php");
        return root;
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
    String nome = "";
    String VideoId = "";
    String autor = "";
    String dataCreated = "";
    int flags[] = {R.drawable.ico};
    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        final String[] heroes = new String[jsonArray.length()];
        final ArrayList<Video> arrayList = new ArrayList<Video>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            nome = heroes[i] = obj.getString("nome");
            VideoId = heroes[i] = obj.getString("videoID");
            autor = heroes[i] = obj.getString("autor");
            dataCreated = heroes[i] = obj.getString("data_create");
            Video video = new Video(VideoId,nome,dataCreated,autor);
            System.out.println("nome " + video.getNome());
            arrayList.add(video);
            CustomAdapter custom = new CustomAdapter(getContext(),arrayList,flags);
            listView.setAdapter(custom);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(),VideoPlayer.class);
                    intent.putExtra("nome",arrayList.get(position).getNome());
                    intent.putExtra("VideoID",arrayList.get(position).getVideoID());
                    intent.putExtra("autor",arrayList.get(position).getAuthor());
                    intent.putExtra("dataCreated",arrayList.get(position).getDataCreated());
                    startActivity(intent);
                }
            });

        }
    }

}
