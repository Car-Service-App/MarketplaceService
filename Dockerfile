FROM eclipse-temurin:17-alpine
WORKDIR .
COPY /target/MarketplaceService-0.0.1-SNAPSHOT.jar MarketplaceService.jar

ENTRYPOINT ["java","-jar","MarketplaceService.jar"]