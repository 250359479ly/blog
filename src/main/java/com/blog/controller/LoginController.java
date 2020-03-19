package com.blog.controller;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blog.bean.AdminLoginLog;
import com.blog.service.AdminLoginLogService;
import com.blog.service.AdminService;

@Controller
public class LoginController {
	@Autowired
	private AdminService adminService;
	@Autowired
	private AdminLoginLogService adminLoginLogService;

	@RequestMapping(value = { "/admin/index", "/admin", "/admin/login" })
	public String toIndex(HttpServletRequest request) {

		return "admin/login";

	}

	/**
	 * 登陆校验
	 *  0:用户不存在 
	 *  1:密码错误 
	 *  2:登陆成功
	 * @param request
	 * @param httpServletResponse
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/api/loginCheck", method = RequestMethod.POST)
	public Object loginCheck(HttpServletRequest req, HttpServletResponse resp) {
		
		int id = Integer.parseInt(req.getParameter("id"));
		String passwd = req.getParameter("password");
		
		HashMap<String, String> result = new HashMap<String, String>();
		if (adminService.getById(id) == null) {
			result.put("stateCode", "0");
		} else if (!adminService.getById(id).getPassword().equals(passwd)) {
			result.put("stateCode", "1");
		} else {
			String ip = req.getRemoteAddr();
			AdminLoginLog adminLoginLog = new AdminLoginLog();
			adminLoginLog.setAdminId(id);
			adminLoginLog.setDate(new Date());
			adminLoginLog.setIp(ip);
			adminLoginLogService.insert(adminLoginLog);
			Cookie cookie = new Cookie("userId", "" + id);
			cookie.setMaxAge(3600 * 24); //24小时
			resp.addCookie(cookie);
			//session默认是半个小时
			req.getSession().setAttribute("admin", adminService.getById(id));
			result.put("stateCode", "2");
		}

		return result;
	}
	
	/**
	 * admin登出
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = { "/admin/logout" })
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("admin");
		return "redirect:/admin";

	}

}
