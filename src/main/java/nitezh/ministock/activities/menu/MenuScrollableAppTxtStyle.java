package nitezh.ministock.activities.menu;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nitezh.ministock.R;

public class MenuScrollableAppTxtStyle extends Activity {

    private Button italic;
    private Button bold;
    private Button boldItalic;

    @Override
    protected  void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_menu_popup_style);

        italic = (Button) findViewById(R.id.italic);
        bold = (Button) findViewById(R.id.bold);
        boldItalic = (Button) findViewById(R.id.bolditalic);

        italic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                italic.setText("Pressed");
            }
        });

        bold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                bold.setText("Pressed");
            }
        });

        boldItalic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button implementation
                boldItalic.setText("Pressed");
            }
        });
    }
}

