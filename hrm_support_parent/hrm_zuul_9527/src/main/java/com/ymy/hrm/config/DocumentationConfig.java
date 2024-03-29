package com.ymy.hrm.config;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class DocumentationConfig implements SwaggerResourcesProvider {
    @Override
    public List<SwaggerResource> get() {
        List resources = new ArrayList<>();
        resources.add(swaggerResource("系统管理", "/services/management/v2/api-docs", "2.0"));
        resources.add(swaggerResource("课程管理", "/services/course/v2/api-docs", "2.0"));
        resources.add(swaggerResource("分布式文件", "/services/fastdfs/v2/api-docs", "2.0"));
        resources.add(swaggerResource("分布式全文检索", "/services/es/v2/api-docs", "2.0"));
        resources.add(swaggerResource("中央缓存系统", "/services/redis/v2/api-docs", "2.0"));
        resources.add(swaggerResource("静态化优化方案系统", "/services/page/v2/api-docs", "2.0"));
        resources.add(swaggerResource("页面代理系统", "/services/pageAgent/v2/api-docs", "2.0"));
        resources.add(swaggerResource("用户中心系统", "/services/user/v2/api-docs", "2.0"));
        return resources;

    }

    private SwaggerResource swaggerResource(String name, String location, String version) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(version);
        return swaggerResource;
    }
}