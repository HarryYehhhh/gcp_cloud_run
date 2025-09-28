# ============================
# Stage 1: Build the app
# ============================
FROM eclipse-temurin:17-jdk AS builder

# 設定工作目錄
WORKDIR /app

# 複製 Maven wrapper & pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# 預先下載依賴 (避免每次改程式都重跑)
RUN ./mvnw dependency:go-offline

# 複製原始碼並建置
COPY src src
RUN ./mvnw clean package -DskipTests

# ============================
# Stage 2: Run the app
# ============================
FROM eclipse-temurin:17-jdk-jammy

# 設定工作目錄
WORKDIR /app

# 複製 Jar (從 builder 階段拿取)
COPY --from=builder /app/target/*.jar app.jar

# 開放 8080
EXPOSE 8080

# 啟動應用程式
ENTRYPOINT ["java", "-jar", "app.jar"]

