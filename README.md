## _GLChallenge_

`Java 8`
`Gradle`
`Spock`

#### _Tasks_
```
gradle clean
```
```
gradle build
```
```
gradle test
```
```
gradle bootRun
```

#### _Test the app_

* Registrar un usuario. **POST**
```
http://localhost:9090/api/v1/user
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.org",
  "password": "Hunter22",
  "phones": [
    {
      "citycode": 57,
      "countrycode": 1,
      "number": 1234567
    }
  ]
}

copy user token
```

* Traer todos los usuarios. **GET**
```
http://localhost:9090/api/v1/user
Set Header
key: Authorization
value: generated token
```
