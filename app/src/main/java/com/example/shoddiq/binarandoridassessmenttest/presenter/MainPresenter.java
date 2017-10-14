package com.example.shoddiq.binarandoridassessmenttest.presenter;

import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;
import com.example.shoddiq.binarandoridassessmenttest.network.NetworkApi;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.ifaces.iMainView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private iMainView mView;
    private DatabaseReference databaseReference;
    private NetworkApi mNetwork;

    private static final String TAG = MainPresenter.class.getSimpleName();

    public MainPresenter() {
    }

    public MainPresenter(iMainView mView) {
        this.mView = mView;
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.STUFF);
        mNetwork = new NetworkApi();
    }

    public void onCreateView() {
        mView.setupView();
    }

    public void loadStuff() {
        mView.showLoading(true);
        mNetwork.getApi()
                .getAllStuff()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HashMap<String, Stuff>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull HashMap<String, Stuff> stringStuffHashMap) {
                        List<Stuff> stuffList = new ArrayList<>();
                        for (Stuff stuff: stringStuffHashMap.values()) {
                            stuffList.add(stuff);
                        }
                        mView.displayData(stuffList);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (e.getLocalizedMessage().contains("Null")) {
                            mView.displayData(new ArrayList<Stuff>());
                            mView.showLoading(false);
                        }
                    }

                    @Override
                    public void onComplete() {
                        mView.showLoading(false);
                    }
                });
    }
}
