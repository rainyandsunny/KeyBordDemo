package com.yuntu.demo.keyborddemo;

import android.content.Context;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.os.Build;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Method;

/**
 * Created by yanghaipeng on 2017/8/8.
 */

public class KeyBoardUtil implements KeyboardView.OnKeyboardActionListener{

    //------------------------普通成员变量------------------------

    private KeyboardView mKeyBoardView;
    private Keyboard mNumberKeyborad;
    private EditText mCurrentFoucusEditText;
    private Editable mEditable;
    private int mStart;
    private Context mContext;

    //------------------------静态成员变量---------------------------


    //------------------------构造方法区------------------------------

    public KeyBoardUtil(KeyboardView keyboardView){

        this.mKeyBoardView = keyboardView;
    }


    //------------------------静态方法区------------------------------




    //-------------------------实现父类/接口方法区----------------------

    @Override
    public void onPress(int i) {


    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int i, int[] ints) {

        mEditable = mCurrentFoucusEditText.getText();
        mStart = mCurrentFoucusEditText.getSelectionStart();

        if(Keyboard.KEYCODE_DONE == i){

            hideKeyboard();

        }else if(Keyboard.KEYCODE_DELETE == i){

            if (mEditable != null && mEditable.length() > 0) {
                if (mStart > 0) {
                    mEditable.delete(mStart - 1, mStart);
                }
            }
        }else if(Keyboard.KEYCODE_CANCEL == i){

            mCurrentFoucusEditText.setText("");

        }else{
            mEditable.insert(mStart, Character.toString((char) i));
        }
    }

    @Override
    public void onText(CharSequence charSequence) {

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



    //-----------------------------普通方法区--------------------------



    public void attachKeyboardToKeyboardView(Context context){

        mContext = context;
        mNumberKeyborad = new Keyboard(context,R.xml.symbols);
        if(null != mKeyBoardView){
            mKeyBoardView.setKeyboard(mNumberKeyborad);
        }
        mKeyBoardView.setPreviewEnabled(false);
        mKeyBoardView.setOnKeyboardActionListener(this);
    }

    private void hideKeyboard(){

        int keyboardVisiableStatus = mKeyBoardView.getVisibility();
        if(View.VISIBLE == keyboardVisiableStatus){
            mKeyBoardView.setVisibility(View.GONE);
        }
    }

    public void displayKeyBoard(){

        int keyboardVisiableStatus = mKeyBoardView.getVisibility();
        if(View.GONE == keyboardVisiableStatus){
            mKeyBoardView.setVisibility(View.VISIBLE);
        }
    }

    public void setCurrentFocusEditText(EditText currentFocusEditText){
        this.mCurrentFoucusEditText  = currentFocusEditText;
    }

    public void hideSystemKeyboard() {
        if(null == mCurrentFoucusEditText){
            throw new RuntimeException("method setCurrentFocusEditText must be called before hideSystemKeyboard()");
        }

        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.HONEYCOMB) {
            try {
                Class<EditText> cls = EditText.class;
                Method setShowSoftInputOnFocus;
                setShowSoftInputOnFocus = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                setShowSoftInputOnFocus.setAccessible(true);
                setShowSoftInputOnFocus.invoke(mCurrentFoucusEditText, false);

            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            mCurrentFoucusEditText.setInputType(InputType.TYPE_NULL);
        }
        // 如果软键盘已经显示，则隐藏
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mCurrentFoucusEditText.getWindowToken(), 0);
    }

    private void unAttachKeyboardToKeyboardView(){

        mNumberKeyborad = null;
        if(null != mKeyBoardView){
            mKeyBoardView.setKeyboard(null);
            mKeyBoardView = null;
        }
        mCurrentFoucusEditText = null;
    }


    //-------------------------------内部类-----------------------------




    //------------------------------接口区-------------------------------


}
