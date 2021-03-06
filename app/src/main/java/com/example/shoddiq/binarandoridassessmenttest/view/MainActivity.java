package com.example.shoddiq.binarandoridassessmenttest.view;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.example.shoddiq.binarandoridassessmenttest.R;
import com.example.shoddiq.binarandoridassessmenttest.helper.DividerItemDecoration;
import com.example.shoddiq.binarandoridassessmenttest.model.Stuff;
import com.example.shoddiq.binarandoridassessmenttest.presenter.MainPresenter;
import com.example.shoddiq.binarandoridassessmenttest.utils.Constants;
import com.example.shoddiq.binarandoridassessmenttest.view.adapter.MainAdapter;
import com.example.shoddiq.binarandoridassessmenttest.view.ifaces.iMainView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements iMainView {

    @BindView(R.id.act_main_toolbar)
    Toolbar toolbar;
    @BindView(R.id.act_main_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.act_main_no_record_linear_layout)
    LinearLayout linearLayout;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    @OnClick(R.id.fab)
    public void fabHandler() {
        startActivity(new Intent(this, AddActivity.class));
    }

    MainPresenter presenter;
    List<Stuff> stuffList;
    MainAdapter adapter;
    String status;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        presenter = new MainPresenter(this);
        presenter.onCreateView();
        presenter.loadStuff();

        Intent intent = getIntent();
        status = intent.getStringExtra(Constants.LOGIN);
        if (status != null) {
            if (status.equals(Constants.WRITER)) {
                fab.setVisibility(View.VISIBLE);
            } else {
                fab.setVisibility(View.GONE);
            }
        } else {
            fab.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem search = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(search);
        search(searchView);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void search(SearchView searchView) {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                Log.d(TAG, newText);
                return true;
            }
        });
    }

    @Override
    public void displayData(List<Stuff> stuffList) {
        if (stuffList.size() != 0) {
            this.stuffList = stuffList;
            adapter = new MainAdapter(this, this.stuffList);
            adapter.notifyDataSetChanged();
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.GONE);
        } else {
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setupView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        RecyclerView.ItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(divider);
    }

    @Override
    public void showLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        presenter.loadStuff();
        super.onResume();
    }
}