package com.bobby.sqlitemvp.view;

import com.bobby.sqlitemvp.adapters.UserAdapter;

public interface ListDataView {

    void listSuccess(UserAdapter userAdapter);
    void listFailure();
}
