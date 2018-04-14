package nitzeh.ministock.ui;

import android.os.Bundle;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;

import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class CustomMenuTests extends AndroidJUnitRunner{
    private static final int LAUNCH_TIMEOUT = 5000;;
    private UiDevice mDevice;
    private String[] customMenuItems = {"Add", "Remove", "Change Order", "Update Stocks", "Appearance", "Help", "About"};

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
    }

    @Test
    public void customMenuItemsTest() throws UiObjectNotFoundException {
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

        UiScrollable scrollableMenuList = new UiScrollable(new UiSelector()
                .className("android.widget.ScrollView")
                .packageName("nitezh.ministock"));

        scrollableMenuList.setAsVerticalList();
        int maxSearchSwipes = scrollableMenuList.getMaxSearchSwipes();
        UiObject menuItemObject = null;

        for (String menuItem : customMenuItems){
            scrollableMenuList.scrollToBeginning(10);
            for (int i = 0; i<maxSearchSwipes; i++){
                menuItemObject = mDevice.findObject(new UiSelector().text(menuItem));
                if (menuItemObject != null){
                    break;
                } else if (menuItemObject == null && (i == maxSearchSwipes -1)){
                    System.out.println("The menu item " + menuItem + " could not be found");
                    fail();
                }else if (menuItemObject == null){
                    scrollableMenuList.scrollForward();
                    continue;
                }
            }
        }
    }

}
