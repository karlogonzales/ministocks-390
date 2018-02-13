package nitzeh.ministock.ui;

import android.graphics.Point;
import android.os.Bundle;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.By;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiSelector;
import android.support.test.uiautomator.Until;
import android.test.InstrumentationTestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)

public class ResizeableWidgetTest extends AndroidJUnitRunner{
    private static final String BASIC_SAMPLE_PACKAGE
            = "com.example.android.testing.uiautomator.BasicSample";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    private UiDevice mDevice;

    @Override
    public void onCreate(Bundle args) {
        super.onCreate(args);
    }

    @Test
    public void setUp() throws InterruptedException {
        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(getInstrumentation());
        Point screenSize = new Point(mDevice.getDisplayWidth(), mDevice.getDisplayHeight());
        Point screenCenter = new Point(screenSize.x / 2, screenSize.y /2);
        Point segments[] = {screenCenter, screenCenter};

        // Start from the home screen
        mDevice.pressHome();

        // Wait for launcher
        final String launcherPackage = mDevice.getLauncherPackageName();
        assertNotNull(launcherPackage);
        mDevice.wait(Until.hasObject(By.pkg(launcherPackage).depth(0)),
                LAUNCH_TIMEOUT);

        //attempt long press
        mDevice.swipe(segments, 150);
        Thread.sleep(2000);

        mDevice.findObject(By.text("Widgets")).click();
        mDevice.waitForIdle();
    }
}
