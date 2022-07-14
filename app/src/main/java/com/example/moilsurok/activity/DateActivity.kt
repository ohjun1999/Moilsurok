package com.example.moilsurok.activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moilsurok.*
import com.example.moilsurok.dataClass.ScheduleDataClass
import com.example.moilsurok.databinding.*
import com.google.firebase.firestore.FirebaseFirestore
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import com.example.moilsurok.fragment.Event
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*


class DateActivity : AppCompatActivity() {
    private val eventsAdapter = com.example.moilsurok.fragment.Example3EventsAdapter {
        AlertDialog.Builder(this)
            .setMessage(R.string.example_3_dialog_delete_confirmation)
            .setNegativeButton(R.string.close, null)
            .show()
    }

//    private val inputDialog by lazy {
//        val editText = AppCompatEditText(this)
//        val layout = FrameLayout(this).apply {
//            // Setting the padding on the EditText only pads the input area
//            // not the entire EditText so we wrap it in a FrameLayout.
//            val padding = dpToPx(20, this@DateActivity)
//            setPadding(padding, padding, padding, padding)
//            addView(editText, FrameLayout.LayoutParams(
//                ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT
//            ))
//        }
//        AlertDialog.Builder(this)
//            .setTitle(getString(R.string.example_3_input_dialog_title))
//            .setView(layout)
//            .setPositiveButton(R.string.save) { _, _ ->
//                saveEvent(editText.text.toString())
//                // Prepare EditText for reuse.
//                editText.setText("")
//            }
//            .setNegativeButton(R.string.close, null)
//            .create()
//            .apply {
//                setOnShowListener {
//                    // Show the keyboard
//                    editText.requestFocus()
//                    context.inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
//                }
//                setOnDismissListener {
//                    // Hide the keyboard
//                    context.inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
//                }
//            }
//    }


    private lateinit var binding: ActivityDateBinding
//    var firestore: FirebaseFirestore? = null


    private var selectedDate: LocalDate? = null
    private val today = LocalDate.now()

    private val titleSameYearFormatter = DateTimeFormatter.ofPattern("MM")
    private val titleFormatter = DateTimeFormatter.ofPattern("yyyy")
    private val selectionFormatter = DateTimeFormatter.ofPattern("MM월의 일정")
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

        class MonthViewContainer(view: View) : ViewContainer(view) {
            val legendLayout = Example3CalendarHeaderBinding.bind(view).legendLayout.root
        }
        binding.exThreeCalendar.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            override fun create(view: View) = MonthViewContainer(view)
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                // Setup each header day text if we have not done that already.
                if (container.legendLayout.tag == null) {
                    container.legendLayout.tag = month.yearMonth
                    container.legendLayout.children.map { it as TextView }
                        .forEachIndexed { index, tv ->
                            tv.text = daysOfWeek[index].name.first().toString()
                            tv.setTextColorRes(R.color.example_3_black)
                        }
                }
            }
        }
        binding.exThreeRv.adapter = ScheduleAdapter()
        binding.exThreeRv.layoutManager = LinearLayoutManager(this)

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

    private fun saveEvent(text: String) {
        if (text.isBlank()) {
            Toast.makeText(this, R.string.example_3_empty_input_text, Toast.LENGTH_LONG).show()
        } else {
            selectedDate?.let {
                events[it] =
                    events[it].orEmpty().plus(Event(UUID.randomUUID().toString(), text, it))
                updateAdapterForDate(it)
            }
        }
    }

    private fun deleteEvent(event: Event) {
        val date = event.date
        events[date] = events[date].orEmpty().minus(event)
        updateAdapterForDate(date)
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

    }


    inner class ScheduleAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        val db = Firebase.firestore
        var deSchedule: ArrayList<ScheduleDataClass> = arrayListOf()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM")
        val formatted = today.format(formatter).toString()

        val first =
            db
                .collection("teams")
                .document("FxRFio9hTwGqAsU5AIZd")
                .collection("Schedule")
                .whereGreaterThanOrEqualTo("date",formatted)
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
                intent.putExtra("title",schedule.title)
                intent.putExtra("date", schedule.date!!.format("yyyy-MM-dd-hh-mm"))
                intent.putExtra("content",schedule.content)
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
    fun comeDate(date: LocalDate){

    }



}