package nitzeh.ministock.ui;

import android.os.Bundle;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import org.junit.Assume;
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
     * @throws UiObjectNotFoundException Thrown when the attempting to perform an operation
     *                                   on a ui object that does not exist.
     */
    @Test
    public void searchForResizeableTest() throws UiObjectNotFoundException {
        //Skipif
        Assume.assumeTrue(System.getenv("TRAVIS_CI") == null);

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

    /**
     * This test searches through scrollable list of stocks and veirifies that the default
     * developer stock list is present.
     * @throws UiObjectNotFoundException Thrown when the attempting to perform an operation
     *                                   on a ui object that does not exist.
     */
    @Test
    public void defaultStockListTest() throws UiObjectNotFoundException {
        //Skipif
        Assume.assumeTrue(System.getenv("TRAVIS_CI") == null);

        // Initialize UiDevice instance
                mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();
        mDevice.waitForIdle();

        UiScrollable scrollableStocks = new UiScrollable(new UiSelector()
                .className("android.widget.ListView")
                .packageName("nitezh.ministock"));

        scrollableStocks.setAsVerticalList();
        int maxSearchSwipes = scrollableStocks.getMaxSearchSwipes();
        UiObject stockObject = null;

        for (String stock : defaultStocks){
            scrollableStocks.scrollToBeginning(10);
            for (int i =0; i<maxSearchSwipes; i++){
                stockObject = scrollableStocks.getChild(new UiSelector().text(stock));
                try {
                    stockObject.click();
                    break; //no exception was caught so stock has been found.
                } catch (UiObjectNotFoundException e) {
                    if (i == maxSearchSwipes - 1){
                        fail();
                    } else {
                        scrollableStocks.scrollForward();
                        continue;
                    }
                }
            }
        }
    }
}