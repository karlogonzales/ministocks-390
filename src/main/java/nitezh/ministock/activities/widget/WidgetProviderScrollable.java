package nitezh.ministock.activities.widget;


import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;
import nitezh.ministock.utils.ListProvider;
import nitezh.ministock.R;
import nitezh.ministock.activities.MenuScrollableActivity;
import nitezh.ministock.activities.PreferencesActivity;
import nitezh.ministock.utils.WidgetService;

public class WidgetProviderScrollable extends WidgetProviderBase {

    /*
     * this method is called every 30 mins as specified on widgetinfo.xml
     * this method is also called on every phone reboot
     */
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

            //menu already implemented
            Intent edit_intent = new Intent(context, PreferencesActivity.class);
            edit_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            edit_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            PendingIntent edit_pending = PendingIntent.getActivity(context, 0,
                    edit_intent, 0);
            RemoteViews edit_views = new RemoteViews(context.getPackageName(),
                    R.layout.widget_scroll_layout);

            edit_views.setOnClickPendingIntent(R.id.edit, edit_pending);
            appWidgetManager.updateAppWidget(appWidgetIds[i], edit_views);

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

			//Refresh menu

			/*Intent refreshIntent = new Intent(context, ListProvider.class);
			refreshIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
			refreshIntent.setData(Uri.parse(refreshIntent.toUri(Intent.URI_INTENT_SCHEME)));
			PendingIntent pendingRefreshIntent = PendingIntent.getService(context, 0, refreshIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            WidgetProviderBase.updateWidgets(context,
                    WidgetProviderBase.UpdateType.VIEW_UPDATE);

			Intent settingsIntent = new Intent(context, ListProvider.class);
			settingsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			settingsIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);
			settingsIntent.setData(Uri.parse(settingsIntent.toUri(Intent.URI_INTENT_SCHEME)));
			PendingIntent pendingSettingsIntent = PendingIntent.getActivity(context, 0, settingsIntent, PendingIntent.FLAG_UPDATE_CURRENT);

			RemoteViews viewsRefresh = new RemoteViews(context.getPackageName(), R.layout.widget_scroll_layout);
			views.setOnClickPendingIntent(R.id.Refresh, pendingSettingsIntent);
			views.setOnClickPendingIntent(R.id.Refresh, pendingRefreshIntent);

			context.startService(refreshIntent);
			appWidgetManager.updateAppWidget(appWidgetIds[i], viewsRefresh);*/

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
