package com.bravo.bravobest.api.entity;

import java.io.Serializable;

/**
 * 侧边菜单栏实体
 */
public class Menu implements Serializable {

    private static final long serialVersionUID = -8762050562271279510L;

    private String id;

    private String url;

    private String path;

    private String component;

    private String name;

    private String iconCls;

    private String keepAlive;

    private String requireAuth;

    private String parentId;

    private String enabled;

    private String component2;

    private String iconCls2;

    private String keepAlive2;

    private String name2;

    private String path2;

    private String requireAuth2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(String keepAlive) {
        this.keepAlive = keepAlive;
    }

    public String getRequireAuth() {
        return requireAuth;
    }

    public void setRequireAuth(String requireAuth) {
        this.requireAuth = requireAuth;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getComponent2() {
        return component2;
    }

    public void setComponent2(String component2) {
        this.component2 = component2;
    }

    public String getIconCls2() {
        return iconCls2;
    }

    public void setIconCls2(String iconCls2) {
        this.iconCls2 = iconCls2;
    }

    public String getKeepAlive2() {
        return keepAlive2;
    }

    public void setKeepAlive2(String keepAlive2) {
        this.keepAlive2 = keepAlive2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPath2() {
        return path2;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getRequireAuth2() {
        return requireAuth2;
    }

    public void setRequireAuth2(String requireAuth2) {
        this.requireAuth2 = requireAuth2;
    }
}
