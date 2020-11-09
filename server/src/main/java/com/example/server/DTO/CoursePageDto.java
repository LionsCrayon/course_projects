package com.example.server.DTO;

/**
 * Description:
 * date: 2020/11/9 15:19
 *
 * @author 896951384
 * @since JDK 1.8
 */
public class CoursePageDto extends PageDTO{
    private String courseId;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CoursePageDto{");
        sb.append("courseId='").append(courseId).append('\'');
        sb.append(", page=").append(page);
        sb.append(", size=").append(size);
        sb.append(", total=").append(total);
        sb.append(", list=").append(list);
        sb.append('}');
        return sb.toString();
    }
}
