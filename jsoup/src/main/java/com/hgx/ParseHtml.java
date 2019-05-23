package com.hgx;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ParseHtml {

    public static void main(String[] args) {
//        String filePath = ParseHtml.class.getClassLoader().getResource("courseTable.html").getPath();
        String filePath = "D:\\courseTable.html";
        File file = new File(filePath);
        try {
            //加载html文件
            Document document = Jsoup.parse(file, "utf-8");
            //获取课表所在table
            Element table6 = document.select("#table6").first();
            List<Course> courses = new ArrayList<Course>();
            Elements tr = table6.getElementsByTag("tr");
            //获取有课程信息的每一行的索引
            Integer[] array = new Integer[]{2, 4, 7, 9};
            //解析构建课表信息实体
            parse(courses, array, tr);
             // 保存到文件中
            saveToFile(courses);
            //打印构建好的课表实体信息
            courses.stream().forEach(System.out::println);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 解析课表信息
     *
     * @param courses
     * @param array
     * @param tr
     */
    private static void parse(List<Course> courses, Integer[] array, Elements tr) {
        String sectionNumber = "";
        for (int j = 0; j < array.length; j++) {
            switch (j) {
                case 0:
                    sectionNumber = "第一节和第二节";
                    break;
                case 1:
                    sectionNumber = "第三节和第四节";
                    break;
                case 2:
                    sectionNumber = "第五节和第六节";
                    break;
                case 3:
                    sectionNumber = "第七节和第八节";
                    break;
            }
            Integer index = array[j];
            Element tr2 = tr.get(index);
            Elements tr2Td = tr2.getElementsByTag("td");
            Integer start = 2;
            Integer end = 6;
            Integer poor = 0;
            if (!(j % 2 == 0)) {
                start = 1;
                end = 5;
                poor = 1;
            }
            for (int i = start; i <= end; i++) {
                Course course = createCourse(tr2Td, i);
                course.setSectionNumber(sectionNumber);
                switch (i + poor) {
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
        }  /*array  ---  end */
    }

    /**
     * 构建课表实体
     *
     * @param tr2Td
     * @param i
     * @return
     */
    private static Course createCourse(Elements tr2Td, int i) {
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
        return course;
    }

    /**
     * 保存到文件中
     *
     * @param courses
     */
    private static void saveToFile(List<Course> courses) {
        Gson gson = new Gson();
        String toJson = gson.toJson(courses);
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("courses.json");
        ) {
            fileOutputStream.write(toJson.getBytes());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
