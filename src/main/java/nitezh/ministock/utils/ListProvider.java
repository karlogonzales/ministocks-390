package nitezh.ministock.utils;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nitezh.ministock.R;
import nitezh.ministock.dataaccess.CryptoCompareQuoteRepository;
import nitezh.ministock.domain.StockQuote;

/**
 * If you are familiar with Adapter of ListView,this is the same as adapter
 * with few changes
 * 
 */
public class ListProvider implements RemoteViewsFactory {
	private ArrayList<ListItem> listItemList = new ArrayList<ListItem>();
	private Context context = null;
	private int appWidgetId;
	private Cache appCache;

	public ListProvider(Context context, Intent intent) {
		this.context = context;
		appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
				AppWidgetManager.INVALID_APPWIDGET_ID);

		populateListItem();
	}

	private void populateListItem() {
		List<String> stocks = new ArrayList<>();
		stocks.add("BTC");
		CryptoCompareQuoteRepository cryptoCompareQuoteRepository = new CryptoCompareQuoteRepository();
		HashMap<String, StockQuote> stockQuote = cryptoCompareQuoteRepository.getQuotes(this.appCache,stocks);
		for (int i = 0; i < 10; i++) {
			ListItem listItem = new ListItem();
			listItem.heading = stockQuote.get("BTC").getSymbol();
			listItem.content = stockQuote.get("BTC").getPrice();
			listItemList.add(listItem);
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
