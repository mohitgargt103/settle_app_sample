package mohit.com.flipkarttest.model;

public class UserTransaction {
    public static final String TABLE_NAME = "users_trans_table";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TRANS_ID = "trans_id";
    public static final String COLUMN_USER_ID = "user_id";
    public static final String COLUMN_TOTAL_PAID = "total_paid";
    public static final String COLUMN_TOTAL_SHARE = "total_share";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String trans_id;
    private String user_id;
    private String total_paid;
    private String total_share;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_USER_TRANSACTION_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TRANS_ID + " INTEGER,"
                    + COLUMN_USER_ID + " INTEGER,"
                    + COLUMN_TOTAL_PAID + " TEXT,"
                    + COLUMN_TOTAL_SHARE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";


    public UserTransaction(int id, String trans_id, String user_id, String total_paid, String total_share, String timestamp) {
        this.id = id;
        this.trans_id = trans_id;
        this.user_id = user_id;
        this.total_paid = total_paid;
        this.total_share = total_share;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotal_paid() {
        return total_paid;
    }

    public void setTotal_paid(String total_paid) {
        this.total_paid = total_paid;
    }

    public String getTotal_share() {
        return total_share;
    }

    public void setTotal_share(String total_share) {
        this.total_share = total_share;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}