package com.blog.service;

import java.util.List;

import com.blog.bean.AdminLoginLog;

public interface AdminLoginLogService {

    List<AdminLoginLog> selectRencent(Integer adminId);

    int insert(AdminLoginLog adminLoginLog);

    int selectCountByAdminId(int adminId);
}
