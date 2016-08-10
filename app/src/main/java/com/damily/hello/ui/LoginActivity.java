package com.damily.hello.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.damily.hello.MyApplication;
import com.damily.hello.R;
import com.damily.hello.common.Constants;
import com.damily.hello.common.URL;
import com.damily.hello.model.entity.LoginBeanInfo;
import com.damily.hello.util.BindUtils;
import com.damily.hello.util.BindView;
import com.damily.hello.util.GsonTools;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Dandan.Cao on 2016/8/2.
 */
public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    @BindView(id = R.id.bt_login, click = true)
    private Button bt_login;
    @BindView(id = R.id.et_pwd)
    private EditText et_pwd;
    @BindView(id = R.id.et_user)
    private EditText et_user;
    @BindView(id = R.id.cb_remember, click = true)
    private CheckBox cb_remember;
    private String username;
    private String pwd;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        BindUtils.initBindView(this);
//        cb_remember.setOnCheckedChangeListener(this);
        sp = getSharedPreferences("USER_INFO", Context.MODE_PRIVATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        et_user.setText(sp.getString(Constants.username, ""));
        et_pwd.setText(sp.getString(Constants.pwd, ""));
        cb_remember.setChecked(sp.getBoolean(Constants.isRemember, false));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (cb_remember.isChecked()) {
            cb_remember.setChecked(true);
            saveUsernameAndPassword();
        } else {
            cb_remember.setChecked(false);
            editor = sp.edit();
            editor.clear();
            editor.commit();

        }

    }

    private void saveUsernameAndPassword() {
        editor = sp.edit();
        editor.putString(Constants.username, et_user.getText().toString().trim());
        editor.putString(Constants.pwd, et_pwd.getText().toString().trim());
        editor.putBoolean(Constants.isRemember, true);
        editor.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                username = et_user.getText().toString().trim();
                pwd = et_pwd.getText().toString().trim();
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, R.string.empty_name,
                            Toast.LENGTH_SHORT).show();
                } else {
                    login(username, pwd, LoginActivity.this);
                }
                break;
        }
    }

    private void login(final String username, final String pwd, final Context context) {
        String url = URL.LOGINURL;
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i(TAG, "onResponse: " + response);
                try {
                    JSONObject object = new JSONObject(response);
                    String status = object.getString("status");
                    if (status.equals("true")) {
                        LoginBeanInfo loginResult = GsonTools.changeGsonToBean(response, LoginBeanInfo.class);
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        String token = loginResult.getMessage().getToken();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("token", token);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    } else {
                        String message = object.getString("message");
                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            public byte[] getBody() throws AuthFailureError {

                JSONObject mBody = new JSONObject();
                try {
                    mBody.put("name", username);
                    mBody.put("pwd", pwd);
                } catch (JSONException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                Gson g = new Gson();
                String param = g.toJson(mBody);
                String inpar = param.substring(18, param.length() - 1);
                byte[] p = null;
                try {
                    p = inpar.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                return p;
            }


        };
        MyApplication.getQueue().add(req);


    }

}
