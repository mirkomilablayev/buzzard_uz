package uz.buzzard.buzzard_uz.service.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.buzzard.buzzard_uz.dto.auth.RegisterDto;
import uz.buzzard.buzzard_uz.entity.User;
import uz.buzzard.buzzard_uz.entity.UserRole;
import uz.buzzard.buzzard_uz.repository.entity.RoleRepo;
import uz.buzzard.buzzard_uz.repository.entity.UserRepo;
import uz.buzzard.buzzard_uz.tools.exceptions.ResourceNotFoundException;
import uz.buzzard.buzzard_uz.tools.exceptions.UserRoleNotFoundException;
import uz.buzzard.buzzard_uz.tools.exceptions.UsernameAlreadyRegisterException;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;


    public HttpEntity<?> register(RegisterDto registerDto) {
        if (!userRepo.existsByUsernameAndIsDeleted(registerDto.getUsername(),false)) {
            User user = new User();
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            String user_role = "USER";
            Set<UserRole> userRoles = new HashSet<>();
            UserRole userRole = roleRepo.findByNameAndIsActive("USER",true).orElseThrow(() -> new UserRoleNotFoundException(user_role + " not found"));
            userRoles.add(userRole);
            user.setFullName(registerDto.getFullName());
            user.setUsername(registerDto.getUsername());
            user.setUserRoleSet(userRoles);
            return ResponseEntity.status(HttpStatus.OK).body(userRepo.save(user));
        }
        throw new UsernameAlreadyRegisterException(registerDto.getUsername()+ " is already registered");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return   userRepo.findByUsernameAndIsDeleted(username,false).orElseThrow(() -> new ResourceNotFoundException(""));
    }
}
