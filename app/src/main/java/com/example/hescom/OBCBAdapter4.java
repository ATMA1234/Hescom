package com.example.hescom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class OBCBAdapter4 extends RecyclerView.Adapter<OBCBAdapter4.Approvals_ViewHolder> {
    private Context context;
    private ArrayList<Response> arrayList;


    public OBCBAdapter4(Context context, ArrayList<Response> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Approvals_ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.obcb_adapter2, viewGroup, false);
        return new Approvals_ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull Approvals_ViewHolder approvals_viewHolder, int i) {
        Response dashboard = arrayList.get(i);
        approvals_viewHolder.tv_year.setText(dashboard.getA1());
        approvals_viewHolder.tv_tariff.setVisibility(View.GONE);
        approvals_viewHolder.tv_status.setVisibility(View.GONE);
        approvals_viewHolder.tv_installation.setText(dashboard.getA2());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class Approvals_ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_year, tv_tariff, tv_status, tv_installation;

        Approvals_ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_year = itemView.findViewById(R.id.txt_year);
            tv_tariff = itemView.findViewById(R.id.txt_tariff);
            tv_status = itemView.findViewById(R.id.txt_status);
            tv_installation = itemView.findViewById(R.id.txt_installation);
        }
    }
}
