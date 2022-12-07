package com.adminjava.controller;

import com.adminjava.service.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.adminjava.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

	@Autowired
	HttpServletRequest req;

	@Autowired
	RedisUtil redisUtil;

	@Autowired
	SysUserService sysUserService;

	@Autowired
	SysRoleService sysRoleService;

	@Autowired
	SysMenuService sysMenuService;

	@Autowired
	SysUserRoleService sysUserRoleService;

	@Autowired
	SysRoleMenuService sysRoleMenuService;

	//获取页面
	public Page getPage() {
		int current = ServletRequestUtils.getIntParameter(req, "cuurent", 1);
		int size = ServletRequestUtils.getIntParameter(req, "size", 10);

		return new Page(current, size);
	}

}