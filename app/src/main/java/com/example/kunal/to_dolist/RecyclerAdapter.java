package com.example.kunal.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder>{
    public TextView title;
    Context ctx;
    private List<Listclass> mtitle=new ArrayList<>();

    public RecyclerAdapter(List<Listclass> title,Context ctx)
    {
        mtitle = title;
        this.ctx=ctx;
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title_list;
        Context ctx;
        private List<Listclass> tolist=new ArrayList<>();
        public RecyclerViewHolder(View view,Context ctx,List<Listclass> tolist){

            super(view);
            this.tolist=tolist;
            this.ctx=ctx;
            view.setOnClickListener(this);
            title_list= (TextView) view.findViewById(R.id.titlelist);

        }

        @Override
        public void onClick(View v) {

            int position=getAdapterPosition();
            Listclass listclass=this.tolist.get(position);
            Intent intent=new Intent(ctx,Detailsclass.class);
            intent.putExtra("details",listclass.getDetails());
            intent.putExtra("title",listclass.getTitle());
            this.ctx.startActivity(intent);
        }
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);

        RecyclerViewHolder recyclerViewHolder=new RecyclerViewHolder(view,ctx,mtitle);
        return recyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        Listclass todolist=mtitle.get(position);
        holder.title_list.setText(todolist.getTitle());
    }

    @Override
    public int getItemCount() {
        return mtitle.size();
    }


}
