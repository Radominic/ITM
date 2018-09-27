package com.example.user.gjsd;

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

import com.example.user.gjsd.modules.GPSManager;
import com.example.user.gjsd.modules.MarketExplorer;
import com.example.user.gjsd.modules.MyClient;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

import java.util.Set;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements MapView.MapViewEventListener,MapView.POIItemEventListener{
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
    Context con1;

    @SuppressLint("ValidFragment")
    public MapFragment() {
        // Required empty public constructor
        // 로딩화면에서 셋팅하기
        marketExplorer = new MarketExplorer();
        myClient = new MyClient(this);
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
        con1 = container.getContext();
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        mapView = new MapView(this.getActivity());
        mapView.setDaumMapApiKey("a6a70a1cac21fb3bdf4b989ef4226727");
        ViewGroup map_view = (ViewGroup) view.findViewById(R.id.map_view);
        map_view.addView(mapView);
        mapView.setPOIItemEventListener(this);
        mapView.setMapViewEventListener(this);

        //이거 대신 커스텀 이미지 삽입(내위치 마커)
        mapView.setDefaultCurrentLocationMarker();

//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//        mapView.setShowCurrentLocationMarker(true);

        setMapMyLocation();
        setAllMarkets();
        setZoomIncludeN(3);
        setPriceOnMap();


        return view;
    }

    public void setMapMyLocation() {
        mapView.setMapCenterPoint(gpsManager.getMyMapPoint(), false);
//        Log.d("@@@",""+gpsManager.getMyMapPoint().getMapPointGeoCoord().latitude+","+gpsManager.getMyMapPoint().getMapPointGeoCoord().longitude);
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
//        mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeadingWithoutMapMoving);
    }


//    public void setMapGuLocation(String guName) {
//        mapView.setMapCenterPoint(guManager.getGuMapPoint(guName), true);
//    }

    public void setMapMarketLocation(String marketName) {
        mapView.setMapCenterPoint(marketExplorer.getMarketMapPoint(marketName), true);
    }

    public void setAllMarkets(){
        Set<String> markets = marketExplorer.getAllMarketList();
        for(String marketName : markets){
            createMarker(marketName);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void setZoomIncludeN(int numOfMarkets) {
        //p기준 정렬
        marketExplorer.updateMarketsSortedByDistance(mapView.getMapCenterPoint());

//        mapView.fitMapViewAreaToShowMapPoints();
        //n개 가져오기
        MapPoint[] nearMarkets = marketExplorer.getNearNMarketsMapPoint(numOfMarkets);
//        for (String marketName : nearMarkets) {
//            createMarker(marketName);
//        }
        mapView.fitMapViewAreaToShowMapPoints(nearMarkets);
        mapView.zoomOut(true);
    }

    public void setCenterMarker(){
        //화면 중심에 마커를 띄움
        //화면이 갱신될 때마다 업데이트?
        //이름 center
        //화면 갱신될 때마다 여기서 생성한 poiItem으로 mappoint에 접근해서 거리 계산 갱신
    }

    private void createMarker(String marketName) {
        MapPOIItem mCustomMarker;
        mCustomMarker = new MapPOIItem();
        String name = marketName;
        mCustomMarker.setItemName(marketName);
        mCustomMarker.setTag(1);
        mCustomMarker.setMapPoint(marketExplorer.getMarketMapPoint(marketName));
        mCustomMarker.setMarkerType(MapPOIItem.MarkerType.CustomImage);


        mCustomMarker.setCustomImageBitmap(writeOnDrawable(R.drawable.custom_marker_red,marketName,30));

        mCustomMarker.setCustomImageAutoscale(false);
        mCustomMarker.setCustomImageAnchor(0.5f, 1.0f);

        mCustomMarker.setShowCalloutBalloonOnTouch(false);
        mapView.addPOIItem(mCustomMarker);

        //클릭시 말풍선 안뜨게 수정
    }

    public void setPriceOnMap(){
        //떠있는 poi들을 client에게 넘김
        MapPOIItem[] poiItems = mapView.getPOIItems();
        for(MapPOIItem poiItem : poiItems){
            myClient.getPriceOfMarket(poiItem,mainActivity.getItemname(),poiItem.getItemName());
        }
    }
    public void setPriceOnPOIItem(MapPOIItem poiItem,String price){
        //가격을 poiItem에 셋팅, 이 부분에 가격정보 띄우는 코드 삽입
        Log.d("price",price);
        poiItem.setItemName(price);
    }

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

    @Override
    public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewZoomLevelChanged(MapView mapView, int i) {

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

    }

    @Override
    public void onMapViewDragEnded(MapView mapView, MapPoint mapPoint) {

    }

    @Override
    public void onMapViewMoveFinished(MapView mapView, MapPoint mapPoint) {

    }

    //poi listener
    @Override
    public void onPOIItemSelected(MapView mapView, MapPOIItem mapPOIItem) {
        setPriceOnMap();
        Log.d("@@@",mapPOIItem.getItemName());
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
    public Bitmap writeOnDrawable(int drawableId, String text, int TextSize){

        Bitmap bm = BitmapFactory.decodeResource(con1.getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        BitmapFactory.decodeResource(con1.getResources(),R.drawable.custom_marker_red);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(TextSize);
        paint.setTextAlign(Paint.Align.CENTER);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(text,bm.getWidth()/2 , bm.getHeight()/2, paint);

        return bm;
    }


}
