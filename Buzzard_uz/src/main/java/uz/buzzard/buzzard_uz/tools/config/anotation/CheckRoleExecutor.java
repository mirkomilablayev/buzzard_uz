package uz.buzzard.buzzard_uz.tools.config.anotation;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.buzzard.buzzard_uz.entity.User;
import uz.buzzard.buzzard_uz.entity.UserRole;
import uz.buzzard.buzzard_uz.tools.exceptions.ForbiddenException;


@Component
@Aspect
public class CheckRoleExecutor {
    @Before(value = "@annotation(checkRole)")
    public void checkRoleMethod(CheckRole checkRole) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        boolean flag = false;
        for (UserRole userRole : user.getUserRoleSet()) {
            if (userRole.getAuthority().equalsIgnoreCase(checkRole.value())) {
                flag = true;
                break;
            }
        }
        if (!flag){
            throw new ForbiddenException("You don't have a permission");
        }
    }
}
