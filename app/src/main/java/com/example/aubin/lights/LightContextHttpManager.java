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

public class LightContextHttpManager {
    private ContextManagementActivity ContextManagementActivity;
    private String baseurl = "https://faircorp-app-deguise.cleverapps.io/api/lights/";

    public LightContextHttpManager(ContextManagementActivity ContextManagementActivity) {
        this.ContextManagementActivity = ContextManagementActivity;
    }


    public void retrieveLightContextState(final String light) {

        String url = baseurl + light;

        RequestQueue queue = Volley.newRequestQueue(ContextManagementActivity);

        JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        response(response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(contextRequest);

    }

    public void switchLight(final String light) {

        String url = baseurl + light + "/switch";

        RequestQueue queue = Volley.newRequestQueue(ContextManagementActivity);

        final JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.PUT, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        response(response);

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        queue.add(contextRequest);

    }


    public void Changecolor(final String light, final String color) {

        String url = baseurl + light + "/color";
        JSONObject body = new JSONObject();
        try {
            body.put("color", color);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(ContextManagementActivity);

        final JsonObjectRequest contextRequest = new JsonObjectRequest
                (Request.Method.PUT, url, body, response -> {
                    response(response);

                }, error -> {

                });
        queue.add(contextRequest);

    }

    private void response(JSONObject response) {
        try {
            int id = Integer.parseInt(response.get("id").toString());
            String status = response.get("status").toString();
            int level = Integer.parseInt(response.get("saturation").toString());
            int roomId = Integer.parseInt(response.get("roomId").toString());
            int color1 = Integer.parseInt(response.get("color").toString().substring(1),16);

            ContextManagementActivity.onUpdateLight(new LightContextState(id, status, level, roomId, -color1));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void deleteLight(final String light) {

        String url = baseurl + light;

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
