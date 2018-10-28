package com.example.dewidar.repository.square;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public interface ISquareContract {

    interface IRequestView{

        void setadapter(SquareAdapter adapter);

        void showAlert(String message);
    }

    interface IRequestPresenter{

        void getRequests(Context context, RecyclerView recyclerView);
        void onDestroy();
    }

    interface IRequestModel{

        interface onRequestFinishedListener{

            void onSuccess(List<Squarevaluse> list);
            void onFailure(String message);
        }

        List<Squarevaluse> downloadRequests(Context context, onRequestFinishedListener listener);
    }
}
