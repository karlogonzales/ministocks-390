package nitezh.ministock.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import nitezh.ministock.PreferenceStorage;
import nitezh.ministock.R;
import nitezh.ministock.Storage;
import nitezh.ministock.activities.widget.WidgetProviderBase;
import nitezh.ministock.domain.AndroidWidgetRepository;
import nitezh.ministock.domain.StockQuote;
import nitezh.ministock.domain.StockQuoteRepository;
import nitezh.ministock.domain.WidgetRepository;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 */
public class ListProvider implements RemoteViewsFactory {
    private ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
    private Context context = null;
    private int appWidgetId;
    private HashMap<String, StockQuote> quotes;
    private WidgetProviderBase.UpdateType updateType;
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

        //Creating new StockQuoteRepository and passing it the current context
        WidgetRepository widgetRepository = new AndroidWidgetRepository(this.context);
        Storage storage = PreferenceStorage.getInstance(this.context);
        StockQuoteRepository quoteRepository = new StockQuoteRepository(
                PreferenceStorage.getInstance(this.context), new StorageCache(storage),
                widgetRepository);
//Extracting the quotes into a list
        this.quotes = quoteRepository.getQuotes(
                widgetRepository.getWidget(this.appWidgetId).getSymbols(),
                updateType == WidgetProviderBase.UpdateType.VIEW_UPDATE);
//
//
        if(!quotes.isEmpty()) {
            Collection<StockQuote> values = quotes.values();
            List<StockQuote> finalStocks = new ArrayList<StockQuote>(values);
            //Creating listItems from the quote list we made and passing them to the listview
            for (int i = 0; i < finalStocks.size(); i++) {
                StockQuote quote = finalStocks.get(i);
                ListItem listItem = new ListItem();
                listItem.symbol = quote.getSymbol();
                listItem.price = quote.getPrice();
                listItem.percentageChange = quote.getPercent();
                listItemList.add(listItem);
            }
        }
        else{
            System.out.println(stockList);
            try {
                for (String stock: stockList) {
                    ListItem listItem = new ListItem();
                    listItem.symbol = stock;

                    if (stock.equals("BTC") || stock.equals("ETH")) {

                        listItem.price = new JSONObject(new ServiceTask(this.context).execute(cryptoURLStringBuilder(stock), "GET", "").get()).getString("CAD");
                    }else
                        listItem.price = new ServiceTask(this.context).execute(stockURLStringBuilder(stock), "GET", "").get();

                    listItemList.add(listItem);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
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
        remoteView.setTextViewText(R.id.symbol, listItem.symbol);
        remoteView.setTextViewText(R.id.price, listItem.price);
        remoteView.setTextViewText(R.id.percentageChange, listItem.percentageChange);

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
