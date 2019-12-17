package com.soldier.common.service;

import com.soldier.common.vo.SysDataModelBean;

/**
 * @author soldier
 * @title: ViewService
 * @projectName teacher_files
 * @create:19-11-10下午6:56
 */
public interface SysDataModelService {

    /**
     * 数据统计
     * @param deptId
     */
    public SysDataModelBean selectCount(Long deptId);

}
