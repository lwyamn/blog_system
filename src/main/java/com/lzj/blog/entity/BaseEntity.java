package com.lzj.blog.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
public abstract class BaseEntity {
    @Schema(description = "主键id")
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;
    @Schema(description = "是否删除")
    @TableField(value = "isDelete")
    @TableLogic(value = "1",delval = "0")
    private Integer isDelete;
    @Schema(description = "创建时间")
    @TableField(value = "createTime",fill = FieldFill.INSERT)
    private Date createTime;
    @Schema(description = "更新时间")
    @TableField(value = "updateTime",fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
