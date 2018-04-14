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

public class AddRemoveStockTest extends AndroidJUnitRunner{
    private static final int LAUNCH_TIMEOUT = 5000;;
    private UiDevice mDevice;
    private String stockToAdd = "NFLX";
    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
    }

    /**
     * This tests adding the NFLX stock to the widget
     * @throws UiObjectNotFoundException
     */
    @Test
    public void addStockTest () throws UiObjectNotFoundException {
        //Skipif
        Assume.assumeTrue(System.getenv("TRAVIS_CI") == null);

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();
        mDevice.waitForIdle();

        // Click the custom menu button
        UiObject customWidgetButton = mDevice.findObject(new UiSelector().text("Menu"));

        try {
            customWidgetButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("The custom menu button could not be found.");
            fail();
        }

        UiObject addButton = mDevice.findObject(new UiSelector().text("Add"));

        try {
            addButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("The Add button could not be found.");
            fail();
        }

        UiObject addItemButton = mDevice.findObject(new UiSelector().text("Add Item"));

        try {
            addItemButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("The custom menu button could not be found.");
            fail();
        }

        UiObject textfield  = mDevice.findObject(new UiSelector().className("android.widget.EditText").instance(0));
        textfield.setText("NFLX");

        UiObject addStockButton = mDevice.findObject(new UiSelector().text("Add Item"));

        try {
            addStockButton.click();
        } catch (UiObjectNotFoundException e) {
            System.out.println("The custom menu button could not be found.");
            fail();
        }

        mDevice.waitForIdle();
        mDevice.pressBack();
        mDevice.pressBack();

        // Click the custom menu button
        UiObject refreshWidgetButton = mDevice.findObject(new UiSelector().text("Refresh"));

        try {
            refreshWidgetButton.clickAndWaitForNewWindow();
        } catch (UiObjectNotFoundException e) {
            System.out.println("The refresh button could not be found.");
            fail();
        }
    }
}