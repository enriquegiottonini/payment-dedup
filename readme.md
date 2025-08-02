only readme is ai polished.

# RPC Call Deduplication with Request ID and Server-Side Caching

This project demonstrates how to deduplicate RPC calls using a client-provided `requestId` and a server-side key-value cache. This approach helps prevent duplicate processing caused by network issues or repeated client requests.

## Overview

- **Deduplication Strategy**: Each client request must include a unique `requestId`. The server caches responses keyed by this ID to ensure idempotency.
- **Caching Mechanism**: Can be implemented using Redis or in-memory caching via Spring's `@Cacheable` annotation.

## Running the Application

To start the application:

```bash
mvn spring-boot:run
```

Once running, access the Swagger UI:

```bash
http://localhost:8080/swagger-ui/index.html`
```

API Usage
1. Check Account Balance
Endpoint: GET /amount
Query Parameter: accountId=enrique
Expected Response: 100

2. Transfer Amount
Endpoint: POST /transfer
Required Field: requestId (must be unique per logical request)
Behavior:
First call processes the transfer and caches the result.
Subsequent calls with the same requestId return the cached response.
This simulates resilience against network partitions or repeated client submissions.
