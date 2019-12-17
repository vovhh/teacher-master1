package com.soldier.common.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.soldier.common.service.SysDataModelService;
import com.soldier.common.vo.R;
import com.soldier.common.vo.SysDataModelBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Program: teacher
 * @Author: soldier
 * @Email： 583403411@qq.com
 * @Create: 2019-04-28 20:19
 * @Describe：
 **/
public class ViewAction extends ActionSupport implements ModelDriven<SysDataModelBean> {

    @Autowired
    private SysDataModelService dataModelService;
    //日志
    private static Logger logger = Logger.getLogger(ViewAction.class);
    //模型驱动
    private SysDataModelBean dataModelBean = new SysDataModelBean();
    //返回集
    private R r = new R();
    //搜索值
    private String key;
    //当前页
    private Integer page;
    //大小
    private Integer limit;
    //大小

    @Override
    public SysDataModelBean getModel() {
        return dataModelBean;
    }

    /////////////////////////////////////////
    public String selectCount() {

        dataModelBean = dataModelService.selectCount(dataModelBean.getDeptId());

        r = R.ok().put("data",dataModelBean);

        return SUCCESS;
    }


    /////////////////////////////////////////

    public R getR() {
        return r;
    }

    public void setR(R r) {
        this.r = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
