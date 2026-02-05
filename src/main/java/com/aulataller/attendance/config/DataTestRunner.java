package com.aulataller.attendance.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DataTestRunner implements CommandLineRunner {

    //    private final UserRepository userRepository;
//    private final AttendanceFlowService attendanceFlowService;


    @Override
    public void run(String... args) {


//        attendanceFlowService.processAttendance(
//                "999999999",
//                "Jhoan",
//                "Londoño",
//                "jhoan@mail.com",
//                "3000000000",
//                "Engineering",
//                Role.STUDENT,
//                "Databases",
//                "DB-101",
//                "Lab",
//                BigDecimal.valueOf(2)
//        );

//        String testNationalId = "1234567890";
//
//        if (!userRepository.existsByNationalId(testNationalId)) {
//            User user = User.builder().nationalId(testNationalId).firstName("Test").lastName("User").career("Software Engineering").role(Role.STUDENT).build();
//            userRepository.save(user);
//            System.out.println("✅ Usuario de prueba creado");
//        }
//
//        userRepository.findByNationalId(testNationalId)
//                .ifPresent(u ->
//                        System.out.println("👤 Usuario encontrado: " + u.getFirstName())
//                );
    }
}