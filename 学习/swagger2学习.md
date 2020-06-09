## 一、swagger2简介

swagger是一个规范和完整的框架，用于生成、描述、调用和可视化RESTful风格的**Web服务**

官网：swagger2简介



## 二、swagger2的特点

1. 及时性：接口变更后，能够及时准确的通知相关的前后台开发人员
2. 规范性：保证接口的规范性，如接口的地址，请求方式，参数及相应格式和错误信息
3. 一致性：接口信息一致，不会出现因开发人员拿到的文档版本不一致，而出现分歧
4. 可测性：直接在接口文档上进行测试，以方便理解业务



## 三、使用

### 1、引用

#### 1、使用官方提供的依赖

```xml
<!--swagger-->
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.5.0</version>
</dependency>
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.5.0</version>
</dependency>
```

springfox-swagger2是API获取的包，springfox-swagger-ui是官方提供的一个ui界面，这个界面可以自定义，默认是官方的，对于安全问题，以及ui路由设置需要着重考虑

swagger2的配置configuration（一般在根目录下进行配置）

```java
package pers.lansir.swagger2demo00;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// 让Spring来加载本配置
@Configuration
// 启用Swagger
@EnableSwagger2
public class Swagger2 {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()).select()
                // 指定扫描的包结构
                .apis(RequestHandlerSelectors.basePackage("pers.lansir.swagger2demo00"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 你的文档标题
                .title("Swagger2文档生成使用")
                // 你的描述
                .description("对接文档,查看方法")
                // 服务条款的网址
                .termsOfServiceUrl("http://www.baidu.com/about/about.html")
                // 联系信息
                .contact(//
                        new Contact("LanSir",// 你的姓名
                                "http://www.baidu.com", // 你的网址java
                                "helloworldlgr@gmail.com"))// 你的邮箱
                // 文档版本
                .version("1.0")
                .build();
    }

}
```

<!--注：RequestHandlerSelectors.basePackage("pers.lansir.swagger2demo00")括号中的路径为swagger扫描的路径，swagger只会扫描这个配置的路径-->

#### 2、使用第三方提供的依赖

```xml
	<dependency>
		<groupId>com.spring4all</groupId>
		<artifactId>swagger-spring-boot-starter</artifactId>
		<version>1.7.0.RELEASE</version>xml
	</dependency>
```

在Spring Boot项目的启动类上添加@EnableSwagger2Doc注解，就可以直接使用



### 2、具体使用

1、在API上做一些声明