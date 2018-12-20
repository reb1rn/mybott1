package com.company;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DataBaseManager {
    private static DataBaseManager sInstance;

    private static final String DB_HOST = "ds131711.mlab.com";
    private static final int DB_PORT = 31711;
    private static final String DB_NAME = "heroku_3bln0m72";
    private static final String DB_USER = "Virus2310";
    private static final String DB_PASSWORD = "Virus231098";

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
