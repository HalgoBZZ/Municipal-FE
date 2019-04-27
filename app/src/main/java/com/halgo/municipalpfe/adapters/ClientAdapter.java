package com.halgo.municipalpfe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.halgo.municipalpfe.DetailsClientActivity;
import com.halgo.municipalpfe.DetailsOffreActivity;
import com.halgo.municipalpfe.R;
import com.halgo.municipalpfe.modals.Client;


import java.util.List;



public class ClientAdapter extends  RecyclerView.Adapter<ClientAdapter.MyViewHolder>{

    private List<Client> clients;
    private Context mContext;


    public ClientAdapter(Context context, List<Client> mClients) {
        clients = mClients;
        mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nom_prenom;
        RelativeLayout parentLayout;

        public MyViewHolder(View view) {
            super(view);
            nom_prenom = view.findViewById(R.id.nom_prenom_client);
            parentLayout = view.findViewById(R.id.parent_layout_client);
            mContext = view.getContext();
        }

    }
    public ClientAdapter(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_client, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.nom_prenom.setText(client.getNom()+" "+client.getPrenom());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsClientActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

}
