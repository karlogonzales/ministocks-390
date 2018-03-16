package nitzeh.ministock.ui;

import android.os.Bundle;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObjectNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class ScrollableWidgetTest extends AndroidJUnitRunner{
    private static final int LAUNCH_TIMEOUT = 5000;;
    private UiDevice mDevice;

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
    }

    @Test
    public void searchForResizeableTest() throws InterruptedException, UiObjectNotFoundException {

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();
        mDevice.waitForIdle();
    }
}