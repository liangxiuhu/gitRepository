# 一、Spring Boot 入门

## 1、Spring Boot简介

- 简化Spring应用开发的一个框架
- 整个Spring技术栈的一个大整合
- 是J2EE开发的一站式解决方案

## 2、微服务

2014年，martin fowler

微服务：是一种架构风格

一个应用就应该是一组小型服务；可以通过HTTP的方式进行互通



每一个功能元素最终都是一个可独立替换和独立升级的软件单元



## 3、Spring Boot优点

​	-快速创建独立运行的Spring项目以及主流框架集成

​	-使用嵌入式的Servlet容器，应用无序达成war包

​	-starters自动依赖与版本控制

​	-大量的自动配置，简化开发，也可修改默认值

​	-无序配置XML，无代码生成，开箱即用

​	-准生产环境的运行时应用监控

​	-与云计算的天然集成

## 4、环境约束

- jdk1.8:Spring Boot官网推荐jdk1.7及以上版本
- maven3.x:maven3.3及以上版本
- IntellijIDEA2017
- SpringBoot1.5.9.RELEASE

## 5、统一环境

### 1、maven设置

给maven的settings.xml配置文件的profiles标签添加(代表使用jdk1.8进行打包)

```
<profile>
      <id>jdk-1.8</id>
      <activation>
		<activeByDefault>true</activeByDefault>
		<jdk>1.8</jdk>
	  </activation>
      <properties>
        <maven.compiler.source>1.8</maven.compiler.source>
		<maven.compiler.target>1.8</maven.compiler.target>
		<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
      </properties>
</profile>
```

### 2、IDEA设置

![image-20200404140116805](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200404140116805.png)



![image-20200404140136535](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200404140136535.png)

![image-20200404140203393](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200404140203393.png)



## 6、Spring Boot HelloWorld

一个功能

浏览器发送hello请求，服务器接收请求并处理，响应Hello World字符串；

### 1、创建一个maven工程(jar)

### 2、导入Spring Boot相关的依赖

```
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>1.5.9.RELEASE</version>
    </parent>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

    </dependencies>
```

### 3、编写一个主程序：启动Spring Boot应用

```
/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args){

        //Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}

```

### 4、编写对于的Controller、Service

```
@Controller
public class HelloController {
    //@RequestMapping("/hello")  接收来自浏览器的hello请求
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){

        return "Hello World";
    }
}
```

### 5、运行主程序

访问http://localhost:8080/hello

### 6、简化部署

```
<!-- 这个插件，可以将应用打包成一个可执行的jar包；-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
```

将这个应用打成jar包，直接使用java -jar的命令进行执行



## 7、Hello World探究

### 1、POM文件

#### 1、父项目

```xml
	<parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.2.1.RELEASE</version>
        <relativePath/>
    </parent>
它的父项目是
  <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-dependencies</artifactId>
    <version>2.2.1.RELEASE</version>
    <relativePath>../spring-boot-dependencies</relativePath>
  </parent>
它来真正管理Spring Boot应用里面的所有依赖版本;
```

Spring Boot的版本仲裁依赖

以后我们导入依赖默认是不需要些版本(没有在dependencies里面管理的依赖自然需要声明版本号)

#### 2、启动器

```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
  	<artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

spring-boot-starter-web:

​	spring-boot-starter:spring-boot场景启动器;帮我们导入了web模块正常运行嗦依赖的组件

Spring Boot将所有的功能场景都抽取出来，做成了一个个的starters(启动器)，只需要再这些项目里面引入这些starter相关场景的所有依赖就都会导入进来，要什么功能就导入什么场景的启动器

### 2、主程序类，主入口类

```java
/**
 * @SpringBootApplication 来标注一个主程序类，说明这是一个Spring Boot应用
 */
@SpringBootApplication
public class HelloWorldMainApplication {

    public static void main(String[] args){

        //Spring应用启动起来
        SpringApplication.run(HelloWorldMainApplication.class,args);
    }
}
```

**@SpringBootApplication**：Spring Boot应用标注再某个类上说明这个类是Spring Boot的著配置类，

Spring Boot就应该运行这个类的main方法来启动SpringBoot应用

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
)
public @interface SpringBootApplication {
```

@**SpringBootConfiguration**：Spring Boot的配置类

​	标注在某个类上，表示这是一个Spring Boot的配置类

​	@**Configuration**：配置类上来标注这个注解

​			配置类-----配置文件：配置类也是容器中的一个组件：@Component

**@EnableAutoConfiguration**:开启自动配置功能

