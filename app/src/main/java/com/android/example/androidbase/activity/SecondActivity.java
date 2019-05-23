package com.android.example.androidbase.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.example.androidbase.R;

import androidx.annotation.Nullable;

@Route(path = "/app/second")
public class SecondActivity extends Activity {
    @Autowired
    String from;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        ARouter.getInstance().inject(this);
        Bundle bundle = getIntent().getExtras();
        TextView tvText = findViewById(R.id.tv_text);
        tvText.setText(from);
        Button btnBack = findViewById(R.id.btn_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setResult(999);
//                finish();
                ARouter.getInstance().build("/modulea/main").navigation(SecondActivity.this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        System.out.println(postcard);
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        System.out.println(postcard);
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        System.out.println(postcard);
                    }

                    @Override
                    public void onInterrupt(Postcard postcard) {
                        System.out.println(postcard);
                    }
                });
            }
        });
    }
}
