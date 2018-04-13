package nitezh.ministock.activities.menu;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import nitezh.ministock.R;

import static org.junit.Assert.*;

public class MenuScrollableAboutTest {


    @Rule
    public ActivityTestRule<MenuScrollableAbout> mActivityTestRule = new ActivityTestRule<MenuScrollableAbout>(MenuScrollableAbout.class);
    private MenuScrollableAbout mActivity = null;

    @Before
    public void setUp () throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    //If view is not null then the Launch of the view is successful
    @Test
    public void testLaunch () {
        View view = mActivity.findViewById(R.id.recent_changes);
        assertNotNull(view);
    }

    @After
    public void tearDown () throws Exception {
        mActivity = null;
    }

}