[Git Repo](https://github.com/in28minutes/spring-master-class)  
[Quick start](https://github.com/in28minutes/in28minutes-initiatives/blob/master/The-in28Minutes-TroubleshootingGuide-And-FAQ/quick-start.md)  

# Basic Spring

**Inversion of controll** e фреймуърка да разбере кой клас от какви депендънсита се нуждае да ги намери и предостави.  
Т.е. тази работа я върши фреймуърка, а не самите класове на приложението.  
**IOC container** e генерично име и може да бъде имплементирано по различни начини - Application context и Bean Factory.  
Application context e подобрен Bean Factory с добавени i18n, АОР и WebApplicationContext. Освен в случаите с много ограничена памет се препоръчва да се ползва Application context.
Работата на Spring Framework е да осъществи Depenedency injection and testability.
Spring Boot добавя автоматични конфигурации за повечето използвани неща (jdbc, actuator, log4j2 etc.)  


>	IOC container е всяко нещо, което имлементира Inversion of controll.  
	Application context e IOC container.  
	Идеята е да вдигне и менажирa всички бийнове. (Component, Repository, Service също са бийнове).


Имаш 3 въпроса които Спринг трябва да знае:
1. Кои са бийновете. (Component, Service, Repository, Controller и тия описани в Configuration с Bean).
2. Кои са депендънситата на бийновете (Autowired).
3. Къде да търси за бийнове (Component scan) `@SpringBootApplication` е началото на пакета за търсене, когато всичко е на едно място.

Като стратира апп-а първо прави компонент скан в пакиджите и събпакиджите обаче само надоло. За това класа (Мейн-а) е добре да е в най-горното ниво.  
За да търси и на други места, в Мейн класа освен `@SpringBootApplication` трябва да имаш и `@ComponentScan("пътя с бийновете")`
Търси за класове с анотации.
Започва да съзадава бийновете и да идентифицира депендънситата.  
Когато има `@SpringBootApplication` създава и AutoConfiguration.  
Според какво намира започва да инициализира необходимите класове.


Когато имаш 2 компонента от даден тип Спринг не знае кой да използва. `@Primary` му показва кой да вземе, иначе гърми.
Инджектнал си нещо с интерфейс без да укажеш коя имплементация използваш. т.е. Имаш 1 интерфейс (тип) и няколко имплементаци (компонента).
`@Primary` има по-висок приоритет. Дoри да си сложил интерфейса и написал иметo на конкретна имплементация (НещоСиImpl), ако имаш `@Primary` на някоя от имплементациите ще вземе нея.

Другия вариант е вместо `@Primary` да използваш `@Qualifier("име")` и после като го инджектваш пак да добавиш `@Qualifier("име")`.
И в двата случая трябва да пишеш допълнителни анотации. За това препоръката е да инджектваш с името на конкретната имплементация.

Като не си намери анотация за компонент а нещото е `@Autowired` гърми със съобщение че не може да намери бийна.
(намира че нещо е в депендънси но не може да намери дадения бийн).

При грешки първо виж за правилната комбинация от `@Component`, `@Autowired`, `@Primary`

Освен конструктор инджекшън има и сетър инджекшън, което е дългото изписване на `@Autowired`.

Старо: За всички задължителни депендънситата (без които класа не може да работи) да се използва конструктор инджекшън, за останалите `@Autowired`.  
Ново: всичко е с `@Autowired`

---

## Scope
По дефолт прави един бийн и всеки път като ти потрябва ти го дава него (singelton).  
Ако всеки път като поискаш получаваш нов бийн - prototype:

```java
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
```

Когато искаш да получиш прототип, но минаваш през сингълтън трябва да конфигурираш прокси.  
Имаш ДАО което депендва на кънекшън.  
ДАО-то е сингълтън, а кънекшъна прототип - ще получиш сингълтън.  
Така получаваш сингълтън ДАО и прототип кънекшън.

```Java
	@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.TARGET_CLASS)
```

В Спринг сингълтън означава 1 инстанция за целия Application context, но може да има много в рамките на JVM.
В Java Design patterns (Gang of four) singelton означава 1 инстанция за цялата JVM.

---

## Lifecycle
След като бийна е създаден и инциализиран като депендънси ще бъде извикан метода анотиран с: `@PostConstruct`.  
Преди да се махне бийна от контекста се изпълнява метода анотиран с: `@PreDestroy`.

---

## CDI
CDI - Context and Dependency Injection
Това е интерфейса който се грижи за цялата система на депендъси инджекжшън и IOC.
Определя анотации, поведение и т.н.
Спринг имплементира CDI.
Ако искаш да създадеш свои анотации минаваш през CDI.
Можеш да използваш директно анотациите на CDI.
@Autowired -> @Inject
@Component -> @Named
CDI e JavaEE standart

за да го ползваш в pom:
```xml
	<dependency>
		<groupId>javax.inject</groupId>
		<artifactId>javax.inject</artifactId>
		<version>1</version>
	</dependency>
```

---


## Махане на SpringBoot

Ако не искаш да ползваш Spring Boot, а само IOC концепцията.  
От pom-а: 
Махаш spring-boot-starter.  
Добавяш:  
javax.inject от горе  
spring-core – дефинира мениджмънта на депендъситата   
spring-context – управлява контекста  

```xml
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-core</artifactId>
        <version>5.3.6</version>
    </dependency>

```

```xml
	<dependency>
	    <groupId>org.springframework</groupId>
	    <artifactId>spring-context</artifactId>
	    <version>5.2.8.RELEASE</version>
    	<scope>compile</scope>
    </dependency>
```

На Main-а махаш `@SpringBootApplication` и слагаш `@Configuration` и `@ComponentScan`
Трябва да създадеш контекста чрез `AnnotationConfigApplicationContext` вместо с `run`.

```Java
@Configuration
@ComponentScan("com.milko.elastic")
public class OurClass {
	
	public static void main(String[] args) {
	
			AnnotationConfigApplicationContext applicationContext =
					new AnnotationConfigApplicationContext(OurClass.class);
					
						make some things...
					
			applicationContext.close();
	}
}
```

---

  
## Използване на XML за настройка

Няма анотации.  
Създаваш си applicationContext.xml файл в resources папката със специфичен синтаксис.  
Първия ред дефинира клас-1, втория ред дефинира клас-2 и депендънсито.
Клас-1 ще бъде Autowire в клас-2

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="клас-1" class="къде се намира от package реда както е"></bean>

    <bean id="клас-2" class="къде се намира от package реда както е">
    		<property name="клас-1" ref="клас-1"/>
    </bean>
</beans>
```

Трябва да заредиш XML конфигурацията като контекст с `ClassPathXmlApplicationContext`.   
`try` блока без `catch` затваря контекста автоматично независимо дали се е заредил контекста или не

```Java
	public class OurClass {
	
		public static void main(String[] args) {
	
			try (ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
					"applicationContext.xml")) {
	
				make some things...
			}
		}
	}
```

### За да използваш component scan с XML дефиниция трябва да добавиш и схема за component scan в хедъра на XML-а.

```XML
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:context="http://www.springframework.org/schema/context"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        http://www.springframework.org/schema/context/spring-context.xsd">
	
	<context:component-scan base-package="къде се намира от package"/>

    <bean id="клас-1" class="къде се намира от package реда както е"></bean>

    <bean id="клас-2" class="къде се намира от package реда както е">
    		<property name="клас-1" ref="клас-1"/>
    </bean>

</beans>
```

---


## Четене на данни от външни файлове
Правиш си файл "app.properties" в ресурс папката и в Main-а добавяш `@PropertySource("classpath:app.properties")`.   
После четеш с:
```Java
	@Value("${external.service.url}")
	private String url;
```
Идеята е, че имаш изнесен файл със стойности за променливите и на тест може да са едни, а на продукшън други, без да се налага да променяш нещо в кода всеки път преди деплой.

---


# Тестване
[JUnit 5 Changes](https://github.com/in28minutes/in28minutes-initiatives/blob/master/The-in28Minutes-TroubleshootingGuide-And-FAQ/junit5.md)  

## Junut 4
`@BeforeClass` и `@AfterClass` трябва да са статични.

---

## Mockito
Работи през интерфейс.  
Не ти трябва да взимаш контекста.

---

## Тестване със Spring

В pom-а трябва да има:
```XML
	<dependency>	
		<groupId>org.springframework</groupId>
		<artifactId>spring-test</artifactId>
		<scope>test</scope>
	</dependency>
	<dependency>
		<groupId>junit</groupId>
		<artifactId>junit</artifactId>
		<scope>test</scope>
	</dependency>
```

### Зареждане на контекста в тестовия метод (с анотации):
```Java
//Load the context
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class) // Или този с анотацията @Configuration
public class BinarySearchTest {

