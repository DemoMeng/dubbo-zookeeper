# Dubbo + Zookeeper






# 使用@Valid验证没有生效：
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
