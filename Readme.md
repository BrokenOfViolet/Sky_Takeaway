Notice(建议开发之前阅读)

1. springboot 开发, java版本选择 java17 or java11, 不然会报错
2. 运行之前修改 application-dev.yml 文件,修改里面的 database, username and password
3. 运行命令:
```bash
mvn install
mvn spring-boot:run 
```
或者直接找到sky-server.src.main.java/SkyApplication文件运行