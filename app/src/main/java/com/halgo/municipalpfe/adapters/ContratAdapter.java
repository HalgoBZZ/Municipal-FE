package com.halgo.municipalpfe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.halgo.municipalpfe.DetailsContratActivity;
import com.halgo.municipalpfe.R;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Contrat;


import java.util.List;

public class ContratAdapter extends RecyclerView.Adapter<ContratAdapter.MyViewHolder>{

private List<Contrat> contrats;
private Context mContext;
private Client connectedUser;


public ContratAdapter(Context context, List<Contrat> mContrats) {
        contrats = mContrats;
        mContext = context;
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView titre;
    public TextView prix;
    RelativeLayout parentLayout;

    public MyViewHolder(View view) {
        super(view);
        prix = view.findViewById(R.id.prix_contrat);
        titre = view.findViewById(R.id.titre_contrat);
        parentLayout = view.findViewById(R.id.parent_layout_contrat);
        mContext = view.getContext();
    }

}
    public ContratAdapter(List<Contrat> contrats, Client connectedUser) {
        this.contrats = contrats;
        this.connectedUser = connectedUser;
    }

    @Override
    public ContratAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_contrat, parent, false);
        return new ContratAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContratAdapter.MyViewHolder holder, int position) {
        final Contrat contrat = contrats.get(position);
        holder.titre.setText(contrat.getTitre_contrat());
        holder.prix.setText(""+contrat.getPrix());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsContratActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                intent.putExtra("contrat", contrat);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contrats.size();
    }

}