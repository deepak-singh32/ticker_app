package com.example.ticker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticker.R;
import com.example.ticker.models.BusTicket;
import com.example.ticker.models.Ticket;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder>{

    List<BusTicket> list;
    private static final String TAG = TicketAdapter.class.getSimpleName();

    public TicketAdapter(List<BusTicket> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View newsView = inflater.inflate(R.layout.ticket_item_layout, parent, false);

        // Return a new holder instance

        return new ViewHolder(newsView);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ViewHolder holder, int position) {
        final BusTicket model = list.get(position);

        holder.route.setText(model.getRoute());
        holder.fare.setText(model.getFare().toString());
       holder.timestamp.setText(model.getBooking_time());
       holder.num_ticket.setText("x "+model.getNum_ticket().toString());
       holder.starting.setText(model.getStarting_station());
       holder.ending.setText(model.getEnding_station());
       holder.cardView.setOnClickListener((v)->{

       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView route,fare,timestamp,num_ticket,starting,ending;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            route = itemView.findViewById(R.id.textView23);
            fare = itemView.findViewById(R.id.textView24);
            timestamp = itemView.findViewById(R.id.textView25);
            num_ticket = itemView.findViewById(R.id.textView26);
            starting = itemView.findViewById(R.id.textView27);
            ending = itemView.findViewById(R.id.textView28);
            cardView = itemView.findViewById(R.id.cardview);
        }
    }
}
