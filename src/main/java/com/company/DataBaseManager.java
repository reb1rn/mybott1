package com.company;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DataBaseManager {
    private static DataBaseManager sInstance;

    private static final String DB_HOST = "ds113169.mlab.com";
    private static final int DB_PORT = 13169;
    private static final String DB_NAME = "heroku_8f7sgf4c";
    private static final String DB_USER = "admin919";
    private static final String DB_PASSWORD = "admin919_passs";

    private static final String DB_URL = "mongodb://" + DB_USER + ":" + DB_PASSWORD + "@" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME;

    private MongoCollection<Document> mUsersCollection;
    private MongoCollection<Document> mTestsCollection;

    private DataBaseManager() {
        MongoClientURI clientURI = new MongoClientURI(DB_URL);
        MongoClient client = new MongoClient(clientURI);
        MongoDatabase db = client.getDatabase(DB_NAME);
        mUsersCollection = db.getCollection("users_vlad");
    }

    public static DataBaseManager getInstance() {
        if (sInstance == null) {
            sInstance = new DataBaseManager();
        }
        return sInstance;
    }

    public MongoCollection<Document> getmUsersCollection() {
        return mUsersCollection;
    }

}
