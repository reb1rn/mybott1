package com.company;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.telegram.telegrambots.meta.api.methods.GetUserProfilePhotos;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

/**
 * Created by sviatosss on 18.12.2018.
 */
public class UsersManager {
    private static UsersManager sInstance;
    public MongoCollection<Document>  mUsersCollection = DataBaseManager.getInstance().getmUsersCollection();

    public static UsersManager getInstance() {
        if (sInstance == null) {
            sInstance = new UsersManager();
        }

        return sInstance;
    }
    public void issetUser(Update update){
        String id = Functions.getInstance().getId(update);
        Document query = new Document("id", id);
        Document user = mUsersCollection.find(query).first();

        if (user == null) {
            Document newUser = new Document("id", id)
                    .append("firstName", update.getMessage().getChat().getFirstName())
                    .append("aboutyou", "")
                    .append("interests", "")
                    .append("hobby", "")
                    .append("gender", "")
                    .append("age", "")
                    .append("username",update.getMessage().getChat().getUserName())
                    .append("current_querty", "");
            mUsersCollection.insertOne(newUser);
            System.out.println("New user");
        }
    }

    public void updateQuery(Update update, String newQuest){
        String id = Functions.getInstance().getId(update);
        mUsersCollection.updateOne(eq("id", id), new Document("$set", new Document("current_querty", newQuest)));
    }
    public String getQuery(Update update){
        return getUserInfoString(update, "current_querty");
    }

    public String getUserInfoString(Update update, String field){
        String id = Functions.getInstance().getId(update);
        Document query = new Document("id", id);
        Document user = mUsersCollection.find(query).first();
        return user.getString(field);
    }

    public void updateUserInfo(Update update, String field, String value){
        String id = Functions.getInstance().getId(update);
        mUsersCollection.updateOne(eq("id", id), new Document("$set", new Document(field, value)));
    }
    public ArrayList<User> getLAnketa(){
        ArrayList<User> anketa = new ArrayList<>();
        for (Document user: mUsersCollection.find()){
            User createdUser = new User(user.getString("aboutyou"), user.getString("interests"),user.getString("hobby"), user.getString("gender"), user.getString("age"),user.getString("username"));
            anketa.add(createdUser);
        }


        return anketa;
    }

}