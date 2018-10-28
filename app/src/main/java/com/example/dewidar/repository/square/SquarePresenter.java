package com.example.dewidar.repository.square;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SquarePresenter implements ISquareContract.IRequestPresenter,ISquareContract.IRequestModel.onRequestFinishedListener {
    ISquareContract.IRequestView mIRequestView;
    ISquareContract.IRequestModel mIRequestModel;
    private SquareAdapter squareadapter;
    RecyclerView recyclerView;
    Context context;

    LinearLayoutManager manager;
    List<Squarevaluse> sharepreflist;

    public SquarePresenter(ISquareContract.IRequestView mIRequestView) {

        this.mIRequestView = mIRequestView;
        mIRequestModel = new SquareModel();
    }

    @Override
    public void getRequests(Context context, RecyclerView recyclerView) {
        // mIRequestView.showProgress();
        this.recyclerView = recyclerView;
        this.context = context;
        mIRequestModel.downloadRequests(context, this);
    }

    @Override
    public void onDestroy() {

        if (mIRequestView != null) {
            mIRequestView = null;
        }
    }

    @Override
    public void onSuccess(final List<Squarevaluse> list) {
        squareadapter = new SquareAdapter(recyclerView.getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(squareadapter);
        try {
            saveHiddenFormulas(context, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mIRequestView.setadapter(squareadapter);
/*

         RecyclerView.OnScrollListener recyclerViewOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                if (!isLoading && !isLastPage) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        Toast.makeText(context, "yes", Toast.LENGTH_SHORT).show();
                       // loadMoreItems();
                        squareadapter = new SquareAdapter(recyclerView.getContext(), list);
                        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext() ));
                        recyclerView.setAdapter(squareadapter);
                    }
                }
            }
        };
*/

    }

    public void saveHiddenFormulas(Context context, List<Squarevaluse> hiddenFormulas) throws IOException {
        Log.i("saved2", String.valueOf(hiddenFormulas));


        FileOutputStream fileOutputStream = context.openFileOutput("HiddenFormulas.txt", MODE_PRIVATE);
        ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
        out.writeObject(hiddenFormulas);

        for (int i = 0; i < hiddenFormulas.size(); i++) {
            Log.i("savedhiden", String.valueOf(hiddenFormulas.get(i)));
        }

        out.close();
        fileOutputStream.close();

    }

    @Override
    public void onFailure(String message) {

        if (mIRequestView != null) {
            // mIRequestView.hideProgress();
            mIRequestView.showAlert(message);
        }
    }

}



