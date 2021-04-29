https://github.com/in28minutes/spring-master-class
https://github.com/in28minutes/in28minutes-initiatives/blob/master/The-in28Minutes-TroubleshootingGuide-And-FAQ/quick-start.md

IOC container е всяко нещо, което имлементира Inversion of controll.
Application context e IOC container. Идеята е да вдигне и менажири всички бийнове. (Component, Repository, Service също са бийнове).

Имаш 3 въпроса които Спринг трябва да знае:
1. Кои са бийновете. (Component, Service, Repository и тия описани в Configuration с Bean).
2. Кои са депендънситата на бийновете (Autowired).
3. Къде да търси за бийнове (Component scan) @SpringBootApplication е началото на пакета за търсене, когато всичко е на едно място.

Като стратира апп-а първо прави компонент скан в пакиджите. Търси за класове с анотации.
Започва да съзадава бийновете и да идентифицира депендънситата.

Когато имаш 2 компонента от даден тип Спринг не знае кой да използва. @Primary му показва кой да вземе, иначе гърми.

Като не си намери анотация за компонент а нещото е Autowired гърми със съобщение че не може да намери бийна.
(намира че нещо е в депендънси но не може да намери дадения бийн).

При грешки първо виж за правилната комбинация от @Component, @Autowired, @Primary

Освен конструктор инджекшън има и сетър инджекшън, което е дългото изписване на @Autowired.
Старо -> За всички задължителни депендънситата (без които класа не може да работи) да се използва конструктор инджекшън, за останалите @Autowired
Ново -> всичко е с @Autowired

AOP -> Aspect oriented programming.


До секция 4 Spring framework in depth.