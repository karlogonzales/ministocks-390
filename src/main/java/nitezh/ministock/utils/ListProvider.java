package nitezh.ministock.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;


import android.os.Looper;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.ArrayList;


import nitezh.ministock.R;


/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 */
public class ListProvider implements RemoteViewsService.RemoteViewsFactory {
    private ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
    private Context context = null;
    private int appWidgetId;
    private JSONObject json = null;
    private ArrayList<String> stockList = StockListSingleton.getInstance().getData();

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
        populateListItem();
    }


    public String stockURLStringBuilder(String stock) {
        return "https:api.iextrading.com/1.0/stock/" + stock + "/price";
    }

    public String cryptoURLStringBuilder(String crypto) {
        return "https://min-api.cryptocompare.com/data/price?fsym=" + crypto + "&tsyms=CAD";
    }


    private void populateListItem() {
        try {
            for (String stock : stockList) {
                ListItem listItem = new ListItem();
                listItem.heading = stock;

                if (stock.equals("BTC") || stock.equals("ETH")) {

                    listItem.content = new JSONObject(new ServiceTask(this.context).execute(cryptoURLStringBuilder(stock), "GET", "").get()).getString("CAD");
                } else
                    listItem.content = new ServiceTask(this.context).execute(stockURLStringBuilder(stock), "GET", "").get();

                listItemList.add(listItem);



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public int getCount() {
        return listItemList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /*
     *Similar to getView of Adapter where instead of View
     *we return RemoteViews
     *
     */
    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews remoteView = new RemoteViews(
                context.getPackageName(), R.layout.list_row);
        ListItem listItem = listItemList.get(position);
        remoteView.setTextViewText(R.id.heading, listItem.heading);
        remoteView.setTextViewText(R.id.content, listItem.content);


        return remoteView;
    }


    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {
    }

}
