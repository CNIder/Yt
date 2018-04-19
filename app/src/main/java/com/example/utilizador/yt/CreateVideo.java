package com.example.utilizador.yt;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CreateVideo extends Fragment {


    public CreateVideo() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    EditText videoId;
    EditText Nome;
    EditText duracao;
    EditText data;
    EditText autor;
    String ServerURL = "http://192.168.1.127/YTAPP/ai.php" ;

     String video;
     String name;
     String dura;
     String date;
     String author;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_create_video, container, false);

        videoId = (EditText)root.findViewById(R.id.videoID);
        Nome = (EditText)root.findViewById(R.id.Nome);
        duracao = (EditText)root.findViewById(R.id.duracao);
        data = (EditText)root.findViewById(R.id.data);
        autor = (EditText)root.findViewById(R.id.autor);


       Button button = (Button) root.findViewById(R.id.submitButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                  video = videoId.getText().toString();
                  name = Nome.getText().toString();
                  dura = duracao.getText().toString();
                  date = data.getText().toString();
                  author = autor.getText().toString();

                InsertData(video,name,dura,date,author);
            }
        });

        return root;
    }

    //inserir dados no mysql
    public void InsertData(final String VideoID, final String Nome,final String duracao,final String data,final String autor){

        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String VideoIDinput = VideoID;
                String Nomeinput = Nome;
                String duracaoinput = duracao;
                String datainput = data;
                String autorinput = autor;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();




                nameValuePairs.add(new BasicNameValuePair("field", video));
                nameValuePairs.add(new BasicNameValuePair("field1", name));
                nameValuePairs.add(new BasicNameValuePair("field2", dura));
                nameValuePairs.add(new BasicNameValuePair("field3", data));
                nameValuePairs.add(new BasicNameValuePair("field4", autor));

                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(ServerURL);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    HttpResponse httpResponse = httpClient.execute(httpPost);

                    HttpEntity httpEntity = httpResponse.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Inserted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {

                super.onPostExecute(result);

                Toast.makeText(getContext(), "Data Submit Successfully", Toast.LENGTH_LONG).show();

            }
        }

        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();

        sendPostReqAsyncTask.execute(VideoID, Nome,duracao,data,autor);
    }
}
