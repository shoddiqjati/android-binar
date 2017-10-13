package com.example.shoddiq.binarandoridassessmenttest.view;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.shoddiq.binarandoridassessmenttest.R;
import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;
import com.example.shoddiq.binarandoridassessmenttest.presenter.AddPresenter;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.ifaces.iAddView;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity implements iAddView {

    @BindView(R.id.act_add_toolbar)
    Toolbar toolbar;
    @BindView(R.id.act_add_name_edit_text)
    EditText etName;
    @BindView(R.id.act_add_supplier_edit_text)
    EditText etSupplier;
    @BindView(R.id.act_add_date_edit_text)
    EditText etDate;
    @BindView(R.id.act_add_total_edit_text)
    EditText etTotal;
    @BindView(R.id.act_add_other_edit_text)
    EditText etOther;

    AddPresenter presenter;
    Context mContext = this;
    ProgressDialog dialog;

    private static final String TAG = AddActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new AddPresenter(this);
        presenter.createDialog();

        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int nowYear = calendar.get(Calendar.YEAR);
                int nowMonth = calendar.get(Calendar.MONTH);
                int nowDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month++;
                        etDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, nowYear, nowMonth, nowDay);
                datePickerDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                validateInput();
                break;
            case android.R.id.home:
                super.onBackPressed();
                break;
        }
        return true;
    }

    public void validateInput() {
        boolean flag = false;
        if (etName.getText().toString().trim().equals("")) {
            etName.setError(Constants.FILL);
            flag = false;
        } else flag = true;
        if (etSupplier.getText().toString().trim().equals("")) {
            etSupplier.setError(Constants.FILL);
            flag = false;
        } else flag = true;
        if (etDate.getText().toString().trim().equals("")) {
            etDate.setError(Constants.FILL);
            flag = false;
        } else flag = true;
        if (etTotal.getText().toString().trim().equals("")) {
            etTotal.setError(Constants.FILL);
            flag = false;
        } else flag = true;

        if (flag) {
            Stuff stuff = new Stuff();
            stuff.setName(etName.getText().toString());
            stuff.setSupplier(etSupplier.getText().toString());
            stuff.setDate(etDate.getText().toString());
            stuff.setTotal(Integer.parseInt(etTotal.getText().toString()));
            if (!etOther.getText().toString().trim().equals("")) {
                stuff.setOther(etOther.getText().toString());
            }
            presenter.addStuff(stuff);
            Log.d(TAG, "complete");
        }
    }

    @Override
    public void showLoadingDialog(boolean isLoading) {
        if (isLoading) {
            dialog.show();
        } else {
            dialog.dismiss();
            finish();
        }
    }

    @Override
    public void setupDialog() {
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
    }
}
