package mohit.com.flipkarttest.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import mohit.com.flipkarttest.R;
import mohit.com.flipkarttest.helper.DatabaseHelper;
import mohit.com.flipkarttest.model.User;
import mohit.com.flipkarttest.view.adapter.DashBoardAdapter;

public class DashBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAddTrans;
    private DatabaseHelper db;
    private RecyclerView userListView;
    private DashBoardAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        //DataBase
        db = new DatabaseHelper(this);
        initUI();
    }

    private void initUI() {
        userListView = (RecyclerView) findViewById(R.id.list_view);
        btnAddTrans = (Button) findViewById(R.id.btnAddTrans);
        btnAddTrans.setOnClickListener(this);
        setUserListData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddTrans:
                Intent intent = new Intent(this, AddTranactiontionActivity.class);
                startActivityForResult(intent, 100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            setUserListData();
        }
    }

    private void setUserListData() {
        List<User> userlist = db.getAllUser();
        userListView.setLayoutManager(new LinearLayoutManager(this));
        userListAdapter = new DashBoardAdapter(DashBoardActivity.this, userlist, null);
        userListView.setAdapter(userListAdapter);
    }

}
