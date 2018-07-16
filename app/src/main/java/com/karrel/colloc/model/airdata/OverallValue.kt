package com.karrel.colloc.model.airdata

//평균값
data class OverallValue(
        var updateTime: String? = null, // 시간
        var grade: Int = 0, // 등급 : 2
        var status: String? = null, // 상태 : 좋음
        var description: String? = null, // 설명 : 신선한 공기 많이 마시세요~
        var locationName: String? = null// 위치 : 서울특별시 광진구 중곡 1동
)