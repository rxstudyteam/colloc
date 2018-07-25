package com.karrel.colloc

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.karrel.colloc.frame.*
import kotlinx.android.synthetic.main.fragment_mesemese_activity.view.*


/**
 * A placeholder fragment containing a simple view.
 */
class MeseMeseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_mesemese_activity, container, false)
        rootView.frame_list.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        val frames = mutableListOf<Frame>()
        frames.add(LabelFrame("일별 예보(좋음,보통,나쁨,매우나쁨)"))
        frames.add(DailyItemFrame("수요일 아침",R.drawable.emoticon_four_level_small_2,"보통"))
        frames.add(DailyItemFrame("수요일 점심",R.drawable.emoticon_four_level_small_1,"좋음"))
        frames.add(DailyItemFrame("수요일 저녁",R.drawable.emoticon_four_level_small_4,"매우나쁨"))
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(DailyItemFrame("목요일 아침",R.drawable.emoticon_four_level_small_3,"나쁨"))
        frames.add(LabelFrame("세부사항"))
        frames.add(DetailItemFrame("업데이트 시간","2018-07-24 21:00"))
        frames.add(DetailItemFrame("PM10 측정소 이름 ","영통동"))
        frames.add(DetailItemFrame("PM2.5 측정소 이름 ","영통동"))
        frames.add(DetailItemFrame("NO2 측정소 이름 ","영통동"))
        frames.add(DetailItemFrame("O3 측정소 이름 ","영통동"))
        frames.add(DetailItemFrame("CO 측정소 이름 ","영통동"))
        frames.add(DetailItemFrame("SO2 측정소 이름 ","영통동"))
        frames.add(DetailItemFrame("pm10 측정망 분류 ","도시대기"))
        frames.add(DetailItemFrame("pm2.5 측정망 분류 ","도시대기"))
        frames.add(DetailItemFrame("통합지수 값","97 unit(최근 24시간 평균)"))
        frames.add(DetailItemFrame("통합지수 상태","보통(최근 24시간 평균)"))
        frames.add(HorizontalLine)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(InfoFrame("미세미세는 사용자분과 가장 가까이 위치한. 정상 작동 중인 측정소의 실시간 정보를 보여드립니다. "))
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(InfoFrame("본 자료는 한국 환경공단(에어코리아)와 기상청에서 제공하는 실시간 관측 자료이며 실제 대기농도 수치와 다를 수 있습니다."))
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(EmptyFrame)
        frames.add(HorizontalLine)
        Log.d("dlwlrma","!!" )
        rootView.frame_list.adapter = FrameAdapter(frames)
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_DATA_KEY = "DATA_KEY"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): MeseMeseFragment {
            val fragment = MeseMeseFragment()
            val args = Bundle()
            args.putInt(ARG_DATA_KEY, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}