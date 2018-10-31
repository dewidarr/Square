package com.example.dewidar.repository.square;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;


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
        mIRequestView.setadapter(squareadapter);

         try {
            writeObject(context, "cachedSquarelist", list);
        } catch (IOException e) {
            Log.d("cashedlistpresenter", String.valueOf(e));

            e.printStackTrace();
        }

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

    @Override
    public void onFailure(String message) {

        if (mIRequestView != null) {
            try {
                Toast.makeText(context, "NoNetwork", Toast.LENGTH_SHORT).show();
               List<Squarevaluse> list=(List<Squarevaluse>) readObject(context,"cachedSquarelist");
               onSuccess(list);

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
    public static void writeObject(Context context, String key, Object object) throws IOException {
        FileOutputStream fos = context.openFileOutput(key, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.close();
        fos.close();
    }
    public static Object readObject(Context context, String key) throws IOException,
            ClassNotFoundException {
        FileInputStream fis = context.openFileInput(key);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

}



