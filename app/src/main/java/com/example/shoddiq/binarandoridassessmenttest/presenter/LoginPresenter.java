package com.example.shoddiq.binarandoridassessmenttest.presenter;

import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.ifaces.iLoginView;

public class LoginPresenter {

    private iLoginView mView;

    public LoginPresenter(iLoginView mView) {
        this.mView = mView;
    }

    public void validateLogin(String username, String password) {
        switch (username) {
            case Constants.WRITER:
                if (password.equals(Constants.WRITER)) {
                    mView.loginStatus(Constants.WRITER);
                } else {
                    mView.loginStatus(Constants.FAILED);
                }
                break;
            case "reader1":
                if (password.equals(Constants.READER)) {
                    mView.loginStatus(Constants.READER);
                } else {
                    mView.loginStatus(Constants.FAILED);
                }
                break;
            case "reader2":
                if (password.equals(Constants.READER)) {
                    mView.loginStatus(Constants.READER);
                } else {
                    mView.loginStatus(Constants.FAILED);
                }
                break;
            case "reader3":
                if (password.equals(Constants.READER)) {
                    mView.loginStatus(Constants.READER);
                } else {
                    mView.loginStatus(Constants.FAILED);
                }
                break;
            case "reader4":
                if (password.equals(Constants.READER)) {
                    mView.loginStatus(Constants.READER);
                } else {
                    mView.loginStatus(Constants.FAILED);
                }
                break;
            case "reader5":
                if (password.equals(Constants.READER)) {
                    mView.loginStatus(Constants.READER);
                } else {
                    mView.loginStatus(Constants.FAILED);
                }
                break;
            default: mView.loginStatus(Constants.WRONG_USERNAME);
                break;
        }
    }
}
