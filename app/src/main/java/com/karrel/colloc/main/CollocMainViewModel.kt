package com.karrel.colloc.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.karrel.colloc.model.airdata.AirData

class CollocMainViewModel : ViewModel() {
    private var mAirData: MutableLiveData<AirData>? = null

    fun getAirData(location: String): MutableLiveData<AirData> {
        if (mAirData == null) {
            mAirData = MutableLiveData()
        }

        var dat = getMsrstnAcctoRltmMesureDnsty(location)
        mAirData!!.value = dat

        return mAirData as MutableLiveData<AirData>
    }


    fun getAirData(long: Double, lan: Double): MutableLiveData<AirData> {
        if (mAirData == null) {
            mAirData = MutableLiveData()
            loadAirData(mAirData!!, long, lan)
        }
        return mAirData as MutableLiveData<AirData>
    }


    private fun loadAirData(airData: MutableLiveData<AirData>, long: Double, lan: Double) {
//        TODO("WBS84 to TM")
//        var pair = wgs2tm(long, lan)

//        TODO("getNearbyMsrstnList airdata via tm location")
//        var dat = getNearbyMsrstnList(pair.first, pair.second)

//        TODO("WBS84 to 주소")
        var address_depth2 = wgs2address(long, lan)

        //      TODO("getMsrstnAcctoRltmMesureDnsty airdata via tm location")
        var dat = getMsrstnAcctoRltmMesureDnsty(address_depth2)
        airData.value = dat
//        airData.postValue(dat)
    }

    //행정구역을이용하는방법-----------------------------------------------------------------------------------------
    //https://dapi.kakao.com/v2/local/geo/coord2address.json?x=127.029475&y=37.496690&input_coord=WGS84&output_coord=WGS84
    private fun wgs2address(long: Double, lan: Double): String {
        return "강남구"
    }

    //http://openapi.airkorea.or.kr/openapi/services/rest/ArpltnInforInqireSvc/getMsrstnAcctoRltmMesureDnsty?stationName=종로구&dataTerm=month&pageNo=1&numOfRows=10000&ServiceKey=wnkqNQdeHUIU37e9NwU7v4JjbuD80jpw%2Fh2sQF2hCbJmw1oyIvKRjd2wrHj0upg4FVubgFzPMgmgxv%2FtsspRtQ%3D%3D&ver=1.3&_returnType=json
    private fun getMsrstnAcctoRltmMesureDnsty(address_depth2: String): AirData {
        var dat = AirData()
        dat.overallValue.description = "미세미세"
        dat.overallValue.locationName = "마포구"
        return dat
    }

    //좌표를이용한방법-----------------------------------------------------------------------------------------
    //제주도 영주고등학교옆
    //https://dapi.kakao.com/v2/local/geo/transcoord.json?x=126.57821604896051&y=33.45613394001848&input_coord=WGS84&output_coord=TM
    fun wgs2tm(long: Double, lan: Double): Pair<Double, Double> {
        return Pair(160710.37832030823, -4388.881011964753)
    }

    //http://openapi.airkorea.or.kr/openapi/services/rest/MsrstnInfoInqireSvc/getNearbyMsrstnList?tmX=160710.37729270622&tmY=-4388.879299157299&pageNo=1&numOfRows=10&ServiceKey=wnkqNQdeHUIU37e9NwU7v4JjbuD80jpw%2Fh2sQF2hCbJmw1oyIvKRjd2wrHj0upg4FVubgFzPMgmgxv%2FtsspRtQ%3D%3D&_returnType=json
    private fun getNearbyMsrstnList(xTM: Double, yTM: Double): AirData {
        var dat = AirData()
        dat.overallValue.description = "미세미세"
        dat.overallValue.locationName = "마포구"
        return dat
    }


}

