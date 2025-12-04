# 📊 Polling Application

A full-stack web application built with Spring Boot and Angular that enables users to create polls and vote in real-time.

## 🚀 Features

- **Create Polls**: Users can create polls with multiple voting options
- **Real-time Voting**: Cast votes and see results instantly
- **View Poll Results**: Visual representation of vote counts for each option
- **RESTful API**: Clean and well-structured backend API
- **Responsive UI**: Modern Angular frontend with mobile-friendly design

## 🛠️ Tech Stack

### Backend
- **Framework**: Spring Boot 3.x
- **Language**: Java 17+
- **Database**: MySQL
- **Build Tool**: Maven
- **ORM**: Spring Data JPA
- **Architecture**: RESTful API with Service Layer Pattern

### Frontend
- **Framework**: Angular 20.3.9
- **Language**: TypeScript
- **Build Tool**: Angular CLI
- **HTTP Client**: HttpClient
- **Styling**: CSS3

## 📋 Prerequisites

- Java JDK 17 or higher
- Maven 3.6+
- Node.js 18+ and npm
- MySQL 8.0+
- Angular CLI 20+

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Ankur071/Polling-App.git
cd Polling-App
```

### 2. Database Setup

```sql
-- Create database
CREATE DATABASE polling_db;

-- The tables will be auto-created by Spring Boot JPA
```

### 3. Backend Setup

```bash
# Navigate to backend directory
cd votingapp

# Configure database connection in src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/polling_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# Build and run the application
mvnw clean install
mvnw spring-boot:run
```

The backend server will start at `http://localhost:8080`

### 4. Frontend Setup

```bash
# Navigate to frontend directory
cd votingapp-angular

# Install dependencies
npm install

# Start development server
ng serve
```

The frontend application will start at `http://localhost:4200`

## 📁 Project Structure

```
Polling-App/
├── votingapp/                          # Spring Boot Backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/voting/votingapp/
│   │   │   │   ├── VotingappApplication.java
│   │   │   │   ├── controllers/
│   │   │   │   │   └── PollController.java
│   │   │   │   ├── entity/
│   │   │   │   │   ├── Poll.java
│   │   │   │   │   └── OptionVote.java
│   │   │   │   ├── repositories/
│   │   │   │   │   └── PollRepository.java
│   │   │   │   ├── services/
│   │   │   │   │   └── PollService.java
│   │   │   │   └── request/
│   │   │   │       └── Vote.java
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── mvnw
│
├── votingapp-angular/                  # Angular Frontend
│   ├── src/
│   │   ├── app/
│   │   │   ├── app.ts                 # Main component
│   │   │   ├── app.config.ts          # App configuration
│   │   │   ├── app.routes.ts          # Routing
│   │   │   ├── poll.models.ts         # Data models
│   │   │   ├── poll.service.ts        # HTTP service
│   │   │   └── poll/
│   │   │       ├── poll.component.ts
│   │   │       ├── poll.component.html
│   │   │       └── poll.component.css
│   │   ├── index.html
│   │   ├── main.ts
│   │   └── styles.css
│   ├── angular.json
│   ├── package.json
│   └── tsconfig.json
│
└── README.md
```

## 🔌 API Endpoints

### Base URL: `http://localhost:8080`

#### Poll Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| GET | `/polls` | Get all polls | - |
| GET | `/polls/{id}` | Get poll by ID | - |
| POST | `/polls` | Create new poll | Poll object |
| PUT | `/polls/{id}` | Update poll | Poll object |
| DELETE | `/polls/{id}` | Delete poll | - |

#### Voting Endpoints

| Method | Endpoint | Description | Request Body |
|--------|----------|-------------|--------------|
| POST | `/polls/{id}/vote` | Cast a vote | Vote object |

### Sample Request/Response

**Create Poll (POST /polls)**
```json
Request:
{
  "question": "What's your favorite programming language?",
  "options": [
    { "optionText": "Java" },
    { "optionText": "Python" },
    { "optionText": "JavaScript" }
  ]
}

Response:
{
  "id": 1,
  "question": "What's your favorite programming language?",
  "options": [
    { "id": 1, "optionText": "Java", "voteCount": 0 },
    { "id": 2, "optionText": "Python", "voteCount": 0 },
    { "id": 3, "optionText": "JavaScript", "voteCount": 0 }
  ]
}
```

**Cast Vote (POST /polls/{id}/vote)**
```json
Request:
{
  "optionId": 1
}

Response:
{
  "id": 1,
  "question": "What's your favorite programming language?",
  "options": [
    { "id": 1, "optionText": "Java", "voteCount": 1 },
    { "id": 2, "optionText": "Python", "voteCount": 0 },
    { "id": 3, "optionText": "JavaScript", "voteCount": 0 }
  ]
}
```

## 🗄️ Database Schema

### Poll Table
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| question | VARCHAR(255) | NOT NULL |

### OptionVote Table
| Column | Type | Constraints |
|--------|------|-------------|
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT |
| option_text | VARCHAR(255) | NOT NULL |
| vote_count | INT | DEFAULT 0 |
| poll_id | BIGINT | FOREIGN KEY (Poll.id) |

### Entity Relationships
- **Poll** (1) ↔ (Many) **OptionVote**
  - One poll can have multiple voting options
  - Each option belongs to one poll (OneToMany/ManyToOne relationship)

## 🧪 Testing

### Backend Tests
```bash
cd votingapp
mvnw test
```

### Frontend Tests
```bash
cd votingapp-angular
ng test
```

## 🚀 Build for Production

### Backend
```bash
cd votingapp
mvnw clean package
java -jar target/votingapp-0.0.1-SNAPSHOT.jar
```

### Frontend
```bash
cd votingapp-angular
ng build --configuration production
# Deploy the dist/votingapp-angular folder
```

## 📝 Configuration

### Backend Configuration (application.properties)
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/polling_db
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# Server Configuration
server.port=8080

# CORS Configuration (if needed)
spring.web.cors.allowed-origins=http://localhost:4200
```

### Frontend Configuration
Update API URL in `poll.service.ts` if backend URL changes:
```typescript
private apiUrl = 'http://localhost:8080/polls';
```

## 🌟 Key Features Implementation

### Backend Architecture
- **Controller Layer**: Handles HTTP requests (`PollController`)
- **Service Layer**: Business logic (`PollService`)
- **Repository Layer**: Database operations (`PollRepository`)
- **Entity Models**: JPA entities (`Poll`, `OptionVote`)
- **Request DTOs**: Data transfer objects (`Vote`)

### Frontend Architecture
- **Components**: Modular UI components (`PollComponent`)
- **Services**: HTTP communication (`PollService`)
- **Models**: TypeScript interfaces (`poll.models.ts`)
- **Routing**: Angular Router configuration

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'feat: Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**Ankur**
- GitHub: [@Ankur071](https://github.com/Ankur071)

## 🙏 Acknowledgments

- Spring Boot Documentation
- Angular Documentation
- Telusko for project guidance

## 📧 Support

For issues and questions, please create an issue in the GitHub repository.

---

⭐ If you found this project helpful, please consider giving it a star!