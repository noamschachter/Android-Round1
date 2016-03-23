package com.il.tech.hqtask;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

/**
 * Created by Noam Schachter on 3/22/2016.
 */
public class WebService {
    private static final String TAG = WebService.class.getSimpleName();
    private Context m_Context;
    private OnWebServiceListener m_WebServiceListener;

    public WebService(Context context) {
        m_Context = context;
    }

    public void setOnWebServiceListener(OnWebServiceListener webServiceListener) {
        m_WebServiceListener = webServiceListener;
    }

    public void getDataFromServer() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Constants.URL, (String) null,
                new com.android.volley.Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        m_WebServiceListener.onWebServiceResponse(response.toString());
                    }
                }, new com.android.volley.Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                if (error.getMessage() != null) {
                    m_WebServiceListener.onWebServiceError(error.getMessage());
                } else {
                    m_WebServiceListener.onWebServiceError(m_Context.getResources().getString(R.string.error_occured));
                }
            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        RequestQueueHelper.getInstance(m_Context).addToRequestQueue(jsonObjectRequest);
    }
}
