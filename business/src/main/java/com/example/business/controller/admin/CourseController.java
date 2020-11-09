package com.example.business.controller.admin;

import com.example.server.DTO.CourseDTO;
import com.example.server.DTO.CoursePageDto;
import com.example.server.DTO.ResponseDto;
import com.example.server.service.CourseService;
import com.example.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 * date: 2020/11/3 16:51
 *
 * @author 896951384
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/admin/course")
public class CourseController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseController.class);
    public static final String BUSINESS_NAME = "课程";

    @Autowired
    public CourseService courseService;

    /**
     * 列表查询操作，具备分页效果
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CoursePageDto pageDTO) {
        ResponseDto responseDto = new ResponseDto();
        courseService.selectAll(pageDTO);
        responseDto.setContent(pageDTO);
        return responseDto;
    }

    /**
     * 保存操作(包含新增和修改，在业务层进行判断是新增还是修改)
     * @param courseDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CourseDTO courseDTO) {
        // 保存校验
        ValidatorUtil.require(courseDTO.getName(), "名称");
        ValidatorUtil.length(courseDTO.getName(), "名称", 1, 50);
        ValidatorUtil.length(courseDTO.getSummary(), "概述", 1, 2000);
        ValidatorUtil.length(courseDTO.getImage(), "封面", 1, 100);

        ResponseDto responseDto = new ResponseDto();
        courseService.save(courseDTO);
        responseDto.setContent(courseDTO);
        return responseDto;
    }

    /**
     * 删除操作
     *
     * @param id 主键
     * @return 状态工具类
     */
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable String id) {
        ResponseDto responseDto = new ResponseDto();
        courseService.delete(id);
        return responseDto;
    }
}
