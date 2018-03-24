package nitezh.ministock.activities.menu;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import nitezh.ministock.R;



public class MenuScrollableActivity extends Activity implements View.OnClickListener{

    private int category_text_size = 20;

    View add_btn = null;
    View remove_btn = null;
    View change_order_btn = null;
    View update_btn = null;
    View appearance_btn = null;
    View help_btn = null;
    View about_btn = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_activity);

       LinearLayout mainContent = (LinearLayout) findViewById(R.id.main_content);



       //Sample Menu

        //adds a category to the menu

        //inflateCategoryRow("Stock Setup", new TextView(getApplicationContext()), mainContent);
        //old implementation of inflateCategoryRow without having a layout for the category row
        inflateCategoryRow("Stock Setup", mainContent);
        //adds 3 buttons to the menu
        add_btn = inflateRow("Add", "Add stock data to the stock list", mainContent);
        remove_btn = inflateRow("Remove", "Remove stock data from the stock list", mainContent);
        change_order_btn = inflateRow("Change Order", "Change the order of a stock data from the stock list", mainContent);
        update_btn = inflateRow("Update Stocks", "Update the prices of all stocks", mainContent);

        inflateCategoryRow("General", mainContent);
        appearance_btn = inflateRow("Appearance", "Change the appearance of the widget", mainContent);
        help_btn = inflateRow("Help", "View help and support options", mainContent);
        about_btn = inflateRow("About", "About Ministocks and disclaimer", mainContent);

    }


    //new TextView in param for testing
    //inflate a category row to order the options in the menu
    private void inflateCategoryRow(String category, LinearLayout layout){
        View v = getLayoutInflater().inflate(R.layout.scroll_menu_category, null);
        ((TextView)v.findViewById(R.id.category_text)).setText(category);
        ((TextView)v.findViewById(R.id.category_text)).setTextSize(category_text_size);

        layout.addView(v);

    }

    //adds a button row to the view of the menu
    private View inflateRow(String title, String description, LinearLayout layout){
        //finds the row layout to inflate title and description
        View v = getLayoutInflater().inflate(R.layout.scroll_menu_btn, null);
        ((TextView)v.findViewById(R.id.row_title)).setText(title);
        ((TextView)v.findViewById(R.id.row_description)).setText(description);

        layout.addView(v);

        v.setOnClickListener(this);
        return v;
    }

    @Override
    // note that a button does not need to change intent, can just call for a method from this class
    public void onClick(View v) {
        Intent intent = null;

        if(v == add_btn){
            intent = new Intent(this, MenuScrollableAdd.class);
//            LinearLayout layout = (LinearLayout) findViewById(R.layout.scroll_menu_add);
//            String message = "Menu screen for adding a stock data";
//            inflateRow("Add",message,layout);
        }
        else if(v == remove_btn){
            //event for this button
            Toast.makeText(getApplicationContext(), "remove", Toast.LENGTH_SHORT).show();
        }
        else if(v == change_order_btn){
            //event for this button
            Toast.makeText(getApplicationContext(), "change order", Toast.LENGTH_SHORT).show();
        }
        else if(v == update_btn){
            //event for this button
            Toast.makeText(getApplicationContext(), "update", Toast.LENGTH_SHORT).show();
        }
        else if(v == appearance_btn){
            //event for this button
            Toast.makeText(getApplicationContext(), "appearance", Toast.LENGTH_SHORT).show();
        }
        else if(v == help_btn){
            //event for this button
            Toast.makeText(getApplicationContext(), "help", Toast.LENGTH_SHORT).show();
        }
        else if(v == about_btn) {
            //event for this button
            Toast.makeText(getApplicationContext(), "about", Toast.LENGTH_SHORT).show();
        }
        //set the intent if the button option requires to change activity
        //otherwise, stay on this activity
        if(intent != null){
            startActivity(intent);
        }
    }
}
