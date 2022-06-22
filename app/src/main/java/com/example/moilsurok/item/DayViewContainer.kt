package com.example.moilsurok.item

import android.view.View
import android.widget.TextView
import com.example.moilsurok.R
import com.kizitonwose.calendarview.ui.ViewContainer

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.calendarDayText)

    // With ViewBinding
    // val textView = CalendarDayLayoutBinding.bind(view).calendarDayText


}
