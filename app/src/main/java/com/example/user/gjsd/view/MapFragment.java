package com.example.user.gjsd.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.user.gjsd.R;
import com.example.user.gjsd.model.Market;
import com.example.user.gjsd.modules.GPSManager;
import com.example.user.gjsd.modules.MarketExplorer;
import com.example.user.gjsd.modules.MyClient;
//import com.example.user.gjsd.modules.MyPOIObject;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements MapView.MapViewEventListener, MapView.POIItemEventListener {
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
    private MyClient myClient;
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

    public void setMarketExplorer(MarketExplorer marketExplorer){
        this.marketExplorer = marketExplorer;
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = new MapView(Objects.requireNonNull(this.getActivity()));
        mapView.setDaumMapApiKey("a6a70a1cac21fb3bdf4b989ef4226727");
        ViewGroup map_view = (ViewGroup) view.findViewById(R.id.map_view);
        map_view.addView(mapView);
        mapView.setPOIItemEventListener(this);
        mapView.setMapViewEventListener(this);
        Log.d("debug","여기까지 ok");

        initMap();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void initMap(){
        setMapMyLocation();
        createCenterMarker();
        marketExplorer.updateMarketPrice();
        marketExplorer.updateMarketDistance(mapView.getMapCenterPoint());
        initMarker();
        updateAllMarkersOnMap();


        setZoomIncludeN(3);
    }
    private void initMarker(){

        ArrayList<String> names = marketExplorer.getMarkets_sort_by_distance();
        for(String name : names){
            Log.d("market_sort_dist",name);
            Market market = marketExplorer.getMarket(name);
//            if(market == null) Log.d("debug_ohohohohoh","true");
            MapPOIItem poiItem = new MapPOIItem();
            poiItem.setMarkerType(MapPOIItem.MarkerType.CustomImage);
            poiItem.setItemName(name);
            poiItem.setMapPoint(market.getMapPoint());
            poiItem.setUserObject(market);
            if(poiItem.getUserObject() == null) Log.d("debug_poiSetobj_null","true");
            mapView.addPOIItem(poiItem);
            Log.d("debug_added_poi_market","true");
        }
    }

    public void updateAllMarkersOnMap(){
        //draw all
        ArrayList<String> names = marketExplorer.getMarkets_sort_by_distance();
//        MapPOIItem poiItems = mapView.
//        for(String name : names)
        MapPOIItem[] poiItems = mapView.getPOIItems();
        for(MapPOIItem poiItem : poiItems){
            if(poiItem.getItemName().equals("centerPoiItem")){continue;}
//            MapPOIItem[] poiItems = mapView.findPOIItemByName(name);
//            MapPOIItem poiItem = poiItems[0];
            Log.d("debug_poi_null",poiItem.getItemName());
            if(poiItem.getUserObject() == null) Log.d("debug_poiobj_null","true");
            Market market = (Market)poiItem.getUserObject();
            if(market == null){Log.d("debug_market_null","true");}
            if(market.getPrice()==null){
                //draw default bitmap
            }else{
                //draw item and price
                poiItem.setCustomImageBitmap(writeOnDrawable(poiItem));
                mainActivity.getSelectedItemName();
            }
        }
    }

    public void setMapMyLocation() {
        mapView.setMapCenterPoint(gpsManager.getMyMapPoint(), false);
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
        //화면 중심에 마커를 띄움
        //화면이 갱신될 때마다 업데이트?
        //이름 center
        //화면 갱신될 때마다 여기서 생성한 poiItem으로 mappoint에 접근해서 거리 계산 갱신

        //p기준 정렬
        marketExplorer.updateMarketDistance(mapView.getMapCenterPoint());

        //생성
        centerPoiItem = new MapPOIItem();
        centerPoiItem.setItemName("centerPoiItem");
        centerPoiItem.setMapPoint(mapView.getMapCenterPoint());
        centerPoiItem.setCustomImageResourceId(R.drawable.custom_marker_red);

        mapView.addPOIItem(centerPoiItem);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateCenterMarkerAndDistance() {
        centerPoiItem.setMapPoint(mapView.getMapCenterPoint());
        //거리 갱신
        marketExplorer.updateMarketDistance(mapView.getMapCenterPoint());
    }


//    private void createMarker(String marketName) {
//
//        MapPOIItem mCustomMarker = new MapPOIItem();
//        mCustomMarker.setUserObject(new MyPOIObject(marketName));
//        mCustomMarker.setItemName(marketName);
//        mCustomMarker.setMapPoint(marketExplorer.getMarketMapPoint(marketName));
//
//        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);
//
//
//
//        //아이템 클래스, 객체 만들기
//        mCustomMarker.setCustomImageBitmap(writeOnDrawable(mCustomMarker));
//
//
//        mCustomMarker.setCustomImageAutoscale(false);
//        mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);
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

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {
        //줌 레벨 구간 강제 설정
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
        updateCenterMarkerAndDistance();
        mapView.addPOIItem(centerPoiItem);
    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

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
        Market market = (Market)mapPOIItem.getUserObject();
        //전통시장이면 false, 대형마트이면 true
        boolean isMart = market.isMart();
        market.getName();
        market.getPrice();

        //마켓의 종류에 따라 다른 드로어블 부여함.
        int drawableId = R.drawable.custom_marker_red;
        String[] namearr = mapPOIItem.getItemName().split(" ");
        String name = "";
        for (int i = 1; i < namearr.length; i++)
            name += namearr[i];
        int TextSize = 30;

        Bitmap bm = BitmapFactory.decodeResource(this.getActivity().getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        BitmapFactory.decodeResource(this.getActivity().getResources(), R.drawable.custom_marker_red);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(TextSize);
        paint.setTextAlign(Paint.Align.CENTER);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(name, bm.getWidth() / 2, bm.getHeight() / 2, paint);

        return bm;
    }


}
