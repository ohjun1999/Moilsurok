package com.example.moilsurok.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.moilsurok.R
import com.example.moilsurok.adapter.AdapterMonth
import com.example.moilsurok.databinding.ActivityAssociationInquiryBinding
import com.example.moilsurok.databinding.ActivityDateBinding
import com.example.moilsurok.item.DayViewContainer
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*


class DateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDateBinding
    val currentMonth = YearMonth.now()
    val firstMonth = currentMonth.minusMonths(10)
    val lastMonth = currentMonth.plusMonths(10)
    val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityDateBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        val monthListManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val monthListAdapter = AdapterMonth()

        binding.calendarView.apply {
            layoutManager = monthListManager
            adapter = monthListAdapter
            scrollToPosition(Int.MAX_VALUE/2)
        }
        val snap = PagerSnapHelper()
        snap.attachToRecyclerView(binding.calendarView)


//        binding.calendarView.dayBinder = object : DayBinder<DayViewContainer> {
//            // Called only when a new container is needed.
//            override fun create(view: View) = DayViewContainer(view)
//
//            // Called every time we need to reuse a container.
//            override fun bind(container: DayViewContainer, day: CalendarDay) {
//                container.textView.text = day.date.dayOfMonth.toString()
//
//                if (day.owner == DayOwner.THIS_MONTH) {
//                    container.textView.setTextColor(Color.WHITE)
//                } else {
//                    container.textView.setTextColor(Color.GRAY)
//                }
//
//                binding.calendarView.setup(firstMonth, lastMonth, firstDayOfWeek)
//                binding.calendarView.scrollToMonth(currentMonth)
//            }



//        }
    }

}