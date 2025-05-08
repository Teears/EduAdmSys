# SpringBoot注解

#### 处理请求地址映射

##### @SpringBootApplication

- @SpringBootApplication=@ComponentScan+@Configuration+@EnableAutoConfiguration
- @ComponentScan：注解在类上，扫描标注了@Controller等注解的类，注册为bean 。@ComponentScan 注解会自动扫描指定包下的全部标有 @Component注解的类，并注册成bean，当然包括 @Component下的子注解@Service、@Repository、@Controller。 

##### @Controller

- @Controller注解在类上，表示这是一个控制层。和@RequestMapping、@RequestParam等一些注解共同处理URL的映射。

##### @RequestController

- @RestController注解在类前，是一个组合注解，结合了 @ResponseBody 和 @Controller，将类中所有控制器的方法返回值转为json格式，并响应前端。而不会跳转界面，如果想跳转页面的话，就使用Controller。

##### @RequestMapping

- @RequestMapping注解作用在类或方法上，用来处理请求地址映射。
- Spring Boot也提供了简化版后的@RequestMapping
  1. @GetMapping
  2. @PostMapping
  3. @PutMapping
  4. @DeleteMapping
  5. @PatchMapping



```java
@RequestMapping(value="/test", method=RequestMethod.GET)
@RequestMapping(value="/test", method=RequestMethod.POST)
@GetMapping("/test")
@PostMapping("/test")
```



##### @RequestParam、@PathVariable

- 两者的作用都是将request里的参数的值绑定到contorl里的方

  

  参数里的，区别在于，URL写法不同。

- ```java
  @RestController
  public class HelloController {
      
      //url--http://host:port/hello?id=01
      @RequestMapping(value="/hello")
      //required=false 表示url中可以不穿入id参数，此时就使用默认参数
      public String sayHello(@RequestParam(value="id",required = false,defaultValue = "1") Integer id){
          return "id:"+id;
      }
  }
  ```

  

- ```java
  @RestController
  public class HelloController {
      
  	//url--http://host:port/hello/01/"xiaoming"
      @RequestMapping(value="/hello/{id}/{name}")
      public String sayHello(@PathVariable("id") Integer id,@PathVariable("name") String name){
          return "id:"+id+" name:"+name;
      }
  }
  ```

  

------

#### 声明Bean的注解

- @Component 没有明确角色的组件
- @Service 在业务逻辑层（Service层）使用
- @Repositpry 在数据访问层（dao层）使用
- @Controller 用于标注控制层组件

##### @Component

- @Component使用在不确定哪一个层的时候使用，可以作用在任何层次，把普通pojo实例化到spring容器
- 不推荐使用@Component注解，而应该使用它的扩展，如@Service、@Repository

##### @Service

- 注解在Service层的实现类上，然后就可以在Controller层直接通过@Autowired引用bean。

##### @Autowired、@Qualifier、@Resource

- @Resource = Autowired+Qualifier

- 如果接口实现只有一个，那么用@Autowierd就可以了，也不需要指定name

- 如果接口有多个实现，那么用@Resource，并指定name（建议）

- 也可以用@Autowired + @Qualifier +Qualifier的value值

  ```java
  //直接用@Autowired
  @RestController 
  @RequestMapping("/api")
  public class StudentController {
      @Autowired
      private StudentService studentService;
  
      @GetMapping("/getStudent")
      public List<Student> getStudent(){
          return studentService.getStudent();
      }
  }
  ```

  ```java
  //多个实现
  @RestController 
  @RequestMapping("/api")
  public class StudentController {
      @Autowired
      @Qualifier("studentServiceImpl")
      private StudentService studentService;
      
      @Resource(name = "studentServiceImpl1")
      private StudentService studentService1;
  
      @GetMapping("/getStudent")
      public List<Student> getStudent(){
          return studentService.getStudent();//调用了接口的第一个实现
      }
      @GetMapping("/getStudent1")
      public List<Student> getStudent(){
          return studentService1.getStudent();//调用了接口的第二个实现
      }
  }
  ```

  

