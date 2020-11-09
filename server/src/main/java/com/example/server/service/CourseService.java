package com.example.server.service;

import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.CoursePageDto;
import com.example.server.DTO.PageDTO;
import com.example.server.domain.Course;
import com.example.server.domain.CourseExample;
import com.example.server.mapper.CourseMapper;
import com.example.server.util.CopyUtil;
import com.example.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
        import java.util.Date;
/**
 * Description:
 * date: 2020/11/3 16:44
 *
 * @author 896951384
 * @since JDK 1.8
 */
@Service
public class CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 业务层查询操作
     * @param pageDTO
     */
    public void selectAll(CoursePageDto pageDTO){
        PageHelper.startPage(pageDTO.getPage(),pageDTO.getSize());
        CourseExample courseExample = new CourseExample();
        courseExample.setOrderByClause("sort asc");
        List<Course> courseList = courseMapper.selectByExample(courseExample);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        pageDTO.setTotal(pageInfo.getTotal());
        List<CourseDTO> courseDTOS = CopyUtil.copyList(courseList, CourseDTO.class);
        pageDTO.setList(courseDTOS);
    }

    /**
     * 保存操作，包含新增和修改
     * @param courseDTO
     */
    public void save(CourseDTO courseDTO){
        Course course = CopyUtil.copy(courseDTO, Course.class);
        if (StringUtils.isEmpty(course.getId())){
            insert(course);
        }
        else{
            update(course);
        }
    }

    /**
     * 新增数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param course
     */
    private void insert(Course course){
        Date now = new Date();
        course.setCreatedAt(now);
        course.setUpdatedAt(now);
        course.setId(UuidUtil.getShortUuid());
        courseMapper.insert(course);
    }

    /**
     * 修改数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param course
     */
    private void update(Course course){
        course.setUpdatedAt(new Date());
        courseMapper.updateByPrimaryKey(course);
    }

    /**
     * 删除数据
     * @param id
     */
    public void delete(String id){
        courseMapper.deleteByPrimaryKey(id);
    }
}
