package com.il.tech.hqtask;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Noam Schachter on 3/22/2016.
 */
public class RequestQueueHelper {
    private static RequestQueueHelper m_Instance;
    private RequestQueue m_RequestQueue;
    private static Context m_Context;

    private RequestQueueHelper(Context context) {
        m_Context = context;
        m_RequestQueue = getRequestQueue();
    }

    public static synchronized RequestQueueHelper getInstance(Context context) {
        if (m_Instance == null) {
            m_Instance = new RequestQueueHelper(context);
        }
        return m_Instance;
    }

    public RequestQueue getRequestQueue() {
        if (m_RequestQueue == null) {
            m_RequestQueue = Volley.newRequestQueue(m_Context.getApplicationContext());
        }
        return m_RequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
