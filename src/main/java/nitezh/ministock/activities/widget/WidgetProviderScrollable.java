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

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        final int N = appWidgetIds.length;
        /*int[] appWidgetIds holds ids of multiple instance of your widget
         * meaning you are placing more than one widgets on your homescreen*/
        for (int i = 0; i < N; ++i) {
            RemoteViews remoteViews = updateWidgetListView(context,
                    appWidgetIds[i]);
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



}
