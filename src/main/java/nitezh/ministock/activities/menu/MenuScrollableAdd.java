package nitezh.ministock.activities.menu;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

import nitezh.ministock.R;
import nitezh.ministock.utils.ListItem;
import nitezh.ministock.utils.StockListSingleton;


public class MenuScrollableAdd extends Activity implements View.OnClickListener{

    //temporary list search
    private String[] stockListSearch = {"BTC", "ETH", "GOOG", "AAPL", "TSLA", "GM", "NFLX", "DIS", "TWTR", "PYPL", "FEYE", "FB", "BABA"};
    private ArrayAdapter<String> adapter;
    private ArrayList<String> myStocks;
    private View additem;
    private ArrayAdapter<String> adapter1;
    private AutoCompleteTextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_add);

        additem = (View) findViewById(R.id.additem);
        additem.setOnClickListener(this);

        /*call for the autocomplete text view
        the adapter will use the list of stock data to give suggestions
        it seems that the suggestions will show only once there is at least 2 char that match
        */
        textView = (AutoCompleteTextView)findViewById(R.id.autocomplete);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stockListSearch);
        textView.setAdapter(adapter);
        myStocks = new ArrayList<>();

    }

    private void populateListView(){
        adapter1 = new ArrayAdapter<String>(this,R.layout.menu_list_item,myStocks);
        ListView listView = (ListView) findViewById(R.id.menuListView);
        listView.setAdapter(adapter1);
    }

    @Override
    public void onClick(View v) {

        if(textView.getVisibility()==View.GONE){
            textView.setVisibility(View.VISIBLE);
            textView.requestFocus();
        }
        else{
            textView.setVisibility(View.GONE);
            myStocks.add(textView.getText().toString());
            StockListSingleton.getInstance().addData(textView.getText().toString());
            populateListView();
            textView.setText("");

        }
    }
}
