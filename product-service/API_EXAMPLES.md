# Product Service API Examples

Bu dosya Product Service için CRUD operasyonlarının örnek kullanımlarını içerir.

## Base URL

```
http://localhost:8080/api/v1/products
```

## 1. Tüm Ürünleri Listele

```bash
curl -X GET http://localhost:8080/api/v1/products
```

### Response (ProductListResponse)

```json
{
  "products": [
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
  ],
  "totalCount": 1,
  "availableCount": 1,
  "outOfStockCount": 0
}
```

## 2. ID'ye Göre Ürün Getir

```bash
curl -X GET http://localhost:8080/api/v1/products/{product-id}
```

## 3. Yeni Ürün Oluştur

```bash
curl -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "iPhone 15",
    "description": "Apple iPhone 15 128GB",
    "price": 999.99,
    "currency": "USD",
    "stock": 50
  }'
```

## 4. Ürün Güncelle

```bash
curl -X PUT http://localhost:8080/api/v1/products/{product-id} \
  -H "Content-Type: application/json" \
  -d '{
    "name": "iPhone 15 Pro",
    "description": "Apple iPhone 15 Pro 256GB",
    "price": 1199.99,
    "currency": "USD",
    "stock": 25
  }'
```

## 5. Ürün Sil

```bash
curl -X DELETE http://localhost:8080/api/v1/products/{product-id}
```

## Örnek Response (ProductResponse)

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

## Validation Kuralları

### CreateProductRequest & UpdateProductRequest

- `name`: Boş olamaz, maksimum 255 karakter
- `description`: Boş olamaz, maksimum 1000 karakter
- `price`: Boş olamaz, 0'dan büyük olmalı
- `currency`: Boş olamaz, 3 harfli ISO kodu (örn: USD, EUR, TRY)
- `stock`: Boş olamaz, negatif olamaz

## Hata Durumları

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
