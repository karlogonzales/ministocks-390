package nitezh.ministock.activities.widget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.Toast;


import nitezh.ministock.R;

import nitezh.ministock.activities.menu.MenuScrollableActivity;
import nitezh.ministock.utils.ListProvider;
import nitezh.ministock.utils.WidgetService;

public class WidgetProviderScrollable extends AppWidgetProvider {

    public static String REFRESH_BUTTON ="APPWIDGET_UPDATE";
    /*
     * this method is called every 30 mins as specified on widgetinfo.xml
     * this method is also called on every phone reboot
     */

    private void updateAppWidget(Context context,
                                AppWidgetManager appWidgetManager,
                                int appWidgetId) {


        RemoteViews remoteViews = updateWidgetListView(context, appWidgetId);
        // Setup update button to send an update request as a pending intent.
        Intent intentUpdate = new Intent(context, WidgetProviderScrollable.class);

        ListProvider listProvider = new ListProvider(context, intentUpdate);

        // The intent action must be an app widget update.
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);

        // Include the widget ID to be updated as an intent extra.
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);

        // Wrap it all in a pending intent to send a broadcast.
        // Use the app widget ID as the request code (third argument) so that
        // each intent is unique.
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(context,
                appWidgetId, intentUpdate, PendingIntent.FLAG_UPDATE_CURRENT);

        // Assign the pending intent to the button onClick handler
        remoteViews.setOnClickPendingIntent(R.id.refresh, pendingUpdate);

        // Instruct the widget manager to update the widget.

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);

        Toast.makeText(context, "so refreshing", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void onUpdate(final Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {



        final int N = appWidgetIds.length;
        /*int[] appWidgetIds holds ids of multiple instance of your widget
         * meaning you are placing more than one widgets on your homescreen*/
        for (int i = 0; i < N; ++i) {
            updateAppWidget(context,appWidgetManager,appWidgetIds[i]);
            RemoteViews remoteViews = updateWidgetListView(context,
                    appWidgetIds[i]);
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);




            //custom menu
            Intent intent = new Intent(context, MenuScrollableActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent pending = PendingIntent.getActivity(context, 0,
                    intent, 0);
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_scroll_layout);

            views.setOnClickPendingIntent(R.id.custom, pending);
            appWidgetManager.updateAppWidget(appWidgetIds, views);


        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);


    }

    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

        //which layout to show on widget
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_scroll_layout);

        //RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, WidgetService.class);
        //passing app widget id to that RemoteViews Service
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //setting a unique Uri to the intent
        //don't know its purpose to me right now
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        //setting adapter to listview of the widget
        remoteViews.setRemoteAdapter(appWidgetId, R.id.listViewWidget,
                svcIntent);
        //setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.listViewWidget, R.id.empty_view);
        return remoteViews;
    }

//    public static void sendRefreshBroadcast(Context context){
//        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
//        intent.setComponent(new ComponentName(context,
//                WidgetProviderScrollable.class));
//        context.sendBroadcast(intent);
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent){
//        final String action = intent.getAction();
//
//        if(action.equals(AppWidgetManager.ACTION_APPWIDGET_UPDATE)){
//            //refresh all widgets
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            ComponentName componentName = new ComponentName(context,
//                    WidgetProviderScrollable.class);
//
//            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName),
//                    R.id.listViewWidget);
//        }
//        super.onReceive(context,intent);
//    }


}
