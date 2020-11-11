package com.example.business.controller.admin;

import com.example.server.DTO.SectionDTO;
import com.example.server.DTO.SectionPageDto;
import com.example.server.DTO.ResponseDto;
import com.example.server.service.SectionService;
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
@RequestMapping("/admin/section")
public class SectionController {

    private static final Logger LOG = LoggerFactory.getLogger(SectionController.class);
    public static final String BUSINESS_NAME = "小节";

    @Autowired
    public SectionService sectionService;

    /**
     * 列表查询操作，具备分页效果
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody SectionPageDto pageDTO) {
        ResponseDto responseDto = new ResponseDto();
        ValidatorUtil.require(pageDTO.getChapterId(), "大章ID");
        ValidatorUtil.require(pageDTO.getCourseId(), "课程ID");
        sectionService.selectAll(pageDTO);
        responseDto.setContent(pageDTO);
        return responseDto;
    }

    /**
     * 保存操作(包含新增和修改，在业务层进行判断是新增还是修改)
     * @param sectionDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody SectionDTO sectionDTO) {
        // 保存校验
        ValidatorUtil.require(sectionDTO.getTitle(), "标题");
        ValidatorUtil.length(sectionDTO.getTitle(), "标题", 1, 50);
        ValidatorUtil.length(sectionDTO.getVideo(), "视频", 1, 200);

        ResponseDto responseDto = new ResponseDto();
        sectionService.save(sectionDTO);
        responseDto.setContent(sectionDTO);
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
        sectionService.delete(id);
        return responseDto;
    }
}
