package nitezh.ministock.activities.menu;


import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import nitezh.ministock.R;

public class MenuScrollableRemove extends Activity{

    //temporary list search
    private String[] stockListSearch = {"BTC", "ETH", "GOOG", "AAPL", "TSLA", "GM", "NFLX", "DIS", "TWTR", "PYPL", "FEYE", "FB", "BABA"};
    private EditText editSearch;
    private ListView listSearch;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_remove);

        listSearch = (ListView) findViewById(R.id.listSearch);
        editSearch = (EditText) findViewById(R.id.editSearch);
        adapter = new ArrayAdapter<String>(this, R.layout.scroll_menu_add_search_listitem, R.id.textView, stockListSearch);
        listSearch.setAdapter(adapter);

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
}
