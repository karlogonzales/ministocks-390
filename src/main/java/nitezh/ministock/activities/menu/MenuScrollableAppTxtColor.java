package nitezh.ministock.activities.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nitezh.ministock.R;


public class MenuScrollableAppTxtColor extends Activity {

    private Button blue;
    private Button red;
    private Button black;
    private Button white;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_popup_color);

        blue = (Button) findViewById(R.id.blue);
        red = (Button) findViewById(R.id.red);
        black = (Button) findViewById(R.id.black);
        white = (Button) findViewById(R.id.white);

        blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                blue.setText("Pressed");
            }
        });

        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                red.setText("Pressed");
            }
        });

        black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                black.setText("Pressed");
            }
        });

        white.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                white.setText("Pressed");
            }
        });
    }
}


