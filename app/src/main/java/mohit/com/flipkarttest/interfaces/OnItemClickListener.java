package mohit.com.flipkarttest.interfaces;

import android.view.View;

public interface OnItemClickListener<T> {

     void onItemClick(View view, int position, int type, T t);

}