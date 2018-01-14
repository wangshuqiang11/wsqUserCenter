package com.bwei.wsq.wsqusercenter.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bwei.wsq.wsqusercenter.R;

import io.reactivex.annotations.Nullable;

/**
 * Created by WSQ on 2018/1/13 0013.
 */

public class AddDeleteView extends LinearLayout {
    private OnAddDelClickListener listener;
    private EditText etNumber;

    //对外提供一个点击的回调接口
    public interface OnAddDelClickListener {
        void onAddClick(View v);

        void onDelClick(View v);
    }

    public void setOnAddDelClickListener(OnAddDelClickListener listener) {
        if (listener != null) {
            this.listener = listener;
        }
    }

    public AddDeleteView(Context context) {
        this(context, null);
    }

    public AddDeleteView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AddDeleteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        View.inflate(context, R.layout.adddelete, this);
        TextView txtDelete = (TextView) findViewById(R.id.tv_delete);
        TextView txtAdd = (TextView) findViewById(R.id.tv_add);
        etNumber = (EditText) findViewById(R.id.ed_num);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AddDeleteViewStyle);

        String leftText = typedArray.getString(R.styleable.AddDeleteViewStyle_left_text);
        String rightText = typedArray.getString(R.styleable.AddDeleteViewStyle_right_text);
        String middleText = typedArray.getString(R.styleable.AddDeleteViewStyle_middle_text);

        txtDelete.setText(leftText);
        txtAdd.setText(rightText);
        etNumber.setText(middleText);

        //回收
        typedArray.recycle();


        txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onDelClick(view);
            }
        });

        txtAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onAddClick(view);
            }
        });

    }

    //对外提供一个修改数字的方法
    public void setNumber(int number) {
        if (number > 0) {
            etNumber.setText(number + "");
        }
    }

    //对外提供一个获取当前数字的方法
    public int getNumber() {
        String string = etNumber.getText().toString();
        int i = Integer.parseInt(string);
        return i;
    }
}
