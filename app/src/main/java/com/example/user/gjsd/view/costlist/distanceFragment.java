package com.example.user.gjsd.view.costlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.user.gjsd.modules.MarketExplorer;
import com.example.user.gjsd.view.MainActivity;
import com.example.user.gjsd.view.detail.DetailActivity;

import java.util.ArrayList;


public class distanceFragment extends ListFragment {
    DistanceViewAdapter adapter;
    MarketExplorer marketExplorer;
    ArrayList<String> markets_sort_by_distance;
    MainActivity mainActivity;

    //    String[] marketname = {"강남롯데백화점강남점","강남이마트역삼점","강동이마트명일점","강동홈플러스강동점","강북롯데백화점미아점","강북하나로클럽미아점","강서이마트가양점","강서홈플러스등촌점","관악롯데백화점관악점","관악세이브마트","광진롯데마트강변점","광진이마트자양점","구로애경백화점","구로이마트신도림점","금천홈플러스독산점","금천홈플러스시흥점","노원롯데백화점노원점","노원홈플러스중계점","도봉이마트창동점","도봉홈플러스방학점","동대문롯데백화점청량리점","동대문홈플러스동대문점","동작태평백화점","마포그랜드마트신촌점","마포홈플러스월드컵점","서대문롯데슈퍼","서대문현대백화점신촌점","서초뉴코아아울렛강남점","서초신세계백화점강남점","서초하나로클럽양재점","성동이마트성수점","성동이마트왕십리점","성북이마트미아점","성북현대백화점미아점","송파롯데백화점잠실점","송파홈플러스잠실점","양천이마트목동점","양천홈플러스목동점","영등포롯데백화점영등포점","영등포이마트여의도점","영등포홈플러스영등포점","용산농협하나로마트용산점","용산이마트용산점","은평2001아울렛불광점","은평이마트은평점","중구롯데마트서울역점","중구롯데백화점","중구신세계백화점","중구이마트청계점","중랑이마트상봉점","중랑홈플러스면목점","강남구도곡시장","강남구청담삼익시장","강동구둔촌역전통시장","강동구암사종합시장","강북구수유재래시장","강북구숭인시장","강서구송화시장","강서구화곡본동시장","관악구관악신사시장","관악구신원시장","관악구원당종합시장","광진구노룬산골목시장","광진구자양골목시장","구로구고척근린시장","구로구남구로시장","금천구남문시장","금천구현대시장","노원구공릉동도깨비시장","노원구상계중앙시장","도봉구방학동도깨비시장","도봉구신창시장","동대문구경동시장","동대문구청량리종합시장","동작구남성시장","마포구마포농수산물시장","마포구망원시장","서대문구영천시장","서대문구인왕시장","서초구방림시장","성동구금남시장","성동구뚝도시장","성북구돈암제일시장","성북구장위골목시장","송파구마천중앙시장","송파구방이시장","양천구목3동시장","양천구신영시장","영등포구대림중앙시장","영등포구영등포전통시장","용산구용문시장","용산구후암시장","은평구대림시장","은평구대조시장","종로구광장시장","종로구통인시장","중구남대문시장","중구서울중앙시장","중랑구동원시장","중랑구우림시장"
//    };
    public distanceFragment() {
    }
    public void setMainActivity(MainActivity mainActivity){this.mainActivity = mainActivity;}
    public String getSelectedItem(){
        return mainActivity.getSelectedItemName();
    }
    public void setMarketExplorer(MarketExplorer marketExplorer) {
        this.marketExplorer = marketExplorer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,

                             @Nullable Bundle savedInstanceState) {
        //해당 품목 가져오기

        adapter = new DistanceViewAdapter(this);
        setListAdapter(adapter);
        updateMarkets_sort_by_distance();


        for (String name : marketExplorer.getMarkets_sort_by_distance()) {
            adapter.addItem(new DistanceViewItem(name, marketExplorer.getMarket(name)));
        }
        adapter.setOrder(markets_sort_by_distance);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void updateMarkets_sort_by_distance() {
        this.markets_sort_by_distance = marketExplorer.getMarkets_sort_by_distance();
        adapter.setOrder(markets_sort_by_distance);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // get TextView's Text.
        DistanceViewItem item = (DistanceViewItem) l.getItemAtPosition(position);
        String marketName = item.getName();
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("marketName", item.getName());
        startActivity(intent);


        // TODO : use item data.
    }
}
