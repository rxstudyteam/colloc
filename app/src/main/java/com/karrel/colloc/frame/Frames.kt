package com.karrel.colloc.frame

object TYPE {
    const val TOTAL = 1
    const val LABEL = 2
    const val CURRENT = 3
    const val HOURLY = 4
    const val DAILY = 5
    const val HORIZONTAL = 6
    const val DETAIL_ITEM = 7
    const val INFO = 8
    const val EMPTY = 9
}



sealed class Frame(val type: Int)

/**
 * 토탈 프레임 용 데이타 클래스. //todo 미완.
 */
data class TotalFrame(val time: String) : Frame(TYPE.TOTAL)

/** 단순 라벨용 */
data class LabelFrame(val label:String) : Frame(TYPE.LABEL)


/**   MutableList<Triple<Measured name,Measured state,Measured value>>  */
data class CurrentFrame(val states : MutableList<Triple<String,String,String>>) : Frame(TYPE.CURRENT)

/**   MutableList<Pair<Time, state>>  */
data class HourlyFrame(val states : MutableList<Pair<String,String>>) : Frame(TYPE.HOURLY)

data class DailyItemFrame(val time:String, val icon : Int, val state : String ) : Frame(TYPE.DAILY)

/** 너비나 방향, 색상을 별도로 받는 것을 고려한다.  */
object HorizontalLine : Frame(TYPE.HORIZONTAL)

data class DetailItemFrame(val label : String, val text : String) : Frame(TYPE.DETAIL_ITEM)


enum class GRAVITY{
    CENTER,
    START,
    END
}

data class InfoFrame(val text:String, val gravity : GRAVITY = GRAVITY.CENTER) : Frame(TYPE.INFO)

/** 8dp GRID에 맞춘다고 했을 때. 수평선이 2dp 이기 때문에 6디피용과 8디피용을 나누거나 2디피를
 * 기준 단위로 하여 높이 값을 따로 받는 것을 고려한다.  */
object  EmptyFrame : Frame(TYPE.EMPTY)
