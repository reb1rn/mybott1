package com.company;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class Sending extends Bot {
    public  void sendMsg(Message message, String s){
        SendMessage sendMessage = new SendMessage();
       // sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(s);
        try {
            execute(sendMessage);
        }catch (TelegramApiException e){
            e.printStackTrace();
        }
    }



    public void sendKeyboard(Message myMessage){
        SendMessage message = new SendMessage()
                .setChatId(myMessage.getChatId())
                .setText("Your keyboard ⬇");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Заповнити анкету");
        row.add("Переглянути анкети");
        row.add("/start");
        keyboard.add(row);
       // row = new KeyboardRow();
        //row.add("Матан");
        //row.add("Англ мова");
        //row.add("Економіка");
        //row.add("Список");
        //keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        keyboardMarkup.setResizeKeyboard(true);
        message.setReplyMarkup(keyboardMarkup);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMainMenu(Update update){

        SendMessage message = InlineKeyboardBuilder.create(update.getMessage().getChat().getId())
                .setText("Головне меню:")
                .row()
                .button("Заповнити анкету", "menu_main_filter")
                .button("Переглянути анкети", "menu_main_service")
                .button("Як це працює?", "menu_main_catalog")
                .endRow()
                .build();

        try {
            // Send the message
            sendApiMethod(message);
            //execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendAnketQuestion(Update update, String title, String callBack){

        SendMessage message = InlineKeyboardBuilder.create(update.getMessage().getChat().getId())
                .setText("Заповніть акнкету:")
                .row()
                .button(title, callBack)
                .endRow()
                .build();
        try {
            // Send the message
            sendApiMethod(message);
            //execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

}
