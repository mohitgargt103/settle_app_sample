package mohit.com.flipkarttest.model;

public class User {
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHN = "phn";
    public static final String COLUMN_TOTAL_PAID = "total_paid";
    public static final String COLUMN_TOTAL_SHARE = "total_share";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String name;
    private String phn;
    private String total_paid;
    private String total_share;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_USER_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_NAME + " TEXT,"
                    + COLUMN_PHN + " TEXT,"
                    + COLUMN_TOTAL_PAID + " TEXT,"
                    + COLUMN_TOTAL_SHARE + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public User(int id, String name, String phn, String total_paid, String total_share, String timestamp) {
        this.id = id;
        this.name = name;
        this.phn = phn;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phn='" + phn + '\'' +
                ", total_paid='" + total_paid + '\'' +
                ", total_share='" + total_share + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}