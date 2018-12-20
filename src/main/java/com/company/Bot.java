package com.company;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;



import java.util.ArrayList;
import java.util.Date;

public class Bot extends TelegramLongPollingBot {

    private static  final  String BOT_NAME ="FindIt1_bot";
    private static  final  String BOT_TOKEN="771393537:AAEPAmaAnPF3c36h48ZibDpaiqR9U7xG974";
public boolean add;
public  boolean aboutme = false;
    public  boolean interests = false;
    public  boolean hobby = false;
    public  boolean gender = false;
    public  boolean age = false;

    @Override
    public void onUpdateReceived(Update update) {
        Sending sending = new Sending();
        ArrayList<User> userArrayList = UsersManager.getInstance().getLAnketa();
        String ms= update.getMessage().getText();
alerts a= new alerts();

        for (User user : userArrayList) {
            if(ms.equals("Переглянути анкети")) {

                sending.sendMsg(update.getMessage(), "Про корситувача:"+"\n\t"+user.aboutyou+"\n"+"Інтереси користувача:"+"\n\t"+user.interests+"\n"+"Хоббі користувача:"+"\n\t"+user.hobby+"\n"+"Стать користувача:"+"\n\t"+user.gender+"\n"+"Вік користувача:"+"\n\t"+user.age+"\n"+"Нікнейм користувача:"+"\n\t"+"@"+user.username);
            }


        }

        if (update.hasMessage()){
            String s = update.getMessage().getText();
            if (s.equals("/start")){
                UsersManager.getInstance().issetUser(update);
                sending.sendKeyboard(update.getMessage());
                sending.sendMsg(update.getMessage(),a.info());
                sending.sendMsg(update.getMessage(),"Щоб прочитати дану інформацію ще раз, використовуйте кнопку\"/start\"");
            }

            else if(s.equals("Заповнити анкету")){
                UsersManager.getInstance().updateQuery(update, "add_anketa_1");
                sending.sendMsg(update.getMessage(), "Введіть дані про себе✍:");
            }else if (UsersManager.getInstance().getQuery(update).contains("add_anketa")){
                if (UsersManager.getInstance().getQuery(update).equals("add_anketa_1")){
                    UsersManager.getInstance().updateUserInfo(update, "aboutyou", s);
                    UsersManager.getInstance().updateQuery(update,"add_anketa_2");
                    sending.sendMsg(update.getMessage(), "\"Введіть ваші Інтереси✍:");
                }else if (UsersManager.getInstance().getQuery(update).equals("add_anketa_2")){
                    UsersManager.getInstance().updateUserInfo(update, "interests", s);
                    UsersManager.getInstance().updateQuery(update,"add_anketa_3");
                    sending.sendMsg(update.getMessage(), "\"Введіть ваші Хоббі✍:");
                }
                else if (UsersManager.getInstance().getQuery(update).equals("add_anketa_3")){
                    UsersManager.getInstance().updateUserInfo(update, "hobby", s);
                    UsersManager.getInstance().updateQuery(update,"add_anketa_4");
                    sending.sendMsg(update.getMessage(), "\"Введіть вашу Cтать✍:");
                }
                else if (UsersManager.getInstance().getQuery(update).equals("add_anketa_4")){
                    UsersManager.getInstance().updateUserInfo(update, "gender", s);
                    UsersManager.getInstance().updateQuery(update,"add_anketa_5");
                    sending.sendMsg(update.getMessage(), "\"Введіть ваш Вік✍:");
                }
                else if (UsersManager.getInstance().getQuery(update).equals("add_anketa_5")){
                    UsersManager.getInstance().updateUserInfo(update, "age", s);
             //       UsersManager.getInstance().updateQuery(update,"add_anketa_6");
                    sending.sendMsg(update.getMessage(), "Анкету успішно додано ✅");
                }

            }
        }
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}

