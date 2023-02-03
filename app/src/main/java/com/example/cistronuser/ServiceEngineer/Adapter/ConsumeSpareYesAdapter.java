package com.example.cistronuser.ServiceEngineer.Adapter;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cistronuser.API.Model.ConsumeSpareRecordModel;
import com.example.cistronuser.R;

import java.util.ArrayList;

public class ConsumeSpareYesAdapter extends RecyclerView.Adapter<ConsumeSpareYesAdapter.ViewHolder> {


    Activity activity;
    public ArrayList<ConsumeSpareRecordModel> consumeSpareRecordModels;
    Reference reference;

    public ConsumeSpareYesAdapter(Activity activity, ArrayList<ConsumeSpareRecordModel> consumeSpareRecordModels, Reference reference) {
        this.activity = activity;
        this.consumeSpareRecordModels = consumeSpareRecordModels;
        this.reference = reference;
    }

    @NonNull
    @Override
    public ConsumeSpareYesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.consume_spare_dialog_yes, parent, false);
        return new ConsumeSpareYesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumeSpareYesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.tvPartName.setText(consumeSpareRecordModels.get(position).getPartName());
        holder.tvPartNo.setText(consumeSpareRecordModels.get(position).getPartNo());
        holder.tvMyqty.setText(consumeSpareRecordModels.get(position).getMyQty());
        int myqty= Integer.parseInt(consumeSpareRecordModels.get(position).getMyQty());
        Log.e(TAG, "onBindViewHolder: "+myqty );



        ConsumeSpareRecordModel id=consumeSpareRecordModels.get(position);

        reference.reference(id);

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
                }catch (Exception e){

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return consumeSpareRecordModels.size();
    }

    public interface Reference {
        void reference(ConsumeSpareRecordModel
                               consumeSpareRecordModel);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvPartName, tvPartNo, tvMyqty,tverror;
        EditText edQtyReq;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvPartName = itemView.findViewById(R.id.tvPartName);
            tvPartNo = itemView.findViewById(R.id.tvPartNo);
            tvMyqty = itemView.findViewById(R.id.tvMyqty);
            edQtyReq = itemView.findViewById(R.id.edQtyReq);
            tverror=itemView.findViewById(R.id.tverror);

        }
    }
}
