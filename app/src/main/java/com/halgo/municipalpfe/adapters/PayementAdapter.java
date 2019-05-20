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
import com.halgo.municipalpfe.DetailsPayementActivity;
import com.halgo.municipalpfe.R;
import com.halgo.municipalpfe.modals.Offre;
import com.halgo.municipalpfe.modals.Payement;

import java.util.List;

public class PayementAdapter extends  RecyclerView.Adapter<PayementAdapter.MyViewHolder>{

private List<Payement> payements;
private Context mContext;


public PayementAdapter(Context context, List<Payement> mPayements) {
        payements = mPayements;
        mContext = context;
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView debut, fin, etat;
    RelativeLayout parentLayout;

    public MyViewHolder(View view) {
        super(view);
        debut = view.findViewById(R.id.debut_payement);
        fin = view.findViewById(R.id.fin_payement);
        etat = view.findViewById(R.id.etat_payement);
        parentLayout = view.findViewById(R.id.parent_layout_payement);
        mContext = view.getContext();
    }

}
    public PayementAdapter(List<Payement> payements) {
        this.payements = payements;
    }

    @Override
    public PayementAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_payement, parent, false);
        return new PayementAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PayementAdapter.MyViewHolder holder, int position) {
        Payement payement = payements.get(position);
        holder.debut.setText(""+payement.getDate_debut());
        holder.fin.setText(""+payement.getDate_fin());
        holder.etat.setText(payement.getEtat().toString());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsPayementActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return payements.size();
    }

}
