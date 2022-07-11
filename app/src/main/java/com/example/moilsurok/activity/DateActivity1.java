//package com.example.moilsurok.activity;
//
//import static com.example.moilsurok.adapter.CalendarUtil.selectedDate;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.ImageButton;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.GridLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.moilsurok.R;
//import com.example.moilsurok.adapter.CalendarAdapter;
//import com.example.moilsurok.adapter.CalendarUtil;
//
//import java.time.LocalDate;
//import java.time.YearMonth;
//import java.time.format.DateTimeFormatter;
//import java.util.ArrayList;
//import java.util.Calendar;
//
//public class DateActivity1 extends AppCompatActivity {
//
//    TextView monthYearText; //년월 텍스트뷰
//    LocalDate selectedDate; //년월 변수
//    ImageButton backKey;
//
//    RecyclerView weekRecyclerView;
//    RecyclerView recyclerView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_date);
//
//        //초기화
//        monthYearText = findViewById(R.id.monthYearText);
//        recyclerView = findViewById(R.id.dateRecyclerView);
//        backKey = findViewById(R.id.backKey);
//        weekRecyclerView = findViewById(R.id.dateWeekRecyclerView);
//
//        //현재 날짜
//        selectedDate = LocalDate.now();
//
//        //화면 설정 (월간 달력)
//        setMonthView();
//        //주간 달력
//        setWeekView();
//
//        backKey.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }//onCreate
//
//    //날짜 타입 설정
//    private String monthYearFromDate(LocalDate date) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월");
//
//        return date.format(formatter);
//    }
//
//    //화면설정
//    private void setMonthView() {
//
//        //년월 텍스트뷰 세팅
//        monthYearText.setText(monthYearFromDate(selectedDate));
//
//        //해당 월 날짜 가져오기
//        ArrayList<LocalDate> dayList = daysInMonthArray(selectedDate);
//
//        //어댑터 데이터 적용
//        CalendarAdapter adapter = new CalendarAdapter(dayList);
//
//        //레이아웃 설정( 열 7개)
//        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);
//
//        //레이아웃 적용
//        recyclerView.setLayoutManager(manager);
//
//        //어댑터 적용
//        recyclerView.setAdapter(adapter);
//
//    }
//
//    //날짜 생성
//    private ArrayList<LocalDate> daysInMonthArray(LocalDate date) {
//        ArrayList<LocalDate> dayList = new ArrayList<>();
//
//        YearMonth yearMonth = YearMonth.from(date);
//
//        //해당월 마지막 날짜 가져오기
//        int lastDay = yearMonth.lengthOfMonth();
//
//        //해당월의 첫번째 날 가져오기
//        LocalDate firstDay = selectedDate.withDayOfMonth(1);
//
//        //첫번째 날 요일 가져오기
//        int dayOfWeek = firstDay.getDayOfWeek().getValue();
//
//        //날짜 생성
//        for (int i = 1; i < 42; i++) {
//            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
//                dayList.add(null);
//            } else {
//                dayList.add(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek));
//            }
//
//        }
//        return dayList;
//    }
//
//    private void setWeekView() {
//
//        //년월 텍스트뷰 세팅
//        monthYearText.setText(monthYearFromDate(selectedDate));
//
//        //해당 월 날짜 가져오기
//        ArrayList<LocalDate> dayList = daysInWeekArray(selectedDate);
//
//        //어댑터 데이터 적용
//        CalendarAdapter adapter = new CalendarAdapter(dayList);
//
//        //레이아웃 설정( 열 7개)
//        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 7);
//
//        //레이아웃 적용
//        weekRecyclerView.setLayoutManager(manager);
//
//        //어댑터 적용
//        weekRecyclerView.setAdapter(adapter);
//
//    }
//
//    private ArrayList<LocalDate> daysInWeekArray(LocalDate date) {
//        ArrayList<LocalDate> dayList = new ArrayList<>();
//
//        YearMonth yearMonth = YearMonth.from(date);
//
//        //해당월 마지막 날짜 가져오기
//        int lastDay = yearMonth.lengthOfMonth();
//
//        //해당월의 첫번째 날 가져오기
//        LocalDate firstDay = selectedDate.withDayOfMonth(1);
//
//        //첫번째 날 요일 가져오기
//        int dayOfWeek = firstDay.getDayOfWeek().getValue();
//
//
//        //날짜 생성
//        for (int i = 1; i < 7; i++) {
//            if (i <= dayOfWeek || i > lastDay + dayOfWeek) {
//                dayList.add(null);
//            } else {
//                dayList.add(LocalDate.of(selectedDate.getYear(), selectedDate.getMonth(), i - dayOfWeek));
//            }
//
//        }
//        return dayList;
//    }
//
//    /**
//     * 날짜 어댑터에서 넘긴 데이터를 받는 메서드
//     * @param dayText 날짜
//     */
//
//}
