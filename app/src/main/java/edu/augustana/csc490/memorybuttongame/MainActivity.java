package edu.augustana.csc490.memorybuttongame;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.os.SystemClock;
import android.provider.CalendarContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;


public class MainActivity extends ActionBarActivity {

    int device_width, button_width,button_height, buttonColor, clickedColor, sequence_index;
    Display display;
    Chronometer timer;
    Point size;
    ImageView img;
    Button[] buttons;
    TextView description, time;
    Button playAgain;
    ButtonSequence bSeq;
    Color[] colors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sequence_index = 0;
        description = (TextView) findViewById(R.id.textView);


       //create colors
        buttonColor = Color.argb(255, 255, 119, 89);
        clickedColor = Color.LTGRAY;

        //create hidden winning image
        img = (ImageView) findViewById(R.id.imageView);
        img.setVisibility(View.GONE);

        //create and start timer
        timer = (Chronometer) findViewById(R.id.chronometer);
        timer.start();

        time = (TextView) findViewById(R.id.textView2);


        //create play again button
        playAgain = (Button) findViewById(R.id.button);
        playAgain.setVisibility(View.INVISIBLE);
        playAgain.setOnClickListener(playAgainListener);

        //get device width
        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        device_width = size.x;

        //create buttons and add to @buttons array
        buttons = new Button[9];
        button_width = (device_width - 122)/3;
        button_height = (int) (button_width * 0.6);
        buttons[0] = (Button) findViewById(R.id.button1);
        buttons[1] = (Button) findViewById(R.id.button2);
        buttons[2] = (Button) findViewById(R.id.button3);
        buttons[3] = (Button) findViewById(R.id.button4);
        buttons[4] = (Button) findViewById(R.id.button5);
        buttons[5] = (Button) findViewById(R.id.button6);
        buttons[6] = (Button) findViewById(R.id.button7);
        buttons[7] = (Button) findViewById(R.id.button8);
        buttons[8] = (Button) findViewById(R.id.button9);

        //set button width to fit any size screen
        //set click listener
        for(Button btn: buttons){
            btn.setWidth(button_width);
            btn.setHeight(button_height);
            btn.setOnClickListener(buttonListener);
        }

        //get and test description
        bSeq = new ButtonSequence();
       // String s = bSeq.toString();
       // Log.d("gameTag", s);
    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            v.setBackgroundColor(clickedColor);
            checkMove(v);

        }
    };

    View.OnClickListener playAgainListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            reset();
            bSeq.randomize();
            for(Button btn : buttons){
                btn.setEnabled(true);
                }
            timer.setBase(SystemClock.elapsedRealtime());
            timer.start();
            playAgain.setVisibility(View.INVISIBLE);
            description.setText(R.string.description);
        }
    };
    //set button colors back, reset point reached in description to 0
    public void reset() {
        //delay before resetting color - better way?
        long start = new Date().getTime();
        while(new Date().getTime() - start < 200){}
        for(Button btn : buttons)
        {btn.setBackgroundColor(buttonColor);
        }

        sequence_index=0;

    }


    public void increment(){
        if(sequence_index <8) {
            sequence_index++;
        }else{
            win();
        }
    }

    public void checkMove(View v){
        Resources res = v.getResources();
        String id = res.getResourceEntryName(v.getId());
        String current = "button" + bSeq.get(sequence_index);
        Log.d("Game", id);
        Log.d("Game", current);
        if(id.compareTo(current) == 0){
            increment();
        }else{
            reset();
        }
    }
    public void win(){
        for(Button btn : buttons){
            btn.setEnabled(false);
        }

        playAgain.setVisibility(View.VISIBLE);
        timer.stop();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
