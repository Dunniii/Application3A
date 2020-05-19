package com.example.application3a.presentation.view;
import java.util.List;


import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.application3a.R;
import com.example.application3a.presentation.model.Atome;
import com.squareup.picasso.Picasso;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Atome> values;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Atome item);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHeader;
        TextView txtFooter;
        ImageView icon;
        View layout;

        ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById( R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            icon = v.findViewById(R.id.icon);
        }
    }

    public ListAdapter(List<Atome> atome, OnItemClickListener listener) {
        this.values = atome;
        this.listener = listener;
    }

   /* public void add(int position, Atome item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }*/

    // Provide a suitable constructor (depends on the kind of dataset)
    /*public ListAdapter(List<Atome> myDataset) {
        values = myDataset;
    }*/

    // Create new views (invoked by the layout manager)
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        LayoutInflater inflater = LayoutInflater.from( parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Atome currentAtome = values.get(position);
        holder.txtHeader.setText(currentAtome.getName());
        holder.txtFooter.setText(currentAtome.getNumero());
        Picasso.get().load(currentAtome.getUrl()).fit().into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(currentAtome);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return values.size();
    }

}