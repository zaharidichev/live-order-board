# Live Order Book Service

This is an example of a REST style service which has the following functionality

  - Users can place SELL and BUY orders
  - They can obtain individual orders
  - They can also view the aggregated order book

# Considerations
The decision to package the whole thing as a rest API was prompted by the fact that it just makes it feel a lot more complete and easy to interact with. I have decided to put a database after all. For ease , embedded Mongo was used. This makes the app, feel a bit more like a real app. Also we get some things for free that we would otherwise need to take care of ourselves. One such thing is Id generation. After all, every order needs to have an ID.
In addition, I decided to put the extra bit of effort and add a custom collector so we can be more idiomatic when aggregating buy and sell side orders into PriceLevelDTO objects on the service level:

```java
orderService.getSellOrders().collect(toSellSideLevels());
orderService.getBuyOrders().collect(toBuySideLevels());
```

However, I did decide to not use CompletableFuture to overlap the two operations and run them in parallel. The reason really is that I dont feel its worth the extra cyclomatic complexity at this stage.

# Installation
In order to install and run locally just do:
```sh
$ mvn spring-boot:run
```

# Testing
Run tests with
```sh
$ mvn test
```

# Persistence
All orders are stored in an embedded Mongo instance. Obviously this could quite easily be replaced with an externally running database.

# Placing an order
After starting the service, the user can place an order via POST to
```curl
localhost:8080/api/order
```

with body of:
```json
{
  "userId": "trader1",
  "quantityInKg": 2.5,
  "pricePerKg": 10,
  "side": "BUY"
}
```

which will return

```json
{
  "orderId": "58d82114d4c63df200fb4218",
  "userId": "trader1",
  "quantityInKg": 2.5,
  "pricePerKg": 10,
  "side": "BUY"
}
```


# Obtaining an order
After placing an order, one could obtain the order via the ID by performing a GET to
```curl
localhost:8080/api/order/{orderId}
```

# Deleting an order
An order can be deleted by performing a DELETE on
```curl
localhost:8080/api/order/{orderId}
```
# Obtaining the order book
An aggregated view of the order book is returned once we perform a GET on
```curl
localhost:8080/api/live-order-board
```

The response looks like:
```json
{
  "sellLevels": [
    {
      "quantityInKg": 15,
      "pricePerKg": 13,
      "side": "SELL",
      "orderIds": [
        "58d823b4d4c640c10155e939"
      ]
    },
    {
      "quantityInKg": 25,
      "pricePerKg": 15,
      "side": "SELL",
      "orderIds": [
        "58d82399d4c640c10155e937",
        "58d823a8d4c640c10155e938"
      ]
    }
  ],
  "buyLevels": [
    {
      "quantityInKg": 5,
      "pricePerKg": 9,
      "side": "BUY",
      "orderIds": [
        "58d823e5d4c640c10155e93c"
      ]
    },
    {
      "quantityInKg": 16,
      "pricePerKg": 8,
      "side": "BUY",
      "orderIds": [
        "58d823d2d4c640c10155e93b",
        "58d823ccd4c640c10155e93a"
      ]
    }
  ]
}
```

# Error handling
In case a parameter is omitted or misspelled or is incorrect in some way, you will receive feedback. This is done via a ControllerAdvice which intercepts exceptions.

For example if we try to post an order with negative size, we will receive an HTTP response with status 406(NOT_ACCEPTABLE) and a payload of:

```json
{
  "error": "NOT_ACCEPTABLE",
  "message": "Cannot create an order with size less than 0.001"
}
```


# Deployment
Currently there is a live instance of the service running on the cloud. You can see the current price levels by doing a GET on:
```curl
http://live-oder-board-service.live-oder-board-service.ae223934.svc.dockerapp.io:8081/api/live-order-board
```
The CircleCI config as well as the docker file are both included in the repo. CI is already setup so the process goes in the following way:

* Push to the repo triggers a build
* The build system packages a docker image and pushes it to Docker Hub
* The image is automatically picked up by the Docker Cloud API and deployed on AWS

# Things that can be improved
* Tests on the controller level
* LiveOrderBoardService overlapping the operations of retreiving and combining sell and buy sides via the use of CompletableFuture
