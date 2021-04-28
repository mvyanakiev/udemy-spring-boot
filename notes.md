https://github.com/in28minutes/spring-master-class

IOC container е всяко нещо, което имлементира Inversion of controll.
Application context e IOC container. Идеята е да вдигне и менажири всички бийнове. (Component, Repository, Service също са бийнове).

Имаш 3 въпроса които Спринг трябва да знае:
1. Кои са бийновете. (Component, Service, Repository и тия описани в Configuration с Bean).
2. Кои са депендънситата на бийновете (Autowired).
3. Къде да търси за бийнове (Component scan) @SpringBootApplication е началото на пакета за търсене, когато всичко е на едно място.