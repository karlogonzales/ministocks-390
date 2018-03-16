package nitezh.ministock.activities.configure;

import android.os.Bundle;

public class ConfigureActivityScrollable extends ConfigureActivityBase {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mWidgetSize = 4;
        super.onCreate(savedInstanceState);
    }
}