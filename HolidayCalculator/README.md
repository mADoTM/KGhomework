# Калькулятор отпускных

Приложение принимает твою среднюю зарплату за 12 месяцев и 
количество дней отпуска - отвечает суммой отпускных, 
которые придут сотруднику

## Request
```http request
GET http://localhost:8080/calculate
Content-Type: application/json

{
    "salary" : {salary},
    "daysCount" : {daysCount}
}
```

## Response
```http request
HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: {}
Keep-Alive: {}
Connection:{}

{
  "money": {money}
}
```