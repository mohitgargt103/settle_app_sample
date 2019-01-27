package mohit.com.flipkarttest.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import mohit.com.flipkarttest.R;
import mohit.com.flipkarttest.interfaces.OnItemClickListener;
import mohit.com.flipkarttest.model.User;

public class UserListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context applicationContext;
    List<User> userList;
    private OnItemClickListener itemClickListener;

    public UserListAdapter(Context applicationContext, List<User> userList, OnItemClickListener itemClickListener) {
        this.applicationContext = applicationContext;
        this.userList = userList;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_user_list, parent, false);
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
        notifyDataSetChanged();
    }

    public class CommonViewHolder extends RecyclerView.ViewHolder {
        TextView txtName;
        Button btnRemove;
        EditText edtTotalPaid, edtTotalShare;

        public CommonViewHolder(View itemView) {
            super(itemView);
            edtTotalPaid = (EditText) itemView.findViewById(R.id.edtTotalPaid);
            edtTotalShare = (EditText) itemView.findViewById(R.id.edtTotalShare);
            btnRemove = (Button) itemView.findViewById(R.id.btnRemove);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.onItemClick(view, getAdapterPosition(), 1, "");
                }
            });
            edtTotalPaid.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (edtTotalPaid.getText().length() == 0) {
                        userList.get(getAdapterPosition()).setTotal_paid("0.0");
                    } else {
                        userList.get(getAdapterPosition()).setTotal_paid(edtTotalPaid.getText().toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            edtTotalShare.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (edtTotalShare.getText().length() == 0) {
                        userList.get(getAdapterPosition()).setTotal_share("0.0");
                    } else {
                        userList.get(getAdapterPosition()).setTotal_share(edtTotalShare.getText().toString());
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }

        private void bindData(User user) {
            txtName.setText(user.getName());
        }
    }


}
