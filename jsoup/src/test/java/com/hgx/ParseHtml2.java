package com.hgx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseHtml2 {


    public static void main(String[] args) {
        String filePath = ParseHtml2.class.getClassLoader().getResource("courseTable.html").getPath();
        File file = new File(filePath);
        try {
            Document document = Jsoup.parse(file, "utf-8");
            Element table6 = document.select("#table6").first();
            List<Course> courses = new ArrayList<Course>();
            Elements tr = table6.getElementsByTag("tr");

            Element tr2 = tr.get(2);
            Elements tr2Td = tr2.getElementsByTag("td");

            Element tr2TdElement = tr2Td.get(2);

            for (int i = 2; i <= 6; i++) {
                Element element = tr2Td.get(i);
                String text = element.text();
                String[] split = text.split(" ");
                Course course = new Course();
                if (split != null && split.length == 5) {
                    course.setCourseName(split[0]);
                    course.setPeriod(split[1]);
                    course.setTeacher(split[2]);
                    course.setVenue(split[3]);
                    course.setDate(split[4]);
                }
                switch (i) {
                    case 2:
                        course.setWeek("星期一");
                        break;
                    case 3:
                        course.setWeek("星期二");
                        break;
                    case 4:
                        course.setWeek("星期三");
                        break;
                    case 5:
                        course.setWeek("星期四");
                        break;
                    case 6:
                        course.setWeek("星期五");
                        break;
                }
                courses.add(course);
            }
            for (int i = 0; i < courses.size(); i++) {
                System.out.println(courses.get(i));
            }

         /*   String tr2TdElementText = tr2TdElement.text();
            String[] tr2TdElementTextSplit = tr2TdElementText.split(" ");
            Course tr2TdElementCourse = new Course();
            tr2TdElementCourse.setCourseName(tr2TdElementTextSplit[0]);
            tr2TdElementCourse.setPeriod(tr2TdElementTextSplit[1]);
            tr2TdElementCourse.setTeacher(tr2TdElementTextSplit[2]);
            tr2TdElementCourse.setVenue(tr2TdElementTextSplit[3]);
            tr2TdElementCourse.setDate(tr2TdElementTextSplit[4]);
            tr2TdElementCourse.setWeek("星期一");
            courses.add(tr2TdElementCourse);
            System.out.println(tr2TdElementCourse);

            Element tr2TdElement1 = tr2Td.get(3);
            String tr2TdElement1Text = tr2TdElement1.text();
            String[] tr2TdElement1TextSplit = tr2TdElement1Text.split(" ");
            Course tr2TdElement1Course = new Course();
            tr2TdElement1Course.setCourseName(tr2TdElement1TextSplit[0]);
            tr2TdElement1Course.setPeriod(tr2TdElement1TextSplit[1]);
            tr2TdElement1Course.setTeacher(tr2TdElement1TextSplit[2]);
            tr2TdElement1Course.setVenue(tr2TdElement1TextSplit[3]);
            tr2TdElement1Course.setDate(tr2TdElement1TextSplit[4]);
            tr2TdElement1Course.setWeek("星期二");
            courses.add(tr2TdElement1Course);
            System.out.println(tr2TdElement1Course);


            Element tr2TdElement2 = tr2Td.get(4);
            String tr2TdElement2Text = tr2TdElement2.text();
            String[] tr2TdElement2TextSplit = tr2TdElement2Text.split(" ");
            Course tr2TdElement2Course = new Course();
            tr2TdElement2Course.setCourseName(tr2TdElement2TextSplit[0]);
            tr2TdElement2Course.setPeriod(tr2TdElement2TextSplit[1]);
            tr2TdElement2Course.setTeacher(tr2TdElement2TextSplit[2]);
            tr2TdElement2Course.setVenue(tr2TdElement2TextSplit[3]);
            tr2TdElement2Course.setDate(tr2TdElement2TextSplit[4]);
            tr2TdElement2Course.setWeek("星期三");
            courses.add(tr2TdElement2Course);
            System.out.println(tr2TdElement2Course);


            Element tr2TdElement3 = tr2Td.get(5);
            String tr2TdElement3Text = tr2TdElement3.text();
            String[] tr2TdElement3TextSplit = tr2TdElement3Text.split(" ");
            Course tr2TdElement3Course = new Course();
            tr2TdElement3Course.setCourseName(tr2TdElement3TextSplit[0]);
            tr2TdElement3Course.setPeriod(tr2TdElement3TextSplit[1]);
            tr2TdElement3Course.setTeacher(tr2TdElement3TextSplit[2]);
            tr2TdElement3Course.setVenue(tr2TdElement3TextSplit[3]);
            tr2TdElement3Course.setDate(tr2TdElement3TextSplit[4]);
            tr2TdElement3Course.setWeek("星期四");
            courses.add(tr2TdElement3Course);
            System.out.println(tr2TdElement3Course);*/


            Element tr4 = tr.get(4);






          /*  Element tr7 = tr.get(7);
            System.out.println(tr7);

            Element tr9 = tr.get(9);
            System.out.println(tr9);*/

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}
