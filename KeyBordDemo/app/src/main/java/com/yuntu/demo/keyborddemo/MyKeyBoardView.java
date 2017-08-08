package com.yuntu.demo.keyborddemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.util.AttributeSet;

import java.util.List;

/**
 * Created by yanghaipeng on 2017/8/8.
 */

public class MyKeyBoardView extends KeyboardView {


    //------------------------普通成员变量------------------------

    private Context mContext;
    private int mConfirmBtnBackground;
    private float mKeyBoardFontSize;

    //------------------------静态成员变量---------------------------

    private static final String TAG = "MyKeyBoardView";

    //------------------------构造方法区------------------------------


    public MyKeyBoardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        mContext = context;
        getAttributeSet(attrs);
    }

    public MyKeyBoardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mContext = context;
        getAttributeSet(attrs);
    }

    public MyKeyBoardView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        mContext = context;
        getAttributeSet(attrs);
    }

    //------------------------静态方法区------------------------------




    //-------------------------实现父类/接口方法区----------------------

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Keyboard keyboard = this.getKeyboard();
        List<Keyboard.Key> keys = null;
        if(null != keyboard){
            keys = keyboard.getKeys();
        }
        for(int index = 0; (null != keys) && index < keys.size(); index ++){

            Keyboard.Key key = keys.get(index);
            int[] codes = key.codes;
            if(codes.length > 0 && codes[0] == Keyboard.KEYCODE_DONE){

                renderConfirmBtnBackground(canvas, key);
                renderConfirmBtnText(canvas, key);
            }
        }

    }

    //-----------------------------普通方法区--------------------------


    private void getAttributeSet(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.MyKeyBoardView);
        if(null != typedArray){
            mConfirmBtnBackground = typedArray.getColor(R.styleable.MyKeyBoardView_confirm_btn_fontcolor,getResources()
                    .getColor(R.color.keyboard_confirm_released_background, null));
            mKeyBoardFontSize = typedArray.getDimension(R.styleable.MyKeyBoardView_confirm_btn_fontsize,getResources()
                    .getDimension(R.dimen.keyboard_fontsize));
            typedArray.recycle();
        }
    }


    private void renderConfirmBtnText(Canvas canvas, Keyboard.Key key) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(mConfirmBtnBackground);
        paint.setTextSize(mKeyBoardFontSize);
        canvas.drawText(key.label.toString(),key.x + key.width / 2,key.y + key.height / 2,paint);
    }

    private void renderConfirmBtnBackground(Canvas canvas, Keyboard.Key key) {
        Drawable confirmDrawble = null;
        if(key.pressed){
            confirmDrawble = mContext.getDrawable(R.color.keyboard_confirm_pressed_background);
        }else{
            confirmDrawble = mContext.getDrawable(R.color.keyboard_confirm_released_background);
        }
        confirmDrawble.setBounds(key.x, key.y, key.x + key.width, key.y + key.height);
        confirmDrawble.draw(canvas);
    }

    //-------------------------------内部类-----------------------------




    //------------------------------接口区-------------------------------



}
