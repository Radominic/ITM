package com.example.user.gjsd.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.user.gjsd.R;
import com.example.user.gjsd.model.Market;
import com.example.user.gjsd.modules.GPSManager;
import com.example.user.gjsd.modules.MarketExplorer;
//import com.example.user.gjsd.modules.MyPOIObject;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements MapView.MapViewEventListener, MapView.POIItemEventListener{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private GPSManager gpsManager;
    private MarketExplorer marketExplorer;
//    private MyClient myClient;
    private MainActivity mainActivity;

    private MapView mapView;
    private FrameLayout framelayout;

    @SuppressLint("ValidFragment")
    public MapFragment() {
        // Required empty public constructor
        // 로딩화면에서 셋팅하기
    }

    public void setFramelayout(FrameLayout frameLayout) {
        this.framelayout = frameLayout;
    }

    public void setGpsManager(GPSManager gpsManager) {
        this.gpsManager = gpsManager;
    }

    public void setMainActivity(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void setMarketExplorer(MarketExplorer marketExplorer) {
        this.marketExplorer = marketExplorer;
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        item = getArguments().getString("itemName");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.i("@@@","open");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = new MapView(Objects.requireNonNull(this.getActivity()));
        mapView.setDaumMapApiKey("a6a70a1cac21fb3bdf4b989ef4226727");
        ViewGroup map_view = (ViewGroup) view.findViewById(R.id.map_view);
        map_view.addView(mapView);
        mapView.setPOIItemEventListener(this);
        mapView.setMapViewEventListener(this);
//        Log.d("debug", "여기까지 ok");

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initMap() {
        mapView.setMapCenterPoint(gpsManager.getMyMapPoint(),false);
        Log.d("debug_my_location",""+gpsManager.getMyMapPoint().getMapPointGeoCoord().latitude+","+gpsManager.getMyMapPoint().getMapPointGeoCoord().longitude);
        createCenterMarker();
        marketExplorer.updateMarketDistance(centerPoiItem.getMapPoint());
//        marketExplorer.updateMarkets_sort_by_price(mainActivity.getSelectedItemName());

        mapView.setZoomLevel(4,false);
//        setZoomIncludeN(3);

//        marketExplorer.updateMarketDistance(mapView.getMapCenterPoint());
        initMarker();
//        updateAllMarkersOnMap();
//        marketExplorer.updateMarketPrice(mainActivity.selectedItem);
//        mainActivity.cf.updateMarkets_sort_by_price();
        mainActivity.df.updateMarkets_sort_by_distance();

    }

    private void initMarker() {
        Log.d("update_initMarker","------------");
        ArrayList<String> names = marketExplorer.getMarkets_sort_by_distance();
        for (String name : names) {
            Log.d("market_sort_dist", name);
            Market market = marketExplorer.getMarket(name);
//            if(market == null) Log.d("debug_ohohohohoh","true");
            MapPOIItem poiItem = new MapPOIItem();
            poiItem.setItemName(name);
            poiItem.setMapPoint(market.getMapPoint());
            poiItem.setUserObject(market);
            poiItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            poiItem.setCustomImageAutoscale(false);
            poiItem.setShowCalloutBalloonOnTouch(false);
            poiItem.setCustomImageAnchor(0.3f, 1.0f);
            poiItem.setCustomImageBitmap(writeOnDrawable(poiItem));
            if (poiItem.getUserObject() == null) Log.d("debug_poiSetobj_null", "true");
            mapView.addPOIItem(poiItem);
            Log.d("debug_added_poi_market", poiItem.getItemName());
        }
    }

    public void updateAllMarkersOnMap() {
        //draw all
//        ArrayList<String> names = marketExplorer.getMarkets_sort_by_distance();
//        MapPOIItem poiItems = mapView.
//        for(String name : names)
//        this.item = item;
        Log.d("update_all_markers_on_map","------------");
        MapPOIItem[] poiItems = mapView.getPOIItems();
        for (MapPOIItem poiItem : poiItems) {
            if (poiItem.getItemName().equals("centerPoiItem")) {
                continue;
            }
            mapView.removePOIItem(poiItem);


//            MapPOIItem[] poiItems = mapView.findPOIItemByName(name);
//            MapPOIItem poiItem = poiItems[0];
            Log.d("debug_poi_null", poiItem.getItemName());
            if (poiItem.getUserObject() == null) Log.d("debug_poiobj_null", "true");
            Market market = (Market) poiItem.getUserObject();
            if (market == null) {
                Log.d("debug_market_null", "true");
            }
            if (market.getItem(mainActivity.selectedItem).getPrice() == null) {
                //draw default bitmap
                Log.d("debug_market_price", "null");
                poiItem.setCustomImageBitmap(writeOnDrawable(poiItem));
                Log.d("draw", "price false");
            } else {
                //draw item and price
                poiItem.setCustomImageBitmap(writeOnDrawable(poiItem));
                mainActivity.getSelectedItemName();
                Log.d("draw", "price true");
            }
           mapView.addPOIItem(poiItem);
        }
        
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setMapMyLocation() {
        mapView.removePOIItem(centerPoiItem);
        mapView.setMapCenterPoint(gpsManager.getMyMapPoint(), true);
        updateCenterMarkerAndDistance();
        mapView.addPOIItem(centerPoiItem);
    }

    public void setMapMarketLocation(String marketName) {
        mapView.setMapCenterPoint(marketExplorer.getMarketMapPoint(marketName), true);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setZoomIncludeN(int numOfMarkets) {
        //n개 가져오기
        MapPoint[] nearMarkets = marketExplorer.getNearNMarketsMapPoint(numOfMarkets);
        mapView.fitMapViewAreaToShowMapPoints(nearMarkets);
        mapView.zoomOut(true);
    }


    MapPOIItem centerPoiItem;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createCenterMarker() {
        centerPoiItem = new MapPOIItem();
        centerPoiItem.setItemName("centerPoiItem");
        centerPoiItem.setMapPoint(mapView.getMapCenterPoint());
        centerPoiItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
        centerPoiItem.setCustomImageResourceId(R.drawable.mypoint);
        centerPoiItem.setShowCalloutBalloonOnTouch(false);
        mapView.addPOIItem(centerPoiItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateCenterMarkerAndDistance() {
        Log.d("update_center_marker_distance","------------");
        centerPoiItem.setMapPoint(mapView.getMapCenterPoint());
//        mapView.addPOIItem(center);
        //거리 갱신
        marketExplorer.updateMarketDistance(mapView.getMapCenterPoint());
//        updateAllMarkersOnMap();
        marketExplorer.getNearNMarketsMapPoint(5);
    }

//    public void updatePrice(){
//        //시장마다 정보 모두 셋팅
////        marketExplorer.updateMarketPrice(mainActivity.getSelectedItemName());
//
//        //가격순 정렬 셋팅
//        marketExplorer.updateMarkets_sort_by_price(mainActivity.getSelectedItemName());
//    }

    public void updateCostFragment(){
        mainActivity.cf.applyMarkets_sort_by_price();
    }
//    private void createMarker(String marketName) {
//
//        MapPOIItem mCustomMarker = new MapPOIItem();
//        mCustomMarker.setUserObject(new MyPOIObject(marketName));
//        mCustomMarker.setItemName(marketName);
//        mCustomMarker.setMapPoint(marketExplorer.getMarketMapPoint(marketName));
//
//        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
//          mCustomMarker.setCustomImageAutoscale(false);
////        mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
//
//
//        //아이템 클래스, 객체 만들기
//        mCustomMarker.setCustomImageBitmap(writeOnDrawable(mCustomMarker));
//
//
//
//
//        mCustomMarker.setShowCalloutBalloonOnTouch(false);
//        mapView.addPOIItem(mCustomMarker);
//        //클릭시 말풍선 안뜨게 수정
//    }
//
//    public void setPriceOnMap() {
//        //떠있는 poi들을 client에게 넘김
//        MapPOIItem[] poiItems = mapView.getPOIItems();
//        for (MapPOIItem poiItem : poiItems) {
//            myClient.setPriceOnMarket(poiItem, mainActivity.getSelectedItemName(), poiItem.getItemName());
//        }
//    }
//
//    public void setPriceOnPOIItem(MapPOIItem poiItem, String price) {
//        //가격을 poiItem에 셋팅, 이 부분에 가격정보 띄우는 코드 삽입
//        Log.d("price", price);
//        MyPOIObject poiObject = (MyPOIObject) poiItem.getUserObject();
//        poiObject.setPrice(price);
//
//        if (poiObject.getPrice() != null) {
//            //drawBitmap
//            String tmp_price = price;
//            String tmp_market_name = poiObject.getMarketName();
//            String selectedItem = mainActivity.getSelectedItemName();
//        } else {
//            //drawBitmapDefault
//        }
//    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

//    public void refresh(){
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.detach(this).attach(this).commit();
//    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onMapViewInitialized(MapView mapView) {
        initMap();
    }


    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {
        Log.d("selected_item",mainActivity.getSelectedItemName());
    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        //줌 레벨 구간 강제 설정
        Log.d("zoomlevel",""+(float)mapView.getZoomLevel());
        if(mapView.getZoomLevel() >= 6){
            mapView.setZoomLevel(5,true);
        }


    }

    @Override
    public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewDragStarted(MapView mapView, MapPoint mapPoint) {
        mapView.removePOIItem(centerPoiItem);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {
        if(mapView.findPOIItemByName("centerPoiItem")!=null){mapView.removePOIItem(centerPoiItem);}
        updateCenterMarkerAndDistance();
        mapView.addPOIItem(centerPoiItem);
    }

    //poi listener
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem) {

    }

    @Override
    public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem mapPOIItem, MapPOIItem.CalloutBalloonButtonType calloutBalloonButtonType) {

    }

    @Override
    public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem mapPOIItem, MapPoint mapPoint) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public Bitmap writeOnDrawable(MapPOIItem mapPOIItem) {
        Market market = (Market) mapPOIItem.getUserObject();
        //전통시장이면 false, 대형마트이면 true
        boolean isMart = market.isMart();
//        market.getName();
//        market.getItem(mainActivity.selectedItem).getPrice();
        int drawableId ;
        //마켓의 종류에 따라 다른 드로어블 부여함.
        if(isMart){
            drawableId = R.drawable.default_mart;
        }else{
            drawableId = R.drawable.default_market;
        }

        //앞에 구 제거해주기
        String[] namearr = mapPOIItem.getItemName().split(" ");
        String name = "";
        for(int i=1;i<namearr.length;i++)
            name += namearr[i];
        //텍스트 사이즈
        int TextSize = 30;

        Bitmap bm = BitmapFactory.decodeResource(this.getActivity().getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);

        Paint paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Black.ttf"));
        //마트인경우 시장인 경우 나누기
        if(isMart)
        paint1.setColor(Color.rgb(9,101,227));
        else
            paint1.setColor(Color.rgb(72,163,132 ));
        paint1.setTextSize(TextSize);
        paint1.setTextAlign(Paint.Align.LEFT);

        //마트,시장 공통
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/Roboto-Black.ttf"));
        paint2.setColor(Color.rgb(244,169,29));
        paint2.setTextSize(TextSize);
        paint2.setTextAlign(Paint.Align.LEFT);

        //문자 생성
        String text = "";
        if(market.getItem(mainActivity.selectedItem)==null){
            //draw default
            text = "품목없음";
        }else{
            //품목
            text += mainActivity.getSelectedItemName()+" : "+ Currency.getInstance(Locale.KOREA).getSymbol();
            //가격 받아오기
            text += market.getItem(mainActivity.selectedItem).getPrice();
        }



        //색 설정하기
        Canvas canvas = new Canvas(bm);
        canvas.drawText(name,13 , 43, paint1);
        canvas.drawText(text,13 , 80, paint2);

        return bm;
    }

    public void refresh(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.detach(this).attach(this).commit();
//        mapView.invalidate();

    }


}
