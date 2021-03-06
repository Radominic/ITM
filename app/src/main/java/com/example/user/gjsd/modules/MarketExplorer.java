package com.example.user.gjsd.modules;

import android.os.Build;
import android.support.annotation.RequiresApi;

import net.daum.mf.map.api.MapPoint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MarketExplorer {
    private Map<String, Point> Markets;
    public ArrayList<MarketD> Markets_sort_by_distance;

    public MarketExplorer() {
        Markets = Collections.synchronizedMap(new HashMap<String, Point>());
        Markets_sort_by_distance = new ArrayList<MarketD>();

        //데이터 삽입
        Markets.put("종로구 통인시장", new Point(37.5807801, 126.9699523));
        Markets.put("용산구 용문시장", new Point(37.5366204, 126.9597976));
        Markets.put("중구 남대문시장", new Point(37.5592111, 126.977639));
        Markets.put("성북구 돈암 제일시장", new Point(37.5916795, 127.0157178));
        Markets.put("성북구 장위 골목시장", new Point(37.6120979, 127.0508262));
        Markets.put("영등포구 대림 중앙시장", new Point(37.4916125, 126.899949));
        Markets.put("영등포구 영등포 전통시장", new Point(37.5197463, 126.9074287));
        Markets.put("도봉구 방학동 도깨비시장", new Point(37.6657435, 127.0329465));
        Markets.put("도봉구 신창시장", new Point(37.63458869999999, 127.01483140000005));
        Markets.put("서대문구 인왕시장", new Point(37.5908692, 126.943696));
        Markets.put("서대문구 영천시장", new Point(37.5703324, 126.9618708));
        Markets.put("강서구 송화시장",new Point(37.5492288, 126.8345762));
        Markets.put("은평구 대림시장",new Point(37.5868145, 126.91771399999993));
        Markets.put("종로구 광장시장",new Point(37.5701227, 126.9997095));
        Markets.put("용산구 후암시장",new Point(37.5501259, 126.9764509));
        Markets.put("강북구 수유 재래시장",new Point(37.6315365, 127.0223447));
        Markets.put("성동구 금남시장",new Point(37.5484673, 127.0227235));
        Markets.put("성동구 뚝도시장",new Point(37.5378062, 127.0548442));
        Markets.put("광진구 자양 골목시장",new Point(37.5338035, 127.0816089));
        Markets.put("동대문구 청량리 종합시장",new Point(37.5808292, 127.0433944));
        Markets.put("동대문구 경동시장",new Point(37.5787997, 127.0396819));
        Markets.put("중랑구 우림시장",new Point(37.5967745, 127.0982882));
        Markets.put("중랑구 동원시장",new Point(37.5898431, 127.0899073));
        Markets.put("노원구 공릉동 도깨비시장",new Point(37.6226999, 127.0762606));
        Markets.put("양천구 목3동시장",new Point(37.5482793, 126.8668424));
        Markets.put("양천구 신영시장", new Point(37.5330235, 126.8360192));
        Markets.put("구로구 남구로시장",new Point(37.4897303, 126.8864836));
        Markets.put("구로구 고척 근린시장",new Point(37.5024083, 126.8505529));
        Markets.put("금천구 현대시장",new Point(37.5669542, 127.0593016));
        Markets.put("금천구 남문시장",new Point(37.4737845, 126.9004226));
        Markets.put("마포구 망원시장",new Point(37.5562398, 126.9056321));
        Markets.put("마포구 마포 농수산물시장",new Point(37.5651843, 126.8984946));
        Markets.put("동작구 남성시장",new Point(37.4894036, 126.9807712));
        Markets.put("관악구 원당 종합시장",new Point(37.6560447, 126.8373471));
        Markets.put("관악구 신원시장",new Point(37.4835132, 126.9258997));
        Markets.put("송파구 마천 중앙시장",new Point(37.4980997, 127.1507085));
        Markets.put("송파구 방이시장",new Point(37.5103559, 127.1175756));
        Markets.put("강동구 암사 종합시장",new Point(37.5508693, 127.1288365));
        Markets.put("중구 서울 중앙시장",new Point(37.5667939, 127.0198036));
        Markets.put("강동구 둔촌역 전통시장",new Point(37.5275029, 127.1351706));
        Markets.put("관악구 관악 신사시장",new Point(37.4868119, 126.9170742));
        Markets.put("노원구 상계 중앙시장",new Point(37.659884, 127.0698784));
        Markets.put("광진구 노룬산 골목시장",new Point(37.5364069, 127.0646822));
        Markets.put("강서구 화곡 본동시장",new Point(37.5428618, 126.8441381));
        Markets.put("강남구 청담 삼익시장",new Point(37.5224855, 127.0576847));
        Markets.put("강남구 도곡시장",new Point(37.4954841, 127.0333574));
        Markets.put("서초구 방림시장",new Point(37.483325, 126.99624500000004));
        Markets.put("은평구 대조시장",new Point(37.6098316, 126.9276087));
        Markets.put("강북구 숭인시장",new Point(37.6131571, 127.0294686));



        //default sort
        Set<String> keys = (Set<String>)Markets.keySet();
        for(String name : keys) {
            Markets_sort_by_distance.add(new MarketD(name));
        }
    }
    public ArrayList<String> marketlist(){

        ArrayList<String> marketlist = new ArrayList<String>(Markets.keySet());

        return marketlist;
    }

    public String searchMarket(String key) {
        //검색 쿼리 구현
        return null;
    }

    public MapPoint getMarketMapPoint(String marketName) {
        return Markets.get(marketName).getMapPoint();
    }

    public Point getMarketPoint(String marketName){
        return Markets.get(marketName);
    }

    public Set<String> getAllMarketList() {
        Set keys = Markets.keySet();
        return keys;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateMarketsSortedByDistance(Point p){
        for(MarketD marketD : Markets_sort_by_distance) {
            double x = Markets.get(marketD.name).x;
            double y = Markets.get(marketD.name).y;
            double stdX = p.x;
            double stdY = p.y;
            double d = Math.pow((x-stdX), 2)+Math.pow((y-stdY), 2);
            marketD.setD_sqaure(d);
        }
        MarketComparator mc = new MarketComparator();
        Markets_sort_by_distance.sort(mc);
    }
    //p기준 새로고침

    public String[] getNearNMarkets(int numOfMarket){
        String[] result = new String[numOfMarket];
        for(int i = 0;i<numOfMarket;i++) {
            result[i] = Markets_sort_by_distance.get(i).name;
        }
        return result;
    }

    public MapPoint[] getNearNMarketsMapPoint(int numOfMarket){
        MapPoint[] result = new MapPoint[numOfMarket];
        for(int i = 0;i<numOfMarket;i++) {
            result[i] = getMarketMapPoint(Markets_sort_by_distance.get(i).name);
        }
        return result;
    }


    class MarketD {
        String name;
        Double d_sqaure;
        public MarketD(String name){
            this.name = name;
            d_sqaure = (double)-1;
        }
        public void setD_sqaure(Double d_square){
            this.d_sqaure = d_square;
        }
    }

    class MarketComparator implements Comparator<MarketD> {

        @Override
        public int compare(MarketD o1, MarketD o2) {
            return o1.d_sqaure.compareTo(o2.d_sqaure);
        }
    }
}
