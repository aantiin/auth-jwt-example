# auth-jwt-example
spring boot with jwt, mysql, and rolling logfile with logback

## Credits

Modify from https://github.com/auth0-blog/spring-boot-jwts

## Getting Started

These instructions will get you a copy of the project up and running on your local machine

### Prerequisites

Create table users on your local database.

```
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `users` (`id`, `password`, `role`, `username`)
VALUES
	(1,'fb019348ea5d4fe7017820682f84f403f525516add1d11265a280e0d1bd2f87362552a7daf35c908','ADMIN','admin');

```

### Run

```
mvn spring-boot:run
```

## Tests

Using Postman

### Ping

URL

```
http://localhost:8080/ping
```

Result

```
{
  "message": "OK",
  "result": "June 9, 2017 12:09:45 PM WIB"
}
```

### Login

URL

```
http://localhost:8080/login
```
Body

```
{"username": "admin", "password" : "welcome1"}
```

Result Header

```
Authorization →Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTQ5Nzg0NjQ1NX0.-dMhgjENsyMhYmi6e1MF5K-063HGTIpRxF1XHi5-IYG5P2PXiXCahQErgrAtkTCxz6yvrOWS0VUuOsYKu5E6uw
```
