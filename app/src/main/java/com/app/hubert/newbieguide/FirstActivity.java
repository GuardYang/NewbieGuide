package com.app.hubert.newbieguide;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Builder;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.listener.OnGuideChangedListener;
import com.app.hubert.guide.listener.OnHighlightDrewListener;
import com.app.hubert.guide.model.GuidePage;
import com.app.hubert.guide.model.HighLight;
import com.app.hubert.guide.model.HighlightOptions;

public class FirstActivity extends AppCompatActivity {
    View anchorView;
    Button btnAnchor;
    Button btnSimple;
    Button btnRect;
    View llRelativeView;

    Controller controller1 = null;
    Controller controller2 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        btnSimple = (Button) findViewById(R.id.btn_simple);
        btnRect = (Button) findViewById(R.id.btn_rect);
        anchorView = findViewById(R.id.ll_anchor);
        llRelativeView = findViewById(R.id.ll_relative);

        btnAnchor = (Button) findViewById(R.id.btn_anchor);
        btnAnchor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Builder builder = NewbieGuide.with(FirstActivity.this);
                HighlightOptions options = new HighlightOptions.Builder()
                        .setOnHighlightDrewListener(new OnHighlightDrewListener() {
                            @Override
                            public void onHighlightDrew(Canvas canvas, RectF rectF) {
                                Paint paint = new Paint();
                                paint.setColor(Color.WHITE);
                                paint.setStyle(Paint.Style.STROKE);
                                paint.setStrokeWidth(10);
                            }
                        })
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (controller1 != null) {
                                    controller1.remove();
                                }
                            }
                        })
                        .build();
                GuidePage page = GuidePage.newInstance()
                        .addHighLightWithOptions(btnAnchor, HighLight.Shape.CIRCLE, options)
                        .addHighLightWithOptions(btnSimple, HighLight.Shape.CIRCLE, options)
                        .setEverywhereCancelable(false);//是否点击任意地方跳转下一页或者消失引导层，默认true
                builder.setLabel("anchorView")
                        .anchor(anchorView)
                        .addGuidePage(page)
                        .alwaysShow(true)//总是显示，调试时可以打开
                        .setOnGuideChangedListener(new OnGuideChangedListener() {
                            @Override
                            public void onShowed(Controller controller) {
                                Toast.makeText(FirstActivity.this, "引导层显示", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onRemoved(Controller controller) {
                                Toast.makeText(FirstActivity.this, "引导层消失", Toast.LENGTH_SHORT).show();
                            }
                        });
                controller1 = builder.build();
                controller1.show();


            }
        });
        btnRect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (controller2 != null) {
                    controller2.remove();
                }
                if (controller1 != null) {
                    controller1.remove();
                }

            }
        });
    }
}
