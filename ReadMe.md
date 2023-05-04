API має наступні ендпоінти і приклади їх використання
1. http://localhost:8080/grouped - статистику по локаціям (місто і кількість вакансій)
2. http://localhost:8080/home?page=2&size=100 - пагінація(якщо параметри відсутні то по дефолту 1 сторінка і 10 записів на ній)
3. http://localhost:8080/home?page=1&size=100&sort=createdAt - сортування по полю createdAt
4. http://localhost:8080/home?page=1&size=100&sort=location,desc- сортування по полю createdAt в зворотньому напрямі

Отримання даних від https://www.arbeitnow.com/api/job-board-api відбувається в класі StartupListener.
```
    @PostConstruct
    public void init() throws JsonProcessingException {
    getDataFromApi();
    }
```
даний метод запускає перше завантаження даних.

```
    @Scheduled(fixedDelay = 60000)
    public void runTask() throws JsonProcessingException {
        getDataFromApi();
    }
```
даний метод запускається періодично для довантаження нових даних.

Метод ``getDataFromApi()`` отримує перші пять сторінок даних, і додаєу БД  тільки ті дані яких ще не має у ній 