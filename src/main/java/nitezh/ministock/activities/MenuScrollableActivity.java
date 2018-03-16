package nitezh.ministock.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;


import nitezh.ministock.R;

public class MenuScrollableActivity extends Activity {

    SearchView src;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_scrollable);

        src = (SearchView) findViewById(R.id.searchView);
        btn = (Button) findViewById(R.id.addButton);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code of what button will do when clicked
                //example
                btn.setText("Pressed");
            }
        });
    }
}
