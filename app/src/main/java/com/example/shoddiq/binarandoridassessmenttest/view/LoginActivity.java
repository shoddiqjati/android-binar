package com.example.shoddiq.binarandoridassessmenttest.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.shoddiq.binarandoridassessmenttest.R;
import com.example.shoddiq.binarandoridassessmenttest.presenter.LoginPresenter;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.ifaces.iLoginView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements iLoginView {

    @BindView(R.id.act_login_username_edit_text)
    EditText etUsername;
    @BindView(R.id.act_login_pass_edit_text)
    EditText etPassword;
    @BindView(R.id.login_button)
    Button btLogin;

    @OnClick(R.id.login_button)
    public void loginHandler() {
        boolean flag = false;
        if (etUsername.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Enter the username", Toast.LENGTH_SHORT).show();
            flag = false;
        } else flag = true;
        if (etPassword.getText().toString().trim().equals("")) {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show();
            flag = false;
        } else flag = true;

        if (flag) {
            presenter.validateLogin(etUsername.getText().toString(), etPassword.getText().toString());
        }
    }

    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenter(this);
    }

    @Override
    public void loginStatus(String status) {
        if (status.equals(Constants.FAILED)) {
            Toast.makeText(this, "Wrong Password", Toast.LENGTH_SHORT).show();
        } else if (status.equals(Constants.WRONG_USERNAME)) {
            Toast.makeText(this, "Invalid Username", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(Constants.LOGIN, status);
            startActivity(intent);
            finish();
        }
    }
}
