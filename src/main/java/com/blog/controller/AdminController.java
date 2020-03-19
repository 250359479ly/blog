package com.blog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.blog.bean.Admin;
import com.blog.bean.AdminLoginLog;
import com.blog.service.AdminLoginLogService;
import com.blog.service.ArticleService;
import com.blog.service.CommentService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired
	private AdminLoginLogService adminLoginLogService;
	@Autowired
	private ArticleService articleService;
	@Autowired
	private CommentService commentService;
	
	@RequestMapping("/main")
	public ModelAndView toMain(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/main");
		String clientIp = request.getRemoteAddr(); 	//获取客户端IP，如：127.0.0.1
		String hostIp = request.getLocalAddr();		//获取服务器IP
		int hostPort = request.getLocalPort();		//获取服务器端口号
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");// 设置日期格式
		String dates = df.format(date);
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		AdminLoginLog lastLoginLog = null;
		try {
			if (adminLoginLogService.selectRencent(admin.getId()) != null
					&& adminLoginLogService.selectRencent(admin.getId()).size() == 2) {
				List<AdminLoginLog> adminLoginLogs = adminLoginLogService.selectRencent(admin.getId());
				lastLoginLog = adminLoginLogs.get(1);	//上一次的登录信息
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			int articleCount = articleService.selectCount();	//总文章数
			int commentCount = commentService.countAllNum();	//总留言条数
			int loginNum = adminLoginLogService.selectCountByAdminId(admin.getId()); //该账号的登录次数
			modelAndView.addObject("clientIp", clientIp);
			modelAndView.addObject("hostIp", hostIp);
			modelAndView.addObject("hostPort", hostPort);
			modelAndView.addObject("date", dates);
			if (lastLoginLog != null) {
				modelAndView.addObject("loginLog", lastLoginLog);
			}else {
				modelAndView.addObject("loginLog", "暂无记录");
			}
			modelAndView.addObject("articleCount", articleCount);
			modelAndView.addObject("commentCount", commentCount);
			modelAndView.addObject("loginNum", loginNum);
		}
		return modelAndView;
	}

}
