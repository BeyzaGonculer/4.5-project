# Clean Architecture Implementation

Bu dosya Product Service için Clean Architecture implementasyonunu gösterir.

## Katman Yapısı

```
product-service/
├── src/main/java/com/turkcell/product_service/
│   ├── application/                    # Application Layer
│   │   ├── dto/                       # Application DTOs
│   │   │   ├── CreateProductRequest.java
│   │   │   ├── UpdateProductRequest.java
│   │   │   ├── ProductResponse.java
│   │   │   └── ProductListResponse.java
│   │   ├── port/                      # Ports (Interfaces)
│   │   │   ├── ProductServicePort.java
│   │   │   └── ProductRepositoryPort.java
│   │   ├── service/                   # Application Services
│   │   │   └── ProductServiceAdapter.java
│   │   └── usecase/                   # Use Cases
│   │       ├── CreateProductUseCase.java
│   │       ├── GetProductByIdUseCase.java
│   │       ├── GetAllProductsUseCase.java
│   │       ├── UpdateProductUseCase.java
│   │       └── DeleteProductUseCase.java
│   ├── domain/                        # Domain Layer
│   │   ├── entity/
│   │   │   └── Product.java
│   │   ├── valueobject/
│   │   │   ├── ProductId.java
│   │   │   ├── ProductName.java
│   │   │   ├── Description.java
│   │   │   ├── Price.java
│   │   │   ├── Currency.java
│   │   │   └── Stock.java
│   │   ├── exception/
│   │   │   ├── ProductDomainException.java
│   │   │   ├── ProductNotFoundException.java
│   │   │   └── InsufficientStockException.java
│   │   ├── repository/
│   │   │   └── ProductRepository.java
│   │   └── service/
│   │       └── ProductDomainService.java
│   ├── infrastructure/                 # Infrastructure Layer
│   │   ├── adapter/
│   │   │   └── ProductRepositoryAdapter.java
│   │   └── repository/
│   │       └── InMemoryProductRepository.java
│   ├── controller/                     # Interface Layer
│   │   ├── ProductsController.java
│   │   └── GlobalExceptionHandler.java
│   └── dto/                           # Interface DTOs (Legacy)
│       ├── CreateProductRequest.java
│       ├── UpdateProductRequest.java
│       └── ProductResponse.java
```

## Dependency Flow

```
Controller Layer
    ↓ (depends on)
Application Layer
    ↓ (depends on)
Domain Layer
    ↑ (depends on)
Infrastructure Layer
```

## Katman Açıklamaları

### 1. Domain Layer (En İç Katman)

- **Entity**: `Product` - İş kurallarını içeren ana varlık
- **Value Objects**: `ProductId`, `ProductName`, `Price`, vb. - Değişmez değer nesneleri
- **Exceptions**: Domain-specific hatalar
- **Repository Interface**: `ProductRepository` - Domain'in ihtiyaç duyduğu veri erişim sözleşmesi
- **Domain Service**: `ProductDomainService` - Domain logic

### 2. Application Layer

- **Use Cases**: Her CRUD operasyonu için ayrı use case
- **DTOs**: Application katmanı için özel DTOs
- **Ports**: Application'ın dış dünyayla iletişim kurduğu arayüzler
- **Service Adapter**: Port'ları implement eden servisler

### 3. Infrastructure Layer

- **Repository Adapter**: Domain repository'yi implement eden sınıf
- **In-Memory Repository**: Gerçek veri erişim implementasyonu
- **External Services**: Dış servislerle entegrasyon

### 4. Interface Layer (Controller)

- **REST Controller**: HTTP endpoint'leri
- **Exception Handler**: Global hata yönetimi
- **DTOs**: API request/response nesneleri

## Use Case Flow

```
HTTP Request
    ↓
Controller
    ↓
Use Case
    ↓
Application Service (Port Implementation)
    ↓
Domain Entity
    ↓
Repository (Port Implementation)
    ↓
Infrastructure Repository
```

## Avantajlar

1. **Separation of Concerns**: Her katman kendi sorumluluğuna odaklanır
2. **Testability**: Her katman bağımsız olarak test edilebilir
3. **Maintainability**: Değişiklikler izole edilir
4. **Flexibility**: Infrastructure değişiklikleri domain'i etkilemez
5. **Clean Dependencies**: Dependencies içe doğru akar

## Port & Adapter Pattern

- **Ports**: Interface'ler (Application katmanında)
- **Adapters**: Port'ları implement eden sınıflar (Infrastructure katmanında)
- **Dependency Inversion**: Yüksek seviye modüller düşük seviye modüllere bağımlı değil
