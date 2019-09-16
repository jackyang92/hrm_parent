package com.ymy.hrm.web.controller;

import com.ymy.hrm.service.IVipBaseService;
import com.ymy.hrm.domain.VipBase;
import com.ymy.hrm.query.VipBaseQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vipBase")
public class VipBaseController {
    @Autowired
    public IVipBaseService vipBaseService;

    /**
    * 保存和修改公用的
    * @param vipBase  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody VipBase vipBase){
        try {
            if(vipBase.getId()!=null){
                vipBaseService.updateById(vipBase);
            }else{
                vipBaseService.insert(vipBase);
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
            vipBaseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public VipBase get(@PathVariable("id")Long id)
    {
        return vipBaseService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<VipBase> list(){

        return vipBaseService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<VipBase> json(@RequestBody VipBaseQuery query)
    {
        Page<VipBase> page = new Page<VipBase>(query.getPage(),query.getRows());
            page = vipBaseService.selectPage(page);
            return new PageList<VipBase>(page.getTotal(),page.getRecords());
    }
}
