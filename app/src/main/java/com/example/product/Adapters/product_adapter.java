package com.example.product.Adapters;

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
import com.example.product.Models.product_model;
import com.example.product.Product.Product_details;
import com.example.product.R;

import java.util.ArrayList;


public class product_adapter extends RecyclerView.Adapter<product_adapter.holder> implements Filterable {
    Context context;
    ArrayList<product_model> models;
    ArrayList<product_model> temp;
    public product_adapter(Context context, ArrayList<product_model> models) {
        this.context = context;
        this.models = models;
        temp = new ArrayList<>(models);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View  view = inflater.inflate(com.example.product.R.layout.custom_all_product_view,parent,false);
        return new product_adapter.holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        product_model temp = models.get(position);
        holder.product.setText(models.get(position).getProduct_name());
        holder.company.setText(models.get(position).getCompany_name());
        holder.quantity.setText(models.get(position).getQuantity());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Product_details.class);
                intent.putExtra("id",temp.getId());
                intent.putExtra("product",temp.getParty_name());
                intent.putExtra("company",temp.getCompany_name());
                intent.putExtra("party",temp.getParty_name());
                intent.putExtra("mrp",temp.getMrp());
                intent.putExtra("quantity",temp.getQuantity());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<product_model> filtered = new ArrayList<>();

            if(charSequence.toString().isEmpty())
            {
                filtered.addAll(temp);
            }
            else
            {
                for (product_model obj:temp)
                {
                    if(obj.getProduct_name().toLowerCase().toString().contains(charSequence.toString().toLowerCase()))
                        filtered.add(obj);
                }
            }
            FilterResults results = new FilterResults();
            results.values = filtered;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            models.clear();
            models.addAll((ArrayList<product_model>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    public class holder extends RecyclerView.ViewHolder{
      TextView quantity,product,company;
        public holder(@NonNull View itemView) {
            super(itemView);

            company = itemView.findViewById(R.id.customcompanyname);
            product = itemView.findViewById(R.id.customproductname);
            quantity = itemView.findViewById(R.id.customquantity);

        }
    }
}
