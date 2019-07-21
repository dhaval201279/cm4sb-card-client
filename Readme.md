# Source code of my [blog](http://dhaval-shah.com/chaos-engineering-demo/) on how to perform [Chaos Engineering](https://principlesofchaos.org/) using
1. [Chaos Monkey for Spring Boot](https://github.com/codecentric/chaos-monkey-spring-boot)
2. [Consul](https://www.consul.io/)
3. [Prometheus](https://prometheus.io/)
4. [Grafana](https://grafana.com/)

#### Card Client APIs
Edge application responsible for consuming [Card Service APIs](https://github.com/dhaval201279/cm4sb-card-service)

Method	| Path	| Description
------------- | ------------------------- | ------------- |
GET	| /card/{cardId}	| Gets Card based on card identifier	
GET	| /cards	| Fetches all cards stored in database