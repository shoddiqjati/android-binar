package com.example.shoddiq.binarandoridassessmenttest.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.shoddiq.binarandoridassessmenttest.R;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.act_detail_name_text_view)
    TextView tvName;
    @BindView(R.id.act_detail_date_text_view)
    TextView tvDate;
    @BindView(R.id.act_detail_supplier_text_view)
    TextView tvSupplier;
    @BindView(R.id.act_detail_total_text_view)
    TextView tvTotal;
    @BindView(R.id.act_detail_other_text_view)
    TextView tvOther;
    @BindView(R.id.act_detail_icon_text_view)
    TextView tvIcon;

    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        showData();
    }

    private void showData() {
        tvIcon.setText(bundle.getString(Constants.BUNDLE_NAME).substring(0, 1));
        tvName.setText(bundle.getString(Constants.BUNDLE_NAME));
        tvDate.setText(bundle.getString(Constants.BUNDLE_DATE));
        tvSupplier.setText(bundle.getString(Constants.BUNDLE_SUPPLIER));
        tvTotal.setText(String.valueOf(bundle.getInt(Constants.BUNDLE_TOTAL)));
        if (bundle.getString(Constants.BUNDLE_OTHER) != null) {
            tvOther.setText(bundle.getString(Constants.BUNDLE_OTHER));
        } else {
            tvOther.setText("Others");
        }
    }


}
