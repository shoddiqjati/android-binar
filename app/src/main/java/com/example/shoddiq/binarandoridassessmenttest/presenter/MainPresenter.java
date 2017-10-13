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
        mView.setupListener();
    }

    public void loadStuff() {
        mView.showLoading(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Stuff> stuffList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    stuffList.add(snapshot.getValue(Stuff.class));
                }
                mView.displayData(stuffList);
                mView.showLoading(false);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, databaseError.getMessage());
            }
        });
    }
}
