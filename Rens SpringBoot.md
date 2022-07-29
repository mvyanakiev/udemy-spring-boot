В началото на Спринг за да дефинираш бийновете е трябвало да ги опишеш в .xml файл с индивидуално име, id и път до класа. Било абослютно недвусмислено.  
SpringBoot си сканира пекиджите и си ги намира сам. Първо търси по тип (`@Service`, `@Controller`), после по име.  
Ако има повече от един с едно и също име определяш главния с `@Primary`, останалите също се добавят, без да са водещи.  
Бийновете по дефолт са сингълтън.  
Unit tests трябва така да се правят, че да нямат нужда от Application context (в тестваните класове да няма `@Autowired`, всичко през конструктора).  

Не е добра идея да вдигаш целия Контекст за тестове. Има много анотации.  
```
@SpringBootTest  
@ExtendWith / @RunWith  
@TestConfiguration  
@ContextConfiguration  
@DynamicPropertySource  
@WebMvcTest  
@JdbcTest  
@DataJpaTest  
```


| Whole context             | Partial context           |
|---------------------------|---------------------------|
| @SpringBootTest 			| @ExtendWith / @RunWith	|
| 							| @ContextConfiguration 	|
|							| @DynamicPropertySource	|


Когато правиш тестове и имаш един общ клас с @Configuration е възможно да се объркв какво откъде да вземе.  
По-добре е нестнеш @Configuration клса в твоя тестов клас.  

В по-новия Спрунг има @DynamicPropertySource - например, когато TestContainres вдигне Докер имидж, взимаш порта на MySLQ базата или Кафка и го добавяш в контекста.  
Нов пример е в периодичното ревю, стар е в хелпдеска.
