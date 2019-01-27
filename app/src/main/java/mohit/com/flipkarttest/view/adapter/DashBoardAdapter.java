package mohit.com.flipkarttest.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import mohit.com.flipkarttest.R;
import mohit.com.flipkarttest.interfaces.OnItemClickListener;
import mohit.com.flipkarttest.model.User;

public class DashBoardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context applicationContext;
    List<User> userList;
    private OnItemClickListener itemClickListener;

    List<User> dataList;

    public DashBoardAdapter(Context applicationContext, List<User> userList, OnItemClickListener itemClickListener) {
        this.applicationContext = applicationContext;
        this.userList = userList;
        this.dataList = userList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_dashboard, parent, false);
        return new CommonViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        User item = userList.get(position);
        viewHolder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public List<User> getlist() {
        return userList;
    }

    public void addAll(List<User> categoryData) {
        this.userList.addAll(categoryData);
        notifyDataSetChanged();
    }

    public void add(User user) {
        this.userList.add(user);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        userList.remove(position);
        dataList.remove(position);
        notifyDataSetChanged();
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtAmount;

        public CommonViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtAmount = (TextView) itemView.findViewById(R.id.txtAmount);

        }

        private void bindData(User user) {
            txtName.setText(user.getName());
            double totalpaid = Double.parseDouble(user.getTotal_paid());
            double totalshare = Double.parseDouble(user.getTotal_share());
            if (totalpaid >= totalshare) {
                txtAmount.setTextColor(Color.GREEN);
            } else {
                txtAmount.setTextColor(Color.RED);
            }
            txtAmount.setText(String.valueOf(totalpaid - totalshare));

        }
    }


}
