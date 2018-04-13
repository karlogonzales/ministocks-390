package nitezh.ministock.activities.widget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import nitezh.ministock.R;
import nitezh.ministock.activities.configure.ConfigureActivityScrollable;
import nitezh.ministock.activities.menu.MenuScrollableActivity;
import nitezh.ministock.activities.PreferencesActivity;
import nitezh.ministock.utils.WidgetService;

public class WidgetProviderScrollable extends WidgetProviderBase {
    public static String EXTRA_WORD = "WORD";
    public static String UPDATE_LIST = "UPDATE_LIST";
    /*
     * this method is called every 30 mins as specified on widgetinfo.xml
     * this method is also called on every phone reboot
     */
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        /*int[] appWidgetIds holds ids of multiple instance of your widget
         * meaning you are placing more than one widgets on your homescreen*/
        Log.e("app widget id - ", appWidgetIds.length+"");
        for (int i = 0; i < appWidgetIds.length; ++i) {
            Intent svcIntent = new Intent(context, WidgetService.class);
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_scroll_layout);
            remoteViews.setRemoteAdapter(appWidgetIds[i], R.id.listViewWidget, svcIntent);
            remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);

            Intent clickIntent = new Intent(context, ConfigureActivityScrollable.class);
            PendingIntent clickPI = PendingIntent.getActivity(context, 0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setPendingIntentTemplate(R.id.listViewWidget, clickPI);


            clickIntent = new Intent(context, WidgetProviderScrollable.class);
            clickIntent.setAction(UPDATE_LIST);
            PendingIntent pendingIntentRefresh = PendingIntent.getBroadcast(context,0, clickIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.refresh_list, pendingIntentRefresh);

            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);

            //custom menu
            Intent intent = new Intent(context, MenuScrollableActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pending = PendingIntent.getActivity(context, 0,
                    intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_scroll_layout);

            views.setOnClickPendingIntent(R.id.custom, pending);
            appWidgetManager.updateAppWidget(appWidgetIds[i], views);


        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);


    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(intent.getAction().equalsIgnoreCase(UPDATE_LIST)){
            updateWidget(context);
        }
        Log.e("onReceive", "onReceive");
    }

    private void updateWidget(Context context) {
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        int appWidgetIds[] = appWidgetManager.getAppWidgetIds(new ComponentName(context, WidgetProviderScrollable.class));
        appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.listViewWidget);
    }
//
//    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {
//
//        //which layout to show on widget
//        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
//                R.layout.widget_scroll_layout);
//
//        //RemoteViews Service needed to provide adapter for ListView
//        Intent svcIntent = new Intent(context, WidgetService.class);
//        //passing app widget id to that RemoteViews Service
//        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
//        //setting a unique Uri to the intent
//        //don't know its purpose to me right now
//        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
//        //setting adapter to listview of the widget
//        remoteViews.setRemoteAdapter(appWidgetId, R.id.listViewWidget,
//                svcIntent);
//        //setting an empty view in case of no data
//        remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
//        return remoteViews;
//    }
//

}
