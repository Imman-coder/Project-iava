package com.imman.iava.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imman.iava.R;

import java.util.ArrayList;

public class SubjectClassAdapter extends RecyclerView.Adapter<SubjectClassAdapter.ViewHolder> {
    ArrayList<SubjectModel> weekSubjectModel;

    public SubjectClassAdapter(ArrayList<SubjectModel> subjectModel){
        this.weekSubjectModel = subjectModel;
    }

    @NonNull
    @Override
    public SubjectClassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View class_card ;
        switch (viewType){
            case 0 : class_card = layoutInflater.inflate(R.layout.row_item_card_subject,parent,false);
                        break;
            case 1 : class_card = layoutInflater.inflate(R.layout.row_item_card_lab,parent,false);
                break;
            case 2 : class_card = layoutInflater.inflate(R.layout.row_item_card_notice,parent,false);
                break;
            default : class_card = layoutInflater.inflate(R.layout.row_item_card_time_divider,parent,false);
                break;
        }
        ViewHolder viewHolder = new ViewHolder(class_card);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectClassAdapter.ViewHolder holder, int position) {
        SubjectModel now = weekSubjectModel.get(position);
        switch (getItemViewType(position)){
            case 0 : setSubject(holder,now.time,"Class "+now.class_number,now.subject+"("+now.teacher+")");
                    break;
            case 1 : setLab(holder,now.time,"Class "+now.class_number, now.subject+"("+now.teacher+")", now.subject2+"("+now.teacher2+")");
                    break;
            case 2 : setNotice(holder,now.time,now.notice);
                    break;
            default : setTimeDivider(holder,now.timeDividerText);
                    break;
        }
    }

    @Override
    public int getItemCount() {
        return weekSubjectModel.size();
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        SubjectModel now = weekSubjectModel.get(position);
        if(now.isClass)
            return 0;
        if(now.isLab)
            return 1;
        if(now.isNotice)
            return 2;
        else
            return 3;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject_time,subject_class,subject_subject,notice_time,notice_notice,lab_time,lab_class,lab_sub1,lab_sub2,time_divider_text;
        public ViewHolder(View itemView) {
            super(itemView);
            subject_time = itemView.findViewById(R.id.card_subject_time);
            subject_class = itemView.findViewById(R.id.card_subject_class);
            subject_subject = itemView.findViewById(R.id.card_sunject_subname);
            notice_time = itemView.findViewById(R.id.card_notice_time);
            notice_notice = itemView.findViewById(R.id.card_notice_notice);
            lab_time = itemView.findViewById(R.id.card_lab_time);
            lab_class = itemView.findViewById(R.id.card_lab_class);
            lab_sub1 = itemView.findViewById(R.id.card_lab_lab1);
            lab_sub2 = itemView.findViewById(R.id.card_lab_lab2);
            time_divider_text = itemView.findViewById(R.id.card_time_divider_text);
        }
    }

    void setSubject(@NonNull SubjectClassAdapter.ViewHolder holder, String time, String Class, String subject){
        //Setting values
        holder.subject_time.setText(time);
        holder.subject_class.setText(Class);
        holder.subject_subject.setText(subject);
    }
    void setLab(@NonNull SubjectClassAdapter.ViewHolder holder, String Time, String Class, String Subject1, String Subject2){
        //Settng values
        holder.lab_time.setText(Time);
        holder.lab_class.setText(Class);
        holder.lab_sub1.setText(Subject1);
        holder.lab_sub2.setText(Subject2);

    }
    void setNotice(@NonNull SubjectClassAdapter.ViewHolder holder, String Time, String Notice){
        //Setting values
        holder.notice_time.setText(Time);
        holder.notice_notice.setText(Notice);
    }
    void setTimeDivider(@NonNull SubjectClassAdapter.ViewHolder holder, String text){
        //Setting values
        holder.time_divider_text.setText(text);
    }

}
