package com.blog.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private Long id;		//留言编号
    private Long articleId;	//被留言文章的编号
    private Date date;		//留言时间
    private String name;	//留言人者
    private String email;	//留言者的邮箱
    private String content;	//留言内容

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getDate() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");//设置日期格式
        String dates = df.format(date);
        return dates;

    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	@Override
	public String toString() {
		return "Comment [id=" + id + ", articleId=" + articleId + ", date=" + date + ", name=" + name + ", email="
				+ email + ", content=" + content + "]";
	}
}