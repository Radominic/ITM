package com.example.user.gjsd.view.detail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.gjsd.R;
import com.example.user.gjsd.model.Market;
import com.example.user.gjsd.modules.MarketExplorer;
import com.example.user.gjsd.view.MapFragment;
import com.example.user.gjsd.view.costlist.CostViewItem;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class DetailAdapter extends BaseExpandableListAdapter {
    private Context mContext;
    private ArrayList<DetailItem> detailItems;

    private LayoutInflater inflater;
    private String marketName;
    private MarketExplorer marketExplorer;
    private String itemname ;
    //class Constructor
    public DetailAdapter (Context mContext, String name) {
        this.marketName = name;
        this.mContext = mContext;
        marketExplorer = MarketExplorer.getInstance();
//        this.detailItemMap = detailItemMap;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addViewItems(ArrayList<DetailItem> detailItems){
//        for(DetailItem detailItem : detailItems){
//            this.detailItems.
//        }
//        detailItemMap.put(detailItem.getItemname(),detailItem);
        this.detailItems = detailItems;
    }

    @Override
    public int getGroupCount() {
        return detailItems.size();
    }

    public DetailAdapter(){
        marketExplorer = MarketExplorer.getInstance();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    //get position
    @Override
    public Object getGroup(int groupPosition) {
        return detailItems.get(groupPosition);
    }

    //this is where we get the information of player
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return detailItems.get(groupPosition).diffrence;
    }

    //position ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //where to get player's id
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    //get parent row
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.parent_list, null);
        }

        //get position
        DetailItem detailItem = (DetailItem) getGroup(groupPosition);

        //set positionName
        itemname = detailItem.getItemname();

        TextView itemname_tv = (TextView) convertView.findViewById(R.id.position_tv);
        itemname_tv.setText(itemname);

//        //부모 텍스트
//        for(String itemname : marketExplorer.getMarket(marketName).allItems())
//            marketExplorer.getMarket(marketName).getItem(itemname).getPrice();

        TextView price_tv = (TextView)convertView.findViewById(R.id.priceinfo) ;
        price_tv.setText(Currency.getInstance(Locale.KOREA).getSymbol()+marketExplorer.getMarket(marketName).getItem(itemname).getPrice());
//        parentcost.setText(Currency.getInstance(Locale.KOREA).getSymbol()+marketExplorer.getMarket(marketName).getItem(position.position).getPrice());
//        for(String itemname : marketExplorer.getMarket(marketName).allItems()){
//
//        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.indicator);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            imageView.setBackground(new ShapeDrawable(new OvalShape()));
        }
        if(Build.VERSION.SDK_INT >= 21) {
            imageView.setClipToOutline(true);
        }

        imageView.setImageDrawable(detailItem.getIcon());
//        팽창됐을때랑 아닐때 이미지 변경 가능
//        if(isExpanded){
//            imageView.setImageResource(R.drawable.itemon);
//        } else {
//            imageView.setImageResource(R.drawable.itemoff);
//        }

        return convertView;
    }


    //get child_list.xml (View)
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        //inflate the layout
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.child_list, null);
        }

        String child = (String) getChild(groupPosition, childPosition);






        //차액정보 그대로 가져옴=====================================================================<>
        String difference = detailItems.get(groupPosition).diffrence;

        //set the child name
        TextView update = (TextView) convertView.findViewById(R.id.updateinfo);
        //get the imageView
        ImageView img = (ImageView) convertView.findViewById(R.id.playerpic);
        TextView cost = (TextView) convertView.findViewById(R.id.cost);

        if(difference.substring(1,2).equals("0")){cost.setTextColor(Color.rgb(70,70,70));
            cost.setText(Currency.getInstance(Locale.KOREA).getSymbol()+difference.substring(1,difference.length()));}
        else if(difference.substring(0,1).equals("▲")){cost.setTextColor(Color.rgb(242,21,40));
            cost.setText(Currency.getInstance(Locale.KOREA).getSymbol()+difference.substring(1,difference.length()));}
        else{cost.setTextColor(Color.rgb(65,108,216));
            cost.setText(Currency.getInstance(Locale.KOREA).getSymbol()+difference.substring(2,difference.length()));}

        //이 아래부터 각각 if문 안에 넣어주기




        update.setText("기준날짜 "+marketExplorer.getMarket(marketName).getUpdateDate().substring(0,10));

        //get position name


        if (itemname == "동태") {
                //데이터 확인해서 상승인지 하락인지 표기
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        } else if (itemname == "조기") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        } else if (itemname == "달걀") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        } else if (itemname == "닭고기") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "돼지고기") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        }else if (itemname == "쇠고기") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "애호박") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "오이") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "상추") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "양파") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "무") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "배추") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        }else if (itemname == "배") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}

        }else if (itemname == "사과") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        }else if (itemname == "오징어") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        }else if (itemname == "고등어") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        }else if (itemname == "명태") {
            if(difference.substring(1,2).equals("0")){img.setImageResource(R.drawable.equalicon);}
            else if(difference.substring(0,1).equals("▲")){img.setImageResource(R.drawable.upicon);}
            else{img.setImageResource(R.drawable.downicon);}
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
