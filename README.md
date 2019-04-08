# About codes with remote reposirtory as config-server

> This is description of `msa-springcloud-java-springboot-maven-properties`(MSA Skeleton using [OSS Netflix](https://netflix.github.io/))
> for "SHARE CONTACT Project (based on MSA Skeleton)" . And Also Project is maded with __whdms705__. Thanks 

### SW Side:
- `Springboot` Application `Runnable JAR`
- Using `Embed WAS` and Build/Package/`runnable jar`
    Servlet Engine: Apache Tomcat/`8.5.4`, Apache Tomcat/`8.5.31`
- Springboot version:
    Springboot : `1.4.0.RELEASE`
    Springboot : `2.0.2.RELEASE`
- Spring Core version:
    Spring core `4.3.2.RELEASE`
- JDK: `1.8`
- Mybatis: `3.4.2`
- Mysql: 
    Local(Windows) : `community-5.7.11.0`
    Server(Linux) : 
- Maven : `3.3.9`
- [External remote reposirtory as config-server](https://github.com/humblem2/test-private-config-for-msa-project)

### HW Side:
- Build/Deploy Automation using Github, Travis, AWS S3, AWS CodeDeploy for 
- Travis(travis.com)
- AWS
    EC2(t2.micro Ubuntu14 [2018. 08. 26], `m4.large`[2018 .08. 27])
    Code Deploy
    S3

* Now, Every Server disk HAVING profile(s) related in Build/Deploy Automation.
* Now, Every project directory HAVING profile(s) related in Build/Deploy Automation.

### Run Configuration Enviroments:
- Local Develop: `dev`
- AWS Server Develop: `beta`
- AWS Server Product: `real`

### Services(5EA micro services):
- API Gateway Proxy Server(`Edge server`;Zuul Server;Gateway) - Routing(Proxying) and L4 Loadbalancing(RR)(`9999`)
- Config Server(`Config server`)(`8888`)
- Dicovery and Register Server(`Eureka Server`)(`8761`)
- Normal Server(`Frontend Server`)(`8000`)(`8002`) - 
- Data Access Server(`Backend Server` with Swagger) connected RDB(mysql) using mapper(Mybatis)(`8787`)(Swagger - http://${HOST}:8787/swagger-ui.html)
- `Config Remote Repository`(https://github.com/VanillaLab/test-private-config) or [this(public version)](https://github.com/humblem2/test-private-config-for-msa-project)

### Logging
- Logger is Logback
- Directory Policy:

![k-10](https://user-images.githubusercontent.com/42057272/44655616-c1b7cb00-aa30-11e8-8cc5-fedbb7ca413b.png)

### Available URL:
- https://github.com/humblem2/test-private-config-for-msa-project
- https://github.com/humblem2
- https://github.com/humblem2/test-config
- https://github.com/VanillaLab/test-private-config
- https://swagger.io/

### Reference
- [LeeKyoungIl님, Naver](http://blog.leekyoungil.com) / [github](https://github.com/Leekyoungil)
- [정윤진님, 피보탈 랩](http://www.comworld.co.kr/news/articleView.html?idxno=49227)
