package nitzeh.ministock.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject2;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;

/**
 * This class is an instrumentation test that uses the ui automator to check if
 * the two new resizeable ministocks widgets have been added to the list of available
 * widgets
 */
@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class ResizeableWidgetTest extends AndroidJUnitRunner{
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

        //Get the center of the screen to be used for long press
        Point screenSize = new Point(mDevice.getDisplayWidth(), mDevice.getDisplayHeight());
        Point screenCenter = new Point(screenSize.x / 2, screenSize.y /2);
        Point segments[] = {screenCenter, screenCenter};

        // Start from the home screen
        mDevice.pressHome();
        mDevice.waitForIdle();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertNotNull(launcherPackage);
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        //attempt long press
        mDevice.swipe(segments, 150);
        Thread.sleep(2000);

        //Bring up the list of widgets
        mDevice.findObject(By.text("Widgets")).click();
        mDevice.waitForIdle();
        Thread.sleep(2000);

        UiScrollable widgets = new UiScrollable(new UiSelector().scrollable(true));

        // Set swiping mode to horizontal and scroll to the beginning of the list
        widgets.setAsHorizontalList();
        widgets.scrollToBeginning(50);
        int maxSearchSwipes = widgets.getMaxSearchSwipes();

        UiObject2 resizeableWidgetOne = null;
        UiObject2 resizeableWidgetTwo = null;

        // Swipe forward through the list of widgets until they have been found
        for (int i = 0; i < maxSearchSwipes; i++){
            if (resizeableWidgetOne == null){
                resizeableWidgetOne = mDevice.findObject(By.text("Resizable"));
            }

            if (resizeableWidgetTwo == null){
                resizeableWidgetTwo = mDevice.findObject(By.text("Resizable2"));
            }

            if (resizeableWidgetOne != null && resizeableWidgetTwo != null){
                break;
            } else{
                widgets.scrollForward();
            }
        }

        assertNotNull(resizeableWidgetOne);
        assertNotNull(resizeableWidgetTwo);
    }
}
