package com.ymy.hrm.web.controller;

import com.ymy.hrm.service.IVipCourseViewService;
import com.ymy.hrm.domain.VipCourseView;
import com.ymy.hrm.query.VipCourseViewQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vipCourseView")
public class VipCourseViewController {
    @Autowired
    public IVipCourseViewService vipCourseViewService;

    /**
    * 保存和修改公用的
    * @param vipCourseView  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody VipCourseView vipCourseView){
        try {
            if(vipCourseView.getId()!=null){
                vipCourseViewService.updateById(vipCourseView);
            }else{
                vipCourseViewService.insert(vipCourseView);
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
            vipCourseViewService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public VipCourseView get(@PathVariable("id")Long id)
    {
        return vipCourseViewService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<VipCourseView> list(){

        return vipCourseViewService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<VipCourseView> json(@RequestBody VipCourseViewQuery query)
    {
        Page<VipCourseView> page = new Page<VipCourseView>(query.getPage(),query.getRows());
            page = vipCourseViewService.selectPage(page);
            return new PageList<VipCourseView>(page.getTotal(),page.getRecords());
    }
}
