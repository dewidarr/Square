package com.example.dewidar.repository.square;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.dewidar.repository.R;
import com.example.dewidar.repository.SquareDetails;

import java.util.ArrayList;
import java.util.List;

public class SquareAdapter extends RecyclerView.Adapter<SquareAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<Squarevaluse> squarevaluse;
    private List<Squarevaluse> exampleListFull;
    //String url = "https://desolate-chamber-62168.herokuapp.com/public/user/map";

    public SquareAdapter(Context context, List<Squarevaluse> squarevaluse) {
        this.context = context;
        this.squarevaluse = squarevaluse;
        exampleListFull = new ArrayList<>(squarevaluse);

    }

    //create viewHolder to hold the content
    @NonNull
    @Override
    public SquareAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.squareitem, parent, false);
        return new SquareAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final SquareAdapter.ViewHolder holder, final int position) {
        final Squarevaluse h = squarevaluse.get(position);

      holder.reponame.setText(squarevaluse.get(position).repoName);
        holder.repoDescrption.setText(squarevaluse.get(position).repoDescrption);
        holder.ownerName.setText(squarevaluse.get(position).repoOwnerName);

      if (squarevaluse.get(position).repoFork=="false")
       {
           holder.cardView.setCardBackgroundColor(Color.parseColor("#32CD32"));
       }else
           {
               holder.cardView.setCardBackgroundColor(Color.parseColor("#ffffff"));
           }


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            //get postion info url and send to webactivity activity
            @Override
            public void onClick(final View view) {
                final Context context = view.getContext();

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Repository").
                        setMessage("which item you want?").
                        setPositiveButton("Go repository", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent = new Intent(context, SquareDetails.class);
                                intent.putExtra("weburl", squarevaluse.get(position).repoUrl);
                                context.startActivity(intent);

                            }
                        }).setNegativeButton("Repository Owner", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(context, SquareDetails.class);
                        intent.putExtra("weburl", squarevaluse.get(position).OwnerUrl);
                        context.startActivity(intent);

                    }
                }).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return squarevaluse.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView reponame;
        TextView ownerName;
        TextView repoDescrption;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            reponame = itemView.findViewById(R.id.reponame);
            ownerName = itemView.findViewById(R.id.ownername);
            repoDescrption = itemView.findViewById(R.id.repodescrotion);
            cardView=itemView.findViewById(R.id.repocardview);
        }
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Squarevaluse> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Squarevaluse item : exampleListFull) {

                    if (item.repoName.toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            squarevaluse.clear();
            squarevaluse.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
    public void clear() {

        exampleListFull.clear();
        squarevaluse.clear();

        notifyDataSetChanged();

    }

}
