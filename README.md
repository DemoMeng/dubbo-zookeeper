# Dubbo + Zookeeper
    1.provider:
        定义服务接口
        注入服务实现类，实现定义的接口

    2.consumer:
        引用服务接口





### 使用@Valid验证没有生效：
- 1.如果是springboot项目那么可以不需要导入@Valid相关依赖，其依赖都已经集成到了 spring-boot-starter-web 中
    * springboot2.3.0以下的不需要引入@valid对应依赖
    * springboot2.3.0版本以上需要引入，不然@Valid和@Validated都导进来
      
          <dependency>
              <groupId>org.springframework.boot</groupId>
              <artifactId>spring-boot-starter-validation</artifactId>
          </dependency>
          <dependency>
              <groupId>org.glassfish</groupId>
              <artifactId>jakarta.el</artifactId>
              <version>3.0.3</version>
          </dependency>

- 2.如果不是springboot项目，需要引入如下依赖：
  
        <dependency>
            <groupId>javax.validation</groupId>
            <artifactId>validation-api</artifactId>
            <version>1.1.0.Final</version>
        </dependency>
        
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>5.4.1.Final</version>
        </dependency>



### git无法忽略target文件：
  * 解决方法就是先把本地缓存删除（改变成未track状态），然后再提交 ，所以得先将远程仓库里面的项目拉下来，然后：
    
        1. git rm -r --cached .  
        2. git add .
        3. git commit -m 'update .gitignore'
        4. git push -u origin master




### springboot 扫描不到 bootstrap.yml 

![bootstrap.yml未被加载](z-images/bootstrap文件加载问题.png)
![bootstrap.yml已经被加载](z-images/bootstrap文件加载问题1.png)

    注意： 一般单独使用 Spring Boot 时，bootstrap.yml 文件一般是不会生效的，也就是没有 小绿叶 图标；
          如果使用了 Spring Cloud 组件 bootstrap.yml 才会生效，需要引入 spring-cloud-commons-dependencies 这个包依赖（或者是使用了springCloud的一个组件，如Eureka、Feign等他们都有这个依赖）。 
          另外 bootstrap.yml 的加载顺序优先于 application.yml。

    - bootstrap.yml (bootstrap.properties) 、 application.yml (application.properties)二者加载关系
      *  bootstrap.yml（bootstrap.properties）用来在程序引导时执行，应用于更加早期配置信息读取，如可以使用来配置application.yml中使用到参数等
      *  application.yml（application.properties) 应用程序特有配置信息，可以用来配置后续各个模块中需使用的公共参数等。
      *  bootstrap.yml 先于 application.yml 加载

    - bootstrap.yml 的使用场景：
      * 1.SpringCloud项目中， 需要在bootstrap.yml中配置配置中心地址（参考 https://github.com/DemoMeng/nacos-consumer-a）
      
    - bootstrap.yml 加载：
        
        父级：SpringApplicationContext -- > 加载 bootstrap文件 
        子级：ApplicationContext -- >  加载application文件
        
        原因： 当使用 Spring Cloud 的时候，配置信息一般是从 config server 加载的，为了取得配置信息（比如密码等），
              需要一些提早的引导配置。因此，把 config server 信息放在 bootstrap.yml，用来加载在这个时期真正需要的配置信息
        
        
## spring-cloud和springboot 版本对应信息： 
    - 官网入口： https://spring.io/projects/spring-cloud#learn