​	以前我们需要配置的东西，Spring Boot帮我们自动配置;**@EnableAutoConfiguration**:告诉SPringleBoot开启自动配置功能，这样自动配置才能生效;

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({AutoConfigurationImportSelector.class})
public @interface EnableAutoConfiguration {
```

**@AutoConfigurationPackag**:自动配置包

​	@Import({AutoConfigurationPackage.Registrar.class}):

​	Spring的底层注解@Import，给容器中导入一个组件；导入的组件由AutoConfigurationPackage.Registrar.class：

​	将主配置类(@SpringBootApplication标注的类)的所在包及下面所有子包里面的所有组件扫描到Spring容器中

​	@**Import**({AutoConfigurationImportSelector.class}):

​		给容器中导入组件：

​			AutoConfigurationImportSelector：导入哪些组件的选择器

​			将所有需要导入的组件以全类名的方式返回；这些组件就会被添加到容器中

​			会给容器中导入非常多的自动配置类(xxxAutoConfiguration):就是给容器中导入这个场景需要的所有组件，并配置好这些组件；

​			有了自动配置类，免去了我们手动编写配置主入功能组件等的工作；

​				SpringFactoriesLoader.loadFactoryNames(EnableAutoConfiguration.class,classLoader)：

Spring Boot在启动的时候从类路径下的META-INF/spring.factories中获取EnableAutoConfiguration指定的值，将这些值作为自动配置类导入到容器中，自动配置类就生效，帮我们进行自动配置工作。-----以前需要我们自己配置的东西，自动配置类都帮我们做了。

J2EE的整体整合解决方案和自动配置都在spring-boot-autoconfigure-2.2.1.RELEASE.jar

​				

## 8、使用Spring Initializer快速创建Spring Boot项目

IDE都支持使用Spring的项目创建向导快速创建一个Spring Boot项目![image-20200406144208100](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200406144208100.png)

![image-20200406144351886](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200406144351886.png)

选择我们需要的模块，向导回联网创建Spring Boot项目

![image-20200406144549454](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200406144549454.png)

无用的文件可以进行删除

![image-20200406144932540](C:\Users\梁修虎\AppData\Roaming\Typora\typora-user-images\image-20200406144932540.png)

默认生成的Spring Boot项目：

- ​	主程序已经生成好了，我们只需要关注我们自己的业务逻辑
- resource文件夹中目录结构
  - static:保存所有的静态资源：js css image
  - templates:保存所有的模板页面(Spring Boot默认jar包使用嵌入式的的Tomcat,默认不支持jsp页面)；可以使用模板引擎(freemarker,thymeleaf);
  - application.properties:Spring Boot应用的配置文件(可以修改Spring Boot已经默认的配置)

# 二、配置文件

## 一、配置文件

Spring Boot使用一个全局的配置文件,配置文件的名字是固定的

- application.properties
- application.yml

配置文件的作用：修改SpringBoot自动配置的默认值，Spring Boot在底层给我们自动配置好



YAML(YAML Ain't Markup Language)

​	YAML A Markup Language:YAML是一个标记语言

​	YAML isn't Markup Language:YAML不是一个标记语言

标记语言：

​	以前的配置文件：大多数使用的是**xxxx.xml**文件；

​	YAML：**以数据为中心**，比json,xml等更适合做配置文件

YAML:配置示例

```
server:
  port: 8081
```

XML:

```xml
<server>
	<port>8081</port>
</server>
```

## 二、YAML语法

### 1、基本语法

key:(空格)value  :表示一对键值对(空格必须有)

以**空格**的缩进来进行控制层级关系，只要是左对齐的一列数据，都是统一层级的

```yaml
server:
   port: 8081
   path: /hello
```

属性和值也是大小写敏感的

### 2、值的写法

**字面量：普通的值(数字，字符串，布尔)**

​	k: v  :字面直接来写：

​			字符串默认不用加上引号；

​			“”引起来的字符串：会转义字符串里的特殊字符；特殊字符会作为本身想表达的意思

​					name: "zhangsan \n list": 输出  zhangsan 换行 list

​			‘’引起来的字符串：不会转义特殊字符，特殊字符最终只是一个普通的字符串数据

​					name: 'zhangsan \n list': 输出  zhangsan \n list

**对象、Map(属性和值)(键值对)**：

 k: v  :在下一行来写对象的属性和值的关系；注意缩进

​	对象还是k: v 的方式

```yaml
server:
   port: 8081
   path: /hello
```

行内写法

```yaml
server: {port: 8081,path: /hello}
```

**数组(List,Set)**:

用- 值 表示数组中的一个元素

```yaml
pets:
  - cat
  - dog
  - pig
```

行内写法

```yaml
pets: [cat,dog,pig]
```



### 3、配置文件值注入

配置文件

```yaml
person:
  lastName: zhangsan
  age: 27
  boss: false
  brithday: 1993/12/24
  maps: {k1: v1,k2: 12}
  lists:
    - lisi
    - wangwu
  dog:
    name: gou
    age: 2

```

JavaBean：

```java
/**
 * j将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties 告诉SpringBoot将本类中所有属性和配置文件中的相关配置进行绑定
 *  prefix = "person"  配置文件中person下面的所有属性进行一一映射
 *  只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
 */
@Component
@ConfigurationProperties(prefix = "person")
public class Person {

    private String lastName;
    private int age;
    private Boolean boss;
    private Date brithday;

    private Map<String,Object> maps;

    private List<Object> lists;

    private Dog dog;
```

我们可以导入配置文件处理器，以后编写配置就有提示了

```xml
<!--  导入配置文件处理器，配置文件进行导入就会有提示 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>
```

