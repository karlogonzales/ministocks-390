package nitezh.ministock.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;
import android.widget.Toast;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import nitezh.ministock.R;
import nitezh.ministock.activities.widget.WidgetProviderScrollable;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 */
public class ListProvider implements RemoteViewsFactory {
    private ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
    private Context context = null;
//    private int appWidgetId;
    private JSONObject json = null;
    private ArrayList<String> stockList = null;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
//        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
//                AppWidgetManager.INVALID_APPWIDGET_ID);


    }

    public String stockURLStringBuilder(String stock) {
        return "https:api.iextrading.com/1.0/stock/" + stock + "/price";
    }

    public String cryptoURLStringBuilder(String crypto) {
        return "https://min-api.cryptocompare.com/data/price?fsym=" + crypto + "&tsyms=CAD";
    }


    private void populateListItem() {
        stockList = new StockListSingleton().getInstance().getData();


        try {
            for (String stock : stockList) {
                String content = new ServiceTask(this.context).execute(stockURLStringBuilder(stock), "GET", "").get();

                if(content.equals("Unknown symbol"))
                    continue;

                if (getCount() == 0){
                    ListItem tempListItem = new ListItem();
                    tempListItem.heading = stock;
                    tempListItem.content = content;
                    listItemList.add(tempListItem);
                }
                else {
                    for (ListItem listItem : listItemList) {
                        if (listItem.heading.equals(stock)) {
                            listItem.content = content;
                        } else {
                            ListItem tempListItem = new ListItem();
                            tempListItem.heading = stock;
                            tempListItem.content = content;
                            listItemList.add(tempListItem);
                        }
                    }
                }
//                if (stock.equals("BTC") || stock.equals("ETH")) {
//
//                    listItem.content = new JSONObject(new ServiceTask(this.context).execute(cryptoURLStringBuilder(stock), "GET", "").get()).getString("CAD");
//                } else
//                    listItem.content = new ServiceTask(this.context).execute(stockURLStringBuilder(stock), "GET", "").get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getStockData(String symbol) {
        String stockData = null;
        try {
            stockData = new ServiceTask(this.context).execute(stockURLStringBuilder(symbol), "GET", "").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return stockData;
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

        Intent i = new Intent();
        Bundle extras = new Bundle();

        extras.putString(WidgetProviderScrollable.EXTRA_WORD, listItem.heading);
        i.putExtras(extras);
        remoteView.setOnClickFillInIntent(R.id.heading, i);
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
        populateListItem();
        Log.e("onDataSetChanged", "onDataSetChanged");
    }

    @Override
    public void onDestroy() {
    }

}
