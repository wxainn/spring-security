# 使用说明

## 创建数据库

在MySQL创建数据库`spring_security`，编码设置为`utf8mb4`/`utf8mb4_general_cli`。

在该数据库上执行`spring_security.sql`文件。

## 普通用户

`user/123456`

### 获取token

```shell
$ curl -i -X POST -d "username=user&password=123456&grant_type=password&client_id=client&client_secret=secret" http://localhost:8000/oauth/token
```

返回：

```shell
HTTP/1.1 200
Cache-Control: no-store
Pragma: no-cache
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 25 Sep 2018 10:22:49 GMT

{"access_token":"4c2c2283-7dc5-4e66-84cf-ed6044c8187c","token_type":"bearer","refresh_token":"6b704a0f-c4e9-47c2-bca1-f72d35c8ccdb","expires_in":6569,"scope":"read write trust"}%
```

### 携带token获取资源


查出当前登录用户信息：

```shell
$ curl -i http://localhost:8000/user/current\?access_token\=4c2c2283-7dc5-4e66-84cf-ed6044c8187c
```

返回：

```shell
HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 25 Sep 2018 10:47:34 GMT

{"id":3,"username":"user","roles":[{"id":3,"name":"USER","displayName":"用户","description":"普通用户\t","deletable":true}],"enabled":true,"authorities":[{"authority":"ROLE_USER"}],"accountNonLocked":true,"accountNonExpired":true,"credentialsNonExpired":true}%
```

查看当前登录管理员信息(未授权)：
```shell
$ curl -i http://localhost:8000/admin/current\?access_token\=4c2c2283-7dc5-4e66-84cf-ed6044c8187c
```

返回：
```shell
HTTP/1.1 403
Cache-Control: no-store
Pragma: no-cache
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 25 Sep 2018 10:48:16 GMT

{"error":"access_denied","error_description":"访问未授权"}%
```

## 管理员


`admin/123456`

### 获取token

```shell
$ curl -i -X POST -d "username=admin&password=123456&grant_type=password&client_id=client&client_secret=secret" http://localhost:8000/oauth/token
```

返回：

```shell
HTTP/1.1 200
Cache-Control: no-store
Pragma: no-cache
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 25 Sep 2018 10:51:01 GMT

{"access_token":"9d3f3de3-425b-4501-a8c8-cc3faab25869","token_type":"bearer","refresh_token":"b5a4e0a5-c93e-4d93-aaa2-4757b5a5c5d8","expires_in":7199,"scope":"read write trust"}%
```

### 携带token获取资源


查出当前登录用户信息：

```shell
$ curl -i http://localhost:8000/user/current\?access_token\=9d3f3de3-425b-4501-a8c8-cc3faab25869
```

返回：

```shell
HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 25 Sep 2018 10:51:30 GMT

{"id":2,"username":"admin","roles":[{"id":2,"name":"ADMIN","displayName":"管理员","description":"管理员","deletable":true}],"enabled":true,"authorities":[{"authority":"ROLE_ADMIN"}],"accountNonLocked":true,"accountNonExpired":true,"credentialsNonExpired":true}%
```

查看当前登录管理员信息(已授权)：
```shell
$ curl -i http://localhost:8000/admin/current\?access_token\=9d3f3de3-425b-4501-a8c8-cc3faab25869
```

返回：
```shell
HTTP/1.1 200
X-Content-Type-Options: nosniff
X-XSS-Protection: 1; mode=block
Cache-Control: no-cache, no-store, max-age=0, must-revalidate
Pragma: no-cache
Expires: 0
X-Frame-Options: DENY
Content-Type: application/json;charset=UTF-8
Transfer-Encoding: chunked
Date: Tue, 25 Sep 2018 10:53:36 GMT

{"id":2,"username":"admin","roles":[{"id":2,"name":"ADMIN","displayName":"管理员","description":"管理员","deletable":true}],"enabled":true,"authorities":[{"authority":"ROLE_ADMIN"}],"accountNonLocked":true,"accountNonExpired":true,"credentialsNonExpired":true}%
```