package com.imman.iava.Schedule;

public class SubjectModel {
    String time,class_number,subject,subject_code,teacher,notice,subject2,subject_code2,teacher2,timeDividerText;
    Boolean isLab,isNotice,isClass,isTimeDivider;

    public SubjectModel(String time, String class_number, String subject, String subject_code, String teacher, String notice, String subject2, String subject_code2, String teacher2, String timeDividerText, Boolean isTimeDivider, Boolean isLab, Boolean isNotice, Boolean isClass) {
        this.time = time;
        this.class_number = class_number;
        this.subject = subject;
        this.subject_code = subject_code;
        this.teacher = teacher;
        this.notice = notice;
        this.subject2 = subject2;
        this.subject_code2 = subject_code2;
        this.teacher2 = teacher2;
        this.isLab = isLab;
        this.isNotice = isNotice;
        this.isClass = isClass;
        this.timeDividerText = timeDividerText;
        this.isTimeDivider = isTimeDivider;
    }
}
