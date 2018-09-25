package com.wxainn.springsecurity.oauth2.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色——资源对应关系
 *
 * @author 王晓安
 */
@Entity
@Table(name = "role_resource")
public class RoleResource implements Serializable {
    private static final long serialVersionUID = 3125977045605856504L;
    private int id;
    private int roleId;
    private int resourceId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "roleId")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "resourceId")
    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public String toString() {
        return "RoleResource{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", resourceId=" + resourceId +
                '}';
    }
}
