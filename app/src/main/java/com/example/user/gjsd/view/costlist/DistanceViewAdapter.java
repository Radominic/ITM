package com.example.user.gjsd.view.costlist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.user.gjsd.R;
import com.example.user.gjsd.model.Market;
import com.example.user.gjsd.view.MapFragment;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DistanceViewAdapter extends BaseAdapter{

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private Map<String, DistanceViewItem> distanceViewItems = Collections.synchronizedMap(new HashMap<String, DistanceViewItem>());
    private ArrayList<String> distanceMarketNameList = new ArrayList<String>();
    private String item = "품목";
    distanceFragment d;
    // ListViewAdapter의 생성자
    public DistanceViewAdapter(distanceFragment d) {
        this.d = d;
    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return distanceViewItems.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.distancefragment, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView numberTextView = (TextView) convertView.findViewById(R.id.textView00);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.textView11) ;
        TextView descTextView = (TextView) convertView.findViewById(R.id.textView22) ;
        TextView distTextView = (TextView) convertView.findViewById(R.id.textView33);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        DistanceViewItem distanceViewItem = distanceViewItems.get(distanceMarketNameList.get(position));

        // 아이템 내 각 위젯에 데이터 반영
        numberTextView.setText(""+(position+1));
        if(distanceViewItem.getName().equals("용산구 농협 하나로마트 용산점"))
            titleTextView.setText("용산구 농협 하나로마트");
        else{
            titleTextView.setText(distanceViewItem.getName());
        }

        String distance = String.format("%.3f" , distanceViewItem.getMarket().getDistance()/1000);
        if(distanceViewItem.getMarket().getItem(d.getSelectedItem())==null){
            descTextView.setText("품목없음");
        }else{if(distanceViewItem.getMarket().getItem(d.getSelectedItem()).getPrice().equals("0")){
            descTextView.setText("품목없음");

        }else{
            descTextView.setText(d.getSelectedItem()+" : "+ Currency.getInstance(Locale.KOREA).getSymbol()+distanceViewItem.getMarket().getItem(d.getSelectedItem()).getPrice()+" "+ MapFragment.getCriteria(d.getSelectedItem()));
        }

        }

        distTextView.setText(distance+" km");


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return distanceViewItems.get(distanceMarketNameList.get(position)) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(DistanceViewItem item) {
        //String number, String title, String desc,String dist
//        DistanceViewItem item = new DistanceViewItem();
//
//        item.setNumber(number);
//        item.setTitle(title);
//        item.setDesc(desc);
//        item.setDisStr(dist);

        distanceViewItems.put(item.getName(),item);
    }

    public void setOrder(ArrayList<String> namelist){
        this.distanceMarketNameList = namelist;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();

    }


    public void setItem(String item) {
        this.item = item;
    }
}
