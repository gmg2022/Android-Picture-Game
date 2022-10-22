package com.example.firstmlapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionRequestInitializer;
import com.google.api.services.vision.v1.model.AnnotateImageRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesRequest;
import com.google.api.services.vision.v1.model.BatchAnnotateImagesResponse;
import com.google.api.services.vision.v1.model.Feature;
import com.google.api.services.vision.v1.model.Image;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class SecondActivity extends AppCompatActivity {


    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;

    MediaPlayer player;
    MediaPlayer letterplayer;
    private boolean clickedH = true;
    private int currentIndex = 0;
    private int currentLetter = 0;
    public static double[] timeToTake = new double[26];
    public static Drawable[] pics = new Drawable[4];

    //holds all the image data
    public static PicDataBlob[][] picdata = new PicDataBlob[26][3];
    public long startTime;
    public long endtime;

    private int[] orderlist = new int[26];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Sets up the board and music, grows a random box
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playgame);
        play();
        createRandomOrder();
        growRandom();

        //setting up all the buttons
        Button abutton = (Button)findViewById(R.id.abutton);
        Button bbutton = (Button)findViewById(R.id.bbutton);
        Button cbutton = (Button)findViewById(R.id.cbutton);
        Button dbutton = (Button)findViewById(R.id.dbutton);
        Button ebutton = (Button)findViewById(R.id.ebutton);
        Button fbutton = (Button)findViewById(R.id.fbutton);
        Button gbutton = (Button)findViewById(R.id.gbutton);
        Button hbutton = (Button)findViewById(R.id.hbutton);
        Button ibutton = (Button)findViewById(R.id.ibutton);
        Button jbutton = (Button)findViewById(R.id.jbutton);
        Button kbutton = (Button)findViewById(R.id.kbutton);
        Button lbutton = (Button)findViewById(R.id.lbutton);
        Button mbutton = (Button)findViewById(R.id.mbutton);
        Button nbutton = (Button)findViewById(R.id.nbutton);
        Button obutton = (Button)findViewById(R.id.obutton);
        Button pbutton = (Button)findViewById(R.id.pbutton);
        Button qbutton = (Button)findViewById(R.id.qbutton);
        Button rbutton = (Button)findViewById(R.id.rbutton);
        Button sbutton = (Button)findViewById(R.id.sbutton);
        Button tbutton = (Button)findViewById(R.id.tbutton);
        Button ubutton = (Button)findViewById(R.id.ubutton);
        Button vbutton = (Button)findViewById(R.id.vbutton);
        Button wbutton = (Button)findViewById(R.id.wbutton);
        Button xbutton = (Button)findViewById(R.id.xbutton);
        Button ybutton = (Button)findViewById(R.id.ybutton);
        Button zbutton = (Button)findViewById(R.id.zbutton);

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);

        Button quitbtn = findViewById(R.id.quitbutton);

        Button textView = (Button)findViewById(R.id.button2);
        CountDownTimer cdt = new CountDownTimer(50000, 1000) {
            public void onTick(long millisUntilFinished) {
                // Used for formatting digit to be in 2 digits only
                NumberFormat f = new DecimalFormat("00");
                long hour = (millisUntilFinished / 3600000) % 24;
                long min = (millisUntilFinished / 60000) % 60;
                long sec = (millisUntilFinished / 1000) % 60;
                textView.setText(f.format(hour) + ":" + f.format(min) + ":" + f.format(sec));
            }
            // When the task is over it will print 00:00:00 there
            public void onFinish() {
                textView.setText("00:00:00");
            }
        }.start();

        //Listeners for the buttons
        quitbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        //setting listeners for all the buttons A-Z
        abutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 0;
                askCameraPermissions();
            }
        });
        bbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 1;
                askCameraPermissions();
            }
        });
        cbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 2;
                askCameraPermissions();
            }
        });
        dbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 3;
                askCameraPermissions();
            }
        });
        ebutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 4;
                askCameraPermissions();
            }
        });
        fbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 5;
                askCameraPermissions();
            }
        });
        gbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 6;
                askCameraPermissions();
            }
        });
        hbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 7;
                askCameraPermissions();
            }
        });
        ibutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 8;
                askCameraPermissions();
            }
        });
        jbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 9;
                askCameraPermissions();
            }
        });
        kbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 10;
                askCameraPermissions();
            }
        });
        lbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 11;
                askCameraPermissions();
            }
        });
        mbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 12;
                askCameraPermissions();
            }
        });
        nbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 13;
                askCameraPermissions();
            }
        });
        obutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 14;
                askCameraPermissions();
            }
        });
        pbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 15;
                askCameraPermissions();
            }
        });
        qbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 16;
                askCameraPermissions();
            }
        });
        rbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 17;
                askCameraPermissions();
            }
        });
        sbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 18;
                askCameraPermissions();
            }
        });
        tbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 19;
                askCameraPermissions();
            }
        });
        ubutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 20;
                askCameraPermissions();
            }
        });
        vbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 21;
                askCameraPermissions();
            }
        });
        wbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 22;
                askCameraPermissions();
            }
        });
        xbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 23;
                askCameraPermissions();
            }
        });
        ybutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 24;
                askCameraPermissions();
            }
        });
        zbutton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                cdt.start();
                currentLetter = 25;
                askCameraPermissions();
            }
        });
    }

    //background music
    public void play() {
        if (player == null) {
            player = MediaPlayer.create(this, R.raw.song);
        }
        player.start();
    }
    //noise for each letter
    public void playLetter(int current) {
        if (current == 0) {
            letterplayer = MediaPlayer.create(this, R.raw.a);
        } else if (current == 1) {
            letterplayer = MediaPlayer.create(this, R.raw.b);
        }else if (current == 2) {
            letterplayer = MediaPlayer.create(this, R.raw.c);
        }else if (current == 3) {
            letterplayer = MediaPlayer.create(this, R.raw.d);
        }else if (current == 4) {
            letterplayer = MediaPlayer.create(this, R.raw.e);
        }else if (current == 5) {
            letterplayer = MediaPlayer.create(this, R.raw.f);
        }else if (current == 6) {
            letterplayer = MediaPlayer.create(this, R.raw.g);
        }else if (current == 7) {
            letterplayer = MediaPlayer.create(this, R.raw.h);
        }else if (current == 8) {
            letterplayer = MediaPlayer.create(this, R.raw.i);
        }else if (current == 9) {
            letterplayer = MediaPlayer.create(this, R.raw.j);
        }else if (current == 10) {
            letterplayer = MediaPlayer.create(this, R.raw.k);
        }else if (current == 11) {
            letterplayer = MediaPlayer.create(this, R.raw.l);
        }else if (current == 12) {
            letterplayer = MediaPlayer.create(this, R.raw.m);
        }else if (current == 13) {
            letterplayer = MediaPlayer.create(this, R.raw.n);
        }else if (current == 14) {
            letterplayer = MediaPlayer.create(this, R.raw.o);
        }else if (current == 15) {
            letterplayer = MediaPlayer.create(this, R.raw.p);
        }else if (current == 16) {
            letterplayer = MediaPlayer.create(this, R.raw.q);
        }else if (current == 17) {
            letterplayer = MediaPlayer.create(this, R.raw.r);
        } else if (current == 18) {
            letterplayer = MediaPlayer.create(this, R.raw.s);
        }else if (current == 19) {
            letterplayer = MediaPlayer.create(this, R.raw.t);
        }else if (current == 20) {
            letterplayer = MediaPlayer.create(this, R.raw.u);
        }else if (current == 21) {
            letterplayer = MediaPlayer.create(this, R.raw.v);
        }else if (current == 22) {
            letterplayer = MediaPlayer.create(this, R.raw.w);
        }else if (current == 23) {
            letterplayer = MediaPlayer.create(this, R.raw.x);
        }else if (current == 24) {
            letterplayer = MediaPlayer.create(this, R.raw.y);
        }else if (current == 25) {
            letterplayer = MediaPlayer.create(this, R.raw.z);
        }
        letterplayer.start();
    }
    public void askCameraPermissions() {
        startTime = System.currentTimeMillis();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},CAMERA_PERM_CODE);
        }
        else {
            openCamera();
        }
    }

    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //deprecated but it works, don't change it
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == CAMERA_PERM_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            }
            else {
                Toast.makeText(this, "Camera Permission is required", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    public void createRandomOrder() {
        ArrayList<Integer> al = new ArrayList<Integer>();
        for (int i = 0; i < 26; i++) {
            al.add(i);
        }
        java.util.Collections.shuffle(al);
        double rand = Math.random();
        for (int i = 0; i < 26; i++) {
            orderlist[i] = al.get(i);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");

            View test = findViewById(R.id.parenttest);
            View aback = findViewById(R.id.linearLayout5);
            Drawable d = new BitmapDrawable(getResources(), image);
            endtime = System.currentTimeMillis();

            pics[0] = d;
            pics[1] = d;
            pics[2] = d;
            pics[3] = d;

            double timeTaken = (double)((endtime - startTime) / 1000);
            timeToTake[currentLetter] = timeTaken;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String[] ans = myVisionTester(image);
                        PicDataBlob picblob = new PicDataBlob(d, endtime, ans, timeTaken);
                        addBlob(picblob);
                        runOnUiThread(new Runnable() {
                            public void run() {
                                setBackground(d, ans);
                                growRandom();
                            }
                        });

                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    String[] myVisionTester(Bitmap bitmap) throws IOException {
        //return the top 3 labels for taken picture, Google Cloud Vision

        //1. ENCODE image.
        //Bitmap bitmap = ((BitmapDrawable)getResources().getDrawable(R.drawable.b1)).getBitmap();
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bout);
        Image myimage = new Image();
        myimage.encodeContent(bout.toByteArray());

        //2. PREPARE AnnotateImageRequest
        AnnotateImageRequest annotateImageRequest = new AnnotateImageRequest();
        annotateImageRequest.setImage(myimage);
        Feature f = new Feature();
        f.setType("LABEL_DETECTION");
        f.setMaxResults(5);
        List<Feature> lf = new ArrayList<Feature>();
        lf.add(f);
        annotateImageRequest.setFeatures(lf);

        //3.BUILD the Vision
        HttpTransport httpTransport = AndroidHttp.newCompatibleTransport();
        GsonFactory jsonFactory = GsonFactory.getDefaultInstance();
        Vision.Builder builder = new Vision.Builder(httpTransport, jsonFactory, null);
        //TODO: new VisionRequestInitializer key, expires every month
        builder.setVisionRequestInitializer(new VisionRequestInitializer("AIzaSyAraBMDeczKZqddEWzxQ9EBa9LCnnDAt7Q"
        ));
        Vision vision = builder.build();

        //4. CALL Vision.Images.Annotate
        BatchAnnotateImagesRequest batchAnnotateImagesRequest = new BatchAnnotateImagesRequest();
        List<AnnotateImageRequest> list = new ArrayList<AnnotateImageRequest>();
        list.add(annotateImageRequest);
        batchAnnotateImagesRequest.setRequests(list);
        Vision.Images.Annotate task = vision.images().annotate(batchAnnotateImagesRequest);
        BatchAnnotateImagesResponse response = task.execute();
        String x1 = response.getResponses().get(0).getLabelAnnotations().get(0).getDescription();
        String x2 = response.getResponses().get(0).getLabelAnnotations().get(1).getDescription();
        String x3 = response.getResponses().get(0).getLabelAnnotations().get(2).getDescription();
        return new String[] {x1, x2, x3};
    }

    public void growRandom() {
        final ViewGroup cview = findViewById(R.id.constraintviewid);
        View randomView = cview.getChildAt(orderlist[currentIndex]);
        int current = orderlist[currentIndex];
        playLetter(current);
        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.scale);
        randomView.startAnimation(anim);
        currentIndex++;
    }

    public String printOut(int[] x) {
        String ans = "";
        for (int i = 0; i < x.length; i++) {
            ans += x[i] + ", ";
        }
        return ans;
    }
    public void setBackground(Drawable d, String[] ans) {
        ViewGroup parentview = findViewById(R.id.constraintviewid);
        char b = (char) (currentLetter + 65);
        if (ans[0].charAt(0) != b && ans[1].charAt(0) != b && ans[2].charAt(0) != b)
            (parentview.getChildAt(currentLetter)).setBackground(getDrawable(R.drawable.nope));
        else
            (parentview.getChildAt(currentLetter)).setBackground(d);
    }

    public void addBlob(PicDataBlob blob) {
        //if this is the first image of <letter> taken
        if (picdata[currentLetter][0] == null) {
            picdata[currentLetter][0] = blob;
        }
        //there has been at least one image taken
        else if (picdata[currentLetter][1] == null){
            picdata[currentLetter][1] = blob;
        }
        //last image empty
        else if (picdata[currentLetter][2] == null) {
            picdata[currentLetter][2] = blob;
        }
        else {
            //full, remove the first entry and move the entries around
            PicDataBlob secondEntry = picdata[currentLetter][1];
            PicDataBlob thirdEntry = picdata[currentLetter][2];
            picdata[currentLetter][0] = secondEntry;
            picdata[currentLetter][1] = thirdEntry;
            picdata[currentLetter][2] = blob;
        }
    }
}
