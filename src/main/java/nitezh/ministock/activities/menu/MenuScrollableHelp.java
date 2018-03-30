package nitezh.ministock.activities.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nitezh.ministock.R;

public class MenuScrollableHelp extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_help);

        Button enteringStocks = (Button) findViewById(R.id.entering_stocks);
        Button selectingWidgetViews = (Button) findViewById(R.id.selecting_widget_views);
        Button usingThePortfolio = (Button) findViewById(R.id.using_the_portfolio);
        Button updatingPrices = (Button) findViewById(R.id.updating_prices);
        Button onlineHelp = (Button) findViewById(R.id.online_help);
        Button onlineFAQs = (Button) findViewById(R.id.online_faqs);
        Button support = (Button) findViewById(R.id.support);

        enteringStocks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableHelp.this, MenuScrollableEnteringStocks.class);
                startActivity(i);
            }
        });

        selectingWidgetViews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableHelp.this, MenuScrollableSelectingWidgetViews.class);
                startActivity(i);
            }
        });

        usingThePortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableHelp.this, MenuScrollableUsingThePortfolio.class);
                startActivity(i);
            }
        });

        updatingPrices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableHelp.this, MenuScrollableUpdatingPrices.class);
                startActivity(i);
            }
        });
        onlineHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableHelp.this, MenuScrollableOnlineHelp.class);
                startActivity(i);
            }
        });
        onlineFAQs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableHelp.this, MenuScrollableOnlineFAQs.class);
                startActivity(i);
            }
        });
        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableHelp.this, MenuScrollableSupport.class);
                startActivity(i);
            }
        });
    }

}