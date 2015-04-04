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
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    int device_width;
    Display display;
    Point size;
    int button_width;
    int buttonColor;
    int clickedColor;
    Button[] buttons;
    int sequence_index;
    TextView description;
    Button playAgain;
    ButtonSequence bSeq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        description = (TextView) findViewById(R.id.textView);
        buttonColor = Color.argb(255, 255, 119, 89);
        clickedColor = Color.LTGRAY;
        sequence_index = 0;

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
        button_width = device_width / 3 - 30;
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
        for(Button b: buttons){
            b.setWidth(button_width);
            b.setOnClickListener(buttonListener);
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
                btn.setVisibility(View.VISIBLE);
                }
            playAgain.setVisibility(View.INVISIBLE);
            description.setText(R.string.description);
        }
    };
    //set button colors back, reset point reached in description to 0
    public void reset() {
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void win(){
for(Button btn : buttons){
    btn.setVisibility(View.INVISIBLE);
}
description.setText("You win!");
playAgain.setVisibility(View.VISIBLE);

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
