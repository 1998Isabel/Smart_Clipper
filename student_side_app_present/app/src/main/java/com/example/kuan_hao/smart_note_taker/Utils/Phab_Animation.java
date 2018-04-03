package com.example.kuan_hao.smart_note_taker.Utils;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.animation.FastOutSlowInInterpolator;

import java.text.AttributedCharacterIterator;

/**
 * Created by chaokuanhao on 14/10/2017.
 */

/**
 * the main function of this class is that when the snack bar appears, the button will move~
 */

public class Phab_Animation extends FloatingActionButton.Behavior {


    private static final android.view.animation.Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsAnimatingOut = false;
    public Phab_Animation(Context context, AttributedCharacterIterator.Attribute attrs){
        super();
    }
}
