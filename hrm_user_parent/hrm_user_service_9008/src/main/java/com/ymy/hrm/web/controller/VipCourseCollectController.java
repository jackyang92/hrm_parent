package com.ymy.hrm.web.controller;

import com.ymy.hrm.service.IVipCourseCollectService;
import com.ymy.hrm.domain.VipCourseCollect;
import com.ymy.hrm.query.VipCourseCollectQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vipCourseCollect")
public class VipCourseCollectController {
    @Autowired
    public IVipCourseCollectService vipCourseCollectService;

    /**
    * 保存和修改公用的
    * @param vipCourseCollect  传递的实体
    * @return Ajaxresult转换结果
    */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult save(@RequestBody VipCourseCollect vipCourseCollect){
        try {
            if(vipCourseCollect.getId()!=null){
                vipCourseCollectService.updateById(vipCourseCollect);
            }else{
                vipCourseCollectService.insert(vipCourseCollect);
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
            vipCourseCollectService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
        e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！"+e.getMessage());
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public VipCourseCollect get(@PathVariable("id")Long id)
    {
        return vipCourseCollectService.selectById(id);
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<VipCourseCollect> list(){

        return vipCourseCollectService.selectList(null);
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    public PageList<VipCourseCollect> json(@RequestBody VipCourseCollectQuery query)
    {
        Page<VipCourseCollect> page = new Page<VipCourseCollect>(query.getPage(),query.getRows());
            page = vipCourseCollectService.selectPage(page);
            return new PageList<VipCourseCollect>(page.getTotal(),page.getRecords());
    }
}
