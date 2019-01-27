package mohit.com.flipkarttest.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import mohit.com.flipkarttest.model.Transaction;
import mohit.com.flipkarttest.model.User;
import mohit.com.flipkarttest.model.UserTransaction;

/**
 * Created by ravi on 15/03/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "flipkart_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // create notes table
        db.execSQL(User.CREATE_USER_TABLE);
        db.execSQL(Transaction.CREATE_TRANSACTION_TABLE);
        db.execSQL(UserTransaction.CREATE_USER_TRANSACTION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Transaction.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + UserTransaction.TABLE_NAME);
        // Create tables again
        onCreate(db);
    }


    // Add User In Add
    public long insertUser(String contactName, String contactNumber, String totalpaid, String totalshare) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_NAME, contactName);
        values.put(User.COLUMN_PHN, contactNumber);
        values.put(User.COLUMN_TOTAL_PAID, totalpaid);
        values.put(User.COLUMN_TOTAL_SHARE, totalshare);
        long id = db.insert(User.TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // Add User In Add
    public long updateUser(int id, String totalpaid, String totalshare) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_TOTAL_PAID, totalpaid);
        values.put(User.COLUMN_TOTAL_SHARE, totalshare);
        // updating row
        return db.update(User.TABLE_NAME, values, User.COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public User getUser(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(User.TABLE_NAME,
                new String[]{User.COLUMN_ID, User.COLUMN_NAME, User.COLUMN_PHN, User.COLUMN_TOTAL_PAID, User.COLUMN_TOTAL_SHARE, User.COLUMN_TIMESTAMP},
                User.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        User note = new User(
                cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_PHN)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_TOTAL_PAID)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_TOTAL_SHARE)),
                cursor.getString(cursor.getColumnIndex(User.COLUMN_TIMESTAMP)));
        // close the db connection
        cursor.close();
        return note;
    }

    public List<User> getAllUser() {
        List<User> users = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + User.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User(
                        cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_PHN)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_TOTAL_PAID)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_TOTAL_SHARE)),
                        cursor.getString(cursor.getColumnIndex(User.COLUMN_TIMESTAMP)));
                users.add(user);
            } while (cursor.moveToNext());
        }
        // close db connection
        db.close();
        // return notes list
        return users;
    }


    // Add Transaction In Add
    public long insertTransaction(String details, String totalpaid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Transaction.COLUMN_DETAILS, details);
        values.put(Transaction.COLUMN_TOTAL_PAID, totalpaid);
        long id = db.insert(Transaction.TABLE_NAME, null, values);
        db.close();
        return id;
    }


    public Transaction getTransaction(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Transaction.TABLE_NAME,
                new String[]{Transaction.COLUMN_ID, Transaction.COLUMN_DETAILS, Transaction.COLUMN_TOTAL_PAID, Transaction.COLUMN_TIMESTAMP},
                Transaction.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        Transaction note = new Transaction(
                cursor.getInt(cursor.getColumnIndex(Transaction.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Transaction.COLUMN_DETAILS)),
                cursor.getString(cursor.getColumnIndex(Transaction.COLUMN_TOTAL_PAID)),
                cursor.getString(cursor.getColumnIndex(Transaction.COLUMN_TIMESTAMP)));
        // close the db connection
        cursor.close();
        return note;
    }


    // Add User Transaction
    public long insertUserTransaction(String trans_id, String user_id, String totalpaid, String totalshare) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserTransaction.COLUMN_TRANS_ID, trans_id);
        values.put(UserTransaction.COLUMN_USER_ID, user_id);
        values.put(UserTransaction.COLUMN_TOTAL_PAID, totalpaid);
        values.put(UserTransaction.COLUMN_TOTAL_SHARE, totalshare);
        long id = db.insert(UserTransaction.TABLE_NAME, null, values);
        db.close();
        return id;
    }


    public UserTransaction getUserTransaction(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Transaction.TABLE_NAME,
                new String[]{UserTransaction.COLUMN_ID,
                        UserTransaction.COLUMN_TRANS_ID,
                        UserTransaction.COLUMN_USER_ID,
                        UserTransaction.COLUMN_TOTAL_PAID,
                        UserTransaction.COLUMN_TOTAL_SHARE,
                        UserTransaction.COLUMN_TIMESTAMP},
                UserTransaction.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();
        // prepare note object
        UserTransaction note = new UserTransaction(
                cursor.getInt(cursor.getColumnIndex(Transaction.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(UserTransaction.COLUMN_TRANS_ID)),
                cursor.getString(cursor.getColumnIndex(UserTransaction.COLUMN_USER_ID)),
                cursor.getString(cursor.getColumnIndex(UserTransaction.COLUMN_TOTAL_PAID)),
                cursor.getString(cursor.getColumnIndex(UserTransaction.COLUMN_TOTAL_SHARE)),
                cursor.getString(cursor.getColumnIndex(UserTransaction.COLUMN_TIMESTAMP)));
        // close the db connection
        cursor.close();
        return note;
    }
}