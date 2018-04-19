package com.example.utilizador.yt;

import android.content.Intent;
import android.net.wifi.hotspot2.pps.Credential;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.utilizador.yt.Config.Config;
import com.example.utilizador.yt.Custom.Adaptador;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VideoPlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private static final int RECOVERY_REQUEST = 1;
    private YouTubePlayerView youTubeView;
    String VideoID = "";
    String VideoNome = "";
    String autor = "";
    String dataCreated = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        Intent intent = getIntent();
        VideoID = intent.getStringExtra("VideoID");
        VideoNome = intent.getStringExtra("nome");
        autor = intent.getStringExtra("autor");
        dataCreated = intent.getStringExtra("dataCreated");
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
        youTubeView.initialize(Config.YOUTUBE_KEY, this);

        //expandable ListView
        ExpandableListView elvCompra = (ExpandableListView) findViewById(R.id.elvCompra);

        List<String> lstGrupos = new ArrayList<>();
        lstGrupos.add("" + VideoNome);


        List<Video> videoConfig = new ArrayList<>();
        videoConfig.add(new Video(VideoID,VideoNome,dataCreated,autor));



        HashMap<String, List<Video>> lstItensGrupo = new HashMap<>();
        lstItensGrupo.put(lstGrupos.get(0), videoConfig);


        Adaptador adaptador = new Adaptador(this, lstGrupos, lstItensGrupo);
        elvCompra.setAdapter(adaptador);

    }

    @Override
    public void onInitializationSuccess(Provider provider, YouTubePlayer player, boolean wasRestored) {
        if (!wasRestored) {
            player.cueVideo(VideoID); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(Provider provider, YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_REQUEST).show();
        } else {
            String error = "erro";
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.YOUTUBE_KEY, this);
        }
    }

    protected Provider getYouTubePlayerProvider() {
        return youTubeView;
    }
}