	// Inject-ваш каквото ти трябва
	@Autowired
	BinarySearchImpl binarySearch;

	@Test
	public void testBasicScenario() {
		
		// изпълняваш
		int actualResult = binarySearch.binarySearch(new int[] {}, 5);

		// проверяваш
		assertEquals(3, actualResult);
	}
}
```

### Зареждане на контекста в тестовия метод (с XML):
```Java
@RunWith(SpringRunner.class)
@ContextConfiguration(locations="/testContext.xml") // пътя до конфигурационния XML
public class BinarySearchTest {

	@Autowired
	BinarySearchImpl binarySearch;

	@Test
	public void testBasicScenario() {
		...същото като горе
	}
}
```

---

# Actuator

За да го има:

```XML
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>

	<dependency>
		<groupId>org.springframework.data</groupId>
		<artifactId>spring-data-rest-hal-browser</artifactId>
	</dependency>
```

в /src/main/resources/application.properties трябва да има:  
`management.endpoints.web.exposure.include=*`

Използва се:  
`localhost:8080/actuator`
или HAL browser:  
`localhost:8080/browser/index.html#/`
и пишеш `/actuator` в Explorer полето

---


# Dev Tools

Съкращава времето за рестарт при малки промени.  
Не зарежда всички депендънсита, а само апп-а.

