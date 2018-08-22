package android.util

import android.annotation.SuppressLint
import android.os.SystemClock
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
object DT {
    val DAY1 = 86400000L
    val HOUR1 = 3600000L
    val MINUTE1 = 60000L

    //달력표시용 날짜계산
    val MAX_DISPLAY_DAY = 42

    val timeZoneOffset: String
        get() {
            val offset = TimeZone.getDefault().rawOffset
            return String.format(Locale.getDefault(), "%+03d%02d", offset / 3600000, offset % 3600000)
        }

    fun today(): Long {
        return ST(System.currentTimeMillis())
    }

    fun elapsed_yyyymmddhhmmsssss_(elapsedRealtime: Long): String {
        return SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US).format(System.currentTimeMillis() + elapsedRealtime - SystemClock.elapsedRealtime())
    }

    /**
     * strip 10minute
     */
    fun S10M(milliseconds: Long): Long {
        return milliseconds / (10L * 60L * 1000L) * (10L * 60L * 1000L)
    }

    /**
     * strip time
     */
    fun ST(milliseconds: Long) = stripTime(milliseconds)

    fun stripTime(milliseconds: Long): Long {
        //        return ((milliseconds + TimeZone.getDefault().rawOffset) / DAY1 * DAY1) - TimeZone.getDefault().rawOffset;
        val c = Calendar.getInstance()
        c.timeInMillis = milliseconds
        c.set(Calendar.HOUR_OF_DAY, 0)
        c.set(Calendar.MINUTE, 0)
        c.set(Calendar.SECOND, 0)
        c.set(Calendar.MILLISECOND, 0)
        return c.timeInMillis
    }

    fun getTime(milliseconds: Long): Long = (milliseconds + TimeZone.getDefault().rawOffset) % DAY1
    /**
     * strip day
     */
    fun SD(milliseconds: Long) = stripDay(milliseconds)

    fun stripDay(milliseconds: Long): Long {
        var milliseconds = milliseconds
        milliseconds = stripTime(milliseconds)
        val c = Calendar.getInstance()
        c.timeInMillis = milliseconds
        c.set(Calendar.DATE, c.getActualMinimum(Calendar.MONTH))
        return c.timeInMillis
    }

    fun move(milliseconds: Long, field: Int, distance: Int): Long {
        val c = Calendar.getInstance()
        c.timeInMillis = milliseconds
        c.add(field, distance)
        return c.timeInMillis
    }

    /**
     * elapsed 시간을 일용한 주기(period) + seed 시간
     *
     * @return
     */
    fun next(period: Long, seed: Long): Long {
        val current = System.currentTimeMillis()
        val elapsed = SystemClock.elapsedRealtime()
        val offset = TimeZone.getDefault().rawOffset.toLong()
        return elapsed + (period - (current + offset) % period + seed) % period
    }

    @Throws(ParseException::class)
    fun parse(date: String, from: SimpleDateFormat): Long {
        return from.parse(date).time
    }

    fun opt_parse(date: String?, from: SimpleDateFormat): Long {
        try {
            return from.parse(date).time
        } catch (e: ParseException) {
            return 0L
        }

    }

    fun format(milliseconds: Long, to: SimpleDateFormat): String {
        return to.format(Date(milliseconds))
    }

    fun format(date: String, from: SimpleDateFormat, to: SimpleDateFormat): String {
        return format(parse(date, from), to)
    }

    fun opt_format(date: String?, from: SimpleDateFormat, to: SimpleDateFormat): String {
        return format(opt_parse(date, from), to)
    }

    /**
     * 2분전, 1시간전, 3일전 표시
     */
    fun format_ago(datetime: Long, format_adayago: String, format_today: String): String {
        val now = System.currentTimeMillis()
        return if (ST(datetime) == ST(now)) SimpleDateFormat(format_today, Locale.US).format(Date(now - datetime - TimeZone.getDefault().rawOffset.toLong())) else format(datetime, SimpleDateFormat(format_adayago, Locale.US))
    }

    fun format_aday(datetime: Long, format_adayago: String, format_today: String): String {
        return format_aday(datetime, SimpleDateFormat(format_adayago, Locale.US), SimpleDateFormat(format_today, Locale.US))
    }

    fun format_aday(datetime: Long, format_adayago: SimpleDateFormat, format_today: SimpleDateFormat): String {
        return if (ST(datetime) == ST(System.currentTimeMillis())) format(datetime, format_today) else format(datetime, format_adayago)
    }

    // 달의 몇번째 주?
    fun weekofmonth(milliseconds: Long): Int {
        val c = Calendar.getInstance()
        c.timeInMillis = milliseconds
        return c.get(Calendar.DAY_OF_WEEK_IN_MONTH)
    }

    fun getStartEndDaysForCalendar(millis: Long, start_end: LongArray) {
        val day_current = DT.stripTime(millis)
        val cal = Calendar.getInstance()
        cal.timeInMillis = day_current
        cal.set(Calendar.DATE, 1)
        val day_of_week = cal.get(Calendar.DAY_OF_WEEK)
        val day_of_week_gap = Calendar.SUNDAY - day_of_week
        cal.add(Calendar.DATE, day_of_week_gap)
        //        System.out.println(cal.getTime().toString());
        val day_start = cal.timeInMillis
        cal.add(Calendar.DATE, MAX_DISPLAY_DAY)
        val day_end = cal.timeInMillis
        //        System.out.println(cal.getTime().toString());

        start_end[0] = day_start
        start_end[1] = day_end
    }

    fun getDayOfWeek(milliseconds: Long): Int {
        val cal = Calendar.getInstance()
        cal.timeInMillis = milliseconds
        return cal.get(Calendar.DAY_OF_WEEK)
    }


    /**
     * <pre>
     * public static final long DAY1 = 86400000L;
     * public static long stripTime(long milliseconds) {
     * return ((milliseconds + TimeZone.getDefault().getRawOffset()) / DAY1 * DAY1) - TimeZone.getDefault().getRawOffset();
     * }
    </pre> *
     */
    fun distance(milliseconds_first: Long, milliseconds_end: Long, field: Int): Int {
        val sd = stripTime(milliseconds_first)
        val ed = stripTime(milliseconds_end)

        if (ed < sd)
            throw IllegalArgumentException("must milliseconds_first < milliseconds_end $sd,$ed")

        if (!(field == Calendar.YEAR || field == Calendar.MONTH || field == Calendar.DATE || field == Calendar.DAY_OF_WEEK))
            throw IllegalArgumentException("filed must in Calendar.YEAR, Calendar.MONTH or Calendar.DATE")

        val firstDayOfWeek = Calendar.getInstance().firstDayOfWeek
        val start = Calendar.getInstance()
        val end = Calendar.getInstance()
        start.timeInMillis = sd
        end.timeInMillis = ed
        val sy = start.get(Calendar.YEAR)
        val ey = end.get(Calendar.YEAR)
        val sm = start.get(Calendar.MONTH)
        val em = end.get(Calendar.MONTH)

        start.set(Calendar.DAY_OF_WEEK, firstDayOfWeek)
        val sw = start.timeInMillis
        end.set(Calendar.DAY_OF_WEEK, firstDayOfWeek)
        val ew = end.timeInMillis

        when (field) {
            Calendar.YEAR -> return ey - sy
            Calendar.MONTH -> return (ey - sy) * 12 + (em - sm)
            Calendar.DAY_OF_WEEK -> return ((ew - sw) / (DAY1 * Calendar.DAY_OF_WEEK)).toInt()
            Calendar.DATE -> return ((ed - sd) / DAY1).toInt()
            else -> {
            }
        }
        return -1
    }

    fun equalMonth(l: Long, r: Long): Boolean {
        return stripDay(l) == stripDay(r)
    }

    fun equalDay(l: Long, r: Long): Boolean {
        return stripTime(l) == stripTime(r)
    }
}
