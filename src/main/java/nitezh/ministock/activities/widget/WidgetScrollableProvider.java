package nitezh.ministock.activities.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RemoteViews;
import android.widget.TextView;

import org.w3c.dom.Text;

import nitezh.ministock.R;

import static java.util.Collections.addAll;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetScrollableProvider extends AppWidgetProvider {

    ListView mListView;
    TextView scrollable;
    int num = 0;


    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        RemoteViews remoteViews;
        ComponentName componentName;

        remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_scrollable);
        componentName = new ComponentName(context, WidgetScrollableProvider.class);

        remoteViews.setTextViewText(R.id.textView,"Scrollable");
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }



    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_scrollable);


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
            scrollable = (TextView) scrollable.findViewById(R.id.textView);
            scrollable.setText("Scrollable");

            String[] array = {"1","2","3","4","5"};
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, array);
            mListView = (ListView) mListView.findViewById(R.id.list_view1);
            mListView.setAdapter(adapter);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created



    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
    class CustomAdapter extends BaseAdapter{

        private Context mContext;

        //Constructor

        public CustomAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return num;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
//            View view = View.inflate(mContext,R.layout.scrollable_item,null);
//            TextView num = (TextView) view.findViewById(R.id.scrollable_item);
            return null;
        }

    }
}

