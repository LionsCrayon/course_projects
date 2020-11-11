package com.example.server.service;

import com.example.server.DTO.CategoryDTO;
import com.example.server.DTO.CategoryPageDTO;
import com.example.server.domain.Category;
import com.example.server.domain.CategoryExample;
import com.example.server.mapper.CategoryMapper;
import com.example.server.util.CopyUtil;
import com.example.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
/**
 * Description:
 * date: 2020/11/3 16:44
 *
 * @author 896951384
 * @since JDK 1.8
 */
@Service
public class CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 业务层查询操作
     * @return
     */
    public List<CategoryDTO> all(){
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        List<CategoryDTO> categoryDTOS = CopyUtil.copyList(categoryList, CategoryDTO.class);
        return categoryDTOS;
    }

    /**
     * 业务层查询操作
     * @param pageDTO
     */
    public void selectAll(CategoryPageDTO pageDTO){
        PageHelper.startPage(pageDTO.getPage(),pageDTO.getSize());
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        pageDTO.setTotal(pageInfo.getTotal());
        List<CategoryDTO> categoryDTOS = CopyUtil.copyList(categoryList, CategoryDTO.class);
        pageDTO.setList(categoryDTOS);
    }

    /**
     * 保存操作，包含新增和修改
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO){
        Category category = CopyUtil.copy(categoryDTO, Category.class);
        if (StringUtils.isEmpty(category.getId())){
            insert(category);
        }
        else{
            update(category);
        }
    }

    /**
     * 新增数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param category
     */
    private void insert(Category category){
        category.setId(UuidUtil.getShortUuid());
        categoryMapper.insert(category);
    }

    /**
     * 修改数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param category
     */
    private void update(Category category){
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 删除数据
     * @param id
     */
    @Transactional
    public void delete(String id){
        deleteChildren(id);
        categoryMapper.deleteByPrimaryKey(id);
    }

    /**
     * 删除子分类
     */

    public void deleteChildren(String id){
        Category category = categoryMapper.selectByPrimaryKey(id);
        if ("0000000".equals(category.getParent())){
            CategoryExample categoryExample = new CategoryExample();
            categoryExample.createCriteria().andParentEqualTo(category.getId());
            categoryMapper.deleteByExample(categoryExample);
        }
    }
}
