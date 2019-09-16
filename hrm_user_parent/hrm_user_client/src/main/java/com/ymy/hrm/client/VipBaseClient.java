package com.ymy.hrm.client;

import com.ymy.hrm.domain.VipBase;
import com.ymy.hrm.query.VipBaseQuery;
import com.ymy.hrm.util.AjaxResult;
import com.ymy.hrm.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "HRM-USER",configuration = FeignClientsConfiguration.class,
        fallbackFactory = VipBaseClientHystrixFallbackFactory.class)
@RequestMapping("/user/vipBase")
public interface VipBaseClient {
    /**
     * 保存和修改公用的
     * @param vipBase  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/add",method= RequestMethod.POST)
    AjaxResult save(VipBase vipBase);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    VipBase get(@RequestParam(value="id",required=true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    @RequestMapping("/list")
    public List<VipBase> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    PageList<VipBase> json(@RequestBody VipBaseQuery query);
}
