package com.yiqiding.ktvbox.widget;

import android.app.Dialog;
import android.app.Service;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiqiding.ktvbox.R;
import com.yiqiding.ktvbox.widget.fragment.MainTopFragment;

/**
 * BaseKeyBoardDialog.java
 * com.yiqiding.ktvbox.widget
 * <p/>
 * Created by culm on 13-11-28.
 * Copyright (c) 2013 YiQiDing Inc. All rights reserved.
 */
public class BaseKeyBoardDialog extends Dialog implements View.OnClickListener{

    private MainTopFragment fg;

    private ImageView iv_hide;
    private TextView tv_result;

    private ImageButton btn_clear;
    private ImageButton btn_delete;

    private Button btn_A;
    private Button btn_B;
    private Button btn_C;
    private Button btn_D;
    private Button btn_E;
    private Button btn_F;

    private Button btn_G;
    private Button btn_H;
    private Button btn_I;
    private Button btn_J;
    private Button btn_K;
    private Button btn_L;

    private Button btn_M;
    private Button btn_N;
    private Button btn_O;
    private Button btn_P;
    private Button btn_Q;
    private Button btn_R;

    private Button btn_S;
    private Button btn_T;
    private Button btn_U;
    private Button btn_V;
    private Button btn_W;
    private Button btn_X;
    private Button btn_Y;
    private Button btn_Z;

    public BaseKeyBoardDialog(MainTopFragment fg) {
        super(fg.getActivity(), R.style.KeyBoardTheme);
        this.fg=fg;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.keyboard_main);
        try {
            initUI();
            setListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initUI() throws Exception{

        iv_hide= (ImageView) findViewById(R.id.iv_hide);
        tv_result=(TextView)findViewById(R.id.tv_result);
        btn_clear= (ImageButton) findViewById(R.id.btn_clear);
        btn_delete=(ImageButton)findViewById(R.id.btn_delete);

        btn_A=(Button)findViewById(R.id.btn_a);
        btn_B=(Button)findViewById(R.id.btn_b);
        btn_C=(Button)findViewById(R.id.btn_c);
        btn_D=(Button)findViewById(R.id.btn_d);
        btn_E=(Button)findViewById(R.id.btn_e);
        btn_F=(Button)findViewById(R.id.btn_f);

        btn_G=(Button)findViewById(R.id.btn_g);
        btn_H=(Button)findViewById(R.id.btn_h);
        btn_I=(Button)findViewById(R.id.btn_i);
        btn_J=(Button)findViewById(R.id.btn_j);
        btn_K=(Button)findViewById(R.id.btn_k);
        btn_L=(Button)findViewById(R.id.btn_l);

        btn_M=(Button)findViewById(R.id.btn_m);
        btn_N=(Button)findViewById(R.id.btn_n);
        btn_O=(Button)findViewById(R.id.btn_o);
        btn_P=(Button)findViewById(R.id.btn_p);
        btn_Q=(Button)findViewById(R.id.btn_q);
        btn_R=(Button)findViewById(R.id.btn_r);

        btn_S=(Button)findViewById(R.id.btn_s);
        btn_T=(Button)findViewById(R.id.btn_t);
        btn_U=(Button)findViewById(R.id.btn_u);
        btn_V=(Button)findViewById(R.id.btn_v);
        btn_W=(Button)findViewById(R.id.btn_w);
        btn_X=(Button)findViewById(R.id.btn_x);

        btn_Y=(Button)findViewById(R.id.btn_y);
        btn_Z=(Button)findViewById(R.id.btn_z);

        ///
    }

    private void setListener() throws Exception
    {
        iv_hide.setOnClickListener(new HideClickListener());

        btn_clear.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        btn_A.setOnClickListener(this);
        btn_B.setOnClickListener(this);
        btn_C.setOnClickListener(this);
        btn_D.setOnClickListener(this);
        btn_E.setOnClickListener(this);
        btn_F.setOnClickListener(this);

        btn_G.setOnClickListener(this);
        btn_H.setOnClickListener(this);
        btn_I.setOnClickListener(this);
        btn_J.setOnClickListener(this);
        btn_K.setOnClickListener(this);
        btn_L.setOnClickListener(this);

        btn_M.setOnClickListener(this);
        btn_N.setOnClickListener(this);
        btn_O.setOnClickListener(this);
        btn_P.setOnClickListener(this);
        btn_Q.setOnClickListener(this);
        btn_R.setOnClickListener(this);

        btn_S.setOnClickListener(this);
        btn_T.setOnClickListener(this);
        btn_U.setOnClickListener(this);
        btn_V.setOnClickListener(this);
        btn_W.setOnClickListener(this);
        btn_X.setOnClickListener(this);

        btn_Y.setOnClickListener(this);
        btn_Z.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            switch (view.getId())
            {
                case R.id.btn_delete:
                    deleteChar();
                    break;
                case R.id.btn_clear:
                    clearChar();
                    break;
                case R.id.btn_a:
                    insertChar('A');
                    break;
                case R.id.btn_b:
                    insertChar('B');
                    break;
                case R.id.btn_c:
                    insertChar('C');
                    break;
                case R.id.btn_d:
                    insertChar('D');
                    break;
                case R.id.btn_e:
                    insertChar('E');
                    break;
                case R.id.btn_f:
                    insertChar('F');
                    break;
                case R.id.btn_g:
                    insertChar('G');
                    break;
                case R.id.btn_h:
                    insertChar('H');
                    break;
                case R.id.btn_i:
                    insertChar('I');
                    break;
                case R.id.btn_j:
                    insertChar('J');
                    break;
                case R.id.btn_k:
                    insertChar('K');
                    break;
                case R.id.btn_l:
                    insertChar('L');
                    break;
                case R.id.btn_m:
                    insertChar('M');
                    break;
                case R.id.btn_n:
                    insertChar('N');
                    break;
                case R.id.btn_o:
                    insertChar('O');
                    break;
                case R.id.btn_p:
                    insertChar('P');
                    break;
                case R.id.btn_q:
                    insertChar('Q');
                    break;
                case R.id.btn_r:
                    insertChar('R');
                    break;
                case R.id.btn_s:
                    insertChar('S');
                    break;
                case R.id.btn_t:
                    insertChar('T');
                    break;
                case R.id.btn_u:
                    insertChar('U');
                    break;
                case R.id.btn_v:
                    insertChar('V');
                    break;
                case R.id.btn_w:
                    insertChar('W');
                    break;
                case R.id.btn_x:
                    insertChar('X');
                    break;
                case R.id.btn_y:
                    insertChar('Y');
                    break;
                case R.id.btn_z:
                    insertChar('Z');
                    break;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void insertChar(char c) throws Exception{

        //vi
        ((Vibrator) fg.getActivity().getSystemService(Service.VIBRATOR_SERVICE)).vibrate(300);

        if(tv_result.getText().toString().length()>=10)
            return;
        tv_result.setText(new StringBuffer(tv_result.getText().toString()).append(c).toString());
    }

    private void clearChar() throws Exception{
        tv_result.setText("");
    }

    private void deleteChar() throws Exception{

        if(tv_result==null|| TextUtils.isEmpty(tv_result.getText().toString()))
            return;

        StringBuffer sb=new StringBuffer(tv_result.getText().toString());

        sb.deleteCharAt(sb.toString().length()-1);

        tv_result.setText(sb.toString());


    }

    private class HideClickListener implements View.OnClickListener
    {

        @Override
        public void onClick(View view) {
            dismiss();
        }
    }

}
