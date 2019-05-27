package com.hihasan.wakemeup.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hihasan.prisom.toaster.Toaster;
import com.hihasan.wakemeup.R;
import com.hihasan.wakemeup.model.ContentModel;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>
{
    public ContentModel[]model;

    public ContentAdapter(ContentModel[]model){
        this.model=model;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View list=inflater.inflate(R.layout.activity_list,viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(list);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position)
    {
        final ContentModel content= model[position];
        viewHolder.phone.setText(model[position].getPhone());
        viewHolder.time.setText(model[position].getTime());

        viewHolder.refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toaster.makeText(v.getContext(),"Update Current Entry", Toaster.INFO,true);
            }
        });

        viewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toaster.makeText(v.getContext(),"Delete Current Entry", Toaster.INFO,true);
            }
        });


        viewHolder.relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toaster.makeText(v.getContext(),content.phone,Toaster.INFO,true);
            }
        });

    }

    @Override
    public int getItemCount() {
        return model.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public AppCompatTextView phone,time;
        public AppCompatImageView refresh,delete;
        public RelativeLayout relative;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.phone=(AppCompatTextView) itemView.findViewById (R.id.phone);
            this.time=(AppCompatTextView) itemView.findViewById (R.id.time);
            this.refresh=(AppCompatImageView) itemView.findViewById (R.id.refresh);
            this.delete=(AppCompatImageView) itemView.findViewById (R.id.delete);
            this.relative=(RelativeLayout) itemView.findViewById (R.id.card);
        }
    }
}
