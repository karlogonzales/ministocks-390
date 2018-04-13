package nitezh.ministock.activities.menu;


import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import nitezh.ministock.R;
import nitezh.ministock.utils.StockListSingleton;


public class MenuScrollableAdd extends Activity implements View.OnClickListener{

    //temporary list search
    private String[] stockListSearch = {"BTC", "ETH", "GOOG", "AAPL", "TSLA", "GM", "NFLX", "DIS", "TWTR", "PYPL", "FEYE", "FB", "BABA"};
    private ArrayAdapter<String> adapterSuggestion;
    private ArrayList<String> myStocks;
    private View additem;
    private ArrayAdapter<String> adapterListView;
    private AutoCompleteTextView textView;
    private ListView listView;

    private ArrayList<String> stockList = StockListSingleton.getInstance().getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_add);

        additem = (View) findViewById(R.id.additem);
        additem.setOnClickListener(this);

        listView = (ListView) findViewById(R.id.menuListView);
        /*call for the autocomplete text view
        the adapter will use the list of stock data to give suggestions
        it seems that the suggestions will show only once there is at least 2 char that match
        */
        textView = (AutoCompleteTextView)findViewById(R.id.autocomplete);

        adapterSuggestion = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stockListSearch);
        adapterListView = new ArrayAdapter<String>(this,R.layout.scroll_menu_add_search_listitem,R.id.textView,stockList);

        //auto complete has an adapter that will give suggestions
        textView.setAdapter(adapterSuggestion);
        //adapter for the listview in the menu
        //original call, check if user has inputed data previously
        populateListView();

    }


    private void populateListView(){
        listView.setAdapter(adapterListView);
    }

    @Override
    public void onClick(View v) {

        //add btn when no search
        //brings the search bar
        if(textView.getVisibility()==View.GONE){
            textView.setVisibility(View.VISIBLE);
            textView.requestFocus();
        }
        //with search, will add to the list view
        else{
            textView.setVisibility(View.GONE);

            //check for empty is valid
            //accept 1 to 4 character, must be a letter
            if(Pattern.matches("[a-zA-Z]{1,4}", textView.getText())){


                StockListSingleton.getInstance().addData(textView.getText().toString());

//            add to array list
//            stockList.add(textView.getText().toString());

                populateListView();

            }
            else{
                Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
            }

            //remove the previous input in the search bar
            textView.setText("");
        }
    }
}
