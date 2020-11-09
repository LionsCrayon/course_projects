package com.example.server.service;

import com.example.server.DTO.${Domain}DTO;
import com.example.server.DTO.${Domain}PageDto;
import com.example.server.DTO.PageDTO;
import com.example.server.domain.${Domain};
import com.example.server.domain.${Domain}Example;
import com.example.server.mapper.${Domain}Mapper;
import com.example.server.util.CopyUtil;
import com.example.server.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
<#list typeSet as type>
    <#if type=='Date'>
        import java.util.Date;
    </#if>
</#list>
/**
 * Description:
 * date: 2020/11/3 16:44
 *
 * @author 896951384
 * @since JDK 1.8
 */
@Service
public class ${Domain}Service {

    @Autowired
    private ${Domain}Mapper ${domain}Mapper;

    /**
     * 业务层查询操作
     * @param pageDTO
     */
    public void selectAll(${Domain}PageDto pageDTO){
        PageHelper.startPage(pageDTO.getPage(),pageDTO.getSize());
        ${Domain}Example ${domain}Example = new ${Domain}Example();
         <#list fieldList as field>
            <#if field.nameHump=='sort'>
        ${domain}Example.setOrderByClause("sort asc");
            </#if>
        </#list>
        List<${Domain}> ${domain}List = ${domain}Mapper.selectByExample(${domain}Example);
        PageInfo<${Domain}> pageInfo = new PageInfo<>(${domain}List);
        pageDTO.setTotal(pageInfo.getTotal());
        List<${Domain}DTO> ${domain}DTOS = CopyUtil.copyList(${domain}List, ${Domain}DTO.class);
        pageDTO.setList(${domain}DTOS);
    }

    /**
     * 保存操作，包含新增和修改
     * @param ${domain}DTO
     */
    public void save(${Domain}DTO ${domain}DTO){
        ${Domain} ${domain} = CopyUtil.copy(${domain}DTO, ${Domain}.class);
        if (StringUtils.isEmpty(${domain}.getId())){
            insert(${domain});
        }
        else{
            update(${domain});
        }
    }

    /**
     * 新增数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param ${domain}
     */
    private void insert(${Domain} ${domain}){
         <#list typeSet as type>
            <#if type=='Date'>
        Date now = new Date();
            </#if>
        </#list>
        <#list fieldList as field>
            <#if field.nameHump=='createdAt'>
        ${domain}.setCreatedAt(now);
            </#if>
            <#if field.nameHump=='updatedAt'>
        ${domain}.setUpdatedAt(now);
            </#if>
        </#list>
        ${domain}.setId(UuidUtil.getShortUuid());
        ${domain}Mapper.insert(${domain});
    }

    /**
     * 修改数据(改为私有方法，暴露保存操作给用户)不对外公布
     * @param ${domain}
     */
    private void update(${Domain} ${domain}){
        <#list fieldList as field>
            <#if field.nameHump=='updatedAt'>
        ${domain}.setUpdatedAt(new Date());
            </#if>
        </#list>
        ${domain}Mapper.updateByPrimaryKey(${domain});
    }

    /**
     * 删除数据
     * @param id
     */
    public void delete(String id){
        ${domain}Mapper.deleteByPrimaryKey(id);
    }
}
