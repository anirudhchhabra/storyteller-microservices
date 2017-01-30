docker run -e "SPRING_PROFILES_ACTIVE=storyteller-api1" -p 9977:9977 --name storyteller-api1 --add-host archimedes1:192.168.99.100 --add-host archimedes2:192.168.99.100 --add-host config-server:192.168.99.100 --add-host my-rabbit:192.168.99.100 --add-host gatekeeper:192.168.99.100 --add-host stories-microservice:192.168.99.100 --add-host images-microservice:192.168.99.100 --add-host storyteller-api:192.168.99.100 --add-host hystrix-dashboard:192.168.99.100  --add-host central-logger:192.168.99.100  --add-host log-processor:192.168.99.100 -d storyteller/storyteller-api
docker run -e "SPRING_PROFILES_ACTIVE=storyteller-api2" -p 7799:7799 --name storyteller-api2 --add-host archimedes1:192.168.99.100 --add-host archimedes2:192.168.99.100 --add-host config-server:192.168.99.100 --add-host my-rabbit:192.168.99.100 --add-host gatekeeper:192.168.99.100 --add-host stories-microservice:192.168.99.100 --add-host images-microservice:192.168.99.100 --add-host storyteller-api:192.168.99.100 --add-host hystrix-dashboard:192.168.99.100  --add-host central-logger:192.168.99.100  --add-host log-processor:192.168.99.100 -d storyteller/storyteller-api