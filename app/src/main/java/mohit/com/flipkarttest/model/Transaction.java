package mohit.com.flipkarttest.model;

public class Transaction {
    public static final String TABLE_NAME = "transactions";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DETAILS = "details";
    public static final String COLUMN_TOTAL_PAID = "total_paid";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String details;
    private String total_paid;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TRANSACTION_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_DETAILS + " TEXT,"
                    + COLUMN_TOTAL_PAID + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public Transaction(int id, String details, String total_paid, String timestamp) {
        this.id = id;
        this.details = details;
        this.total_paid = total_paid;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTotal_paid() {
        return total_paid;
    }

    public void setTotal_paid(String total_paid) {
        this.total_paid = total_paid;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}