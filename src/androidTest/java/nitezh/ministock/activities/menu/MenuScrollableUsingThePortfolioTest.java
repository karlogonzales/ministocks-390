package nitezh.ministock.activities.menu;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import nitezh.ministock.R;

import static org.junit.Assert.assertNotNull;

public class MenuScrollableUsingThePortfolioTest {

    @Rule
    public ActivityTestRule<MenuScrollableUsingThePortfolio> mActivityTestRule = new ActivityTestRule<>(MenuScrollableUsingThePortfolio.class);
    private MenuScrollableUsingThePortfolio mActivity = null;

    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = mActivity.findViewById(R.id.using_the_portfolio);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;

    }

}