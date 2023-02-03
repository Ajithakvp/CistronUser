package com.example.cistronuser.ServiceEngineer.Adapter;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.ConsumerCustSpareRecordModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class ConsumeCusSpareYesAdapter extends RecyclerView.Adapter<ConsumeCusSpareYesAdapter.ViewHolder> {

    public ArrayList<ConsumerCustSpareRecordModel> consumerCustSpareRecordModels;
    Activity activity;
    GetConsumerCustSpare getConsumerCustSpare;

    public ConsumeCusSpareYesAdapter(Activity activity, ArrayList<ConsumerCustSpareRecordModel> consumerCustSpareRecordModels, GetConsumerCustSpare getConsumerCustSpare) {
        this.activity = activity;
        this.consumerCustSpareRecordModels = consumerCustSpareRecordModels;
        this.getConsumerCustSpare = getConsumerCustSpare;
    }

    @NonNull
    @Override
    public ConsumeCusSpareYesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.consume_cus_spare_dialog_yes, parent, false);
        return new ConsumeCusSpareYesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumeCusSpareYesAdapter.ViewHolder holder, int position) {
        holder.tvId.setText("# " + consumerCustSpareRecordModels.get(position).getId() + "-");
        holder.tvPartName.setText(consumerCustSpareRecordModels.get(position).getPartName());
        holder.tvPartNo.setText(consumerCustSpareRecordModels.get(position).getPartNo());
        holder.tvMyqty.setText(consumerCustSpareRecordModels.get(position).getAvlQty());
        ConsumerCustSpareRecordModel id = consumerCustSpareRecordModels.get(position);
        getConsumerCustSpare.getData(id);

        int myqty = Integer.parseInt(consumerCustSpareRecordModels.get(position).getAvlQty());

        holder.edQtyReq.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                try {
                    int qtyConsume = Integer.parseInt(holder.edQtyReq.getText().toString());
                    if (qtyConsume > myqty) {
                        holder.edQtyReq.setError("Quantity should be smaller than Stock");
                        holder.edQtyReq.requestFocus();
                    } else {
                        holder.edQtyReq.setError(null);
                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return consumerCustSpareRecordModels.size();
    }

    public interface GetConsumerCustSpare {
        void getData(ConsumerCustSpareRecordModel consumerCustSpareRecordModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvPartName, tvPartNo, tvMyqty;
        EditText edQtyReq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvId = itemView.findViewById(R.id.tvId);
            tvPartName = itemView.findViewById(R.id.tvPartName);
            tvPartNo = itemView.findViewById(R.id.tvPartNo);
            tvMyqty = itemView.findViewById(R.id.tvMyqty);
            edQtyReq = itemView.findViewById(R.id.edQtyReq);

        }
    }
}
