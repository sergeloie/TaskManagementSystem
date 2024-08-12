## Task Management System  
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_TaskManagementSystem&metric=bugs)](https://sonarcloud.io/summary/new_code?id=sergeloie_TaskManagementSystem)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_TaskManagementSystem&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=sergeloie_TaskManagementSystem)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_TaskManagementSystem&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=sergeloie_TaskManagementSystem)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_TaskManagementSystem&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=sergeloie_TaskManagementSystem)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sergeloie_TaskManagementSystem&metric=coverage)](https://sonarcloud.io/summary/new_code?id=sergeloie_TaskManagementSystem)

Бекэнд веб-сервиса для управления задачами. Позволяет регистрироваться пользователям, создавать и редактировать задачи, 
назначать исполнителей, оставлять комментарии. 
Написан на Java 21 с использованием Spring Framework. Авторизация через JWT с использованием Spring Security.
Реализована возможность запуска через docker compose up совмсестно с Postgres.

Описание API доступно после запуска по ссылке
http://localhost:8100/swagger-ui/index.html#/

## Описание основных оперций

### 1 - Создание пользователя
В качестве имени пользователя принимается email.
Для создания пользователя необходимо отправить POST запрос на `"/users"` JSON вида:
```json
{
  "username": "string",
  "password": "string"
}
```

### 2 - Авторизация пользователя
Для авторизации необходимо отправить POST запрос на `"/login""` JSON вида:
```json
{
"username": "string",
"password": "string"
}
```
В ответ приходит JWT токен, который нужно использовать для всех остальных запросов как Bearer токен

### 3 - Создание задачи
Для создания задачи необходимо отправить POST запрос на `"/tasks""` с JSON вида:
```json
{
  "header": "string",
  "description": "string",
  "taskStatus": "CREATED",
  "taskPriority": "HIGH",
  "executorId": 0
}
```
Статус задачи принимает значения `CREATED, IN_PROGRESS, SUSPENDED, COMPLETED`  
Приоритет задачи принимает значения `HIGH, MEDIUM, LOW`

### 4 - Получение списка задач
Для получения списка задач нужно отправить GET запрос на `"/tasks"`  
Доступна фильтрация и постраничный вывод результатов. Пример запроса  
`/tasks?headerContains=task&taskStatus=CREATED&taskPriority=MEDIUM&authorId=1&executorId=2&page=0&size=2&sort=desc`  
Где `headerContains` - фильтрует по вхождению строки в заголовок задачи,  
`headerContains` и `headerContains` - фильтруют по статусу и приоритету, соответсвенно,  
`authorId` и `executorId` - фильтрация по ID автора и исполнителя, соответственно,  
`page`, `size` `sort` - номер и размер страницы и порядок сортировки.

## Запуск приложения
Для запуска приложения можно склонировать репозиторий
```shell
git clone https://github.com/sergeloie/TaskManagementSystem.git
```
Перейти в каталог приложения и запустить Docker Compose
```shell
cd TaskManagementSystem && docker-compose up
```
Сервис будет доступен на 8100 порту.
