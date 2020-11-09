package com.example.server.service;

import com.example.server.DTO.SectionDTO;
import com.example.server.DTO.SectionPageDto;
import com.example.server.DTO.PageDTO;
import com.example.server.domain.Section;
import com.example.server.domain.SectionExample;
import com.example.server.mapper.SectionMapper;
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
public class SectionService {

    @Autowired
    private SectionMapper sectionMapper;

    /**
     * 业务层查询操作
     * @param pageDTO
     */
    public void selectAll(SectionPageDto pageDTO){
        PageHelper.startPage(pageDTO.getPage(),pageDTO.getSize());
        SectionExample sectionExample = new SectionExample();
        sectionExample.setOrderByClause("sort asc");
        List<Section> sectionList = sectionMapper.selectByExample(sectionExample);
        PageInfo<Section> pageInfo = new PageInfo<>(sectionList);
        pageDTO.setTotal(pageInfo.getTotal());
        List<SectionDTO> sectionDTOS = CopyUtil.copyList(sectionList, SectionDTO.class);
        pageDTO.setList(sectionDTOS);
    }

    /**
     * 保存操作，包含新增和修改
     * @param sectionDTO
     */
    public void save(SectionDTO sectionDTO){
        Section section = CopyUtil.copy(sectionDTO, Section.class);
        if (StringUtils.isEmpty(section.getId())){
            insert(section);
        }
        else{
            update(section);
        }
    }

    /**
     * 新增数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param section
     */
    private void insert(Section section){
        Date now = new Date();
        section.setCreatedAt(now);
        section.setUpdatedAt(now);
        section.setId(UuidUtil.getShortUuid());
        sectionMapper.insert(section);
    }

    /**
     * 修改数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param section
     */
    private void update(Section section){
        section.setUpdatedAt(new Date());
        sectionMapper.updateByPrimaryKey(section);
    }

    /**
     * 删除数据
     * @param id
     */
    public void delete(String id){
        sectionMapper.deleteByPrimaryKey(id);
    }
}
