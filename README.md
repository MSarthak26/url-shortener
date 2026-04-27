
# 🔗 URL Shortener - Spring Boot

A backend service that converts long URLs into short, shareable links and redirects users efficiently.



## 🚀 Features

- Generate short URLs from long URLs
- Redirect to original URL using short code
- Track number of clicks (analytics)
- Input validation using Spring Validation
- Global exception handling
- RESTful API design

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Maven


## 📌 API Endpoints

### 🔹 Create Short URL
POST /api/shorten

**Request**
```json
{
  "url": "http://google.com"
}
```
**Response**
```json
{
  "shortUrl": "http://localhost:8080/abc123"
}
```

### 🔹 Redirect

GET /{shortCode}

➡️ Redirects to original URL

### 🔹 Get URL Stats

GET /api/stats/{shortCode}

**Response**
```json
{
  "shortUrl": "http://localhost:8080/abc123",
  "originalUrl": "http://google.com",
  "clickCount": 5
}
```
## 🔄 Application Flow

1. Create Short URL

User → POST /api/shorten → Save mapping in DB → Return short URL

2. Redirect

User → GET /{shortCode} → Fetch original URL → Increment click count → Redirect

3. Get Analytics

User → GET /api/stats/{shortCode} → Return click count + original URL
## ⚙️ Configuration

This project uses environment variables:

- DB_URL
- DB_USERNAME
- DB_PASSWORD
- BASE_URL

**Example**:

- DB_URL=jdbc:postgresql://localhost:5432/url_shortener
- DB_USERNAME=postgres
- DB_PASSWORD=yourpassword
- BASE_URL=http://localhost:8080
## 🧠 Design Decisions

- UUID-based short codes for randomness and uniqueness
- DTO pattern to separate API and database models
- Global exception handling using @RestControllerAdvice
- Click tracking implemented at service layer
