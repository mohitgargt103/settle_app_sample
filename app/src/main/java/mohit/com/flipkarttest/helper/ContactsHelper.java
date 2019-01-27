package mohit.com.flipkarttest.helper;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

public class ContactsHelper {
    Context context;

    public ContactsHelper(Context context) {
        this.context = context;
    }

    public String retrieveContactNumber(Uri uriContact) {
        String contactNumber = null;
        String contactID = null;
        // getting contacts ID
        Cursor cursorID = context.getContentResolver().query(uriContact,
                new String[]{ContactsContract.Contacts._ID},
                null, null, null);
        if (cursorID.moveToFirst()) {
            contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
        }
        cursorID.close();
        Log.d("TAG", "Contact ID: " + contactID);
        Cursor cursorPhone = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},

                ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                        ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,

                new String[]{contactID},
                null);

        if (cursorPhone.moveToFirst()) {
            contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
        }
        cursorPhone.close();
        Log.d("TAG", "Contact Phone Number: " + contactNumber);
        return contactNumber;
    }

    public String retrieveContactName(Uri uriContact) {

        String contactName = null;
        // querying contact data store
        Cursor cursor = context.getContentResolver().query(uriContact, null, null, null, null);

        if (cursor.moveToFirst()) {

            // DISPLAY_NAME = The display name for the contact.
            // HAS_PHONE_NUMBER =   An indicator of whether this contact has at least one phone number.

            contactName = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
        }
        cursor.close();
        Log.d("TAG", "Contact Name: " + contactName);
        return contactName;
    }
}
