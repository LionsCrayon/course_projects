package com.example.${module}.controller.admin;

import com.example.server.DTO.${Domain}DTO;
import com.example.server.DTO.${Domain}PageDto;
import com.example.server.DTO.ResponseDto;
import com.example.server.service.${Domain}Service;
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
@RequestMapping("/admin/${domain}")
public class ${Domain}Controller {

    private static final Logger LOG = LoggerFactory.getLogger(${Domain}Controller.class);
    public static final String BUSINESS_NAME = "${TableNameCN}";

    @Autowired
    public ${Domain}Service ${domain}Service;

    /**
     * 列表查询操作，具备分页效果
     * @param pageDTO
     * @return
     */
    @PostMapping("/list")
    public ResponseDto list(@RequestBody ${Domain}PageDto pageDTO) {
        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.selectAll(pageDTO);
        responseDto.setContent(pageDTO);
        return responseDto;
    }

    /**
     * 保存操作(包含新增和修改，在业务层进行判断是新增还是修改)
     * @param ${domain}DTO
     * @return
     */
    @PostMapping("/save")
    public ResponseDto save(@RequestBody ${Domain}DTO ${domain}DTO) {
        // 保存校验
        <#list fieldList as field>
        <#if field.name!="id" && field.nameHump!="createdAt" && field.nameHump!="updatedAt" && field.nameHump!="sort">
            <#if !field.nullAble>
        ValidatorUtil.require(${domain}DTO.get${field.nameBigHump}(), "${field.nameCn}");
            </#if>
            <#if (field.length > 0)>
        ValidatorUtil.length(${domain}DTO.get${field.nameBigHump}(), "${field.nameCn}", 1, ${field.length?c});
            </#if>
        </#if>
        </#list>

        ResponseDto responseDto = new ResponseDto();
        ${domain}Service.save(${domain}DTO);
        responseDto.setContent(${domain}DTO);
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
        ${domain}Service.delete(id);
        return responseDto;
    }
}
