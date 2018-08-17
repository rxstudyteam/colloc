package android.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

enum class SDF private constructor(var format: String, locale: Locale = Locale.getDefault()) {

    /** Wed, 14 Jan 2015 05:46:36 GMT */
    _long("EEE, d MMM yyyy HH:mm:ss z", Locale.US),
    yy("yy"),
    yyyymm("yyyyMM"),
    yyyy("yyyy"),
    yyyy__("yyyy년"),
    weekofmonth("W"),
    mmdd("MMdd"),
    mmdd__("MM월dd일"),
    mmdd_2("MM.dd"),
    mm("MM"),
    m__("M월"),
    mm__("MM월"),
    d("d"),
    dd("dd"),
    dd__("dd일"),
    hh("hh"),
    EEE_US("EEE", Locale.US),
    EEEE_US("EEEE", Locale.US),
    E("E"),
    E__("E요일"),
    emdhs("(E)M/d HH:mm"),
    mdeahs("M/d(E) a hh:mm"),
    mde("M/d(E)"),
    yyyy_mmdd("yyyy\nMM.dd"),
    yyyy__mmdd("yyyy.\nMM.dd"),
    yyyymmddE__("yyyy년 MM월 dd일 E요일"),
    yyyymmdd("yyyyMMdd"),
    yyyymmdd_("yyyy/MM/dd"),
    yyyymmdd__("yyyy년 MM월 dd일"),
    yyyymmdd_1("yyyy-MM-dd"),
    yyyymmdd_2("yyyy.MM.dd"),
    yyyymmddhhmm("yyyyMMddHHmm"),
    yyyymmddhhmm_1("yyyy-MM-dd HH:mm"),
    yyyymmddahmm_("yyyy/MM/dd a hh:mm"),
    yyyymmddhhmma_2("yyyy.MM.dd hh:mm a"),
    hhmma_2("hh:mm a"),
    yyyymmddhhmm__("yyyy년 MM월 dd일 HH:mm"),
    yyyymmddhhmmss("yyyyMMddHHmmss"),
    yyyymmddhhmmss_("yyyy/MM/dd HH:mm:ss"),
    yyyymmddhhmmss_1("yyyy-MM-dd HH:mm:ss"),
    yyyymmddhhmmss_2("yyyy.MM.dd HH:mm:ss"),
    yyyymm_("yyyy/MM"),
    yyyymm_2("yyyy.MM"),
    yyyymm__("yyyy년 MM월"),
    yyyymmddhhmmsssss_("yyyy/MM/dd HH:mm:ss.SSS"),
    yyyymmddhhmmsssss_1("yyyy-MM-dd HH:mm:ss.SSS"),
    mmddhhmm__("MM월dd일 HH:mm"),
    mmddhhmm_("MM/dd HH:mm"),
    hhmmss("HHmmss"),
    ahhmm("a hh:mm"),
    hhmm("HHmm"),
    hhmm_("HH:mm"),
    mmss("mm:ss"),
    ms("m:s"),
    ahm("a h시 m분"),
    HH("HH"),
    mm2("mm"),
    yyyymmdd_hhmmss("yyyyMMdd-HHmmss");


    var sdf: SimpleDateFormat

    init {
        sdf = SimpleDateFormat(format, locale)
    }

    fun format(date: Date?): String {
        return if (date == null) "" else sdf.format(date)
    }

    fun format(milliseconds: Long): String {
        return format(Date(milliseconds))
    }

    fun now(): String {
        return format(System.currentTimeMillis())
    }

    @Throws(ParseException::class)
    fun parse(date: String): Long {
        return sdf.parse(date).time
    }

    fun opt_parse(date: String): Long {
        try {
            return parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
            return 0L
        }

    }

    fun opt_parse(date: String, default_date: Long): Long {
        try {
            return sdf.parse(date).time
        } catch (e: ParseException) {
            e.printStackTrace()
            return default_date
        }
    }
}