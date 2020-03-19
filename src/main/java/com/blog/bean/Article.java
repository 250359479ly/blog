package com.blog.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private Integer id;

    private String title;	//标题
    private String keywords;//关键字
    private String desci;	//简介
    private String pic;
    private Integer click;	//点击量
    private Date time;		//发布时间
    private Integer catalogId;//栏目
    private String content;	//正文

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords == null ? null : keywords.trim();
    }

    public String getdesci() {
        return desci;
    }

    public void setdesci(String desci) {
        this.desci = desci == null ? null : desci.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    public Date getTime() {
        return time;
    }

    public String getLocalTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-M-dd HH:mm");//设置日期格式
        String date = df.format(time);
        return date;

    }


    public void setTime(Date time) {
        this.time = time;
    }

    public Integer getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Integer catalogId) {
        this.catalogId = catalogId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}