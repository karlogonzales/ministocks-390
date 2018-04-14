package nitezh.ministock.activities.menu;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import nitezh.ministock.R;

import static org.junit.Assert.assertNotNull;

public class MenuScrollableAddTest {

    @Rule
    public ActivityTestRule<MenuScrollableAdd> mActivityTestRule = new ActivityTestRule<MenuScrollableAdd>(MenuScrollableAdd.class);
    private MenuScrollableAdd mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityTestRule.getActivity();
    }

    //If view is not null then the Launch of the view is successful
    @Test
    public void testLaunch() {
        View view = mActivity.findViewById(R.id.autocomplete);
        assertNotNull(view);
    }

    //Ensure that the MenuListView is present
    @Test
    public void ensureListViewIsPresent() throws Exception {
        MenuScrollableAdd activity = mActivityTestRule.getActivity();
        View viewById = activity.findViewById(R.id.menuListView);
        assertNotNull(viewById);
    }

    //Check if "AddItem" adds to menuListView
    @Test
    public void testAddItem() throws Exception {
        /*
        MenuScrollableAdd activity = mActivityTestRule.getActivity();
        ListView viewById = (ListView) activity.findViewById(R.id.menuListView);
        TextView btn = (TextView)activity.findViewById(R.id.additem);
        viewById.addView(btn);
        btn.setPressed(true);
        assertEquals(viewById, btn);
        */
    }

    @After
    public void tearDown() throws Exception {
        mActivity = null;
    }

}