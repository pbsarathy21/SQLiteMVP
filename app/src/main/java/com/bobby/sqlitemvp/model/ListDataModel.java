package com.bobby.sqlitemvp.model;

import android.content.Context;
import android.database.Cursor;

import com.bobby.sqlitemvp.adapters.UserAdapter;
import com.bobby.sqlitemvp.modelClasses.User;
import com.bobby.sqlitemvp.presenter.ListDataPresenter;
import com.bobby.sqlitemvp.utilities.DatabaseHelper;
import com.bobby.sqlitemvp.view.ListDataView;

import java.util.List;

public class ListDataModel implements ListDataPresenter{

    private Context context;
    private ListDataView listDataView;
    private UserAdapter userAdapter;
    private List<User> userList;

    public ListDataModel(Context context, ListDataView listDataView) {
        this.context = context;
        this.listDataView = listDataView;
    }

    @Override
    public void loadData() {

        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        Cursor cursor = databaseHelper.getAllData();

        if (cursor.moveToFirst())
        {
            do {
                userList.add(new User(cursor.getString(1), cursor.getString(4)));
            } while (cursor.moveToNext());
        }

        if (!userList.isEmpty())
        {
            userAdapter = new UserAdapter(userList, context);
            listDataView.listSuccess(userAdapter);
        }

        else
        {
            listDataView.listFailure();
        }
    }
}
