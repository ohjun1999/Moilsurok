package com.example.moilsurok.fragment

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.R
import com.example.moilsurok.dataClass.ScheduleDataClass
import com.example.moilsurok.databinding.Example1CalendarDayBinding
import com.example.moilsurok.databinding.Example1FragmentBinding
import com.example.moilsurok.daysOfWeekFromLocale
import com.example.moilsurok.getColorCompat
import com.example.moilsurok.setTextColorRes
import com.google.firebase.firestore.FirebaseFirestore
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.model.InDateStyle
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.yearMonth
import com.example.moilsurok.fragment.HasToolbar
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.*

class Example1Fragment : BaseFragment(R.layout.example_1_fragment), HasToolbar {

    var firestore: FirebaseFirestore? = null
    override val toolbar: Toolbar?
        get() = null

    override val titleRes: Int = R.string.example_1_title

    private lateinit var binding: Example1FragmentBinding

    private val selectedDates = mutableSetOf<LocalDate>()
    private val today = LocalDate.now()
    private val monthTitleFormatter = DateTimeFormatter.ofPattern("MM")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        binding = Example1FragmentBinding.bind(view)
        val daysOfWeek = daysOfWeekFromLocale()
//        binding.legendLayout.root.children.forEachIndexed { index, view ->
//            (view as TextView).apply {
//                text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.KOREAN)
//                setTextColorRes(R.color.example_1_white)
//            }
//        }


        val currentMonth = YearMonth.now()
        val startMonth = currentMonth.minusMonths(10)
        val endMonth = currentMonth.plusMonths(10)
        binding.scheduleRecyclerView.adapter = ScheduleAdapter()
        binding.scheduleRecyclerView.layoutManager =
            LinearLayoutManager(this.context)
        binding.exOneCalendar.setup(startMonth, endMonth, daysOfWeek.first())
        binding.exOneCalendar.scrollToMonth(currentMonth)
        binding.backKey.setOnClickListener {
            activity?.finish()



        }



        class DayViewContainer(view: View) : ViewContainer(view) {
            // Will be set when this container is bound. See the dayBinder.
            lateinit var day: CalendarDay
            val textView = Example1CalendarDayBinding.bind(view).exOneDayText

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        if (selectedDates.contains(day.date)) {
                            selectedDates.remove(day.date)
                        } else {
                            selectedDates.add(day.date)
                        }
                        binding.exOneCalendar.notifyDayChanged(day)
                    }
                }
            }

        }

        binding.exOneCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            val currentDate = LocalDate.now()
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.textView
                textView.text = day.date.dayOfMonth.toString()

