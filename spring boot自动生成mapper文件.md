# spring boot自动生成mapper文件

批量生成mapper配置文件：

- 连接数据库application.yml文件，以及匹配mapper.xml文件所处的位置:

  ```yml
  spring:
    datasource:
      username: root
      password: 123456
      url: jdbc:mysql://127.0.0.1:3306/leduo?serverTimezone=UTC
      driver-class-name: com.mysql.cj.jdbc.Driver
  mybatis:
    config-location:
      log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
      mapper-locations: classpath*:mappers/*Mapper.xml
  ```

- 在pom.xml文件夹中安装插件

```xml
<plugin>
    <groupId>org.mybatis.generator</groupId>
    <artifactId>mybatis-generator-maven-plugin</artifactId>
    <version>1.4.0</version>
    <dependencies>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <!--<scope>runtime</scope>-->
            <version>8.0.19</version>
        </dependency>
    </dependencies>
</plugin>
```

- 在java文件夹和resource文件夹下面创建生成的包的存储位置：

![image-20200502132452962](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\image-20200502132452962.png)

- 配置generatorConfig.xml文件内容如下：

```xml
<!DOCTYPE generatorConfiguration PUBLIC
        "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<generatorConfiguration>

    <context id="simple" targetRuntime="MyBatis3Simple">
<!--        <jdbcConnection driverClass="com.mysql.cj.jdbc.Driver"-->
<!--                        connectionURL="jdbc:mysql://127.0.0.1:3306/leduo?serverTimezone=UTC" />-->
        <jdbcConnection connectionURL="jdbc:mysql://127.0.0.1:3306/leduo?serverTimezone=UTC"
                        driverClass="com.mysql.cj.jdbc.Driver" password="123456" userId="root" />

        <javaModelGenerator targetPackage="com.example.automapper.entity" targetProject="src/main/java"/>

        <sqlMapGenerator targetPackage="mappers" targetProject="src/main/resources"/>

        <javaClientGenerator type="XMLMAPPER" targetPackage="com.example.automapper.mapper" targetProject="src/main/java"/>

        <table tableName="employee" />
        <table tableName="goods" />
        <table tableName="stock_in" />
        <table tableName="vender" />
        <table tableName="stock_out" />
        <table tableName="user" />
        <table tableName="user_log" />
        <table tableName="vender" />
<!--        <table tableName="orders" domainObjectName="Orders"-->
<!--               enableInsert="true"-->
<!--               enableCountByExample="false" enableUpdateByExample="false" enableDeleteByExample="false"-->
<!--               enableSelectByExample="false" selectByExampleQueryId="false">-->
<!--        </table>-->
    </context>
</generatorConfiguration>
```

- 下面的部分自己尝试（不是很确定）

![image-20200502132858767](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\image-20200502132858767.png)

运行maven项目的mybatis插件generator:

![image-20200502142321974](C:\Users\lenovo\AppData\Roaming\Typora\typora-user-images\image-20200502142321974.png)



使用的时候在各个文件的头上加上注解。

