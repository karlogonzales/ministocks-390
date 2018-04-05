package nitezh.ministock.activities.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import nitezh.ministock.R;

public class MenuScrollableAbout extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_about);

        Button recentChanges = (Button) findViewById(R.id.recent_changes);
        Button license = (Button) findViewById(R.id.license);
        Button termsOfService = (Button) findViewById(R.id.terms_of_service);
        Button attributions = (Button) findViewById(R.id.attributions);

        recentChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableAbout.this, MenuScrollableRecentChanges.class);
                startActivity(i);
            }
        });

        license.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableAbout.this, MenuScrollableLicense.class);
                startActivity(i);
            }
        });

        termsOfService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableAbout.this, MenuScrollableTermsOfService.class);
                startActivity(i);
            }
        });

        attributions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableAbout.this, MenuScrollableAttributions.class);
                startActivity(i);
            }
        });
    }

}