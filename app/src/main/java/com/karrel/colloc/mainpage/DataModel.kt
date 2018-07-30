package com.karrel.colloc.mainpage

import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable

class DataModel : IDataModel {

    override fun getMiseDataFromApiServer(location: String): Observable<AirData> {
        // TODO Network 작업
        // Dummy Data
        return Observable.just(AirData())
    }

}