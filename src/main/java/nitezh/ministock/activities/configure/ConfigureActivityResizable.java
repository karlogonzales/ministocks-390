package nitezh.ministock.activities.configure;


import android.os.Bundle;

/**
 * Implementation of App Widget functionality.
 */
public class ConfigureActivityResizable extends ConfigureActivityBase {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mWidgetSize = 3;
        super.onCreate(savedInstanceState);
    }}

