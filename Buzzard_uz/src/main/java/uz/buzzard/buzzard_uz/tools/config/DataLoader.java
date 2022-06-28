package uz.buzzard.buzzard_uz.tools.config;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.buzzard.buzzard_uz.entity.User;
import uz.buzzard.buzzard_uz.entity.UserRole;
import uz.buzzard.buzzard_uz.repository.entity.RoleRepo;
import uz.buzzard.buzzard_uz.repository.entity.UserRepo;
import uz.buzzard.buzzard_uz.tools.Constant;
import uz.buzzard.buzzard_uz.tools.exceptions.UserRoleNotFoundException;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;




@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;



    @Value(value = "${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        if (ddl.equalsIgnoreCase("create") || ddl.equalsIgnoreCase("create-drop")) {
//            User roles
            roleRepo.save(new UserRole(Constant.SUPER_ADMIN));
            roleRepo.save(new UserRole(Constant.USER));
            roleRepo.save(new UserRole(Constant.TEACHER));
            roleRepo.save(new UserRole(Constant.STUDENT));
            // this is super admin
            User admin = new User();
            admin.setFullName("Ablayev");
            admin.setUserRoleSet(new HashSet<UserRole>(Collections.singletonList(
                    roleRepo.findByNameAndIsActive(Constant.SUPER_ADMIN, true).orElseThrow(() ->
                            new UserRoleNotFoundException(Constant.SUPER_ADMIN + " role not found"))
            )));
            admin.setUsername("1");
            admin.setPassword(passwordEncoder.encode("1"));
            userRepo.save(admin);
        }
    }
}
