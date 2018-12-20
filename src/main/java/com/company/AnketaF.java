package com.company;

import org.telegram.telegrambots.meta.api.objects.Message;

public class AnketaF {
    public void getQuest(Message message, User user){
        Sending sending = new Sending();
        sending.sendMsg(message,
                user.aboutyou + "\n" +
                        user.interests + "\n" +
                        user.hobby + "\n" +
                        user.gender + "\n"
        + user.age);
    }
}
