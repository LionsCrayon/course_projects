package com.example.server.mapper.my;

import org.apache.ibatis.annotations.Param;

/**
 * Description:
 * date: 2020/11/10 14:43
 *
 * @author 896951384
 * @since JDK 1.8
 */
public interface MyCourseMapper {
    int updateTime(@Param("courseId") String courseId);
}
