package com.bravo.bravobest.api.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 侧边菜单栏实体
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = -8762050562271279510L;

    private Long id;
    private String url;
    private String path;
    private String component;
    private String name;
    private String iconCls;
    private Long parentId;
//    private List<Role> roles;
    private List<Menu> children;
//    private MenuMeta meta;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }
}
