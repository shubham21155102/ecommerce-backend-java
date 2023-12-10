## MySQL Docker image creation

```
docker run -p 3307:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.2.0
```

## App Running Configruation

```
-DMYSQL_USER=root -DMYSQL_PASSWORD=123456 -DMYSQL_PORT=3307 
```

## Docker Network Check

```
 docker network 
```

## Docker Network Creation

```
docker network create spring-run
```

    ***result*** 

```
58d729b93eede649b4514f2812c2f......................
```

## Docker Network Deletion

```
docker network rm spring-run
```

    ***IF ERROR DELETING***

```
docker network inspect spring-run
```

## Copy Container Id then Delete 

```
docker network disconnect spring-run 7654849e98fb0ce7142982fd4ccfdc4b810ebe7e9faee069bb5084232c246f1d
```

## Now Delete 

```
docker network rm spring-run
```

## Docker Image Creation for current directory where Docker file is present

```
docker build -t spring-bits-backend .
```

## Docker MySQL Image Check

```
docker ps
```

## Now we are ready to rock with our Docker Container for Spring Boot

```
docker run -p 3307:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=123456 -d mysql:8.2.0
docker network ls
docker network connect spring-run mysqldb
docker network inspect spring-run
```

**Our Inspect will be like this**

```
[
    {
        "Name": "spring-run",
        "Id": "58d729b93eede6........c2faa6d6711b6c887ede9f0e6724b84750a640",
        "Created": "2023-12-10T04:48:00.821689167Z",
        "Scope": "local",
        "Driver": "bridge",
        "EnableIPv6": false,
        "IPAM": {
            "Driver": "default",
            "Options": {},
            "Config": [
                {
                    "Subnet": "191.21.0.0/160",
                    "Gateway": "191.41.0.1"
                }
            ]
        },
        "Internal": false,
        "Attachable": false,
        "Ingress": false,
        "ConfigFrom": {
            "Network": ""
        },
        "ConfigOnly": false,
        "Containers": {
            "7654849e98fb0c.....dc4b810ebe7e9faee069bb5084232c246f1d": {
                "Name": "mysqldb",
                "EndpointID": "4c5f29ef1fd73f422......de280c4cd7d237d3",
                "MacAddress": "02:42:ac:15:00:02",
                "IPv4Address": "172.21.0.2/16",
                "IPv6Address": ""
            }
        },
        "Options": {},
        "Labels": {}
    }
]
```

## Application.properties file will be like this 


```
#server.port=8080
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://${MYSQL_HOST:localhost}:${MYSQL_PORT:3306}/spring
spring.datasource.username=${MYSQL_USER:shubham}
spring.datasource.password=${MYSQL_PASSWORD:shubham}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```
