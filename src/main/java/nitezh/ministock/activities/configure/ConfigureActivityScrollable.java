/*
 The MIT License

 Copyright (c) 2013 Nitesh Patel http://niteshpatel.github.io/ministocks

 Permission is hereby granted, free of charge, to any person obtaining a copy
 of this software and associated documentation files (the "Software"), to deal
 in the Software without restriction, including without limitation the rights
 to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 copies of the Software, and to permit persons to whom the Software is
 furnished to do so, subject to the following conditions:

 The above copyright notice and this permission notice shall be included in
 all copies or substantial portions of the Software.

 THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 THE SOFTWARE.
 */

package nitezh.ministock.activities.configure;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nitezh.ministock.R;
import nitezh.ministock.domain.StockQuote;


public class ConfigureActivityScrollable extends ConfigureActivityBase {

    private List<StockQuote> myQuotes = new ArrayList<StockQuote>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_scrollable);

        populateQuoteList();
        populateListView();
    }

    public void populateQuoteList(){
// this needs to be populated by the added stockQuotes
    }

    public void populateListView(){
        ArrayAdapter<StockQuote> adapter = new MyAdapter();
        ListView list = (ListView) findViewById(R.id.list_view1);
        list.setAdapter(adapter);
    }

    private class MyAdapter extends ArrayAdapter<StockQuote>{

        public MyAdapter(){
            super(ConfigureActivityScrollable.this,R.layout.scrollable_item,myQuotes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View itemView = convertView;
            if(itemView==null){
                itemView = getLayoutInflater().inflate(R.layout.scrollable_item,parent,false);
            }
//Track Position
            StockQuote currentQuote = myQuotes.get(position);
//Fill Views
            TextView stockText1 = (TextView) itemView.findViewById(R.id.test__hanna_text1);
            stockText1.setText(currentQuote.getSymbol());

            TextView stockText2 = (TextView) itemView.findViewById(R.id.test__hanna_text2);
            stockText2.setText(currentQuote.getPrice());

            TextView stockText3 = (TextView) itemView.findViewById(R.id.test__hanna_text3);
            stockText3.setText(currentQuote.getExchange());

            TextView stockText4 = (TextView) itemView.findViewById(R.id.test__hanna_text4);
            stockText4.setText(currentQuote.getChange());

            TextView stockText5 = (TextView) itemView.findViewById(R.id.test__hanna_text5);
            stockText5.setText(currentQuote.getPercent());


            return itemView;
        }
    }
}

