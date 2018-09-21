package com.example.user.gjsd.modules;

import net.daum.mf.map.api.MapPoint;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MarketExplorer {
    private Map<String, MapPoint> Markets;

    public MarketExplorer(){
        Markets = Collections.synchronizedMap(new HashMap<String,MapPoint>());

        //데이터 삽입
        Markets.put("통인시장", MapPoint.mapPointWithGeoCoord(37.5807801,126.9699523));
        Markets.put("용문시장", MapPoint.mapPointWithGeoCoord(37.5366204,126.9597976));
        Markets.put("남대문시장", MapPoint.mapPointWithGeoCoord(37.5592111,126.977639));
        Markets.put("돈암제일시장", MapPoint.mapPointWithGeoCoord(37.5916795,127.0157178));
        Markets.put("장위골목시장", MapPoint.mapPointWithGeoCoord(37.6120979,127.0508262));
        Markets.put("대림중앙시장", MapPoint.mapPointWithGeoCoord(37.4916125,126.899949));
        Markets.put("영등포전통시장", MapPoint.mapPointWithGeoCoord(37.5197463,126.9074287));
        Markets.put("방학동도깨비시장", MapPoint.mapPointWithGeoCoord(37.6657435,127.0329465));
        Markets.put("신창시장", MapPoint.mapPointWithGeoCoord(37.63458869999999, 127.01483140000005));
        Markets.put("인왕시장", MapPoint.mapPointWithGeoCoord(37.5908692,126.943696));
        Markets.put("영천시장", MapPoint.mapPointWithGeoCoord(37.5703324,126.9618708));
        Markets.put("송화시장", MapPoint.mapPointWithGeoCoord(37.5492288,126.8345762));
        Markets.put("대림시장", MapPoint.mapPointWithGeoCoord(37.5868145, 126.91771399999993));
        Markets.put("광장시장", MapPoint.mapPointWithGeoCoord(37.5701227,126.9997095));
        Markets.put("후암시장", MapPoint.mapPointWithGeoCoord(37.5501259,126.9764509));
        Markets.put("수유재래시장", MapPoint.mapPointWithGeoCoord(37.6315365,127.0223447));
        Markets.put("금남시장", MapPoint.mapPointWithGeoCoord(37.5484673,127.0227235));
        Markets.put("뚝도시장", MapPoint.mapPointWithGeoCoord(37.5378062,127.0548442));
        Markets.put("자양골목시장", MapPoint.mapPointWithGeoCoord(37.5338035,127.0816089));
        Markets.put("청량리종합시장", MapPoint.mapPointWithGeoCoord(37.5808292,127.0433944));
        Markets.put("경동시장", MapPoint.mapPointWithGeoCoord(37.5787997,127.0396819));
        Markets.put("우림시장", MapPoint.mapPointWithGeoCoord(37.5967745,127.0982882));
        Markets.put("동원시장", MapPoint.mapPointWithGeoCoord(37.5898431,127.0899073));
        Markets.put("공릉동 도깨비시장", MapPoint.mapPointWithGeoCoord(37.6226999,127.0762606));
        Markets.put("목3동시장", MapPoint.mapPointWithGeoCoord(37.5482793,126.8668424));
        Markets.put("신영시장", MapPoint.mapPointWithGeoCoord(37.5330235,126.8360192));
        Markets.put("남구로시장", MapPoint.mapPointWithGeoCoord(37.4897303,126.8864836));
        Markets.put("고척근린시장", MapPoint.mapPointWithGeoCoord(37.5024083,126.8505529));
        Markets.put("현대시장", MapPoint.mapPointWithGeoCoord(37.5669542,127.0593016));
        Markets.put("남문시장", MapPoint.mapPointWithGeoCoord(37.4737845,126.9004226));
        Markets.put("망원시장", MapPoint.mapPointWithGeoCoord(37.5562398,126.9056321));
        Markets.put("마포농수산물시장", MapPoint.mapPointWithGeoCoord(37.5651843,126.8984946));
        Markets.put("남성시장", MapPoint.mapPointWithGeoCoord(37.4894036,126.9807712));
        Markets.put("원당종합시장", MapPoint.mapPointWithGeoCoord(37.6560447,126.8373471));
        Markets.put("신원시장(신림1동)", MapPoint.mapPointWithGeoCoord(37.4835132,126.9258997));
        Markets.put("마천중앙시장", MapPoint.mapPointWithGeoCoord(37.4980997,127.1507085));
        Markets.put("방이시장", MapPoint.mapPointWithGeoCoord(37.5103559,127.1175756));
        Markets.put("암사종합시장", MapPoint.mapPointWithGeoCoord(37.5508693,127.1288365));
        Markets.put("서울중앙시장", MapPoint.mapPointWithGeoCoord(37.5667939,127.0198036));
        Markets.put("둔촌역전통시장", MapPoint.mapPointWithGeoCoord(37.5275029,127.1351706));
        Markets.put("관악신사시장(신림4동)", MapPoint.mapPointWithGeoCoord(37.4868119,126.9170742));
        Markets.put("상계중앙시장", MapPoint.mapPointWithGeoCoord(37.659884,127.0698784));
        Markets.put("노룬산골목시장", MapPoint.mapPointWithGeoCoord(37.5364069,127.0646822));
        Markets.put("화곡본동시장", MapPoint.mapPointWithGeoCoord(37.5428618,126.8441381));
        Markets.put("청담삼익시장", MapPoint.mapPointWithGeoCoord(37.5224855,127.0576847));
        Markets.put("도곡시장", MapPoint.mapPointWithGeoCoord(37.4954841,127.0333574));
        Markets.put("방림시장", MapPoint.mapPointWithGeoCoord(35.1369729,126.9136617));
        Markets.put("대조시장", MapPoint.mapPointWithGeoCoord(37.6098316,126.9276087));
        Markets.put("숭인시장", MapPoint.mapPointWithGeoCoord(37.6131571,127.0294686));
    }

    public String searchMarket(String key){
        //검색 쿼리 구현
        return null;
    }

    public MapPoint getMarketPoint(String marketName){
        return Markets.get(marketName);
    }

}
