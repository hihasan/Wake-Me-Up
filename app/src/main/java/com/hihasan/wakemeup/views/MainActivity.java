package com.hihasan.wakemeup.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.clans.fab.FloatingActionMenu;
import com.hihasan.prisom.toaster.Toaster;
import com.hihasan.wakemeup.R;
import com.hihasan.wakemeup.adapter.ContentAdapter;
import com.hihasan.wakemeup.model.ContentModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private FloatingActionMenu fab_menu;
    private com.github.clans.fab.FloatingActionButton add,faq,about_us;

    public AppCompatEditText phone,time;

    private Context context=this;
    private RecyclerView recycler;
    private ContentAdapter adapter;
    List<ContentModel> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab_menu=(FloatingActionMenu) findViewById (R.id.fabmenu);
        add=(com.github.clans.fab.FloatingActionButton) findViewById (R.id.add);
        faq=(com.github.clans.fab.FloatingActionButton) findViewById (R.id.faq);
        about_us=(com.github.clans.fab.FloatingActionButton) findViewById (R.id.about_us);

        fabAction();



        recycler=(RecyclerView) findViewById (R.id.recycler);

        if(recycler ==null){
            Toaster.makeText(getApplicationContext(),"No Data", Toaster.CONFUSING, true);
        }

        else {
            recycler.setLayoutManager(new LinearLayoutManager(this));
            adapter=new ContentAdapter(list);
            recycler.setAdapter(adapter);
        }


    }

    private void fabAction()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_info);

                phone=(AppCompatEditText) dialog.findViewById (R.id.phn);
                time=(AppCompatEditText) dialog.findViewById (R.id.time);



                AppCompatButton submit=(AppCompatButton) dialog.findViewById (R.id.submit);
                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String p=phone.getText().toString();
                        String t=time.getText().toString();



                        final ContentModel model=new ContentModel();

                        Toaster.makeText(getApplicationContext(), p + t, Toaster.INFO,true);

                        model.setPhone(p);
                        model.setTime(t);

                        list.add(model);
                        adapter.notifyDataSetChanged();
                        Toaster.makeText(getApplicationContext(),"Data Saved Successfully", Toaster.SUCCESS,true);
                        dialog.dismiss();
                    }
                });

                AppCompatButton cancel=(AppCompatButton) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        faq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toaster.makeText(getApplicationContext(),"Action Needed", Toaster.INFO,true);
            }
        });

        about_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toaster.makeText(getApplicationContext(), "Action Needed", Toaster.INFO, true);
            }
        });
    }
}
