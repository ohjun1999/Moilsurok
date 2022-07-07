package com.example.moilsurok.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moilsurok.R;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {
    ArrayList<LocalDate> dayList;

    public CalendarAdapter(ArrayList<LocalDate> dayList) {

        this.dayList = dayList;
    }

    @NonNull
    @Override
    public CalendarAdapter.CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.calendar_cell, parent, false);

        return new CalendarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {

        //날짜 변수에 담기
        LocalDate day = dayList.get(position);

        if(day == null){
            holder.dayText.setText("");
        }else {
            holder.dayText.setText(String.valueOf(day.getDayOfMonth()));

            //현재 날짜 색상 칠하기
            if (day.equals(CalendarUtil.selectedDate)){
                holder.parentView.setBackgroundColor(Color.GREEN);
            }

        }


        //텍스트 색상 지정
        if ((position + 1) % 7 == 0) { // 토요일 파랑

            holder.dayText.setTextColor(Color.BLUE);

        } else if (position == 0 || position % 7 == 0) { //일요일 빨강

            holder.dayText.setTextColor(Color.RED);
        }

        //날짜 클릭 이벤트
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int iYear = day.getYear();//년
                int iMonth = day.getMonthValue();//월
                int iDay = day.getDayOfMonth();//일

                String yearMonDay = iYear + "년" + iMonth + "월" + iDay + "일";

                Toast.makeText(holder.itemView.getContext(), yearMonDay,Toast.LENGTH_SHORT).show();

            }
        });



    }

    @Override
    public int getItemCount() {

        return dayList.size();
    }

    class CalendarViewHolder extends RecyclerView.ViewHolder {
        //초기화
        TextView dayText;

        View parentView;

        public CalendarViewHolder(@NonNull View itemView) {
            super(itemView);

            dayText = itemView.findViewById(R.id.dayText);
            parentView = itemView.findViewById(R.id.parentView);
        }
    }


}
