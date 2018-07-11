package com.karrel.colloc.model.airdata

// 현재값
class CurrentValue {
    var title: String? = null // 미세먼지, 초미세먼지 등
    var grade: Int = 0 // 등급 : 2
    var status: String? = null // 상태 : 좋음
    var value: String? = null // 17 ㎍/㎥
}