# Notice（开发前建议阅读）

1. 本项目基于 **Spring Boot** 开发，Java 版本请使用 **Java 17 或 Java 11**，否则可能报错。
2. 运行前请修改 `application-dev.yml` 文件，配置 `database`、`username` 和 `password`。
3. 启动项目命令：
```bash
mvn install
mvn spring-boot:run
```
或者直接运行 `sky-server/src/main/java/SkyApplication` 文件。

4\. 项目默认将文件和图片资源存储在本地，默认路径：
```
/opt/sky_take_out/uploads/
```
如需使用远程文件上传，请在配置中修改阿里云相关参数。

5\. 若开通微信后台支付功能，请配置相关微信参数；在测试环境下，也可选择修改相关代码绕过支付配置。
6\. nginx核心配置:
```
 location / {
            root   /Users/mac/Vscode/nginx;  # 静态资源路径
            index  index.html index.htm;     # 默认首页文件
        }

        location /uploads/ {
            alias   /opt/sky_take_out/uploads/;  # 注意末尾的斜杠必须添加
            autoindex off;  # 关闭目录浏览（安全考虑）
            expires 1d;     # 设置缓存过期时间（可选）
        }
        
        # 管理端 API 反向代理
        location /api/ {
            proxy_pass   http://localhost:8080/admin/;  # 转发到后端服务
        }
```