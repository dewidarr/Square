package com.example.dewidar.repository.square;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SquareModel implements ISquareContract.IRequestModel {
    ISquareContract.IJsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    public List<Squarevaluse> downloadRequests(Context context, final onRequestFinishedListener listener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/users/square/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(ISquareContract.IJsonPlaceHolderApi.class);

        Call<List<Squarevaluse>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Squarevaluse>>() {

            @Override
            public void onResponse(Call<List<Squarevaluse>> call, retrofit2.Response<List<Squarevaluse>> response) {

                if (!response.isSuccessful()) {
                    //textViewResult.setText("Code: " + response.code());
                    Log.i("retrofit", "faild");
                    return;
                }



                List<Squarevaluse> posts = response.body();

                listener.onSuccess(posts);
                Log.i("retrofit", String.valueOf(posts));

            }

            @Override
            public void onFailure(Call<List<Squarevaluse>> call, Throwable t) {
                Log.d("retrofitfailur", String.valueOf(t));
                listener.onFailure("NoNetwork");
            }
        });
        return null;
    }

}
