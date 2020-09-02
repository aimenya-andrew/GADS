package com.paidapp.gadsproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.paidapp.gadsproject.Retrofit.Entry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SubmitLink extends AppCompatActivity {
    EditText nameFirst, lastName, linkProject, emailAddress;
    Button submitProject;
    ProgressDialog progressDialog;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_link);


        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");


        emailAddress = findViewById(R.id.emailAddress);
        nameFirst = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        queue = Volley.newRequestQueue(getApplicationContext());
        linkProject = findViewById(R.id.linkProject);
        submitProject = findViewById(R.id.submitProject);

        submitProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });


    }


    public void postData(final String name, final String email, final String link, final String lastname) {
        progressDialog.show();
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Entry.url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("TAG", "Response: " + response);
                        if (response.length() > 0) {
                            ShowSuccessDialog();

                            emailAddress.setText(null);
                            nameFirst.setText(null);
                            lastName.setText(null);
                            linkProject.setText(null);
                        } else {
                        }
                        progressDialog.dismiss();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                ShowFailedDialog();

            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put(Entry.nameField, name);
                params.put(Entry.emailField, email);
                params.put(Entry.lastNameField, lastname);
                params.put(Entry.lastNameField, link);
                return params;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                50000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(request);
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.areusure_layout);

        Button btndialogyes = dialog.findViewById(R.id.btnyes);
        Button btndialogclose = dialog.findViewById(R.id.closeimg);
        btndialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btndialogyes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                postData(nameFirst.getText().toString().trim(), emailAddress.getText().toString().trim(), linkProject.getText().toString().trim(), lastName.getText().toString().trim());
                dialog.dismiss();

            }


        });
        dialog.show();

    }

    private void ShowSuccessDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.successdialog_layout);

        Button btndialogclose = dialog.findViewById(R.id.successimg);
        btndialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    private void ShowFailedDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

        dialog.setContentView(R.layout.faileddialog_layout);

        Button btndialogclose = dialog.findViewById(R.id.failedimg);
        btndialogclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}