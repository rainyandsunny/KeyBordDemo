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

public class MainActivity extends Activity implements View.OnTouchListener{


    //--------------普通成员变量---------------

    private EditText mInputText;
    private KeyBoardUtil mKeyBoardUtil;
    private MyKeyBoardView mMyKeyBoardView;


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

        mMyKeyBoardView = findViewById(R.id.keyboardview);



    }

    private void hideSystemKeyboard() {
        mInputText.setInputType(InputType.TYPE_NULL);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        int action = motionEvent.getAction();
        if(MotionEvent.ACTION_UP == action){

            switch (view.getId()){

                case R.id.input:{

                    if(null == mKeyBoardUtil){
                        mKeyBoardUtil = new KeyBoardUtil(mMyKeyBoardView);
                        mKeyBoardUtil.attachKeyboardToKeyboardView(getApplicationContext());
                    }
                    mKeyBoardUtil.setCurrentFocusEditText(mInputText);
                    mKeyBoardUtil.hideSystemKeyboard();
                    mKeyBoardUtil.displayKeyBoard();
                    mInputText.setCursorVisible(true);

                }break;
            }
        }
        return false;
    }



    //---------------普通方法区----------------



    //-----------------内部类------------------




    //---------------接口区-------------------


}
