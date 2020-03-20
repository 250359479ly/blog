package com.blog.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.bean.Comment;
import com.blog.service.CommentService;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加一条新的留言信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/comment/add", method = RequestMethod.POST)
    public Object commentAdd(Comment comment) {
    	comment.setDate(new Date());
    	HashMap<String, String> res = new HashMap<String, String>();
    	if(commentService.insertComment(comment)!=0){
        	res.put("stateCode", "1");
        }else {
            res.put("stateCode", "0");
        }
        return res;
    }
    /**
     * 删除对应的留言信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/api/comment/del", method = RequestMethod.POST)
    public Object commentDel(HttpServletRequest request) {
        long id=Long.parseLong(request.getParameter("id"));
        HashMap<String, String> res = new HashMap<String, String>();
        if (commentService.delById(id)){
        	res.put("stateCode", "1");
        }else {
        	res.put("stateCode", "0");
        }
        return res;
    }
}
