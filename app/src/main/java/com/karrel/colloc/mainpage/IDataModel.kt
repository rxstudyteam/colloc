package com.karrel.colloc.mainpage

import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable

interface IDataModel {
    fun getMiseDataFromApiServer(location: String) : Observable<AirData>
}