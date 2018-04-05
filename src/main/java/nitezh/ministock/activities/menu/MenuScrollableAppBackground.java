package nitezh.ministock.activities.menu;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import nitezh.ministock.R;

public class MenuScrollableAppBackground extends Activity {

    private Button sky;
    private Button fire;
    private Button game;
    private Button black;
    private Button white;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_popup_background);

        sky = (Button) findViewById(R.id.sky);
        fire = (Button) findViewById(R.id.fire);
        game = (Button) findViewById(R.id.game);
        black = (Button) findViewById(R.id.black);
        white = (Button) findViewById(R.id.white);

        sky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                sky.setText("Pressed");
            }
        });

        fire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                fire.setText("Pressed");
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                game.setText("Pressed");
            }
        });
    }
}

