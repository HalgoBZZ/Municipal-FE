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
import com.halgo.municipalpfe.modals.Propertie;

import java.util.List;

public class PropertiesAdapter extends RecyclerView.Adapter<PropertiesAdapter.MyViewHolder>{

private List<Propertie> properties;
private Context mContext;


public PropertiesAdapter(Context context, List<Propertie> mpropeties) {
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
    public PropertiesAdapter(List<Propertie> properties) {
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
        Propertie propertie = properties.get(position);
        holder.adresse.setText(propertie.getAdresse());
        holder.type.setText(propertie.getType());


        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailsPropertieActivity.class);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return properties.size();
    }

}