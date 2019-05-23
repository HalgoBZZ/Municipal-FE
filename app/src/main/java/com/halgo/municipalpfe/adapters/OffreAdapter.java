package com.halgo.municipalpfe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.halgo.municipalpfe.DetailsOffreActivity;
import com.halgo.municipalpfe.R;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Offre;

import java.util.List;



public class OffreAdapter extends  RecyclerView.Adapter<OffreAdapter.MyViewHolder>{

    private List<Offre> offres;
    private Context mContext;
    private Client connectedUser;
    private Offre offre;


    public OffreAdapter(Context context, List<Offre> mOffres) {
        offres = mOffres;
        mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titre, description, etat;
        RelativeLayout parentLayout;

        public MyViewHolder(View view) {
            super(view);
            titre = view.findViewById(R.id.titre_offre);
            description = view.findViewById(R.id.description_offre);
            etat = view.findViewById(R.id.etat_offre);
            parentLayout = view.findViewById(R.id.parent_layout);
            mContext = view.getContext();
        }

    }
    public OffreAdapter(List<Offre> offres, Client connectedUser) {
        this.connectedUser = connectedUser;
        this.offres = offres;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_offre, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Offre offre = offres.get(position);
        holder.titre.setText(offre.getTitre_offre());
        holder.description.setText(offre.getDescription_offre());
        holder.etat.setText(offre.getEtat().toString());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsOffreActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                intent.putExtra("offre", offre);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return offres.size();
    }

}
