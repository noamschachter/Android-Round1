package com.il.tech.hqtask;


/**
 * Created by Noam Schachter on 3/22/2016.
 */
public interface OnWebServiceListener {
    public void onWebServiceResponse(String response);
    public void onWebServiceError(String error);
}
