package ukr.net.jaroshov.calendarview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CalendarDayAdapter extends RecyclerView.Adapter<CalendarDayAdapter.MyViewHolder> {
    private ArrayList<CalendarDayModel> list;
    private Context mCtx;
    private CalendarDayView.OnCalendarListener onCalendarListener;

    private CalendarDayModel selectDayModel = new CalendarDayModel(0);

    public void setOnCalendarListener(CalendarDayView.OnCalendarListener onCalendarListener) {
        this.onCalendarListener = onCalendarListener;
    }

    public CalendarDayAdapter(ArrayList<CalendarDayModel> list, Context mCtx) {
        this.list = list;
        this.mCtx = mCtx;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, month, day;
        LinearLayout parent;
        RelativeLayout parentDay;
        View dayBorder;
        View dayTop;

        public MyViewHolder(View view) {
            super(view);
            date = view.findViewById(R.id.dayTime);
//            month = view.findViewById(R.id.month);
            parent = view.findViewById(R.id.parent);
            day = view.findViewById(R.id.dayNote);

            parentDay = view.findViewById(R.id.parentDay);
            dayBorder = view.findViewById(R.id.day_border);
            dayTop = view.findViewById(R.id.day_top);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_day_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final CalendarDayModel model = list.get(position);

        Display display = ((Activity) mCtx).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        holder.parent.setMinimumWidth(Math.round(width / 7));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        final SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");

        holder.date.setText(sdf.format(model.getTimeinmilli()).split(" ")[0]);
        holder.day.setText(model.getText());
        holder.dayTop.setVisibility(View.INVISIBLE);

        if (model.getStatus() == 0) {
            holder.date.setTextColor(mCtx.getColor(R.color.grey_600));
            holder.day.setTextColor(mCtx.getColor(R.color.grey_600));
            holder.parent.setBackgroundColor(Color.WHITE);
            holder.parentDay.setBackgroundResource(R.color.grey);
            holder.dayBorder.setBackgroundResource(R.color.black2);
        } else if (model.getStatus() == 2) {
            holder.date.setTextColor(mCtx.getColor(R.color.textColorLight2));
            holder.day.setTextColor(mCtx.getColor(R.color.textColorLight2));
            if (model.isCurrentDate()) {
                holder.parent.setBackgroundResource(R.drawable.color_status_2);
                holder.parentDay.setBackgroundResource(R.drawable.color_status_1);
                holder.dayBorder.setBackgroundResource(R.drawable.color_status_2);
            } else {
                holder.parent.setBackgroundResource(R.drawable.color_status_2);
                holder.parentDay.setBackgroundResource(R.drawable.color_status_2);
                holder.dayBorder.setBackgroundResource(R.drawable.color_status_2);
                if (!model.isStart()) {
                    holder.date.setText("");
                } else {
                    holder.dayTop.setVisibility(View.VISIBLE);
                    holder.dayTop.setBackgroundResource(R.color.black2);
                }
            }
        } else {
            holder.date.setTextColor(mCtx.getColor(R.color.textColorLight));
            holder.day.setTextColor(mCtx.getColor(R.color.textColorLight));
            holder.parent.setBackgroundResource(R.drawable.color_status_1);
            holder.parentDay.setBackgroundResource(R.drawable.color_status_1);
            holder.dayBorder.setBackgroundResource(R.color.black2);
        }

        if (model == selectDayModel) {
            holder.parent.setBackgroundResource(R.drawable.color_select);
            holder.parentDay.setBackgroundResource(R.drawable.color_select);
        }

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectDayModel == model) {
                    selectDayModel = new CalendarDayModel(0);
                } else {
                    selectDayModel = model;
                    DayItem dayItem = model.getDayItem();
                    if (dayItem.date == null) {
                        dayItem.date = new Date(model.timeinmilli);
                    }
                    onCalendarListener.onDateSelected(model);
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}