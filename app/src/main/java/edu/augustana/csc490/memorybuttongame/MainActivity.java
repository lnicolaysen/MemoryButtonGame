package edu.augustana.csc490.memorybuttongame;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    Button button1, button2, button3, button4, button5, button6, button7, button8, button9;
    int device_width;
    Display display;
    Point size;
    int button_width;
    int buttonColor;
    int sequence_index;
    ButtonSequence bSeq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get device width
        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        device_width = size.x;

        buttonColor = Color.argb(255, 255, 119, 89);
        sequence_index = 0;
        //create buttons
        //set button width to fit any size screen
        button_width = device_width / 3 - 30;
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);

        button1.setWidth(button_width);
        button2.setWidth(button_width);
        button3.setWidth(button_width);
        button4.setWidth(button_width);
        button5.setWidth(button_width);
        button6.setWidth(button_width);
        button7.setWidth(button_width);
        button8.setWidth(button_width);
        button9.setWidth(button_width);
        //create and set click listener

        button1.setOnClickListener(buttonListener);
        button2.setOnClickListener(buttonListener);
        button3.setOnClickListener(buttonListener);
        button4.setOnClickListener(buttonListener);
        button5.setOnClickListener(buttonListener);
        button6.setOnClickListener(buttonListener);
        button7.setOnClickListener(buttonListener);
        button8.setOnClickListener(buttonListener);
        button9.setOnClickListener(buttonListener);

        //get and test sequence
        bSeq = new ButtonSequence();
        String s = bSeq.toString();
        Log.d("gameTag", s);

    }

    View.OnClickListener buttonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            checkMove(v);
            //restart if not
            //increment if yes
            v.setBackgroundColor(Color.DKGRAY);

        }
    };
    //set button colors back, reset point reached in sequence to 0
    public void reset() {
        button1.setBackgroundColor(buttonColor);
        button2.setBackgroundColor(buttonColor);
        button3.setBackgroundColor(buttonColor);
        button4.setBackgroundColor(buttonColor);
        button5.setBackgroundColor(buttonColor);
        button6.setBackgroundColor(buttonColor);
        button7.setBackgroundColor(buttonColor);
        button8.setBackgroundColor(buttonColor);
        button9.setBackgroundColor(buttonColor);
        sequence_index=0;
    }


    public void increment(){
        sequence_index++;
    }

    public void checkMove(View v){
        Resources res = v.getResources();
        String id = res.getResourceEntryName(v.getId());
        String current = "button" + bSeq.get(sequence_index);

        if(id == current){
            increment();
        }else{
            reset();
        }

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
