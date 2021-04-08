package com.yjisolutions.ipdc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.util.Util;

import static android.view.View.GONE;

public class VideoViewer extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    private TextView title;
    private Toolbar toolbar;
    private float speed = 1f;
    private  ImageView fs;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_video_viewer);

        title = findViewById(R.id.header_tv);
        fs = findViewById(R.id.exo_fullscreen);
        ImageView speedT = findViewById(R.id.speedToggle);

        speedT.setOnClickListener(v -> {
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View layout = inflater.inflate(R.layout.playbackspeed, findViewById(R.id.speedPOP));
            final PopupWindow pw = new PopupWindow(layout, 500, 700, true);
            pw.showAtLocation(findViewById(R.id.exoPlayerController), Gravity.CENTER, 0, 0);
            TextView one = layout.findViewById(R.id.speedOne);
            TextView two = layout.findViewById(R.id.speedTwo);
            TextView three = layout.findViewById(R.id.speedThree);
            TextView four = layout.findViewById(R.id.speedFour);
            TextView five = layout.findViewById(R.id.speedFive);
            TextView six = layout.findViewById(R.id.speedsix);

            one.setOnClickListener(v16 -> {
                speed = 1.0f;
                PlaybackParameters parameters = new PlaybackParameters(speed);
                player.setPlaybackParameters(parameters);
                pw.dismiss();
            });

            two.setOnClickListener(v15 -> {
                speed = 1.2f;
                PlaybackParameters parameters = new PlaybackParameters(speed);
                player.setPlaybackParameters(parameters);
                pw.dismiss();
            });
            three.setOnClickListener(v12 -> {
                speed = 1.4f;
                PlaybackParameters parameters = new PlaybackParameters(speed);
                player.setPlaybackParameters(parameters);
                pw.dismiss();
            });
            four.setOnClickListener(v13 -> {
                speed = 1.6f;
                PlaybackParameters parameters = new PlaybackParameters(speed);
                player.setPlaybackParameters(parameters);
                pw.dismiss();
            });
            five.setOnClickListener(v14 -> {
                speed = 1.8f;
                PlaybackParameters parameters = new PlaybackParameters(speed);
                player.setPlaybackParameters(parameters);
                pw.dismiss();
            });
            six.setOnClickListener(v1 -> {
                speed = 2.0f;
                PlaybackParameters parameters = new PlaybackParameters(speed);
                player.setPlaybackParameters(parameters);
                pw.dismiss();
            });

        });

        fs.setOnClickListener(v -> hideSystemUi());


        toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Lecture - " + getIntent().getStringExtra("chapter"));
        toolbar.setSubtitle(getIntent().getStringExtra("dis"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_baseline_arrow_back_24));
        toolbar.setNavigationOnClickListener(v -> {
            //What to do on back clicked
            finish();
        });

        //ExoPlayer
        playerView = findViewById(R.id.video_view);
        initializePlayer();

    }

    @SuppressLint("SetTextI18n")
    private void initializePlayer() {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        MediaItem mediaItem = MediaItem.fromUri(getIntent().getStringExtra("url"));
        player.setMediaItem(mediaItem);
        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        player.prepare();
        title.setText("Lecture - " + getIntent().getStringExtra("chapter"));

    }

    public void hideSystemUi() {
        toolbar.setVisibility(GONE);
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showSystemUi() {
        toolbar.setVisibility(View.VISIBLE);
        playerView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            hideSystemUi();

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            showSystemUi();
        }
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }


    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

}
