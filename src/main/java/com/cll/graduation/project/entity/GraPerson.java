package com.cll.graduation.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author chelinlai
 * @since 2022-04-15
 */
@TableName("gra_person")
@ApiModel(value = "GraPerson对象", description = "")
public class GraPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String roleName;

    private String roleCode;

    private Boolean yn;

    private String creator;

    private LocalDateTime createTime;

    private String modifier;

    private LocalDateTime modifyTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }
    public Boolean getYn() {
        return yn;
    }

    public void setYn(Boolean yn) {
        this.yn = yn;
    }
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
    public LocalDateTime getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(LocalDateTime modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        return "GraPerson{" +
            "id=" + id +
            ", name=" + name +
            ", roleName=" + roleName +
            ", roleCode=" + roleCode +
            ", yn=" + yn +
            ", creator=" + creator +
            ", createTime=" + createTime +
            ", modifier=" + modifier +
            ", modifyTime=" + modifyTime +
        "}";
    }
}
