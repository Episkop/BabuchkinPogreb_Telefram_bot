package com.example.telefram_bot.Controller;


import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Log4j
@Component
//@PropertySource("classpath:application.properties")
public class TelegramBot extends TelegramLongPollingBot {
    @Value("${bot.name}")
    String botName;
    @Value("${bot.token}")
    String botToken;
    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        var massage = update.getMessage();
        log.debug(massage.getText());

        SendMessage respose = new SendMessage();
        respose.setChatId(massage.getChatId().toString());
        respose.setText("Hi, from bot!");
        sendAnsverMassege(respose);
    }
    public void sendAnsverMassege(SendMessage message){
        if(message!= null){
            try {
                execute(message);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
