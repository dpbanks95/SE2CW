package uk.ac.uea.activityprogram;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Rixh_ on 27/11/2016.
 */

public class ActivityRecyclerAdapter extends RecyclerView.Adapter<ActivityRecyclerAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<ActivityModel> activitiesList;

    public ActivityRecyclerAdapter(Context context, ArrayList<ActivityModel> activitiesList) {
        this.context = context;
        this.activitiesList = activitiesList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView roomNumber;
        public TextView time;
        public CheckBox remind;

        public ViewHolder(View v){
            super(v);
            title = (TextView) v.findViewById(R.id.titleTxt);
            roomNumber = (TextView) v.findViewById(R.id.roomNumTxt);
            time = (TextView) v.findViewById(R.id.timeTxt);
            remind = (CheckBox) v.findViewById(R.id.remindChk);
        }
    }

    @Override
    public ActivityRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_activity_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ActivityModel act = activitiesList.get(position);

        TextView title = holder.title;
        TextView roomNumber = holder.roomNumber;
        TextView time = holder.time;
        final CheckBox remind = holder.remind;

        title.setText(act.getTitle());
        roomNumber.setText(act.getRoomNumber());
        time.setText(act.getTime());
        remind.setChecked(act.isSaved());

        remind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String checkTxt = "Reminder set for " + act.getTitle();
                String uncheckTxt = "Reminder removed for " + act.getTitle();
                Snackbar check = Snackbar.make(v, checkTxt, Snackbar.LENGTH_LONG);
                Snackbar uncheck = Snackbar.make(v, uncheckTxt, Snackbar.LENGTH_LONG);

                act.setSaved(remind.isChecked());
                if(act.isSaved()){
                    check.show();
                }
                else{
                    uncheck.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return activitiesList.size();
    }


}