//                if (day==){
//
//                } else {
//
//                }

                if (day.owner == DayOwner.THIS_MONTH) {
                    when {
                        selectedDates.contains(day.date) -> {
                            textView.setTextColorRes(R.color.black)
                            textView.setBackgroundResource(R.drawable.example_1_selected_bg)
                        }
                        today == day.date -> {
                            textView.setTextColorRes(R.color.white)
                            textView.setBackgroundResource(R.drawable.example_1_selected_bg)
                        }
                        else -> {
                            textView.setTextColorRes(R.color.black)
                            textView.background = null
                        }
                    }
                } else {
                    textView.setTextColorRes(R.color.backGray)
                    textView.background = null
                }
            }
        }


        binding.exOneCalendar.monthScrollListener = {
            if (binding.exOneCalendar.maxRowCount == 6) {
                binding.exOneYearText.text = it.yearMonth.year.toString()
                binding.exOneMonthText.text = monthTitleFormatter.format(it.yearMonth)
            } else {
                // In week mode, we show the header a bit differently.
                // We show indices with dates from different months since
                // dates overflow and cells in one index can belong to different
                // months/years.
                val firstDate = it.weekDays.first().first().date
                val lastDate = it.weekDays.last().last().date
                if (firstDate.yearMonth == lastDate.yearMonth) {
                    binding.exOneYearText.text = firstDate.yearMonth.year.toString()
                    binding.exOneMonthText.text = monthTitleFormatter.format(firstDate)
                } else {
                    binding.exOneMonthText.text =
                        "${monthTitleFormatter.format(firstDate)} - ${
                            monthTitleFormatter.format(
                                lastDate
                            )
                        }"
                    if (firstDate.year == lastDate.year) {
                        binding.exOneYearText.text = firstDate.yearMonth.year.toString()
                    } else {
                        binding.exOneYearText.text =
                            "${firstDate.yearMonth.year} - ${lastDate.yearMonth.year}"
                    }
                }
            }
        }

        binding.weekModeCheckBox.setOnCheckedChangeListener { _, monthToWeek ->
            val firstDate = binding.exOneCalendar.findFirstVisibleDay()?.date
                ?: return@setOnCheckedChangeListener
            val toDate = LocalDate.now()
            val lastDate = binding.exOneCalendar.findLastVisibleDay()?.date
                ?: return@setOnCheckedChangeListener

            val oneWeekHeight = binding.exOneCalendar.daySize.height
            val oneMonthHeight = oneWeekHeight * 6

            val oldHeight = if (monthToWeek) oneMonthHeight else oneWeekHeight
            val newHeight = if (monthToWeek) oneWeekHeight else oneMonthHeight

            // Animate calendar height changes.
            val animator = ValueAnimator.ofInt(oldHeight, newHeight)
            animator.addUpdateListener { animator ->
                binding.exOneCalendar.updateLayoutParams {
                    var height = animator.animatedValue as Int
                }
            }

            // When changing from month to week mode, we change the calendar's
            // config at the end of the animation(doOnEnd) but when changing
            // from week to month mode, we change the calendar's config at
            // the start of the animation(doOnStart). This is so that the change
            // in height is visible. You can do this whichever way you prefer.

            animator.doOnStart {
                if (!monthToWeek) {
                    binding.exOneCalendar.updateMonthConfiguration(
                        inDateStyle = InDateStyle.ALL_MONTHS,
                        maxRowCount = 6,
                        hasBoundaries = true
                    )
                }
            }
            animator.doOnEnd {
                if (monthToWeek) {
                    binding.exOneCalendar.updateMonthConfiguration(
                        inDateStyle = InDateStyle.FIRST_MONTH,
                        maxRowCount = 1,
                        hasBoundaries = false
                    )
                }

                if (monthToWeek) {
                    // We want the first visible day to remain
                    // visible when we change to week mode.
                    binding.exOneCalendar.scrollToDate(toDate)
                } else {
                    // When changing to month mode, we choose current
                    // month if it is the only one in the current frame.
                    // if we have multiple months in one frame, we prefer
                    // the second one unless it's an outDate in the last index.
                    if (firstDate.yearMonth == lastDate.yearMonth) {
                        binding.exOneCalendar.scrollToMonth(firstDate.yearMonth)
                    } else {
                        // We compare the next with the last month on the calendar so we don't go over.
                        binding.exOneCalendar.scrollToMonth(
                            minOf(
                                firstDate.yearMonth.next,
                                endMonth
                            )
                        )
                    }
                }
            }
            animator.duration = 250
            animator.start()
        }
    }

    inner class ScheduleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        var deSchedule: ArrayList<ScheduleDataClass> = arrayListOf()
        private val first =
            firestore
                ?.collection("teams")
                ?.document("FxRFio9hTwGqAsU5AIZd")
                ?.collection("Schedule")


        // firebase data 불러오기
        init {
            first
                ?.addSnapshotListener { querySnapshot, _ ->

                    deSchedule.clear()
                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(ScheduleDataClass::class.java)
                        deSchedule.add(item!!)
                    }

                    notifyDataSetChanged()
                }

        }


        // xml 파일을 inflate 하여 ViewHolder 를 생성
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_schedule, parent, false)

            return ViewHolder(view)
        }

        inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        }

        // onCreateViewHolder 에서 만든 view 와 실제 데이터를 연결
        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView
            val schedule: ScheduleDataClass = deSchedule[position]
            holder.scheduleTitle.text = schedule.title
            holder.scheduleDate.text = schedule.date

//            holder.itemView.setOnClickListener {
//                val intent = Intent(holder.itemView?.context, NoticeDetailActivity::class.java)
//                intent.putExtra("content", "원하는 데이터를 보냅니다.")
//                intent.putExtra("content", notice.content)
//                intent.putExtra("title", notice.title)
//                intent.putExtra("modifiedDate", notice.modifiedDate)

//                ContextCompat.startActivity(holder.itemView.context, intent, null)

        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return deSchedule.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val scheduleDate: TextView = itemView.findViewById(R.id.scheduleDate)
            val scheduleTitle: TextView = itemView.findViewById(R.id.scheduleTitle)


        }
    }


    override fun onStart() {
        super.onStart()
        requireActivity().window.statusBarColor =
            requireContext().getColorCompat(R.color.white)
    }

    override fun onStop() {
        super.onStop()
        requireActivity().window.statusBarColor =
            requireContext().getColorCompat(R.color.white)
    }


}

