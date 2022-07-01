package uz.oshxona.oshxona_uzbot.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import uz.oshxona.oshxona_uzbot.configuration.BotConfiguration;
import uz.oshxona.oshxona_uzbot.entity.User;
import uz.oshxona.oshxona_uzbot.exception.UserNotFoundException;
import uz.oshxona.oshxona_uzbot.repository.UserRepository;
import uz.oshxona.oshxona_uzbot.repository.UserRoleRepo;
import uz.oshxona.oshxona_uzbot.utils.Constant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MyBotService extends TelegramLongPollingBot {


    private final BotConfiguration botConfiguration;
    private final UserRepository userRepository;
    private final UserRoleRepo roleRepo;


    @Override
    public String getBotUsername() {
        return this.botConfiguration.getUsername();
    }

    @Override
    public String getBotToken() {
        return this.botConfiguration.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {

    }


    public String getChatId(Update update) {
        if (update.hasMessage()) {
            return update.getMessage().getChatId().toString();
        } else if (update.hasCallbackQuery()) {
            return update.getCallbackQuery().getMessage().getChatId().toString();
        } else return "";
    }


    public User getCurrentUser(Update update, String chatId) {
        Optional<User> userOptional = userRepository.findByChatId(chatId);
        try {
            return userOptional.orElseThrow(UserNotFoundException::new);
        } catch (UserNotFoundException e) {
            User user = new User();
            Message message = update.getMessage();
            user.setRealId(message.getFrom().getId().toString());
            user.setChatId(chatId);
            user.setFistName(message.getFrom().getFirstName());
            user.setUserRoles(new ArrayList<>(
                    Arrays.asList(
                            roleRepo.findByName(Constant.user).get()
                    )));
            user.setStatus(Constant.step1);
            return userRepository.save(user);
        }
    }


    private String getText(Update update) {
        try {
            if (update.hasMessage()) {
                Message message = update.getMessage();
                if (message.hasText()) return message.getText();
                else if (message.hasContact()) return message.getContact().getPhoneNumber();
                else return "  ";
            } else
                return "  ";
        } catch (Exception e) {
            return "  ";
        }
    }

    private String getCalBackData(Update update) {
        try {
            return update.getCallbackQuery().getData();
        } catch (Exception e) {
            return "  ";
        }
    }

    public Boolean isBlank(String str) {
        if (str != null)
            return str.trim().isEmpty();
        else
            return false;
    }


//    public void PdfTicketMaker(List<BookingToCourse> booking) {
//        try {
//            PdfWriter writer = new PdfWriter("src/main/resources/pdfList.pdf");
//            PdfDocument pdfDocument = new PdfDocument(writer);
//            pdfDocument.addNewPage();
//            List<BookersDto> bookersDtos = makeBookers(booking);
//            Document document = new Document(pdfDocument);
//            for (BookersDto bookersDto : bookersDtos) {
//                document.add(new Paragraph(bookersDto.getFullName()));
//                for (String cours : bookersDto.getCourses()) {
//                    document.add(new Paragraph(cours));
//                }
//                document.add(new Paragraph("---------------------"));
//            }
//            document.close();
//            System.out.println("PDF Created");
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }


}
