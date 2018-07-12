package com.example.xiaojw.tamodel
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import com.common.baseframe.net.HttpManager
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class MainActivity :    AppCompatActivity(), ViewPager.OnPageChangeListener, View.OnTouchListener{


    companion object {

        const  val MIN_SCALE=0.9F
        const  val MSG_BANNER_LOOP=0x11
    }

   lateinit var mBannerVp:ViewPager
    lateinit var mBannerImg:ImageView
    lateinit var mHandler: BannerHandler
    lateinit var mRecyclerView: RecyclerView

    lateinit var mWebView: WebView

    val  resArray= intArrayOf(R.mipmap.ic_banner_1,R.mipmap.ic_banner_2,R.mipmap.ic_banner_3,R.mipmap.ic_banner_4,R.mipmap.ic_banner_5,R.mipmap.ic_banner_6)

    lateinit var  mRefreshHeader: RefreshHeader


//    lateinit var mRegisty:LifecycleRegistry

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)
        val  outMetrics=DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(outMetrics)
        mHandler= BannerHandler()
        mRecyclerView=findViewById(R.id.banner_rlv)
        mBannerVp=findViewById(R.id.banner_vp)
        mBannerImg=findViewById(R.id.banner_img)
        val viewList= arrayListOf<View>()
        for (value in resArray.withIndex()){
            val  itemImg=ImageView(this)
            itemImg.layoutParams=ViewGroup.LayoutParams( outMetrics.widthPixels, ViewGroup.LayoutParams.MATCH_PARENT)
            itemImg.scaleType=ImageView.ScaleType.FIT_XY
            itemImg.setImageResource(value.value)
            itemImg.id=value.index
            itemImg.scaleY= MIN_SCALE
            viewList.add(itemImg)
        }
        mBannerVp.adapter=BannerPagerAdapter(viewList)
        mBannerVp.setPageTransformer(false,BannerPagerTransformer())
        mBannerVp.addOnPageChangeListener(this)
//        mHandler.sendEmptyMessage(MSG_BANNER_LOOP)
        mRecyclerView.layoutManager= LinearLayoutManager(this) as RecyclerView.LayoutManager?;
        val adapter=DataListAdapter()
        mRefreshHeader=RefreshHeader(this);
        adapter.setRefreshHeader(mRefreshHeader)
        mRecyclerView.adapter=adapter;
        mRecyclerView.setOnTouchListener(this)

        initWebView()

//        testkApi();



//        mRegisty= LifecycleRegistry(this)
//        mRegisty.markState(Lifecycle.State.CREATED)
//        mRegisty.addObserver(MyObserver())



    }

    override fun onStart() {
        super.onStart()
//        mRegisty.markState(Lifecycle.State.STARTED)
    }

//
//    fun  testkApi(){
//        var a=1;
//        var b=2;
//
//        val max=if (a>b){
//            print("fasfsajflk")
//            a
//
//        }else{
//b
//        }
//
//        when(a){
//            1->{}
//            2->{}
//            else->{
//
//            }
//
//        }
//
//        BookComputer.use()
//        BookComputer.a
//
//        var bookList= arrayListOf<String?>()
//
//        for (book in bookList){
//
//             book?.let {
//
//                 APPLog.printDebug("faskl;fas")
//
//             }
//
//        }
//
////        var c=Car("");
////        c.name
//        val c=Calendar.getInstance();
//        if (c.firstDayOfWeek==Calendar.SATURDAY){
//            c.firstDayOfWeek=Calendar.MONDAY;
//        }
//        if (!c.isLenient){
//            c.isLenient=true
//        }
//
//
//    }




    fun  initWebView(){

        mWebView=findViewById(R.id.banner_wv)
        mWebView.webViewClient=WebViewClient()
        mWebView.settings.javaScriptEnabled=true
        mWebView.settings.allowFileAccess=true
        mWebView.settings.pluginState=WebSettings.PluginState.ON_DEMAND
        mWebView.loadUrl("http://tech.163.com/")

    }

    var lastY=0f;


    var offsetY=0f;
    var mLastY=0f;


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                offsetY=event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                offsetY=event.rawY-mLastY
                mLastY=event.rawY;
                mRefreshHeader.smoothScrollTo(offsetY.toInt())
            }

            else->{

                mRefreshHeader.smoothScrollTo(0)
            }



        }
        return false;
    }






    class BannerPagerAdapter(itemViewList:List<View>):PagerAdapter(){

         var mItemViewList:List<View>


        init {

            mItemViewList=itemViewList

        }


        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            val  itemView=mItemViewList.get(position)
            container.removeView(itemView)
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val itemView=mItemViewList.get(position)
            container.addView(itemView)
            return itemView
        }



        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view==`object`
        }

        override fun getCount(): Int {

            return  if (mItemViewList.size>0) mItemViewList.size else 0

        }


    }

    class BannerPagerTransformer:ViewPager.PageTransformer{
        override fun transformPage(page: View, position: Float) {
            Log.d("banner","pageId:"+page.id+"   position:"+position+"\n");

            if (position<-1){
                page.scaleY= MIN_SCALE
            }else if (position<=1){
               val scale=Math.max(MIN_SCALE,1-Math.abs(position))
                page.scaleY= scale
            }else{

                page.scaleY= MIN_SCALE
            }

        }


    }
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
//        mBannerImg.setImageResource(resArray[position])

    }


  inner  class BannerHandler:Handler(){


      var index=0
      var isReverse=false

        override fun handleMessage(msg: Message?) {




            when(msg?.what){
                MSG_BANNER_LOOP->{
                    mBannerVp.setCurrentItem(index)
                    if (index>=resArray.size-1){
                        isReverse=true
                    }else if (index<=0){
                        isReverse=false
                    }
                    if (isReverse){
                        index--
                    }else{
                        index++
                    }
                    sendEmptyMessageDelayed(MSG_BANNER_LOOP,1000)
                }
            }


        }

    }





    fun  uploadFile(file:File){

         var desBody=RequestBody.create(MediaType.parse("multipart/form-data"),"xxxx")


        var requestFile=RequestBody.create(MediaType.parse("multipart/form-data"),file)


        var bodyPart=MultipartBody.Part.createFormData("pic",file.name,requestFile)


        var ta= HttpManager.getApi(TA::class.java,"")


//        ta.upload(desBody,bodyPart)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io()).subscribe(object :Observer<ResponseBody>{
//                    override fun onCompleted() {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onNext(t: ResponseBody?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//                    override fun onError(e: Throwable?) {
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//                    }
//
//
//                })

      var call=    ta.upload(desBody,bodyPart)

        call.enqueue(object :Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
            }


        })

















    }


//    override fun getLifecycle(): Lifecycle {
//        return mRegisty;
//
//    }



}
