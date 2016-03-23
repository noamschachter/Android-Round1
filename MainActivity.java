package com.il.tech.hqtask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.LinkedHashMap;


public class MainActivity extends AppCompatActivity implements OnWebServiceListener, AdapterView.OnItemClickListener {

    private ListView m_ListView;
    private LinkedHashMap<String, Data> m_DataMap;
    private WebService m_WebService;
    private ListAdapter m_ListAdapter;
    private ProgressDialogHelper m_ProgressDialogHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        m_ProgressDialogHelper.showProgressDialog(getString(R.string.loading));
        m_WebService.getDataFromServer();
    }

    private void init() {
        m_WebService = new WebService(this);
        m_WebService.setOnWebServiceListener(this);
        m_ListView = (ListView) findViewById(R.id.list_view);
        m_ListView.setOnItemClickListener(this);
        m_ProgressDialogHelper = new ProgressDialogHelper(this);
    }

    @Override
    public void onWebServiceResponse(String response) {
        m_DataMap = new Gson().fromJson(response, new TypeToken<LinkedHashMap<String, Data>>() {
        }.getType());
        String[] dataArray = m_DataMap.keySet().toArray(new String[m_DataMap.keySet().size()]);
        m_ListAdapter = new ListAdapter(this, dataArray);
        m_ListView.setAdapter(m_ListAdapter);
        m_ProgressDialogHelper.hideProgressDialog();
    }

    @Override
    public void onWebServiceError(String error) {
        m_ProgressDialogHelper.hideProgressDialog();
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, WebViewActivity.class);
        intent.putExtra(Constants.TITLE, m_ListAdapter.getItem(position));
        intent.putExtra(Constants.DATA, m_DataMap.get(m_ListAdapter.getItem(position)));
        startActivity(intent);
    }
}
