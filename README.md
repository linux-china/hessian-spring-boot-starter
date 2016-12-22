hessian-spring-boot-start
=========================
Spring boot starter hessian。

### 如何使用

* 在Spring Boot项目的pom.xml中添加以下依赖:

          <dependency>
                     <groupId>com.mvnsearch.spring.boot</groupId>
                     <artifactId>hessian-boot-starter</artifactId>
                     <version>1.0.0-SNAPSHOT</version>
          </dependency>

* 创建对应的hessian service bean, 这里请注意bean的名称。 代码如下:

          @Component(value = "userService")
          @HessianService(serviceInterface = UserService.class)
          public class UserServiceImpl implements UserService {
              public String findNick(Integer id) {
                  return "nick:" + id;
              }
          }
          
* hessian服务最终会以"/userService" 发布出去，客户端代码调用如下，当然你可以可以创建一个Bean。

      String url = "http://localhost:8080/userService";
      HessianProxyFactory factory = new HessianProxyFactory();
      UserService basic = (UserService) factory.create(UserService.class, url);

### 参考文档

* Hessian: http://hessian.caucho.com/

