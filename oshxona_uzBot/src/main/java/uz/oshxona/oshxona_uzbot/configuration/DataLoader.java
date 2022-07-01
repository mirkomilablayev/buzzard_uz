package uz.oshxona.oshxona_uzbot.configuration;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import uz.oshxona.oshxona_uzbot.controller.MyBotService;
import uz.oshxona.oshxona_uzbot.entity.UserRole;
import uz.oshxona.oshxona_uzbot.repository.UserRepository;
import uz.oshxona.oshxona_uzbot.repository.UserRoleRepo;
import uz.oshxona.oshxona_uzbot.utils.Constant;


@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final MyBotService myBotService;
    private final UserRepository userRepository;
    private final UserRoleRepo userRoleRepo;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(this.myBotService);

        if (ddl.equalsIgnoreCase("create")
                || ddl.equalsIgnoreCase("create-drop")){



            userRoleRepo.save(new UserRole(Constant.admin));
            userRoleRepo.save(new UserRole(Constant.user));

        }



    }




}
