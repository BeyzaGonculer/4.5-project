# Web Layer Implementation

Bu dosya Product Service için Web Layer (Interface Layer) implementasyonunu gösterir.

## Katman Yapısı

```
product-service/
├── src/main/java/com/turkcell/product_service/
│   ├── web/                           # Web Layer (Interface Layer)
│   │   ├── controller/               # Web Controllers
│   │   │   └── ProductWebController.java
│   │   ├── dto/                      # Web DTOs
│   │   │   ├── CreateProductWebRequest.java
│   │   │   ├── UpdateProductWebRequest.java
│   │   │   ├── ProductWebResponse.java
│   │   │   └── ProductListWebResponse.java
│   │   ├── exception/                # Web Exception Handlers
│   │   │   ├── WebExceptionHandler.java
│   │   │   └── ErrorResponse.java
│   │   └── mapper/                   # Web Mappers
│   │       └── ProductWebMapper.java
│   ├── application/                  # Application Layer
│   ├── domain/                       # Domain Layer
│   └── infrastructure/              # Infrastructure Layer
```

## Web Layer Bileşenleri

### 1. **Web DTOs**

- **CreateProductWebRequest**: Ürün oluşturma için web request DTO
- **UpdateProductWebRequest**: Ürün güncelleme için web request DTO
- **ProductWebResponse**: Ürün response DTO (JSON formatting ile)
- **ProductListWebResponse**: Ürün listesi response DTO (metadata ile)

### 2. **Web Controllers**

- **ProductWebController**: RESTful API endpoint'leri
- HTTP status kodları ile proper response handling
- Validation annotations ile input validation

### 3. **Web Exception Handlers**

- **WebExceptionHandler**: Global exception handling
- **ErrorResponse**: Standardized error response format
- HTTP status kodları ile proper error handling

### 4. **Web Mappers**

- **ProductWebMapper**: Web DTOs ↔ Application DTOs conversion
- Clean separation between web and application layers

## API Endpoints

### GET /api/v1/products

```json
{
  "products": [...],
  "totalCount": 10,
  "availableCount": 8,
  "outOfStockCount": 2,
  "message": "Found 10 products (8 available, 2 out of stock)"
}
```

### GET /api/v1/products/{id}

```json
{
  "id": "123e4567-e89b-12d3-a456-426614174000",
  "name": "iPhone 15",
  "description": "Apple iPhone 15 128GB",
  "price": 999.99,
  "currency": "USD",
  "currencySymbol": "$",
  "stock": 50,
  "formattedPrice": "$999.99",
  "available": true,
  "outOfStock": false,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### POST /api/v1/products

```json
{
  "name": "iPhone 15",
  "description": "Apple iPhone 15 128GB",
  "price": 999.99,
  "currency": "USD",
  "stock": 50
}
```

### PUT /api/v1/products/{id}

```json
{
  "name": "iPhone 15 Pro",
  "description": "Apple iPhone 15 Pro 256GB",
  "price": 1199.99,
  "currency": "USD",
  "stock": 25
}
```

### DELETE /api/v1/products/{id}

```
204 No Content
```

## Error Responses

### 400 Bad Request - Validation Error

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Validation Failed",
  "message": "Invalid input data",
  "errors": {
    "name": "Product name is required",
    "price": "Price must be greater than 0"
  }
}
```

### 404 Not Found - Product Not Found

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 404,
  "error": "Product Not Found",
  "message": "Product not found with id: 123e4567-e89b-12d3-a456-426614174000"
}
```

### 500 Internal Server Error

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 500,
  "error": "Internal Server Error",
  "message": "An unexpected error occurred"
}
```

## Validation Rules

### CreateProductWebRequest & UpdateProductWebRequest

- `name`: @NotBlank, @Size(max = 255)
- `description`: @NotBlank, @Size(max = 1000)
- `price`: @NotNull, @DecimalMin(value = "0.0", inclusive = false)
- `currency`: @NotBlank, @Pattern(regexp = "^[A-Z]{3}$")
- `stock`: @NotNull, @Min(value = 0)

## Web Layer Avantajları

1. **Separation of Concerns**: Web layer sadece HTTP handling ile ilgilenir
2. **Validation**: Input validation web layer'da yapılır
3. **Error Handling**: Consistent error responses
4. **DTO Mapping**: Clean conversion between layers
5. **JSON Formatting**: Proper date/time formatting
6. **HTTP Status Codes**: Proper RESTful responses

## Clean Architecture Flow

```
HTTP Request
    ↓
Web Controller
    ↓
Web Mapper (Web DTO → Application DTO)
    ↓
Use Case
    ↓
Application Service
    ↓
Domain Entity
    ↓
Repository
```

## Dependencies

- **Web Layer** → **Application Layer** (Use Cases)
- **Web Layer** → **Application Layer** (DTOs)
- **Web Layer** → **Domain Layer** (Exceptions)

Web layer sadece application layer'a bağımlıdır, domain ve infrastructure layer'lara doğrudan bağımlı değildir.
