package com.example.demo.modules.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.modules.account.dao.ResourceDao;
import com.example.demo.modules.account.dao.RoleResourceDao;
import com.example.demo.modules.account.entity.Resource;
import com.example.demo.modules.account.entity.Role;
import com.example.demo.modules.account.service.ResourceService;
import com.example.demo.modules.common.vo.Result;
import com.example.demo.modules.common.vo.Result.ResultStatus;
import com.example.demo.modules.common.vo.SearchVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	@Autowired
	private RoleResourceDao roleResourceDao;

	@Override
	@Transactional
	public Result<Resource> editResource(Resource resource) {
		if (resource == null || StringUtils.isBlank(resource.getResourceUri()) ) {
			return new Result<Resource>(500, "resource info is null");
		}
		
		// 添加 resource
		if (resource.getResourceId() > 0) {
			resourceDao.updateResource(resource);
		} else {
			resourceDao.addResource(resource);
		}
		
		// 添加 roleResource
		roleResourceDao.deletRoleResourceByResourceId(resource.getResourceId());
		if (resource.getRoles() != null && !resource.getRoles().isEmpty()) {
			for (Role role : resource.getRoles()) {
				roleResourceDao.addRoleResource(role.getRoleId(), resource.getResourceId());
			}
		}
		
		return new Result<Resource>(200, "success", resource);
	}

	@Override
	@Transactional
	public Result<Resource> deleteResource(int resourceId) {
		roleResourceDao.deletRoleResourceByResourceId(resourceId);
		resourceDao.deleteResource(resourceId);
		return new Result<Resource>(ResultStatus.SUCCESS.status, "");
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public PageInfo<Resource> getResources(SearchVo searchVo) {
		searchVo.initSearchVo(searchVo);
		PageHelper.startPage(searchVo.getCurrentPage(), searchVo.getPageSize());
		return new PageInfo(
				Optional.ofNullable(resourceDao.getResources())
				.orElse(Collections.emptyList()));
	}

	@Override
	public List<Resource> getResourcesByRoleId(int roleId) {
		return resourceDao.getResourcesByRoleId(roleId);
	}

	@Override
	public Resource getResourceById(int resourceId) {
		return resourceDao.getResourceById(resourceId);
	}
}
