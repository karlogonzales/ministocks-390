package nitzeh.ministock.ui;

import android.os.Bundle;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class ScrollableWidgetTest extends AndroidJUnitRunner{
    private static final int LAUNCH_TIMEOUT = 5000;;
    private UiDevice mDevice;

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
    }

    /**
     * This test finds the scrollable ListView in the widget and scrolls to the end
     * to validate that it is a scrollable widget.
     * @throws InterruptedException
     * @throws UiObjectNotFoundException
     */
    @Test
    public void searchForResizeableTest() throws InterruptedException, UiObjectNotFoundException {

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();
        mDevice.waitForIdle();

        UiObject2 button = mDevice.findObject(By.text("Edit"));
        String appPackage = button.getApplicationPackage();
        UiObject2 parent = button.getParent();
        String p = parent.getClassName();
        UiObject2 parent2 = parent.getParent();
        String p2 = parent2.getClassName();
        System.out.println("test");

        UiObject stockList = mDevice.findObject(new UiSelector()
                .className("android.widget.ListView")
                .packageName("nitezh.ministock"));

        UiScrollable scrollableStocks = new UiScrollable(new UiSelector()
                .className("android.widget.ListView")
                .packageName("nitezh.ministock"));

        scrollableStocks.setAsVerticalList();
        scrollableStocks.scrollToEnd(50);
    }
}