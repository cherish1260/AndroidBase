package com.android.example.androidbase.mvptest.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.example.androidbase.R;
import com.android.example.androidbase.activity.MainActivity;
import com.android.example.androidbase.mvptest.login.model.User;
import com.android.example.androidbase.mvptest.login.presenter.UserPresener;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends Activity implements ILoginView {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.loading)
    ProgressBar loading;
    @BindView(R.id.btn_clear)
    Button btnClear;

    private UserPresener presener = new UserPresener(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_clear})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                presener.login();
                break;
            case R.id.btn_clear:
                presener.clear();
                break;
        }
    }

    @Override
    public String getUserName() {
        return etName.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPwd.getText().toString();
    }

    @Override
    public void clearUserName() {
        etName.setText("");
    }

    @Override
    public void clearPassword() {
        etPwd.setText("");
    }

    @Override
    public void showLoading() {
        loading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public void toMainActivity(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showFailedError() {
        Toast.makeText(this,
                "login failed", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_clear)
    public void onClick() {
    }
}
