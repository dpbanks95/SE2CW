package uk.ac.uea.activityprogram;

import android.content.Context;
import android.support.design.internal.ParcelableSparseArray;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Danny on 20/11/2016.
 */

public class ActivityListAdapter extends BaseAdapter {
    View.OnClickListener snackbarListener;
    private Context mContext;
    private List<ActivityModel> mActivityList;
//    private View v;
//    private TextView titleTxt;
//    private TextView locationTxt;
//    private TextView timeTxt;
//    private CheckBox reminder;

    public ActivityListAdapter(Context mContext, List<ActivityModel> mActivityList) {
        this.mContext = mContext;
        this.mActivityList = mActivityList;
    }

    @Override
    public int getCount() {
        return this.mActivityList.size();
    }

    @Override
    public Object getItem(int position) {
        return mActivityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.item_activity_list, null);
        }
        RelativeLayout layout = (RelativeLayout)parent;
        View v = View.inflate(mContext, R.layout.item_activity_list, null);
        TextView titleTxt = (TextView)v.findViewById(R.id.titleTxt);
        TextView locationTxt = (TextView)v.findViewById(R.id.roomNumTxt);
        TextView timeTxt = (TextView)v.findViewById(R.id.timeTxt);
        CheckBox reminder = (CheckBox)v.findViewById(R.id.remindChk);

        titleTxt.setText(mActivityList.get(position).getTitle());
        locationTxt.setText(mActivityList.get(position).getRoomNumber());
        timeTxt.setText(mActivityList.get(position).getTime());
        reminder.setChecked(false);

        reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mActivityList.get(position).setSaved(isChecked);
            }
        });
        if(mActivityList.get(position).isSaved()){

            String set = "Reminder set for " + mActivityList.get(position).getTitle();
            Snackbar sb = Snackbar.make(layout, set, Snackbar.LENGTH_LONG);
//                           .setAction("Undo", snackbarListener);
            reminder.setChecked(true);
            sb.show();
        }
        else {
            String unset = "Reminder removed for " + mActivityList.get(position).getTitle();
            Snackbar sb = Snackbar.make(layout, unset, Snackbar.LENGTH_LONG);
//                    .setAction("Undo", snackbarListener);
            reminder.setChecked(false);
            sb.show();
        }

//        snackbarListener  = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                CheckBox reminder = (CheckBox)v.findViewById(R.id.remindChk);
//                reminder.setChecked(false);
//            }
//        };

        v.setTag(mActivityList.get(position).getId());

        return v;
    }
}
