package com.example.subway_application;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class KeyboardActivity extends AppCompatActivity {
    //This method is from StackOverflow
    public void closeKeyboard(View v) {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    public Toast makeText(String text){
        return Toast.makeText(getApplicationContext(), text ,Toast.LENGTH_SHORT);
    }
}
