package com.example.shoddiq.binarandoridassessmenttest.view.ifaces;

import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;

import java.util.List;

public interface iMainView {
    void displayData(List<Stuff> stuffList);

    void setupView();

    void showLoading(boolean isLoading);

    void onItemTouch(int position);
}
