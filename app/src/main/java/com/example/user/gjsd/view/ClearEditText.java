package com.example.user.gjsd.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.example.user.gjsd.R;

/**
 * Created by TonyChoi on 2016. 4. 4..
 */
public class ClearEditText extends RelativeLayout {

    LayoutInflater inflater = null;
    EditText editText;
    Button btnClear;

    public ClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayout();
    }

    public ClearEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayout();
    }

    public ClearEditText(Context context) {
        super(context);
        setLayout();
    }

    private void setLayout() {
        inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.clearable_edit_text, this, true);

        editText = (EditText) findViewById(R.id.autoCompleteTextView);
        btnClear = (Button) findViewById(R.id.deletebutton);
        btnClear.setVisibility(RelativeLayout.INVISIBLE);

        clearText();
        showHideClearButton();
    }

    private void showHideClearButton() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    btnClear.setVisibility(RelativeLayout.VISIBLE);
                } else {
                    btnClear.setVisibility(RelativeLayout.INVISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void clearText() {
        btnClear.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });
    }

    public Editable getText() {
        Editable text = editText.getText();
        return text;
    }
}


