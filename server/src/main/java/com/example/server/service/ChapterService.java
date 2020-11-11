package com.example.server.service;

import com.example.server.DTO.ChapterDTO;
import com.example.server.DTO.ChapterPageDto;
import com.example.server.DTO.PageDTO;
import com.example.server.domain.Chapter;
import com.example.server.domain.ChapterExample;
import com.example.server.mapper.ChapterMapper;
import com.example.server.util.CopyUtil;
import com.example.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class ChapterService {

    @Autowired
    private ChapterMapper testMapper;

    /**
     * 业务层查询操作
     * @param pageDTO
     */
    public void selectAll(ChapterPageDto pageDTO){
        PageHelper.startPage(pageDTO.getPage(),pageDTO.getSize());
        ChapterExample testExample = new ChapterExample();
        ChapterExample.Criteria criteria = testExample.createCriteria();
        if (!StringUtils.isEmpty(pageDTO.getCourseId())){
            criteria.andCourseIdEqualTo(pageDTO.getCourseId());
        }
        List<Chapter> chapterList = testMapper.selectByExample(testExample);
        PageInfo<Chapter> pageInfo = new PageInfo<>(chapterList);
        pageDTO.setTotal(pageInfo.getTotal());
        List<ChapterDTO> chapterDTOS = CopyUtil.copyList(chapterList, ChapterDTO.class);
        pageDTO.setList(chapterDTOS);
    }

    /**
     * 保存操作，包含新增和修改
     * @param chapterDTO
     */
    public void save(ChapterDTO chapterDTO){
        Chapter chapter = CopyUtil.copy(chapterDTO, Chapter.class);
        if (StringUtils.isEmpty(chapter.getId())){
            insert(chapter);
        }
        else{
            update(chapter);
        }
    }

    /**
     * 新增数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param chapter
     */
    private void insert(Chapter chapter){
        chapter.setId(UuidUtil.getShortUuid());
        testMapper.insert(chapter);
    }

    /**
     * 修改数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param chapter
     */
    private void update(Chapter chapter){
        testMapper.updateByPrimaryKey(chapter);
    }

    /**
     * 删除数据
     * @param id
     */
    public void delete(String id){
        testMapper.deleteByPrimaryKey(id);
    }
}
