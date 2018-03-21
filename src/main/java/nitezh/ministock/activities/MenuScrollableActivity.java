package nitezh.ministock.activities;


import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;


import nitezh.ministock.R;



public class MenuScrollableActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_activity);

       LinearLayout mainContent = (LinearLayout) findViewById(R.id.main_content);



       //Sample Menu

        //adds a category to the menu
        inflateCategoryRow("Category 0", new TextView(getApplicationContext()), mainContent);
        //adds 3 buttons to the menu
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);

        inflateCategoryRow("Category 1", new TextView(getApplicationContext()), mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);

        inflateCategoryRow("Category 2", new TextView(getApplicationContext()), mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);
        inflateRow("Title", "Description", mainContent);


    }


    //new TextView in param for testing
    //inflate a category row to order the options in the menu
    private void inflateCategoryRow(String category, TextView v, LinearLayout layout){
        v.setTextSize(40);
        v.setText(category);
        layout.addView(v);

    }

    //adds a button row to the view of the menu
    //also add a onClickListener to this row [implemented later]
    private void inflateRow(String title, String description, LinearLayout layout){
        //finds the row layout to inflate title and description
        View v = (View) getLayoutInflater().inflate(R.layout.scroll_menu_btn, null);
        ((TextView)v.findViewById(R.id.row_title)).setText(title);
        ((TextView)v.findViewById(R.id.row_description)).setText(description);

        layout.addView(v);


//        v.setOnClickListener((View.OnClickListener) this);

    }
}
