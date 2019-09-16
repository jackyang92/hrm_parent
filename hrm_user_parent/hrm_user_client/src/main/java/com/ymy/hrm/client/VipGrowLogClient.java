package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipGrowLog;
import com.ymy.hrm.query.VipGrowLogQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "HRM-USER",configuration = FeignClientsConfiguration.class,
        fallbackFactory = VipGrowLogClientHystrixFallbackFactory.class)
@RequestMapping("/user/vipGrowLog")
public interface VipGrowLogClient {
    /**
     * 保存和修改公用的
     * @param vipGrowLog  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    AjaxResult save(VipGrowLog vipGrowLog);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    VipGrowLog get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    @RequestMapping("/list")
    public List<VipGrowLog> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    PageList<VipGrowLog> json(@RequestBody VipGrowLogQuery query);
}
