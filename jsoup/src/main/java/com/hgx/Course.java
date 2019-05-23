package com.hgx;

public class Course {

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 周期
     */
    private String period;

    /**
     * 授课老师
     */
    private String teacher;

    /**
     * 上课地点
     */
    private String venue;

    /**
     * 日期
     */
    private String date;

    /**
     * 星期
     */
    private String week;

    /**
     * 节数 例如第一节与第二节
     */
    private String sectionNumber;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(String sectionNumber) {
        this.sectionNumber = sectionNumber;
    }


    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", period='" + period + '\'' +
                ", teacher='" + teacher + '\'' +
                ", venue='" + venue + '\'' +
                ", date='" + date + '\'' +
                ", week='" + week + '\'' +
                ", sectionNumber='" + sectionNumber + '\'' +
                '}';
    }

}
