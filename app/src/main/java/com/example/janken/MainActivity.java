package com.example.janken;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.view.*;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv2;
    ImageView iv1;
    ImageView iv2;
    ImageView[] iv = new ImageView[3];
    int player = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTitle("じゃんけんゲーム");

        LinearLayout ll1 = new LinearLayout(this);
        ll1.setOrientation(LinearLayout.VERTICAL);
        setContentView(ll1);

        TextView tv1 = new TextView(this);
        tv1.setText("さいしょはぐー！");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextSize(25);
        ll1.addView(tv1);

        LinearLayout ll2 = new LinearLayout(this);
        ll2.setOrientation(LinearLayout.HORIZONTAL);
        ll1.addView(ll2);

        iv1 = new ImageView(this);
        iv2 = new ImageView(this);

        for(int i = 0; i < iv.length; i++) {
            iv[i] =new ImageView(this);
        }

        //画面サイズの取得
        WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
        Display dp = wm.getDefaultDisplay();
        Point p = new Point();
        dp.getSize(p);

        iv1.setImageResource(R.drawable.gu);
        iv2.setImageResource(R.drawable.gu);

        ll2.addView(iv1, p.x / 2, p.y / 3);
        ll2.addView(iv2, p.x / 2, p.y / 3);

        LinearLayout ll3 = new LinearLayout(this);
        ll3.setOrientation(LinearLayout.HORIZONTAL);
        ll1.addView(ll3);

        iv[0].setImageResource(R.drawable.gu);
        iv[1].setImageResource(R.drawable.tyoki);
        iv[2].setImageResource(R.drawable.pa);

        for(int i = 0; i < iv.length; i++) {
            ll3.addView(iv[i], p.x / 3, p.y / 3);
        }

        tv2 = new TextView(this);
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextSize(25);
        ll1.addView(tv2);

        for(int i = 0; i < iv.length; i++) {
            iv[i].setOnClickListener(new SampleClickListener());
        }

    }

    class SampleClickListener implements View.OnClickListener {
    @Override
        public void onClick(View v) {
            Random ran = new Random();
            int dom = ran.nextInt(3);

            if (dom == 0) {
                iv1.setImageResource(R.drawable.gu);
            }else if (dom == 1) {
                iv1.setImageResource(R.drawable.tyoki);
            }else if (dom == 2) {
                iv1.setImageResource(R.drawable.pa);
            }

            if (iv[0] == v) {
                iv2.setImageResource(R.drawable.gu);
                player = 0;
            } else if (iv[1] == v) {
                iv2.setImageResource(R.drawable.tyoki);
                player = 1;
            } else if (iv[2] == v) {
                iv2.setImageResource(R.drawable.pa);
                player = 2;
            }

            if(dom == player) {
                tv2.setText("あいこで…");
                tv2.setTextColor(Color.BLACK);
            }else if((dom == 0 && player == 1) || (dom == 1 && player == 2) || (dom == 2 && player == 0)) {
                tv2.setText("あなたの負け！");
                tv2.setTextColor(Color.BLUE);
            }else {
                tv2.setText("あなたの勝ち！");
                tv2.setTextColor(Color.RED);
            }
        }
    }
}
