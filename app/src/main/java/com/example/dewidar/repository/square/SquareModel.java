package com.example.dewidar.repository.square;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SquareModel implements ISquareContract.IRequestModel {

    ISquareContract.IRequestView mIRequestView;
    private ArrayList<Squarevaluse> requests;
    String url = "https://api.github.com/users/square/repos";

    @Override
    public List<Squarevaluse> downloadRequests(final Context context, final onRequestFinishedListener listener) {

        requests = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.i("GitResponse", response.toString());


                        try {
                            for (int i = 0; i < response.length(); i++) {
                                // Get current json object
                                JSONObject squareobj = response.getJSONObject(i);


                                String name = squareobj.getString("name");
                                String descrption = squareobj.getString("description");
                                String repoFork=squareobj.getString("fork");
                                String repoUrl=squareobj.getString("html_url");

                                JSONObject ownerinfo = squareobj.getJSONObject("owner");
                                String ownerurl=ownerinfo.getString("html_url");
                                String ownerName=ownerinfo.getString("login");

                                requests.add(new Squarevaluse(name,descrption,ownerName,repoFork,repoUrl,ownerurl));
                                Log.i("ownerurl", ownerurl.toString());
                            }
                            Log.i("squarerequst", requests.toString());
                            listener.onSuccess(requests);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Erroe", error.toString());
                        listener.onFailure("noNetwork");
                    }
                });
        Volley.newRequestQueue(context).add(jsonArrayRequest);

        return requests;

    }


}
