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
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class ScrollableWidgetTest extends AndroidJUnitRunner{
    private static final int LAUNCH_TIMEOUT = 5000;;
    private UiDevice mDevice;
    private String[] defaultStocks = {"BTC", "ETH", "GOOG", "AAPL", "TSLA", "GM", "NFLX", "DIS", "TWTR", "PYPL", "FEYE", "FB", "BABA"};

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

        UiScrollable scrollableStocks = new UiScrollable(new UiSelector()
                .className("android.widget.ListView")
                .packageName("nitezh.ministock"));

        scrollableStocks.setAsVerticalList();
        scrollableStocks.scrollToEnd(50);

        assertTrue(scrollableStocks.isScrollable());
    }

    @Test
    public void defaultStockListTest() throws UiObjectNotFoundException {
        // Initialize UiDevice instance
                mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();
        mDevice.waitForIdle();

        UiScrollable scrollableStocks = new UiScrollable(new UiSelector()
                .className("android.widget.ListView")
                .packageName("nitezh.ministock"));

        scrollableStocks.setAsVerticalList();
        scrollableStocks.scrollToBeginning(50);
        int maxSearchSwipes = scrollableStocks.getMaxSearchSwipes();

        for (String stock : defaultStocks){
            try {
                UiObject stockObject = scrollableStocks.getChild(new UiSelector().text(stock));
                stockObject.click();
            } catch (UiObjectNotFoundException e) {
                fail("The stock " + stock + " could not be found in the default developer stock list.");
            }
        }
    }
}