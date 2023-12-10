## Check Docker Versions

```
docker --version
```

## Check all Docker Images

```
docker images
```

## View Docker Containers

```
docker ps #all running
docker ps -a #all
```

## To inspect a specific container

```
docker inspect <container_name_or_id>
```

## To inspect a specific image

```
docker inspect <image_name_or_id>
```

## Delete Specific Container

```
docker rm <container_name_or_id>
```

## Delete Specific Image

```
docker rmi <image_name_or_id>
docker rmi -f 5235f5cfc379
```

## Stop Running Container

```
docker stop <container_name_or_id>
```
