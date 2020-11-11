package com.example.business.controller.admin;

import com.example.server.DTO.CategoryDTO;
import com.example.server.DTO.CategoryPageDTO;
import com.example.server.DTO.ResponseDto;
import com.example.server.service.CategoryService;
import com.example.server.util.ValidatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Description:
 * date: 2020/11/3 16:51
 *
 * @author 896951384
 * @since JDK 1.8
 */
@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryController.class);
    public static final String BUSINESS_NAME = "分类";

    @Autowired
    public CategoryService categoryService;

    /**
     * 列表查询操作，具备分页效果
     * @return
     */
    @PostMapping("/all")
    public ResponseDto all() {
        ResponseDto responseDto = new ResponseDto();
        List<CategoryDTO> categoryDTOList = categoryService.all();
        responseDto.setContent(categoryDTOList);
        return responseDto;
    }

    /**
     * 列表查询操作，具备分页效果
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody CategoryPageDTO pageDTO) {
        ResponseDto responseDto = new ResponseDto();
        categoryService.selectAll(pageDTO);
        responseDto.setContent(pageDTO);
        return responseDto;
    }

    /**
     * 保存操作(包含新增和修改，在业务层进行判断是新增还是修改)
     * @param categoryDTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody CategoryDTO categoryDTO) {
        // 保存校验
        ValidatorUtil.require(categoryDTO.getParent(), "父id");
        ValidatorUtil.require(categoryDTO.getName(), "名称");
        ValidatorUtil.length(categoryDTO.getName(), "名称", 1, 50);

        ResponseDto responseDto = new ResponseDto();
        categoryService.save(categoryDTO);
        responseDto.setContent(categoryDTO);
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
        categoryService.delete(id);
        return responseDto;
    }
}
