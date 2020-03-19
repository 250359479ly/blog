package com.blog.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 管理（admin）登录记录的类
 * @author LY
 *
 */
public class AdminLoginLog {
    private Long id;

    private Integer adminId;	//用户ID
    private Date date;			//登陆时间
    private String ip;			//登陆的客户端IP地址
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public String getLocalTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");//设置日期格式
        String dates = df.format(date);
        return dates;

    }
}