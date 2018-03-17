package nitezh.ministock.activities;


import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;


import nitezh.ministock.R;

public class MenuScrollableActivity extends Activity {


    ListView lv;
    SearchView sv;
    //The Following Array is for testing purpose for front-end
    String[] alphabet = {"Apple", "Allen", "Ball", "Bask", "Carlo", "DejaVu", "Liver", "Zello"};
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_scrollable);

        lv=(ListView) findViewById(R.id.listview);
        sv=(SearchView) findViewById(R.id.searchview);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_gallery_item, alphabet);
        lv.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });

    }
}
