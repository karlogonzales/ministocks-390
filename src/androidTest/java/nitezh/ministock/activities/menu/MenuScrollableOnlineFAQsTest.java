package nitezh.ministock.activities.menu;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import nitezh.ministock.R;

import static org.junit.Assert.*;

public class MenuScrollableOnlineFAQsTest {

    @Rule
    public ActivityTestRule<MenuScrollableOnlineFAQs> mActivityTestRule = new ActivityTestRule<>(MenuScrollableOnlineFAQs.class);
    private MenuScrollableOnlineFAQs mActivity = null;
    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch () {
        View view = mActivity.findViewById(R.id.FAQ);
        assertNotNull(view);
    }
    @After
    public void tearDown() throws Exception {
        mActivity = null;

    }

}