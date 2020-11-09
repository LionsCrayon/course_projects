package com.example.business.controller.admin;

import com.example.server.DTO.ChapterDTO;
import com.example.server.DTO.ChapterPageDto;
import com.example.server.DTO.ResponseDto;
import com.example.server.service.ChapterService;
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
@RequestMapping("/admin/chapter")
public class ChapterController {

    private static final Logger LOG = LoggerFactory.getLogger(ChapterController.class);
    public static final String BUSINESS_NAME = "大章";

    @Autowired
    public ChapterService testBusiness;

    /**
     * 列表查询操作，具备分页效果
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody ChapterPageDto pageDTO) {
        ResponseDto responseDto = new ResponseDto();
        testBusiness.selectAll(pageDTO);
        responseDto.setContent(pageDTO);
        return responseDto;
    }

    /**
     * 保存操作(包含新增和修改，在业务层进行判断是新增还是修改)
     * @param chapterDTO  大章实体类
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ChapterDTO chapterDTO) {
        // 保存校验
        ValidatorUtil.require(chapterDTO.getName(), "名称");
        ValidatorUtil.require(chapterDTO.getCourseId(), "课程ID");
        ValidatorUtil.length(chapterDTO.getCourseId(), "课程ID", 1, 8);

        ResponseDto responseDto = new ResponseDto();
        testBusiness.save(chapterDTO);
        responseDto.setContent(chapterDTO);
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
        testBusiness.delete(id);
        return responseDto;
    }
}
