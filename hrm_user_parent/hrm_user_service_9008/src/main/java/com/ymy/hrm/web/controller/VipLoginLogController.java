package com.ymy.hrm.web.controller;

import com.ymy.hrm.service.IVipLoginLogService;
import com.ymy.hrm.domain.VipLoginLog;
import com.ymy.hrm.query.VipLoginLogQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vipLoginLog")
public class VipLoginLogController {
    @Autowired
    public IVipLoginLogService vipLoginLogService;

    /**
    * 保存和修改公用的
    * @param vipLoginLog  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody VipLoginLog vipLoginLog){
        try {
            if(vipLoginLog.getId()!=null){
                vipLoginLogService.updateById(vipLoginLog);
            }else{
                vipLoginLogService.insert(vipLoginLog);
            }
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！"+e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @RequestMapping(value="/{id}",method=RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id){
        try {
            vipLoginLogService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public VipLoginLog get(@PathVariable("id")Long id)
    {
        return vipLoginLogService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<VipLoginLog> list(){

        return vipLoginLogService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<VipLoginLog> json(@RequestBody VipLoginLogQuery query)
    {
        Page<VipLoginLog> page = new Page<VipLoginLog>(query.getPage(),query.getRows());
            page = vipLoginLogService.selectPage(page);
            return new PageList<VipLoginLog>(page.getTotal(),page.getRecords());
    }
}
