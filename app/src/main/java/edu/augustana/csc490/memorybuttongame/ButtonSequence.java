package edu.augustana.csc490.memorybuttongame;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by lindseynicolaysen11 on 4/1/2015.
 */
public class ButtonSequence {
    private ArrayList<Integer> seq;

    public ButtonSequence() {
        seq = new ArrayList<Integer>();
        for (int i = 1; i <= 9; i++) {
            seq.add(i);
        }
        randomize();
    }

    public ArrayList<Integer> getSeq() {
        return seq;
    }

    public int get(int index){
        return seq.get(index);
    }

    public void randomize() {
        Collections.shuffle(seq);
    }

    public String toString(){
        return seq.toString();
    }











}
