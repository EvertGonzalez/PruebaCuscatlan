# 🛒 Spring Boot Shopping Cart Microservice

## 📋 Descripción del Proyecto

Este proyecto implementa una **solución basada en microservicios para un carrito de compras** utilizando Spring Boot. La aplicación proporciona APIs RESTful para gestionar productos, órdenes y pagos, actuando como un proxy hacia la API externa [FakeStore API](https://fakestoreapi.com) para obtener información de productos.

**Desarrollado como parte de la evaluación técnica de Cuscatlán**, este proyecto demuestra las mejores prácticas de desarrollo con Spring Boot y arquitectura de microservicios.

## 🎯 Características Principales

- **🏪 Gestión de Productos**: Proxy hacia FakeStore API con caché integrado
- **📦 Gestión de Órdenes**: Creación y consulta de órdenes con estados
- **💳 Procesamiento de Pagos**: Simulación del proceso de pago
- **👥 Gestión de Clientes**: Entidades y DTOs para el mecanismo de pago
- **🔒 Manejo de Errores**: Respuestas JSON consistentes con códigos de estado HTTP apropiados
- **🗄️ Base de Datos**: H2 en memoria para desarrollo y testing
- **⚡ Caché**: Implementación de caché para optimizar consultas de productos

## 🏗️ Arquitectura del Sistema

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐
│   Frontend      │    │   Spring Boot    │    │   FakeStore     │
│   (Cliente)     │◄──►│   Microservice   │◄──►│   API           │
└─────────────────┘    └──────────────────┘    └─────────────────┘
                              │
                              ▼
                       ┌──────────────────┐
                       │   H2 Database    │
                       │   (In-Memory)    │
                       └──────────────────┘
```

## 🛠️ Tecnologías Utilizadas

### **Backend Framework**
- **Spring Boot 3.2.10** - Framework principal
- **Spring Data JPA** - Persistencia de datos
- **Spring Web** - APIs RESTful
- **Spring Validation** - Validación de datos
- **Spring Caching** - Sistema de caché

### **Base de Datos**
- **H2 Database** - Base de datos en memoria
- **Hibernate 6.4.10** - ORM

### **Herramientas de Desarrollo**
- **Maven 3.8.1** - Gestión de dependencias y build
- **Java 21** - Lenguaje de programación
- **Lombok** - Reducción de código boilerplate

## 📁 Estructura del Proyecto

```
springboot-shopping-cart/
├── src/
│   ├── main/
│   │   ├── java/com/testCus/shoppingcart/
│   │   │   ├── config/           # Configuraciones de la aplicación
│   │   │   ├── controller/       # Controladores REST
│   │   │   ├── dto/             # Objetos de transferencia de datos
│   │   │   ├── exception/       # Manejo global de excepciones
│   │   │   ├── interceptor/     # Interceptores de logging
│   │   │   ├── model/           # Entidades JPA
│   │   │   ├── repository/      # Repositorios de datos
│   │   │   ├── service/         # Lógica de negocio
│   │   │   └── util/            # Utilidades
│   │   └── resources/
│   │       └── application.properties
├── pom.xml                      # Configuración de Maven
└── README.md                    # Este archivo
```

## 🚀 Instalación y Configuración

### **Prerrequisitos**
- **Java 21** o superior
- **Maven 3.8.1** o superior
- **IDE** (IntelliJ IDEA, Eclipse, VS Code)

### **1. Clonar el Repositorio**
```bash
git clone https://github.com/EvertGonzalez/PruebaCuscatlan.git
cd springboot-shopping-cart
```

### **2. Compilar el Proyecto**
```bash
mvn clean compile
```

### **3. Ejecutar la Aplicación**
```bash
mvn spring-boot:run
```

### **4. Verificar la Aplicación**
La aplicación estará disponible en:
- **URL Base**: `http://localhost:8082/springboot-shopping-cart`
- **Puerto**: 8082
- **Context Path**: `/springboot-shopping-cart`

## 📚 Endpoints de la API

### **🏪 Productos (`/api/products`)**

#### **Obtener Todos los Productos**
```http
GET /api/products
```
**Respuesta Exitosa (200):**
```json
[
  {
    "id": 1,
    "title": "Fjallraven - Foldsack No. 1 Backpack",
    "price": 109.95,
    "description": "Your perfect pack for everyday use...",
    "category": "men's clothing",
    "image": "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg",
    "rating": {
      "rate": 3.9,
      "count": 120
    }
  }
]
```

#### **Obtener Producto por ID**
```http
GET /api/products/{id}
```
**Respuesta de Error (404):**
```json
{
  "status": 404,
  "error": "PRODUCT_NOT_FOUND",
  "message": "No se encontró el producto con ID: 999",
  "timestamp": "2024-08-21T21:07:48.000Z",
  "path": "/api/products/999"
}
```

#### **Obtener Categorías**
```http
GET /api/products/categories
```

#### **Obtener Productos por Categoría**
```http
GET /api/products/category/{category}
```

#### **Limpiar Caché**
```http
POST /api/products/cache/clear
```

### **📦 Órdenes (`/api/orders`)**

#### **Crear Nueva Orden**
```http
POST /api/orders
```
**Cuerpo de la Petición:**
```json
{
  "customer": {
    "customerId": 1,
    "name": "John Doe",
    "email": "john@example.com",
    "address": {
      "street": "123 Main St",
      "city": "New York",
      "zipCode": "10001"
    }
  },
  "products": [
    {
      "id": 1,
      "title": "Product Name",
      "price": 29.99,
      "quantity": 2
    }
  ]
}
```

**Respuesta Exitosa (200):**
```json
{
  "transactionId": "TXN_20240821_001",
  "status": "SUCCESS",
  "message": "Orden creada exitosamente",
  "order": {
    "id": 1,
    "customerId": 1,
    "status": "Pending",
    "total": 59.98,
    "productCount": 2,
    "productSummary": "Product Name (x2), Product Name 2 (x1)"
  }
}
```

#### **Obtener Órdenes por Estado**
```http
GET /api/orders/status/{status}
```
**Estados Válidos**: `Pending`, `Completed`, `Cancelled`

**Respuesta de Error (400):**
```json
{
  "status": 400,
  "error": "INVALID_STATUS",
  "message": "El estado debe ser: Pending, Completed o Cancelled. Estado recibido: invalid",
  "timestamp": "2024-08-21T21:07:48.000Z",
  "path": "/api/orders/status/invalid"
}
```

### **💳 Pagos (`/api/payments`)**

#### **Procesar Pago**
```http
POST /api/payments
```
**Cuerpo de la Petición:**
```json
{
  "customer": {
    "customerId": 1,
    "name": "John Doe",
    "email": "john@example.com"
  }
}
```

**Respuesta Exitosa (200):**
```json
{
  "transactionId": "PAY_20240821_001",
  "status": "SUCCESS",
  "message": "Pago procesado exitosamente",
  "payment": {
    "customerId": 1,
    "amount": 59.98,
    "status": "Completed"
  },
  "order": {
    "id": 1,
    "status": "Completed",
    "total": 59.98
  }
}
```

**Respuesta de Error (404):**
```json
{
  "status": 404,
  "error": "PENDING_ORDER_NOT_FOUND",
  "message": "No se encontró una orden pendiente para el cliente con ID: 1",
  "timestamp": "2024-08-21T21:07:48.000Z",
  "path": "/api/payments"
}
```

### **🧪 Testing (`/api/test`)**

#### **Probar Códigos de Estado HTTP**
```http
GET /api/test/404
GET /api/test/400
GET /api/test/500
```

## 🔧 Configuración de la Aplicación

### **application.properties**
```properties
# Puerto del servidor
server.port=8082

# Context path de la aplicación
server.servlet.context-path=/springboot-shopping-cart

# Configuración de la base de datos H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password

# Configuración de JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

# Perfil activo
spring.profiles.active=dev
```

## 🗄️ Modelo de Datos

### **Entidades Principales**

#### **OrderDetail**
```java
@Entity
@Table(name = "order_details")
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private Integer customerId;
    private String status;
    private double total;
    private int productCount;
    private String productSummary;
    
    @Embedded
    private Customer customer;
}
```

#### **Customer**
```java
@Embeddable
public class Customer {
    private Integer customerId;
    private String name;
    private String email;
    
    @Embedded
    private Address address;
}
```

#### **Product**
```java
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    
    @Embedded
    private Rating rating;
}
```

## 🚨 Manejo de Errores

### **Códigos de Estado HTTP**

| **Código** | **Descripción** | **Ejemplo de Uso** |
|------------|-----------------|---------------------|
| **200** | OK - Operación exitosa | Producto encontrado, orden creada |
| **400** | Bad Request - Datos inválidos | Estado de orden inválido |
| **404** | Not Found - Recurso no encontrado | Producto inexistente |
| **500** | Internal Server Error - Error del servidor | Excepción no manejada |

### **Estructura de Respuesta de Error**
```json
{
  "status": 404,
  "error": "PRODUCT_NOT_FOUND",
  "message": "Descripción detallada del error",
  "timestamp": "2024-08-21T21:07:48.000Z",
  "path": "/api/products/999"
}
```

### **Códigos de Error Implementados**

| **Código de Error** | **Descripción** | **HTTP Status** |
|---------------------|-----------------|-----------------|
| `PRODUCT_NOT_FOUND` | Producto específico no encontrado | 404 |
| `PRODUCTS_NOT_FOUND` | Lista de productos vacía | 404 |
| `CATEGORIES_NOT_FOUND` | No hay categorías disponibles | 404 |
| `ORDERS_NOT_FOUND` | No hay órdenes con el estado especificado | 404 |
| `PENDING_ORDER_NOT_FOUND` | No hay orden pendiente para el cliente | 404 |
| `INVALID_STATUS` | Estado de orden inválido | 400 |
| `ORDER_WITHOUT_PRODUCTS` | Orden sin productos para pago | 400 |
| `VALIDATION_ERROR` | Error de validación general | 400 |
| `API_ERROR` | Error de la API externa | 404 |
| `INTERNAL_SERVER_ERROR` | Error interno del sistema | 500 |

## 🧪 Testing y Validación

### **Orden de Prueba de Endpoints**

1. **🏪 Productos** - Verificar que se obtienen desde FakeStore API
2. **📦 Órdenes** - Crear órdenes y consultar por estado
3. **💳 Pagos** - Procesar pagos de órdenes pendientes
4. **🚨 Errores** - Probar respuestas de error con datos inválidos

### **Casos de Prueba Recomendados**

#### **Productos**
- ✅ Obtener todos los productos
- ✅ Obtener producto específico por ID
- ✅ Obtener categorías disponibles
- ✅ Obtener productos por categoría
- ❌ Producto con ID inexistente (debe retornar 404)
- ❌ Categoría inexistente (debe retornar 404)

#### **Órdenes**
- ✅ Crear orden válida
- ✅ Consultar órdenes por estado válido
- ❌ Estado inválido (debe retornar 400)
- ❌ Sin órdenes para el estado (debe retornar 404)

#### **Pagos**
- ✅ Procesar pago de orden pendiente
- ❌ Cliente sin orden pendiente (debe retornar 404)
- ❌ Orden sin productos (debe retornar 400)

## 🔄 Flujo de Trabajo de la Aplicación

```
1. Cliente solicita productos → ProductService → FakeStore API
2. Cliente crea orden → OrderController → OrderService → Base de Datos
3. Cliente procesa pago → PaymentController → OrderService → Base de Datos
4. Respuestas consistentes con códigos HTTP apropiados
```

## 🚀 Despliegue

### **Desarrollo Local**
```bash
mvn spring-boot:run
```

### **Build del JAR**
```bash
mvn clean package
```

### **Ejecutar JAR**
```bash
java -jar target/springboot-shopping-cart-1.0.jar
```

### **Variables de Entorno**
```bash
export SERVER_PORT=8082
export SPRING_PROFILES_ACTIVE=prod
```

## 📊 Monitoreo y Logging

### **Logs de la Aplicación**
- **Spring Boot**: Logs automáticos de inicio y operación
- **Hibernate**: Logs de consultas SQL (configurable)
- **Interceptores**: Logging personalizado de requests/responses

### **Métricas Disponibles**
- **Health Check**: `/actuator/health` (si se agrega Spring Boot Actuator)
- **Logs de Transacción**: Cada operación genera un ID único
- **Timestamps**: Todas las respuestas incluyen timestamp

## 🔮 Próximas Mejoras (Opcionales)

### **Seguridad y Autenticación**
- [ ] Implementar Spring Security
- [ ] JWT Authentication
- [ ] Role-based Authorization
- [ ] API Key Management

### **Performance y Escalabilidad**
- [ ] Redis Cache para productos
- [ ] Rate Limiting
- [ ] Circuit Breaker Pattern
- [ ] Load Balancing

### **Monitoreo y Observabilidad**
- [ ] Spring Boot Actuator
- [ ] Micrometer Metrics
- [ ] Distributed Tracing
- [ ] Health Checks

### **Testing**
- [ ] Unit Tests con JUnit 5
- [ ] Integration Tests
- [ ] API Tests con TestContainers
- [ ] Performance Tests

## 🤝 Contribución

### **Estándares de Código**
- **Java**: Seguir convenciones de nomenclatura Java
- **Spring**: Usar anotaciones estándar de Spring Boot
- **Documentación**: Comentar métodos públicos y clases
- **Logging**: Usar niveles apropiados (INFO, WARN, ERROR)

### **Proceso de Desarrollo**
1. Crear rama feature para nuevas funcionalidades
2. Implementar cambios con tests unitarios
3. Ejecutar `mvn clean compile` antes de commit
4. Crear Pull Request con descripción detallada

## 📝 Licencia

Este proyecto está desarrollado como parte de la **evaluación técnica de Cuscatlán**. Todos los derechos reservados.

**Organización**: Cuscatlán  
**Tipo de Proyecto**: Evaluación Técnica - Carrito de Compras  
**Tecnología**: Spring Boot Microservices

## 👨‍💻 Autor

**Desarrollado por**: Evert Josue Gonzalez Lopez
**Fecha**: Agosto 2024
**Versión**: 1.0
**GitHub**: [@EvertGonzalez](https://github.com/EvertGonzalez)

## 📞 Soporte

Para preguntas o soporte técnico:
- **Email**: faris_alghazi1993@hotmail.com
- **GitHub**: [https://github.com/EvertGonzalez/PruebaCuscatlan](https://github.com/EvertGonzalez/PruebaCuscatlan)
- **Issues**: Crear issue en el repositorio del proyecto

---

## 🎉 ¡Gracias por revisar este proyecto!

Este proyecto demuestra las mejores prácticas de desarrollo con Spring Boot, incluyendo:
- ✅ Arquitectura de microservicios
- ✅ APIs RESTful bien diseñadas
- ✅ Manejo robusto de errores
- ✅ Integración con APIs externas
- ✅ Persistencia de datos con JPA
- ✅ Código limpio y mantenible

**Desarrollado con dedicación para la evaluación técnica de Cuscatlán**, este proyecto representa un ejemplo completo de implementación de microservicios con Spring Boot, siguiendo los estándares empresariales y las mejores prácticas de la industria.

¡Esperamos que este proyecto demuestre las capacidades técnicas y la calidad del código desarrollado!
