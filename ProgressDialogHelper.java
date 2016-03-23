package com.il.tech.hqtask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by Noam Schachter on 3/22/2016.
 */
public class ProgressDialogHelper {
    private Context m_Context;
    private ProgressDialog m_ProgressDialog;

    public ProgressDialogHelper(Context context) {
        this.m_Context = context;
        m_ProgressDialog = new ProgressDialog(context);
        m_ProgressDialog.setCancelable(false);
    }

    public void showProgressDialog(String message) {
        m_ProgressDialog.setMessage(message);
        if (!((Activity) m_Context).isFinishing() && !m_ProgressDialog.isShowing()) {
            try {
                m_ProgressDialog.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void hideProgressDialog() {
        if (m_ProgressDialog.isShowing())
            m_ProgressDialog.dismiss();
    }
}
