package nitezh.ministock.activities.menu;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

import nitezh.ministock.R;
import nitezh.ministock.utils.StockListSingleton;

public class MenuScrollableRemove extends Activity implements AdapterView.OnItemClickListener{

    //temporary list search
    private String[] stockListSearch = {"BTC", "ETH", "GOOG", "AAPL", "TSLA", "GM", "NFLX", "DIS", "TWTR", "PYPL", "FEYE", "FB", "BABA"};
    private EditText editSearch;
    private ListView listSearch;
    private ArrayAdapter<String> adapter;

    private ArrayList<String> stockList = StockListSingleton.getInstance().getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_remove);

        listSearch = (ListView) findViewById(R.id.listSearch);
        editSearch = (EditText) findViewById(R.id.editSearch);
        adapter = new ArrayAdapter<String>(this, R.layout.scroll_menu_add_search_listitem, R.id.textView, stockList);
        listSearch.setAdapter(adapter);

        listSearch.setOnItemClickListener(this);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MenuScrollableRemove.this.adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    @Override
    public void onItemClick(AdapterView<?> adapter, View view, int position, long id) {
        // Get the selected item text from ListView
        String item = Integer.toString(position);

        Toast toast= Toast.makeText(getApplicationContext(),item,Toast.LENGTH_SHORT);
        toast.show();
        removeStockData(position);
    }


    public void removeStockData(int position){
        stockList.remove(position);
        adapter = new ArrayAdapter<String>(this, R.layout.scroll_menu_add_search_listitem, R.id.textView, stockList);
        listSearch.setAdapter(adapter);
    }
}
