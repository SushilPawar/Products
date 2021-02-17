package com.example.product.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.product.Company.Company_details;
import com.example.product.Models.company_model;
import com.example.product.R;

import java.util.ArrayList;
import java.util.Collection;

public class company_adapter extends RecyclerView.Adapter<company_adapter.holder> implements Filterable {
    Context context;
    ArrayList<company_model> allcompanies;
    ArrayList<company_model> backup;
    Activity activity;

    public company_adapter(Activity activity,Context context, ArrayList<company_model> allcompanies) {
        this.activity = activity;
        this.context = context;
        this.allcompanies = allcompanies;
        backup = new ArrayList<>(allcompanies);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.custom_list_view,parent,false);
        return new company_adapter.holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        final company_model model = allcompanies.get(position);
        holder.company.setText(allcompanies.get(position).getName());
        holder.party.setText(allcompanies.get(position).getParty());
        holder.sector.setText(allcompanies.get(position).getSector());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, Company_details.class);
                intent.putExtra("id",model.getId());
                intent.putExtra("company_name",model.getName());
                intent.putExtra("party_name",model.getParty());
                intent.putExtra("sector",model.getSector());
                activity.startActivityForResult(intent,1);
                //context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allcompanies.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            ArrayList<company_model> filterdata = new ArrayList<>();

            if(charSequence.toString().isEmpty())
            {
                filterdata.addAll(backup);
            }
            else {
                for (company_model obj: backup)
                {
                    if(obj.getName().toString().toLowerCase().contains(charSequence.toString().toLowerCase()))
                        filterdata.add(obj);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filterdata;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            allcompanies.clear();
            allcompanies.addAll((ArrayList<company_model>)filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class holder extends RecyclerView.ViewHolder{
        TextView company,party,sector;
        public holder(@NonNull View itemView) {
            super(itemView);
            company = itemView.findViewById(R.id.comp_name);
            party = itemView.findViewById(R.id.p_name);
            sector = itemView.findViewById(R.id.companysector);
        }
    }
}
