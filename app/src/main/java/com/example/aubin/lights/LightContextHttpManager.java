package com.example.aubin.lights;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RoomContextHttpManager  {
    private ContextManagementActivity ContextManagementActivity;

    public RoomContextHttpManager(ContextManagementActivity ContextManagementActivity) {
        this.ContextManagementActivity = ContextManagementActivity;
    }


    public void retrieveLightContextState(final String light) {

        String url = "https://faircorp-aubin-rabouan.cleverapps.io/api/lights/" + light;

        RequestQueue queue = Volley.newRequestQueue(ContextManagementActivity);

        JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = Integer.parseInt(response.get("id").toString());
                            String status = response.get("status").toString();
                            int level = Integer.parseInt(response.get("level").toString());
                            int roomId = Integer.parseInt(response.get("roomId").toString());

                            ContextManagementActivity.onUpdateLight(new RoomContextState(id, status, level, roomId));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(contextRequest);

    }

    public void switchLight(final String light) {

        String url = "https://faircorp-aubin-rabouan.cleverapps.io/api/lights/" + light + "/switch";

        RequestQueue queue = Volley.newRequestQueue(ContextManagementActivity);

        final JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            int id = Integer.parseInt(response.get("id").toString());
                            String status = response.get("status").toString();
                            int level = Integer.parseInt(response.get("level").toString());
                            int roomId = Integer.parseInt(response.get("roomId").toString());


                            ContextManagementActivity.onUpdateLight(new RoomContextState(id, status, level, roomId));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(contextRequest);

    }
    public void deleteLight(final String light) {

        String url = "https://faircorp-aubin-rabouan.cleverapps.io/api/lights/" + light;

        RequestQueue queue = Volley.newRequestQueue(ContextManagementActivity);

        StringRequest contextRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // response
                        ContextManagementActivity.onDeleteLight();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        queue.add(contextRequest);

    }






}
