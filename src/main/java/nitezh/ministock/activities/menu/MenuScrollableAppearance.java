package nitezh.ministock.activities.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import nitezh.ministock.R;

public class MenuScrollableAppearance extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_appearance);

        Button background = (Button) findViewById(R.id.background);
        Button txtColor = (Button) findViewById(R.id.txtColor);
        Button txtStyle = (Button) findViewById(R.id.txtStyle);

        background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableAppearance.this, MenuScrollableAppBackground.class);
                startActivity(i);
            }
        });

        txtColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableAppearance.this, MenuScrollableAppTxtColor.class);
                startActivity(i);
            }
        });

        txtStyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuScrollableAppearance.this, MenuScrollableAppTxtStyle.class);
                startActivity(i);
            }
        });
    }

}
