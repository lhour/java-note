@Component ：标准一个普通的spring Bean类。
@Repository：标注一个DAO组件类。
@Service：标注一个业务逻辑组件类。
@Controller：标注一个控制器组件类。

当一个组件代表数据访问层（DAO）的时候，我们使用@Repository进行注解
```java
@Repository
public class HappyDaoImpl implements HappyDao{
    private final static Logger LOGGER = LoggerFactory.getLogger(HappyDaoImpl.class);
        public void  club(){
            //do something ,like drinking and singing
        }
}
```

当一个组件代表业务层时，我们使用@Service进行注解
```java
@Service(value="goodClubService")
//使用@Service注解不加value ,默认名称是clubService
public class ClubServiceImpl implements ClubService {
    @Autowired
    private ClubDao clubDao;
  
    public void doHappy(){
        //do some Happy
    }
 }
```

当一个组件作为前端交互的控制层，使用@Controller进行注解
```java
@Controller
public class HappyController {

    private ClubService clubService;
    
	// Control the people entering the Club
	// do something
}
/*Controller相关的注解下面进行详细讲解，这里简单引入@Controller*/
```

配置文件
```xml
<!-- 自动扫描指定包及其子包下的所有Bean类 -->
<context:component-scan base-package="org.springframework.*"/>
```

@Autowired：属于Spring 的org.springframework.beans.factory.annotation包下,
可用于为类的属性、构造器、方法进行注值 
@Resource：不属于spring的注解，而是来自于JSR-250位于java.annotation包下，
使用该annotation为目标bean指定协作者Bean。 
@PostConstruct 和 @PreDestroy 方法 实现初始化和销毁bean之前进行的操作

### @Autowired
```java
@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, 
ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    boolean required() default true;
}
```

```java
@Controller
public class HappyController {
	@Autowired //默认依赖的ClubDao 对象（Bean）必须存在
	//@Autowired(required = false) 改变默认方式
	@Qualifier("goodClubService")
    private ClubService clubService;
    
	// Control the people entering the Club
	// do something
}
```
