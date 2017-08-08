package com.yuntu.demo.keyborddemo;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity implements View.OnTouchListener,KeyboardView.OnKeyboardActionListener {


    //--------------普通成员变量---------------

    private EditText mInputText;
    private KeyboardView mKeyboardView;
    private Keyboard mNumberKeyBorad;


    //--------------静态成员变量---------------

    private static String TAG = "MainActivity";


    //---------------静态方法区----------------




    //-------------实现父类/接口方法区-----------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mInputText = findViewById(R.id.input);
        mInputText.setOnTouchListener(this);
        mInputText.setInputType(InputType.TYPE_NULL);

        mNumberKeyBorad = new Keyboard(getApplicationContext(),R.xml.symbols);
        mKeyboardView = findViewById(R.id.keyboardview);

        mKeyboardView.setKeyboard(mNumberKeyBorad);
        mKeyboardView.setPreviewEnabled(false);
        mKeyboardView.setOnKeyboardActionListener(this);
        mKeyboardView.setBackgroundColor(Color.rgb(70,66,80));

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        if(MotionEvent.ACTION_UP == action){

            switch (view.getId()){

                case R.id.input:{

                    displayKeyboard();

                }break;
            }
        }
        return false;
    }


    @Override
    public void onPress(int i) {

        Log.d(TAG,"onPress: " + i);
    }

    @Override
    public void onRelease(int i) {

        Log.d(TAG,"onRelease: " + i);
        if(i == 111){
            hideKeyboard();
        }

    }

    @Override
    public void onKey(int i, int[] ints) {
        Log.d(TAG,"onRelease: " + i);
    }

    @Override
    public void onText(CharSequence charSequence) {
        Log.d(TAG,"onRelease: " + charSequence);
    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }


    //---------------普通方法区----------------

    public void hideKeyboard(){

        int keyboardVisiableStatus = mKeyboardView.getVisibility();
        if(View.VISIBLE == keyboardVisiableStatus){
            mKeyboardView.setVisibility(View.GONE);
        }
    }

    public void displayKeyboard(){

        int keyboardVisiableStatus = mKeyboardView.getVisibility();
        if(View.GONE == keyboardVisiableStatus){
            mKeyboardView.setVisibility(View.VISIBLE);
        }
    }

    //-----------------内部类------------------




    //---------------接口区-------------------


}