```XML
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
	</dependency>
```

---


# AOP

[Repo](https://github.com/in28minutes/spring-master-class/tree/master/03-spring-aop)

>	AOP -> Aspect oriented programming.

_AOP dependency is not available on the Spring Initializr website anymore._

```XML
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-aop</artifactId>
	</dependency>
```

AspectJ е по-силно от Spring AOP.  
Използваш AOP за да прекъснеш всякакви извиквания на бийновете.  
Loggingh, Security, Performence tracking взаимодействат между всички слоеве (@Servive, @Repository etc.) на апп-а - Cross-cutting concerns.  
Имплементираш на едно място и проверяваш например дали юзера има достъп за всички класове и методи от целия пекидж.  

Когато Мейн класа имплементира `CommandLineRunner` получаваш `run`-методa.  
Също така вече не е нужно да са ти статични методите в Мейн класа, можеш и да `@Autowired` други бийнове.

```Java
	@SpringBootApplication
	public class SpringAopApplication implements CommandLineRunner{
		
		private Logger logger = LoggerFactory.getLogger(this.getClass());
	
		@Autowired
		private Business1 business1;
	
		@Autowired
		private Business2 business2;
		
		public static void main(String[] args) {
			SpringApplication.run(SpringAopApplication.class, args);
		}
	
		@Override
		public void run(String... args) throws Exception {
			logger.info(business1.calculateSomething());
			logger.info(business2.calculateSomething());
		}
	}
```

## Терминология

*PointCut*: какво и къде ще прекъснеш -> `"execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))"`  
*Advise*: какво се изпълнява, когато настъпи прекъсването.  
*Aspect*: комбинацията от _PointCut_ и _Advise_ -> Какво ще прекъснеш и какво ще изпълниш.  
*JoinPoint*: специфична инстанция на прекъсването. Носи информацията от прекъснатия метод.  
*Weaving*: процеса на прекъсване и изпълняване на нещо.  
*Weaver*: фреймуърка, който го имплементира.  

```JAVA
	@Aspect
	@Configuration
	public class UseAccessAspect {
	
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Before("execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")
		public void before(JoinPoint joinPoint){
			
			make some things...
			logger.info(" Check for user access ");
		}
	}
```

---


### Before

Трябва да му кажеш кои методи прекъсваш.  
Првиш си клас с анотации `@Aspect` и `@Configuration`

На метода, който ще изпълняваш, когато прекъсваш: 

> @Before - прекъсва преди изпълнението. 

`@Before("execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")`  
1-ва звезда -> всичко в пекиджа  
`com.in28minutes.spring.aop.springaop.business` -> пекиджа  
`.*` -> всички класове  
второ `.*` -> всички методи  
`(..)` -> извикани с всякакъв вид аргументи  

`@Before("execution(* com.in28minutes.spring.aop.springaop..*.*(..))")`  
ще бъдат прекъсвани всички извиквания за апп-а

---


### After

Можеш да вземеш резултата от *успешното* изпълнение на метод:  

```Java
1	@Aspect
2	@Configuration
3	public class AfterAopAspect {
4	
5		private Logger logger = LoggerFactory.getLogger(this.getClass());
6	
7		@AfterReturning(
8				value = "execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))", 
9				returning = "result"
10				)
11		public void afterReturning(JoinPoint joinPoint, Object result) {
12			logger.info("{} returned with value {}", joinPoint, result);
13		}
14		
15		@After(value = "execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")
16		public void after(JoinPoint joinPoint) {
17			logger.info("after execution of {}", joinPoint);
18		}
19	}
```

7-10 Map-ва резултата към обекта Object result. Ти си знаеш какво е това и какво да го правиш по-нататък.  
11 Подаваш резултата на метода за изпълнение (Advise).  

> @After - прекъсва след изпълнението, без значение дали е гръмнало или не.

> @AfterReturning - визма резултата от метода, ако е минал успешно.

> @AfterThrowing - прекъсва Exceptions.

---

### Around

```Java
	@Aspect
	@Configuration
	public class MethodExecutionCalculationAspect {
	
		private Logger logger = LoggerFactory.getLogger(this.getClass());
	
		@Around("com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
		public void around(ProceedingJoinPoint joinPoint) throws Throwable {
			long startTime = System.currentTimeMillis();
	
			joinPoint.proceed();
	
			long timeTaken = System.currentTimeMillis() - startTime;
			logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
		}
	}
```

С `@Around` прекъсваш преди изпълнението.  
Взимаш текущото време преди.   
ProceedingJoinPoint - позволява ти да изпълниш метода. `joinPoint.proceed();`   
Взимаш текущото време след и калкулираш.   

---

## Best practices

Можеш да създадеш клас с Point Cuts и да ги извикваш по-лесно и от много места.  
Празен метод с `@Pointcut` анотация и израз.  

```Java
	public class CommonJoinPointConfig {
		
		@Pointcut("execution(* com.in28minutes.spring.aop.springaop.data.*.*(..))")
		public void dataLayerExecution(){} // всичко в data package
		
		@Pointcut("execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")
		public void businessLayerExecution(){} // всичко в business package
		
		@Pointcut("dataLayerExecution() && businessLayerExecution()")
		public void allLayerExecution(){} // всичко от data и business
		
		@Pointcut("bean(*dao*)")
		public void beanContainingDao(){} // всичко, което съдържа dao в името си
		
		@Pointcut("within(com.in28minutes.spring.aop.springaop.data..*)")
		public void dataLayerExecutionWithWithin(){} // всичко в data package
	
		@Pointcut("@annotation(com.in28minutes.spring.aop.springaop.aspect.TrackTime)")
		public void trackTimeAnnotation(){}
	
	}
```

После където искаш да го изпълниш даваш пътя до `CommonJoinPointConfig` класа и нужния метод:  

```Java
	@Aspect
	@Configuration
	public class UserAccessAspect {
		
		private Logger logger = LoggerFactory.getLogger(this.getClass());
		
		@Before("com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.dataLayerExecution()")
		public void before(JoinPoint joinPoint){
	
			logger.info(" Check for user access ");
			logger.info(" Allowed execution for {}", joinPoint);
		}
	}
```

---

## Custom Annotation

Създаваме custom анотация за измерване на времето за изпълнение `@TrackTime`  

```Java
	@Target(ElementType.METHOD) // Само на методи, без класове
	@Retention(RetentionPolicy.RUNTIME) // Изпълнява се през цялото време, когато работи апп-а
	public @interface TrackTime { // Името на анотацията
	
	}
```

Дефинираш `@PointCut` с префикса "@annotation" в класа, където описваш PointCuts:  
```Java
	@Pointcut("@annotation(com.in28minutes.spring.aop.springaop.aspect.TrackTime)")
	public void trackTimeAnnotation(){}
```  

Добавяш логиката.
```Java
	@Aspect
	@Configuration
	public class MethodExecutionCalculationAspect {
	
		private Logger logger = LoggerFactory.getLogger(this.getClass());
	
		@Around("пътя до класа с PointCut описанието + името на метода")
		public void around(ProceedingJoinPoint joinPoint) throws Throwable {
			long startTime = System.currentTimeMillis();
	
			joinPoint.proceed();
	
			long timeTaken = System.currentTimeMillis() - startTime;
			logger.info("Time Taken by {} is {}", joinPoint, timeTaken);
		}
	}
```

---

# Database access
[Repo](https://github.com/in28minutes/spring-master-class/tree/master/04-spring-jdbc-to-jpa)


# JDBC



До 77. Step 01 - Setting up a project with JDBC, JPA, H2 and Web Dependencies















