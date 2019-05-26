package com.hihasan.wakemeup.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
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
import com.hihasan.wakemeup.utils.Utils;

public class MainActivity extends AppCompatActivity
{
    private FloatingActionMenu fab_menu;
    private com.github.clans.fab.FloatingActionButton add,faq,about_us;

    private Context context=this;
    private RecyclerView recycler;
    private ContentAdapter adapter;

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

        Utils.model();

        recycler=(RecyclerView) findViewById (R.id.recycler);
        adapter=new ContentAdapter(Utils.model);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);
    }

    private void fabAction()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_info);

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
