package nitezh.ministock.activities.menu;

import android.app.Activity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import nitezh.ministock.R;



public class MenuScrollableAdd extends Activity {

    //temporary list search
    private String[] stockListSearch = {"BTC", "ETH", "GOOG", "AAPL", "TSLA", "GM", "NFLX", "DIS", "TWTR", "PYPL", "FEYE", "FB", "BABA"};
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_add);

        /*call for the autocomplete text view
        the adapter will use the list of stock data to give suggestions
        it seems that the suggestions will show only once there is at least 2 char that match
        */
        AutoCompleteTextView textView = (AutoCompleteTextView)findViewById(R.id.autocomplete);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stockListSearch);
        textView.setAdapter(adapter);
    }
}
