package com.halgo.municipalpfe.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.halgo.municipalpfe.DetailsPropertieActivity;
import com.halgo.municipalpfe.R;
import com.halgo.municipalpfe.modals.Client;
import com.halgo.municipalpfe.modals.Propriete;

import java.util.List;

public class PropertiesAdapter extends RecyclerView.Adapter<PropertiesAdapter.MyViewHolder>{

private List<Propriete> properties;
private Context mContext;
    private Client connectedUser;


public PropertiesAdapter(Context context, List<Propriete> mpropeties) {
        properties = mpropeties;
        mContext = context;
        }

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView adresse;
    public TextView type;
    RelativeLayout parentLayout;

    public MyViewHolder(View view) {
        super(view);
        adresse = view.findViewById(R.id.adresse_propertie);
        type = view.findViewById(R.id.type_propertie);
        parentLayout = view.findViewById(R.id.parent_layout_propertie);
        mContext = view.getContext();
    }

}
    public PropertiesAdapter(List<Propriete> properties, Client connectedUser) {
    this.connectedUser = connectedUser;
    this.properties = properties;
    }

    @Override
    public PropertiesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row_properties, parent, false);
        return new PropertiesAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PropertiesAdapter.MyViewHolder holder, int position) {
        final Propriete propriete = properties.get(position);
        holder.adresse.setText(propriete.getAdresse());
        holder.type.setText(propriete.getType().toString());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsPropertieActivity.class);
                intent.putExtra("connectedUser", connectedUser);
                intent.putExtra("propertie", propriete);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

}