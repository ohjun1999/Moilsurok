package com.example.moilsurok.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.*
import com.example.moilsurok.dataClass.EventDataClass
import com.example.moilsurok.dataClass.ScheduleDataClass
import com.example.moilsurok.databinding.*
import com.example.moilsurok.fragment.Event
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

data class Event(val date: LocalDate)

class Example3EventsAdapter(val onClick: (Event) -> Unit) :
    RecyclerView.Adapter<Example3EventsAdapter.Example3EventsViewHolder>() {
    val db = Firebase.firestore
    private val titleFormatter = DateTimeFormatter.ofPattern("yyyy")
    val events = mutableListOf<Event>()
    val second =
        db
            .collection("teams")
            .document("FxRFio9hTwGqAsU5AIZd")
            .collection("Schedule")
            .whereGreaterThanOrEqualTo("date", titleFormatter.toString())
            .whereLessThanOrEqualTo("date", titleFormatter.toString() + "\uF7FF")


    // firebase data 불러오기
    init {
        second.get().addOnSuccessListener {
            events.clear()
        }.addOnFailureListener {

        }
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Example3EventsViewHolder {
        return Example3EventsViewHolder(
            Example3EventItemViewBinding.inflate(parent.context.layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(viewHolder: Example3EventsViewHolder, position: Int) {
        viewHolder.bind(events[position])
    }

    override fun getItemCount(): Int = events.size

    inner class Example3EventsViewHolder(private val binding: Example3EventItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick(events[bindingAdapterPosition])
            }
        }
        fun bind(event: Event) {

        }

    }
}

class DateActivity : AppCompatActivity() {
    private val eventsAdapter = Example3EventsAdapter {
        AlertDialog.Builder(this)
            .setMessage(R.string.example_3_dialog_delete_confirmation)
            .setNegativeButton(R.string.close, null)
            .show()
    }

    private lateinit var binding: ActivityDateBinding


    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()
    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MM")
    private val titleFormatter = DateTimeFormatter.ofPattern("yyyy")
    private val selectionFormatter = DateTimeFormatter.ofPattern("MM월의 일정")
    private val selectionDateFormatter = DateTimeFormatter.ofPattern("MM월 dd일의 일정")
    private val events = mutableMapOf<LocalDate, List<Event>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // binding class 인스턴스 생성
        binding = ActivityDateBinding.inflate(layoutInflater)
        // binding class의 root를 참조하여 view로
        val view = binding.root
        setContentView(view)

        binding = ActivityDateBinding.bind(view)

        binding.backKey.setOnClickListener {
            finish()
        }

        val daysOfWeek = daysOfWeekFromLocale()
        val currentMonth = YearMonth.now()


        binding.exThreeCalendar.apply {
            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
            scrollToMonth(currentMonth)
        }

        if (savedInstanceState == null) {
            binding.exThreeCalendar.post {
                // Show today's events initially.
                selectDate(today)
            }
        }

        class DayViewContainer(view: View) : ViewContainer(view) {
            lateinit var day: CalendarDay // Will be set when this container is bound.
            val binding = Example3CalendarDayBinding.bind(view)

            init {
                view.setOnClickListener {
                    if (day.owner == DayOwner.THIS_MONTH) {
                        selectDate(day.date)
                    }

                }
            }
        }




        binding.exThreeCalendar.dayBinder = object : DayBinder<DayViewContainer> {
            override fun create(view: View) = DayViewContainer(view)
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.day = day
                val textView = container.binding.exThreeDayText
                val dotView = container.binding.exThreeDotView


                textView.text = day.date.dayOfMonth.toString()
//                itemView.setTextColor(when(position % 7) {
//                    0 -> Color.RED
//                    6 -> Color.BLUE
//                    else -> Color.BLACK
//                })

                if (day.owner == DayOwner.THIS_MONTH) {
                    textView.makeVisible()
                    when (day.date) {
                        today -> {
                            textView.setTextColorRes(R.color.example_3_white)
                            textView.setBackgroundResource(R.drawable.example_3_today_bg)
                            dotView.makeInVisible()
                        }
                        selectedDate -> {
                            textView.setTextColorRes(R.color.example_3_blue)
                            textView.setBackgroundResource(R.drawable.example_3_selected_bg)
                            dotView.makeInVisible()
                            binding.selectDateRecyclerView.adapter = selectDateScheduleAdapter()
                            binding.selectDateRecyclerView.layoutManager =
                                LinearLayoutManager(this@DateActivity)

                        }

                        else -> {
                            textView.setTextColorRes(R.color.example_3_black)
                            textView.background = null
                            dotView.isVisible = events[day.date].orEmpty().isNotEmpty()
                        }
                    }
                } else {
                    textView.makeInVisible()
                    dotView.makeInVisible()
                }
            }
        }

        binding.exThreeCalendar.monthScrollListener = {
//            homeActivityToolbar.title = if (it.year == today.year) {
//                titleSameYearFormatter.format(it.yearMonth)
//            } else {
//                titleFormatter.format(it.yearMonth)
//            }

            // Select the first day of the month when
            // we scroll to a new month.
            selectDate(it.yearMonth.atDay(1))
        }

        binding.exThreeRv.adapter = ScheduleAdapter()
        binding.exThreeRv.layoutManager = LinearLayoutManager(this)
        binding.selectDateRecyclerView.adapter = selectDateScheduleAdapter()
        binding.selectDateRecyclerView.layoutManager = LinearLayoutManager(this)

//        binding.exThreeAddButton.setOnClickListener {
//            inputDialog.show()
//        }


    }

    private fun selectDate(date: LocalDate) {
        if (selectedDate != date) {
            val oldDate = selectedDate
            selectedDate = date
            oldDate?.let { binding.exThreeCalendar.notifyDateChanged(it) }
            binding.exThreeCalendar.notifyDateChanged(date)
            updateAdapterForDate(date)
        }
    }


    private fun updateAdapterForDate(date: LocalDate) {
        eventsAdapter.apply {
            events.clear()
//            events.addAll(this.events[date].orEmpty())
            notifyDataSetChanged()
        }
        binding.exThreeSelectedDateText.text = selectionFormatter.format(date)
        binding.exOneYearText.text = titleFormatter.format(date)
        binding.exOneMonthText.text = titleSameYearFormatter.format(date)
        binding.selectedDate.text = selectionDateFormatter.format(date)

    }


    inner class ScheduleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val db = Firebase.firestore
        var deSchedule: ArrayList<ScheduleDataClass> = arrayListOf()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM")
        val formatted = selectedDate.toString().format(formatter)

        private fun selectDate(date: LocalDate) {
            if (selectedDate != date) {
                val oldDate = selectedDate
                selectedDate = date
                oldDate?.let { binding.exThreeCalendar.notifyDateChanged(it) }
                binding.exThreeCalendar.notifyDateChanged(date)
                updateAdapterForDate(date)
            }
        }

        private fun updateAdapterForDate(date: LocalDate) {
            eventsAdapter.apply {
                events.clear()
                notifyDataSetChanged()
            }
            binding.exThreeSelectedDateText.text = selectionFormatter.format(date)
            binding.exOneYearText.text = titleFormatter.format(date)
            binding.exOneMonthText.text = titleSameYearFormatter.format(date)
            binding.selectedDate.text = selectionDateFormatter.format(date)

        }


        val first =
            db
                .collection("teams")
                .document("FxRFio9hTwGqAsU5AIZd")
                .collection("Schedule")
                .whereGreaterThanOrEqualTo("date", formatted)
                .whereLessThanOrEqualTo("date", formatted + "\uF7FF")


        // firebase data 불러오기
        init {

            first

                .addSnapshotListener { querySnapshot, _ ->
                    // ArrayList 비워줌

                    deSchedule.clear()

                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(ScheduleDataClass::class.java)
                        deSchedule.add(item!!)
                        Log.d("test", formatted)
                    }
                    notifyDataSetChanged()

                }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.example_3_event_item_view, parent, false)

            return ViewHolder(view)
        }

        inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView
            val schedule: ScheduleDataClass = deSchedule[position]
            holder.title.text = schedule.title



            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, ScheduleDetailActivity::class.java)
                intent.putExtra("title", schedule.title)
                intent.putExtra("date", schedule.date!!.format("yyyy-MM-dd-hh-mm"))
                intent.putExtra("content", schedule.content)
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }


        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return deSchedule.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.daTitle)
