package com.example.ishitasinha.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ishitasinha on 12/04/16.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MovieViewHolder> {

    List<ListItems> searchList;
    Context context;

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView poster;
        TextView title;

        public MovieViewHolder(View itemView) {
            super(itemView);
            poster = (ImageView) itemView.findViewById(R.id.poster);
            title = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "Movie name: " + ((TextView) view.findViewById(R.id.title)).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    public SearchAdapter(List<ListItems> movies) {
        searchList = movies;
    }


    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.list_items, parent, false);
        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        ListItems item = searchList.get(position);
        holder.title.setText(item.getTitle() + " (" + item.getReleased() + ")");
        Picasso.with(context).load(item.getPoster()).into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }
}
