package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipCourseCollect;
import com.ymy.hrm.query.VipCourseCollectQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "HRM-USER",configuration = FeignClientsConfiguration.class,
        fallbackFactory = VipCourseCollectClientHystrixFallbackFactory.class)
@RequestMapping("/user/vipCourseCollect")
public interface VipCourseCollectClient {
    /**
     * 保存和修改公用的
     * @param vipCourseCollect  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    AjaxResult save(VipCourseCollect vipCourseCollect);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    VipCourseCollect get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    @RequestMapping("/list")
    public List<VipCourseCollect> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    PageList<VipCourseCollect> json(@RequestBody VipCourseCollectQuery query);
}
