package com.hihasan.wakemeup.views;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.github.clans.fab.FloatingActionMenu;
import com.hihasan.prisom.toaster.Toaster;
import com.hihasan.wakemeup.R;
import com.hihasan.wakemeup.adapter.ContentAdapter;
import com.hihasan.wakemeup.model.ContentModel;
import com.hihasan.wakemeup.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import static com.hihasan.wakemeup.utils.Utils.MyPreferences;
import static com.hihasan.wakemeup.utils.Utils.PHONE;
import static com.hihasan.wakemeup.utils.Utils.TIME;

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

        Utils.sharedPreferences=getSharedPreferences(MyPreferences, Context.MODE_PRIVATE);

        fabAction();





        recycler=(RecyclerView) findViewById (R.id.recycler);
            recycler.setLayoutManager(new LinearLayoutManager(this));
            adapter=new ContentAdapter(list);
            recycler.setAdapter(adapter);
            String phoneValue= Utils.sharedPreferences.getString(PHONE,"");
            String timeValue=Utils.sharedPreferences.getString(TIME,"");

            final ContentModel model=new ContentModel();

            model.setPhone(phoneValue);
            model.setTime(timeValue);

            list.add(model);
            adapter.notifyDataSetChanged();



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


                        SharedPreferences.Editor editor=Utils.sharedPreferences.edit();

                        editor.putString(PHONE,p);
                        editor.putString(TIME,t);
                        editor.commit();



                        Toaster.makeText(getApplicationContext(), p + t, Toaster.INFO,true);


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
                final Dialog dialog=new Dialog(context);
                dialog.setContentView(R.layout.activity_about_us);

                AppCompatTextView txt=(AppCompatTextView) dialog.findViewById (R.id.txt);
                txt.setText("Wake Me Up\n V1.0");

                AppCompatButton close=(AppCompatButton) dialog.findViewById (R.id.close);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });
    }
}
