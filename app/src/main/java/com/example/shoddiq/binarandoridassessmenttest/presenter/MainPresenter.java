package com.example.shoddiq.binarandoridassessmenttest.presenter;

import android.util.Log;

import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.ifaces.iMainView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import durdinapps.rxfirebase2.DataSnapshotMapper;
import durdinapps.rxfirebase2.RxFirebaseChildEvent;
import durdinapps.rxfirebase2.RxFirebaseDatabase;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.BlockingMultiObserver;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter {
    private iMainView mView;
    private DatabaseReference databaseReference;

    private static final String TAG = MainPresenter.class.getSimpleName();

    public MainPresenter() {
    }

    public MainPresenter(iMainView mView) {
        this.mView = mView;
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.STUFF);
    }

    public void onCreateView() {
        mView.setupView();
    }

    public void loadStuff() {
        mView.showLoading(true);
        RxFirebaseDatabase.observeValueEvent(databaseReference, DataSnapshotMapper.listOf(Stuff.class))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Stuff>>() {
                    @Override
                    public void accept(List<Stuff> stuffList) throws Exception {
                        mView.displayData(stuffList);
                        mView.showLoading(false);
                    }
                });
    }
}
