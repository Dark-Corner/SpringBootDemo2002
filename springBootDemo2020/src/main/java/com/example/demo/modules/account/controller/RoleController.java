package com.example.demo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modules.account.entity.Role;
import com.example.demo.modules.account.service.RoleService;
import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/api")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@PostMapping(value = "/roles", consumes = "application/json")
	public PageInfo<Role> getRoles(@RequestBody SearchVo searchVo) {
		return roleService.getRoles(searchVo);
	}
	
	@PostMapping(value = "/role", consumes = "application/json")
	public Result<Role> editRole(@RequestBody Role role) {
		return roleService.editRole(role);
	}
	
	@RequestMapping("/role/{roleId}")
	public Role getRole(@PathVariable int roleId) {
		return roleService.getRoleById(roleId);
	}
	
	@DeleteMapping("/role/{roleId}")
	public Result<Role> deletRole(@PathVariable int roleId) {
		return roleService.deleteRole(roleId);
	}
	
	@RequestMapping("/roles")
	public List<Role> getRoles() {
		return roleService.getRoles();
	}
}
