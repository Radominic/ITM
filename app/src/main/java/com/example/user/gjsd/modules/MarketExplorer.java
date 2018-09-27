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
    private Map<String, MapPoint> Markets;
    public ArrayList<MarketD> Markets_sort_by_distance;

    public MarketExplorer() {
        Markets = Collections.synchronizedMap(new HashMap<String, MapPoint>());
        Markets_sort_by_distance = new ArrayList<MarketD>();

        //데이터 삽입
        Markets.put("종로구 통인시장", MapPoint.mapPointWithGeoCoord(37.5807801,126.9699523));
        Markets.put("용산구 용문시장", MapPoint.mapPointWithGeoCoord(37.5366204,126.9597976));
        Markets.put("중구 남대문시장", MapPoint.mapPointWithGeoCoord(37.5592111,126.977639));
        Markets.put("성북구 돈암제일시장", MapPoint.mapPointWithGeoCoord(37.5916795,127.0157178));
        Markets.put("성북구 장위골목시장", MapPoint.mapPointWithGeoCoord(37.6120979,127.0508262));
        Markets.put("영등포구 대림중앙시장", MapPoint.mapPointWithGeoCoord(37.4916125,126.899949));
        Markets.put("영등포구 영등포전통시장", MapPoint.mapPointWithGeoCoord(37.5197463,126.9074287));
        Markets.put("도봉구 방학동도깨비시장", MapPoint.mapPointWithGeoCoord(37.6657435,127.0329465));
        Markets.put("도봉구 신창시장", MapPoint.mapPointWithGeoCoord(37.63458869999999, 127.01483140000005));
        Markets.put("서대문구 인왕시장", MapPoint.mapPointWithGeoCoord(37.5908692,126.943696));
        Markets.put("서대문구 영천시장", MapPoint.mapPointWithGeoCoord(37.5703324,126.9618708));
        Markets.put("강서구 송화시장", MapPoint.mapPointWithGeoCoord(37.5492288,126.8345762));
        Markets.put("은평구 대림시장", MapPoint.mapPointWithGeoCoord(37.5868145, 126.91771399999993));
        Markets.put("종로구 광장시장", MapPoint.mapPointWithGeoCoord(37.5701227,126.9997095));
        Markets.put("용산구 후암시장", MapPoint.mapPointWithGeoCoord(37.5501259,126.9764509));
        Markets.put("강북구 수유재래시장", MapPoint.mapPointWithGeoCoord(37.6315365,127.0223447));
        Markets.put("성동구 금남시장", MapPoint.mapPointWithGeoCoord(37.5484673,127.0227235));
        Markets.put("성동구 뚝도시장", MapPoint.mapPointWithGeoCoord(37.5378062,127.0548442));
        Markets.put("광진구 자양골목시장", MapPoint.mapPointWithGeoCoord(37.5338035,127.0816089));
        Markets.put("동대문구 청량리종합시장", MapPoint.mapPointWithGeoCoord(37.5808292,127.0433944));
        Markets.put("동대문구 경동시장", MapPoint.mapPointWithGeoCoord(37.5787997,127.0396819));
        Markets.put("중랑구 우림시장", MapPoint.mapPointWithGeoCoord(37.5967745,127.0982882));
        Markets.put("중랑구 동원시장", MapPoint.mapPointWithGeoCoord(37.5898431,127.0899073));
        Markets.put("노원구 공릉동 도깨비시장", MapPoint.mapPointWithGeoCoord(37.6226999,127.0762606));
        Markets.put("양천구 목3동시장", MapPoint.mapPointWithGeoCoord(37.5482793,126.8668424));
        Markets.put("양천구 신영시장", MapPoint.mapPointWithGeoCoord(37.5330235,126.8360192));
        Markets.put("구로구 남구로시장", MapPoint.mapPointWithGeoCoord(37.4897303,126.8864836));
        Markets.put("구로구 고척근린시장", MapPoint.mapPointWithGeoCoord(37.5024083,126.8505529));
        Markets.put("동대문구 현대시장", MapPoint.mapPointWithGeoCoord(37.5669542,127.0593016));
        Markets.put("금천구 남문시장", MapPoint.mapPointWithGeoCoord(37.4737845,126.9004226));
        Markets.put("마포구 망원시장", MapPoint.mapPointWithGeoCoord(37.5562398,126.9056321));
        Markets.put("마포구 마포농수산물시장", MapPoint.mapPointWithGeoCoord(37.5651843,126.8984946));
        Markets.put("동작구 남성시장", MapPoint.mapPointWithGeoCoord(37.4894036,126.9807712));
        Markets.put("덕양구 원당종합시장", MapPoint.mapPointWithGeoCoord(37.6560447,126.8373471));
        Markets.put("관악구 신원시장(신림1동)", MapPoint.mapPointWithGeoCoord(37.4835132,126.9258997));
        Markets.put("송파구 마천중앙시장", MapPoint.mapPointWithGeoCoord(37.4980997,127.1507085));
        Markets.put("송파구 방이시장", MapPoint.mapPointWithGeoCoord(37.5103559,127.1175756));
        Markets.put("강동구 암사종합시장", MapPoint.mapPointWithGeoCoord(37.5508693,127.1288365));
        Markets.put("중구 서울중앙시장", MapPoint.mapPointWithGeoCoord(37.5667939,127.0198036));
        Markets.put("강동구 둔촌역전통시장", MapPoint.mapPointWithGeoCoord(37.5275029,127.1351706));
        Markets.put("관악구 관악신사시장(신림4동)", MapPoint.mapPointWithGeoCoord(37.4868119,126.9170742));
        Markets.put("노원구 상계중앙시장", MapPoint.mapPointWithGeoCoord(37.659884,127.0698784));
        Markets.put("광진구 노룬산골목시장", MapPoint.mapPointWithGeoCoord(37.5364069,127.0646822));
        Markets.put("강서구 화곡본동시장", MapPoint.mapPointWithGeoCoord(37.5428618,126.8441381));
        Markets.put("강남구 청담삼익시장", MapPoint.mapPointWithGeoCoord(37.5224855,127.0576847));
        Markets.put("강남구 도곡시장", MapPoint.mapPointWithGeoCoord(37.4954841,127.0333574));
        Markets.put("남구 방림시장", MapPoint.mapPointWithGeoCoord(37.483325, 126.99624500000004));
        Markets.put("은평구 대조시장", MapPoint.mapPointWithGeoCoord(37.6098316,126.9276087));
        Markets.put("강북구 숭인시장", MapPoint.mapPointWithGeoCoord(37.6131571,127.0294686));
        Markets.put("중구 신세계백화점", MapPoint.mapPointWithGeoCoord(37.5609164,126.9809698));
        Markets.put("용산구 이마트 용산점", MapPoint.mapPointWithGeoCoord(37.5298702,126.9648759));
        Markets.put("중구 롯데마트 서울역점", MapPoint.mapPointWithGeoCoord(37.5574467,126.9695134));
        Markets.put("성북구 이마트 미아점", MapPoint.mapPointWithGeoCoord(37.6108546,127.0299225));
        Markets.put("성북구 현대백화점 미아점", MapPoint.mapPointWithGeoCoord(37.6084082,127.0287466));
        Markets.put("영등포구 홈플러스 영등포점", MapPoint.mapPointWithGeoCoord(37.5182193,126.8958098));
        Markets.put("영등포구 이마트 여의도점", MapPoint.mapPointWithGeoCoord(37.518272,126.9260954));
        Markets.put("도봉구 이마트 창동점", MapPoint.mapPointWithGeoCoord(37.6516448,127.0467326));
        Markets.put("도봉구 홈플러스 방학점", MapPoint.mapPointWithGeoCoord(37.6648915,127.043577));
        Markets.put("서대문구 현대백화점 신촌점", MapPoint.mapPointWithGeoCoord(37.5560866,126.9358496));
        Markets.put("강서구 홈플러스 등촌점", MapPoint.mapPointWithGeoCoord(37.5601997,126.84633));
        Markets.put("강서구 이마트 가양점", MapPoint.mapPointWithGeoCoord(37.5582132,126.861794));
        Markets.put("강남구 이마트 역삼점", MapPoint.mapPointWithGeoCoord(37.4992649,127.0484171));
        Markets.put("강남구 롯데백화점 강남점", MapPoint.mapPointWithGeoCoord(37.497041,127.0532153));
        Markets.put("은평구 2001아울렛 불광점", MapPoint.mapPointWithGeoCoord(37.60974,126.9289681));
        Markets.put("은평구 이마트 은평점", MapPoint.mapPointWithGeoCoord(37.6008414,126.9205342));
        Markets.put("노원구 롯데백화점", MapPoint.mapPointWithGeoCoord(37.6550434,127.0610971));
        Markets.put("중구 이마트 청계점", MapPoint.mapPointWithGeoCoord(37.5709405,127.0210984));
        Markets.put("용산구 농협 하나로마트 용산점", MapPoint.mapPointWithGeoCoord(37.5331778,126.9647535));
        Markets.put("강북구 롯데백화점 미아점", MapPoint.mapPointWithGeoCoord(37.6145947,127.0305364));
        Markets.put("성동구 이마트 왕십리점", MapPoint.mapPointWithGeoCoord(37.5615131,127.0384279));
        Markets.put("성동구 이마트 성수점", MapPoint.mapPointWithGeoCoord(37.5399353,127.0535218));
        Markets.put("광진구 이마트 자양점", MapPoint.mapPointWithGeoCoord(37.5385628,127.0730552));
        Markets.put("광진구 롯데마트 강변점", MapPoint.mapPointWithGeoCoord(37.5349755,127.0957226));
        Markets.put("동대문구 홈플러스 동대문점", MapPoint.mapPointWithGeoCoord(37.5745613,127.038873));
        Markets.put("동대문구 롯데백화점 청량리점", MapPoint.mapPointWithGeoCoord(37.581387,127.048979));
        Markets.put("중랑구 이마트 상봉점", MapPoint.mapPointWithGeoCoord(37.5964905,127.093618));
        Markets.put("중랑구 홈플러스 면목점", MapPoint.mapPointWithGeoCoord(37.5792089,127.0812071));
        Markets.put("노원구 롯데백화점 노원점", MapPoint.mapPointWithGeoCoord(37.6550434,127.0610971));
        Markets.put("노원구 홈플러스 중계점", MapPoint.mapPointWithGeoCoord(37.6399096,127.0686298));
        Markets.put("구로구 이마트 신도림점", MapPoint.mapPointWithGeoCoord(37.5070488,126.890246));
        Markets.put("팔달구 애경백화점", MapPoint.mapPointWithGeoCoord(37.2656796,127.0002404));
        Markets.put("금천구 홈플러스 시흥점", MapPoint.mapPointWithGeoCoord(37.4518936,126.9007885));
        Markets.put("마포구 그랜드마트 신촌점", MapPoint.mapPointWithGeoCoord(37.5550041,126.9359849));
        Markets.put("마포구 홈플러스 월드컵점", MapPoint.mapPointWithGeoCoord(37.5699509,126.899029));
        Markets.put("동작구 태평백화점", MapPoint.mapPointWithGeoCoord(37.4869128,126.9816608));
        Markets.put("영등포구 롯데백화점 영등포점", MapPoint.mapPointWithGeoCoord(37.5156838,126.9076607));
        Markets.put("관악구 롯데백화점 관악점", MapPoint.mapPointWithGeoCoord(37.4904913,126.9249815));
        Markets.put("덕양구 세이브 마트", MapPoint.mapPointWithGeoCoord(37.7105685,126.9056044));
        Markets.put("서초구 하나로클럽 양재점", MapPoint.mapPointWithGeoCoord(37.4634962,127.0435314));
        Markets.put("송파구 롯데백화점 잠실점", MapPoint.mapPointWithGeoCoord(37.5110794,127.0981638));
        Markets.put("송파구 홈플러스 잠실점", MapPoint.mapPointWithGeoCoord(37.5162594,127.1030345));
        Markets.put("강동구 이마트 명일점", MapPoint.mapPointWithGeoCoord(37.5547054,127.1556281));
        Markets.put("강동구 홈플러스 강동점", MapPoint.mapPointWithGeoCoord(37.5456952,127.142258));
        Markets.put("서초구 뉴코아아울렛 강남점", MapPoint.mapPointWithGeoCoord(37.5092377,127.0074886));
        Markets.put("강북구 하나로클럽 미아점", MapPoint.mapPointWithGeoCoord(37.6215636,127.0265155));
        Markets.put("양천구 이마트 목동점", MapPoint.mapPointWithGeoCoord(37.52609,126.871084));
        Markets.put("서초구 신세계백화점 강남점", MapPoint.mapPointWithGeoCoord(37.5049178,127.0032131));
        Markets.put("노원구 롯데슈퍼", MapPoint.mapPointWithGeoCoord(37.6226316,127.0795041));
        Markets.put("금천구 홈플러스 독산점", MapPoint.mapPointWithGeoCoord(37.4686645,126.8968827));
        Markets.put("양천구 홈플러스 목동점", MapPoint.mapPointWithGeoCoord(37.5302268,126.8733084));



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
        return Markets.get(marketName);
    }

//    public Point getMarketPoint(String marketName){
//        return Markets.get(marketName);
//    }

    public Set<String> getAllMarketList() {
        Set keys = Markets.keySet();
        return keys;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateMarketsSortedByDistance(MapPoint p){
        for(MarketD marketD : Markets_sort_by_distance) {
            double x = Markets.get(marketD.name).getMapPointGeoCoord().latitude;
            double y = Markets.get(marketD.name).getMapPointGeoCoord().longitude;
            double stdX = p.getMapPointGeoCoord().latitude;
            double stdY = p.getMapPointGeoCoord().longitude;
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
