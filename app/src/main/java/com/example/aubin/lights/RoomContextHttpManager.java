package com.example.aubin.lights;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class RoomContextHttpManager extends Context {
    String CONTEXT_SERVER_URL = "http://10.0.2.2:8083/";

    public void switchLight(RoomContextState state, String room){
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = CONTEXT_SERVER_URL + "/" + room + "/";

//get room sensed context
        final JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String id = response.getString("id").toString();
                            int lightLevel = Integer.parseInt(response.getJSONObject("light").get("level").toString());
                            String lightStatus = response.getJSONObject("light").get("status").toString();
                            // do something with results...
                            onUpdate(this);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Some error to access URL : Room may not exists...
                    }
                });
        queue.add(contextRequest);

    }



    public void retrieveRoomContextState(String room){


    }


    private void onUpdate(Response.Listener<JSONObject> listener) {



    }




}
