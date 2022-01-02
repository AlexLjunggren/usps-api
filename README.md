## USPS (United States Postal Service) API Tools ##

**Note:** Register for API access here: https://registration.shippingapis.com/

## Instantiate ##

```java
UspsApi uspsApi = new UspsApi(environment, username);
```

Environment (Production, Testing)

```java
UspsEnvironment environment = UspsEnvironment.TESTING;
```

## Track Package ##

```java
TrackingResponse trackingResponse = uspsApi.track("EJ958083578US");
```
