package com.karrel.colloc.mainpage

import com.karrel.colloc.model.airdata.AirData
import io.reactivex.Observable

class MainViewModel(dataModel: IDataModel) {
    private val myDataModel = dataModel

    fun getData(location: String) : Observable<AirData> {
        return myDataModel.getMiseDataFromApiServer(location)
    }
}