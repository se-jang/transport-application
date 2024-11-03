# Transport Application

## รายชื่อสมาชิก ของกลุ่ม JANG
- 6510450607 ปริยานุช มั่งคั่ง
- 6510450763 ภัควัฒน์ ปานกลาง

## Installation
1. **Clone repository**: `git clone https://github.com/se-jang/transport-application.git`
2. **Backend Setup**:
   - ติดตั้ง dependencies: `mvn install`
   - รัน Spring Boot application: `mvn spring-boot:run`
3. **Frontend Setup**:
   - เข้าไปที่โฟลเดอร์ frontend: `cd frontend`
   - ติดตั้ง dependencies: `npm install`
   - รัน application: `npm run serve`

## Configuration

### 1. การตั้งค่า Environment Variables
สร้างไฟล์ `.env` ในโฟลเดอร์ src/main/resources ด้วยเนื้อหาดังนี้:

```plaintext
JWT_SECRET=c2b000d555fe251e11a30a7ae438b65a6a51c9989f42997b361fd9c9bd543757d167ff92a3961ff4ca3c25c2122e94a3e53db62a45ffd198846a7441f57a68e82f1ca43e218a458ade6d2e979dd609198ebc0b75d96f87c60e3213a22321479a9cbb304bbf04ed0b49af8c04d5cef5aef143530b6867cb7627d965bc53979d253fa8686b0f249c29cdd447f443d896a2a939740ea9a376feada51a481f825cc65ebf25cde6e4ce308363c139dd1a6367249cf8a242882396792770ed98760970c12768eefa787c6f99c5fb446bb6c187a274310d8580a6db8e5120c420800e5717417047f6fd5a1fdf6529b62dfd6a262a5dca4b617d987c00f853b089084637
```
### 2. การตั้งค่าการส่งอีเมล
สร้างไฟล์ application-secret.properties ในโฟลเดอร์ src/main/resources ด้วยเนื้อหาดังนี้:

```plaintext
spring.email.sender.host=smtp.gmail.com
spring.mail.username=transportapplicationjab@gmail.com
spring.mail.password=iuzq kgqr uilf wcmn
spring.mail.debug=true
```

## Features

- **สร้างผู้ใช้ (Create User)**
   - Admin สามารถสร้าง Account ของ User และ Worker ได้
  
- **สร้างคำสั่งซื้อ (Create Order)**
   - ใช้สำหรับสร้างคำสั่งซื้อใหม่ในระบบโดยจะให้กรอกชื่อ ที่อยู่ของลูกค้า และ ข้อมูล product 

- **เข้าสู่ระบบ (Login)**
   - ผู้ใช้สามารถเข้าสู่ระบบโดยใช้ชื่อผู้ใช้และรหัสผ่านที่ admin ได้สร้างไว้

- **การจัดการคำสั่งซื้อ (Order Management)**
   - แสดงและกรองคำสั่งซื้อที่สถานะต่าง ๆ เช่น:
      - **Unchecked**
      - **Checked**
      - **On-going**

- **การอัพโหลดเอกสาร (File Upload)**
   - Worker สามารถอัพโหลดไฟล์ PDF ที่เกี่ยวข้องกับใบออเดอร์ได้หลังจากที่ลูกค้าเซ็นเอกสาร

- **การจัดการพนักงานขนส่ง (Transportation Worker Management)**
   - สามารถดูรายละเอียดและมอบหมายพนักงานขนส่งให้กับคำสั่งซื้อได้

## Usage
- เปิดเว็บเบราว์เซอร์ไปที่ `http://localhost:5173/login` เพื่อเข้าถึงระบบ
- เมื่อเปิดแอพพลิเคชั่น ระบบจะพาคุณไปที่หน้า **Login** เท่านั้น เนื่องจากเป็นแอพพลิเคชั่นแบบ **Private**
- **การสร้างผู้ใช้ (Create User)**:
    - ต้องให้ Admin เป็นผู้สร้างผู้ใช้ โดยสามารถทำได้ผ่าน Postman
    - ใช้คำสั่ง **POST** ที่ URL: `http://localhost:8080/create-user`
    - สำหรับการสร้างผู้ใช้ใหม่ ต้องส่งคำขอ (request) ตามรูปแบบดังนี้:

### Create User Request Example
```json
{
  "username": "user123",
  "name": "John Doe",
  "password": "your_password",
  "phoneNumber": "0123456789",
  "email": "john.doe@example.com",
  "role": 1
}
```
### Note for Creating Admin
- Role ของ User
    - 0 คือ Admin
    - 1 คือ User
    - 2 คือ Worker
- หากต้องการสร้างผู้ใช้ที่มีบทบาทเป็น Admin จะต้องสร้างผู้ใช้เป็น User ก่อน จากนั้นทำการเปลี่ยน status ของผู้ใช้ในฐานข้อมูลเป็น 0 เพื่อกำหนดให้เป็น Admin

### Field Descriptions
- **username**: ชื่อผู้ใช้ ต้องมีความยาวอย่างน้อย 4 ตัวอักษร
- **name**: ชื่อผู้ใช้ ต้องเป็นตัวอักษรและเว้นวรรค
- **password**: รหัสผ่าน ต้องมีความยาวตั้งแต่ 8 ตัวอักษรขึ้นไป
- **phoneNumber**: หมายเลขโทรศัพท์ ต้องเป็นตัวเลข 10 หลัก
- **email**: อีเมล ต้องมีรูปแบบที่ถูกต้อง
- **role**: ระบุบทบาทของผู้ใช้ เช่น USER หรือ WORKER


## Design Principles

### Single Responsibility Principle (SRP)
- **การใช้งานในโค้ด:**
    - `CreateOrderController` รับผิดชอบเฉพาะการสร้างคำสั่งซื้อใหม่ในระบบ
    - `LoginController` รับผิดชอบการจัดการการเข้าสู่ระบบของผู้ใช้
    - `OrderController` ดูแลการแสดงและจัดการคำสั่งซื้อที่มีอยู่
    - `CreateUserRequest` เป็นคลาสที่ใช้ในการสร้างผู้ใช้ใหม่ ซึ่งรวมถึงการตรวจสอบความถูกต้องของข้อมูลที่ส่งเข้ามา

### Dependency Inversion Principle (DIP)
- **การใช้งานในโค้ด:**
    - ใช้ Spring's `@Autowired` เพื่อจัดการ Dependency Injection ใน Service ต่าง ๆ เช่น `UserService` และ `OrderService` ช่วยให้โค้ดมีความยืดหยุ่นและง่ายต่อการทดสอบ
