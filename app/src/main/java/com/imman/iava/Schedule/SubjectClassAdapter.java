package com.imman.iava.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.imman.iava.R;

public class SubjectClassAdapter extends RecyclerView.Adapter<SubjectClassAdapter.ViewHolder> {
    String s[];
    public SubjectClassAdapter(String s[]){
        this.s=s;
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
            default : class_card = layoutInflater.inflate(R.layout.row_item_card_notice,parent,false);
                break;
        }
        ViewHolder viewHolder = new ViewHolder(class_card);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubjectClassAdapter.ViewHolder holder, int position) {
        String pos = Integer.toString(position);
        if(position%3==0)
            setSubject(holder,"09:15-10:1"+pos,"Class "+pos,"Subject"+pos);
        if(position%3==1)
            setLab(holder,"09:15-10:1"+pos,"Class "+pos,"Subject1:"+pos,"Subject2:"+pos);
        if(position%3==2)
            setNotice(holder,"09:15-10:1"+pos,"Hello"+pos);
//        if(position%3==2)
//            setNotice(holder,"09:15-10:1"+pos,"Hello"+pos);
    }

    @Override
    public int getItemCount() {
        return s.length;
    }

    @Override
    public int getItemViewType(int position) {
        super.getItemViewType(position);
        return position%3;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView subject_time,subject_class,subject_subject,notice_time,notice_notice,lab_time,lab_class,lab_sub1,lab_sub2;
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
    void setNotice(@NonNull SubjectClassAdapter.ViewHolder holder, String Time, String notice){
        //Setting values
        holder.notice_time.setText(Time);
        holder.notice_notice.setText(notice);
    }

}
