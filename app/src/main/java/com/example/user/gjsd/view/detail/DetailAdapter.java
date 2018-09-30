package com.example.user.gjsd.view.detail;

import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorSpace;
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
        String itemname = detailItem.getItemname();

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






        //set the child name
        TextView update = (TextView) convertView.findViewById(R.id.updateinfo);
        //get the imageView
        ImageView img = (ImageView) convertView.findViewById(R.id.playerpic);
        TextView cost = (TextView) convertView.findViewById(R.id.cost);
        cost.setTextColor(Color.rgb(242,21,40));
//        cost.setTextColor(Color.rgb(65,108,216));
        //이 아래부터 각각 if문 안에 넣어주기


        //차액정보 그대로 가져옴=====================================================================<>
        String difference = detailItems.get(groupPosition).diffrence;
        cost.setText(Currency.getInstance(Locale.KOREA).getSymbol()+difference);
        update.setText("업데이트 정보 자리");

        //get position name
        String positionName = (String) getGroup(groupPosition).toString();
        if (positionName == "동태") {
            if (child == "1") {
                //데이터 확인해서 상승인지 하락인지 표기
                img.setImageResource(R.drawable.upicon);
            }
        } else if (positionName == "조기") {
            if (child == "2") {
                img.setImageResource(R.drawable.upicon);
            }
        } else if (positionName == "달걀") {
            if (child == "3") {
                img.setImageResource(R.drawable.upicon);
            }
        } else if (positionName == "닭고기") {
            if (child == "4") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "돼지고기") {
            if (child == "5") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "쇠고기") {
            if (child == "6") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "애호박") {
            if (child == "7") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "오이") {
            if (child == "8") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "상추") {
            if (child == "9") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "양파") {
            if (child == "10") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "무") {
            if (child == "11") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "배추") {
            if (child == "12") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "배") {
            if (child == "13") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "사과") {
            if (child == "14") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "오징어") {
            if (child == "15") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "고등어") {
            if (child == "16") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "명태") {
            if (child == "17") {
                img.setImageResource(R.drawable.upicon);
            }
        }else if (positionName == "호박") {
            if (child == "18") {
                img.setImageResource(R.drawable.upicon);
            }
        }
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


}
