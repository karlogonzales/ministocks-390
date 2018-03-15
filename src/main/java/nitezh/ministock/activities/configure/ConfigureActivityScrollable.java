package nitezh.ministock.activities.configure;

import android.os.Bundle;

/**
 * Created by Andy on 2018-03-14.
 */

public class ConfigureActivityScrollable extends ConfigureActivityBase {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        mWidgetSize = 4;
        super.onCreate(savedInstanceState);
    }
}