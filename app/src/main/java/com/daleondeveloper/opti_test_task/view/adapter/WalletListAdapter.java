package com.daleondeveloper.opti_test_task.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daleondeveloper.opti_test_task.R;
import com.daleondeveloper.opti_test_task.db.Wallet;

import java.util.List;

public class WalletListAdapter extends RecyclerView.Adapter<WalletListAdapter.WalletHolder> {

    private Context context;
    private List<Wallet> walletList;

    public WalletListAdapter(Context context) {
        this.context = context;
    }

    public void setWalletList(List<Wallet> walletList) {
        this.walletList = walletList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public WalletListAdapter.WalletHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.wallet_row,parent,false);
        return new WalletHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WalletListAdapter.WalletHolder holder, int position) {
        int price = this.walletList.get(position).getPrice();
        holder.price.setText((price + " $"));
        if(price > 0 ){
            holder.price.setTextColor(context.getColor(R.color.colorIncome));
        }else{
            holder.price.setTextColor(context.getColor(R.color.colorRed));
        }
        holder.date.setText(this.walletList.get(position).getData());
        holder.title.setText(this.walletList.get(position).getName());
        if(price > 0){
            holder.image.setImageResource(R.drawable.credit_card);
        }else{
            holder.image.setImageResource(R.drawable.person);
        }
    }

    @Override
    public int getItemCount() {
        if(walletList == null) {return 0;}
        else return walletList.size();

    }

    public class WalletHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView title;
        private TextView price;
        private TextView date;

        public WalletHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_wallet);
            title = itemView.findViewById(R.id.titleWallet);
            price = itemView.findViewById(R.id.price);
            date = itemView.findViewById(R.id.date);
        }
    }
}
