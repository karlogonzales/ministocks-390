package nitezh.ministock.utils;

import java.util.ArrayList;

/**
 * Created by k_onzale on 30-Mar-18.
 */
public class StockListSingleton {
    private ArrayList<String> data = new ArrayList<>();
    public ArrayList<String> getData() {return data;}
    public void addData(String data) {this.data.add(data);}
    public void deleteData(int position){this.data.remove(position);}

    private static final StockListSingleton stocklist = new StockListSingleton();
    public static StockListSingleton getInstance() {return stocklist;}
}
