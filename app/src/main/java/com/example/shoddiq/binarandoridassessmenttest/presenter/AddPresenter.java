package com.example.shoddiq.binarandoridassessmenttest.presenter;

import android.support.annotation.NonNull;

import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.ifaces.iAddView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddPresenter {
    private DatabaseReference databaseReference;
    private iAddView mView;

    public AddPresenter(iAddView mView) {
        this.mView = mView;
        databaseReference = FirebaseDatabase.getInstance().getReference(Constants.STUFF);
    }

    public void createDialog() {
        mView.setupDialog();
    }

    public void addStuff(Stuff stuff) {
        mView.showLoadingDialog(true);
        databaseReference.push().setValue(stuff).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isComplete()) {
                    mView.showLoadingDialog(false);
                }
            }
        });
    }
}
