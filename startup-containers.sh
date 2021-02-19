# inicializar banco
docker start hr-user-pg12
docker start hr-worker-pg12

# hr-config-server
cd hr-config-server && ./mvnw clean package
docker build -t hr-config-server:v1 .
docker run -d -p 8888:8888 --name hr-config-server --network hr-net -e GITHUB_USERNAME=$GITHUB_USERNAME -e GITHUB_PASSWORD=$GITHUB_PASSWORD hr-config-server:v1
cd ..

# hr-eureka-server
cd hr-eureka-server && ./mvnw clean package
docker build -t hr-eureka-server:v1 .
docker run -d -p 8761:8761 --name hr-eureka-server --network hr-net hr-eureka-server:v1
cd ..

# hr-worker
cd hr-worker && ./mvnw clean package -DskipTests
docker build -t hr-worker:v1 .
docker run -d -P --network hr-net hr-worker:v1
cd ..

# hr-user
cd hr-user && ./mvnw clean package -DskipTests
docker build -t hr-user:v1 .
docker run -d -P --network hr-net hr-user:v1
cd ..

# hr-payroll
cd hr-payroll && ./mvnw clean package -DskipTests
docker build -t hr-payroll:v1 .
docker run -d -P --network hr-net hr-payroll:v1
cd ..

# hr-api-gateway-zuul
cd hr-api-gateway-zuul && ./mvnw clean package -DskipTests
docker build -t hr-api-gateway-zuul:v1 .
docker run -d -p 8765:8765 --name hr-api-gateway-zuul --network hr-net hr-api-gateway-zuul:v1
cd ..