package com;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MongoDB {

    public static void main(String[] args) {
//        //连接到Mongodb服务
//        MongoClient mongoClient = new MongoClient("localhost",27017);
//        //连接到数据库
//        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
//        //获取集合
//        MongoCollection<Document> students = mongoDatabase.getCollection("student");
//        System.out.println(students.toString());
        MongoDB mongoDB = new MongoDB();
//        mongoDB.save();
//        mongoDB.query();
//        mongoDB.update();
        mongoDB.delete();
    }

    public void save(){
        //连接到Mongodb服务
        MongoClient mongoClient = new MongoClient("localhost",27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");

        Document document = new Document("username", "MongoDB")
                            .append("description", "a user")
                            .append("age", 10)
                            .append("sex", "boy");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
    }

    public void query(){
        //连接到Mongodb服务
        MongoClient mongoClient = new MongoClient("localhost",27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");

        //获取迭代器
        FindIterable<Document> findIterable = collection.find();
        //获取迭代游标
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        //执行迭代检索文档
        while( mongoCursor.hasNext() ){
            System.out.println(mongoCursor.next());
        }
    }

    public void update(){
        //连接到Mongodb服务
        MongoClient mongoClient = new MongoClient("localhost",27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        //更新文档
        collection.updateMany(Filters.eq("age",10)
                ,new Document("$set", new Document("username","Liddy")));
    }

    private void delete() {
        //连接到Mongodb服务
        MongoClient mongoClient = new MongoClient("localhost",27017);
        //连接到数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
        //获取集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        //删除符合条件的第一个文档
//        collection.deleteOne(Filters.eq("age",10));
        //删除符合条件的所有文档
        collection.deleteMany(Filters.gt("age", 19));
    }

}