//


        }


    }

    inner class selectDateScheduleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val db = Firebase.firestore
        var deSchedule: ArrayList<ScheduleDataClass> = arrayListOf()
        val sFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val sFormatted = selectedDate.toString().format(sFormatter)

        val first =
            db
                .collection("teams")
                .document("FxRFio9hTwGqAsU5AIZd")
                .collection("Schedule")
                .whereGreaterThanOrEqualTo("date", sFormatted)
                .whereLessThanOrEqualTo("date", sFormatted + "\uF7FF")


        // firebase data 불러오기
        init {

            first

                .addSnapshotListener { querySnapshot, _ ->
                    // ArrayList 비워줌

                    deSchedule.clear()

                    for (snapshot in querySnapshot!!.documents) {
                        var item = snapshot.toObject(ScheduleDataClass::class.java)
                        deSchedule.add(item!!)
                        Log.d("test", sFormatted)
                    }
                    notifyDataSetChanged()

                }


        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            var view =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.example_3_event_item_view, parent, false)


            return ViewHolder(view)
        }

        inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            var viewHolder = (holder as ViewHolder).itemView
            val schedule: ScheduleDataClass = deSchedule[position]
            holder.title.text = schedule.title


            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context, ScheduleDetailActivity::class.java)
                intent.putExtra("title", schedule.title)
                intent.putExtra("date", schedule.date!!.format("yyyy-MM-dd-hh-mm"))
                intent.putExtra("content", schedule.content)
                ContextCompat.startActivity(holder.itemView.context, intent, null)
            }


        }

        // 리사이클러뷰의 아이템 총 개수 반환
        override fun getItemCount(): Int {
            return deSchedule.size
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val title: TextView = itemView.findViewById(R.id.daTitle)
//


        }


    }

    inner class EventsAdapter(val onClick: (EventDataClass) -> Unit) :
        RecyclerView.Adapter<EventsAdapter.EventsViewHolder>() {

        val events = mutableListOf<EventDataClass>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
            return EventsViewHolder(
                Example3EventItemViewBinding.inflate(parent.context.layoutInflater, parent, false)
            )
        }

        override fun onBindViewHolder(viewHolder: EventsViewHolder, position: Int) {
            viewHolder.bind(events[position])
        }

        override fun getItemCount(): Int = events.size

        inner class EventsViewHolder(private val binding: Example3EventItemViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            init {
                itemView.setOnClickListener {
                    onClick(events[bindingAdapterPosition])
                }
            }

            fun bind(event: EventDataClass) {

            }
        }
    }


}