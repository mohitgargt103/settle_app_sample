package mohit.com.flipkarttest.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import mohit.com.flipkarttest.R;
import mohit.com.flipkarttest.helper.ContactsHelper;
import mohit.com.flipkarttest.helper.DatabaseHelper;
import mohit.com.flipkarttest.interfaces.OnItemClickListener;
import mohit.com.flipkarttest.model.Transaction;
import mohit.com.flipkarttest.model.User;
import mohit.com.flipkarttest.view.adapter.UserListAdapter;

public class AddTranactiontionActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtTransDetails, edtTransAmount;
    private Button btnAddMember, btnAddTrans;
    private RecyclerView userListView;
    private static final int REQUEST_CODE_PICK_CONTACTS = 1;
    DatabaseHelper db;
    private ContactsHelper contactsHelper;
    private UserListAdapter userListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        //DataBase
        db = new DatabaseHelper(this);
        //Contacts
        contactsHelper = new ContactsHelper(this);
        initUI();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    private void initUI() {
        edtTransDetails = (EditText) findViewById(R.id.edtTransDetails);
        edtTransAmount = (EditText) findViewById(R.id.edtTransAmount);
        btnAddMember = (Button) findViewById(R.id.btnAddMember);
        btnAddTrans = (Button) findViewById(R.id.btnAddTrans);
        userListView = (RecyclerView) findViewById(R.id.list_view);
        btnAddMember.setOnClickListener(this);
        btnAddTrans.setOnClickListener(this);
        // get UserList
        List<User> userlist = db.getAllUser();
        Log.e("User List", userlist.size() + "");
        setUserListData(userlist);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddMember:
                onClickSelectContact(btnAddMember);
                break;
            case R.id.btnAddTrans:
                String details = edtTransDetails.getText().toString();
                String amount = edtTransAmount.getText().toString();
                if (details.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Details", Toast.LENGTH_LONG).show();
                    return;
                }
                if (amount.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter amount", Toast.LENGTH_LONG).show();
                    return;
                }

                double totalPaidByUser = 0;
                double totalShareByUser = 0;
                for (User user : userListAdapter.getlist()) {
                    totalPaidByUser += Double.parseDouble(user.getTotal_paid());
                    totalShareByUser += Double.parseDouble(user.getTotal_share());
                }
                if (totalPaidByUser > Double.parseDouble(amount) || totalPaidByUser < Double.parseDouble(amount)) {
                    Toast.makeText(getApplicationContext(), "Total amount should be equal of total amount", Toast.LENGTH_LONG).show();
                    return;
                }
                if (totalShareByUser > Double.parseDouble(amount) || totalShareByUser < Double.parseDouble(amount)) {
                    Toast.makeText(getApplicationContext(), "Share amount should be equal of total amount", Toast.LENGTH_LONG).show();
                    return;
                }
                addTransation(details, amount, userListAdapter.getlist());
                break;
        }
    }


    public void onClickSelectContact(View btnSelectContact) {
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), REQUEST_CODE_PICK_CONTACTS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PICK_CONTACTS && resultCode == RESULT_OK) {
            Log.e("TAg", "Response: " + data.toString());
            Uri uriContact = data.getData();
            String contactName = contactsHelper.retrieveContactName(uriContact);
            String contactNumber = contactsHelper.retrieveContactNumber(uriContact);
            // Add New User In the DataBase
            Log.e("Tag", "ContactName: " + contactName + " ContactNumber " + contactNumber);
            addMember(contactName, contactNumber);
        }
    }

    private void setUserListData(List<User> userlist) {
        userListView.setLayoutManager(new LinearLayoutManager(this));
        if (userListAdapter == null) {
            userListAdapter = new UserListAdapter(AddTranactiontionActivity.this, userlist, new OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position, int type, Object o) {
                    switch (type) {
                        case 1:
                            userListAdapter.remove(position);
                            break;
                    }
                }
            });
            userListView.setAdapter(userListAdapter);
        } else {
            userListAdapter.addAll(userlist);
        }
    }

    // DataBase Functions
    private long addMember(String contactName, String contactNumber) {
        String totalpaid = "0.0";
        String totalshare = "0.0";
        long id = db.insertUser(contactName, contactNumber, totalpaid, totalshare);
        // get the newly inserted note from db
        User n = db.getUser(id);
        if (n != null) {
            Log.e("Tag", "Contact " + n.toString());
            // Add New User In the List
            userListAdapter.add(n);
        }
        return id;
    }


    private void addTransation(String details, String total, List<User> userList) {
        // Add transaction first
        long id = db.insertTransaction(details, total);
        // get Transaction Details
        Transaction transaction = db.getTransaction(id);
        if (transaction != null) {
            // Add Entry to every user transaction table
            for (int i = 0; i < userList.size(); i++) {
                // String trans_id, String user_id, String totalpaid, String totalshare
                db.insertUserTransaction(String.valueOf(transaction.getId()),
                        String.valueOf(userList.get(i).getId()), userList.get(i).getTotal_paid(), userList.get(i).getTotal_share());

                // Update the User Table with new details
                User user = db.getUser(userList.get(i).getId());
                double totalpaid = Double.parseDouble(user.getTotal_paid());
                double totalShare = Double.parseDouble(user.getTotal_share());
                double currentTotalPaid = Double.parseDouble(userList.get(i).getTotal_paid());
                double currentTotalShare = Double.parseDouble(userList.get(i).getTotal_share());
                db.updateUser(user.getId(), String.valueOf(totalpaid + currentTotalPaid), String.valueOf(totalShare + currentTotalShare));
            }

        }
        Toast.makeText(getApplicationContext(), "Transaction Done", Toast.LENGTH_LONG).show();
        onBackPressed();
    }


}
