package com.bobby.sqlitemvp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bobby.sqlitemvp.R;
import com.bobby.sqlitemvp.adapters.UserAdapter;
import com.bobby.sqlitemvp.model.ListDataModel;
import com.bobby.sqlitemvp.presenter.ListDataPresenter;
import com.bobby.sqlitemvp.utilities.DialogBox;
import com.bobby.sqlitemvp.view.ListDataView;

public class ListDataActivity extends AppCompatActivity implements ListDataView{

    RecyclerView recyclerView;
    ListDataPresenter listDataPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listDataPresenter = new ListDataModel(getApplicationContext(), this);

        listDataPresenter.loadData();
    }

    @Override
    public void listSuccess(UserAdapter userAdapter) {

        recyclerView.setAdapter(userAdapter);

    }

    @Override
    public void listFailure() {

        new DialogBox(this, "ERROR", "Unable to load data");

    }
}
