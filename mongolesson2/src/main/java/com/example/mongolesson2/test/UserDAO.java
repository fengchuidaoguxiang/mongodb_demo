package com.example.mongolesson2.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入操作
     */
    public void save( User user ){
        mongoTemplate.save(user);
    }

    /**
     *查找操作
     */
    public User findByName( String name ){
        Query query = new Query(Criteria.where("name").is(name));
        User user = mongoTemplate.findOne(query,User.class);
        return user;
    }

    /**
     * 修改操作
     */
    public void update(User user){
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("age", user.getAge()).set("name",user.getName());
        //更新查询返回结果集的第1条
//        mongoTemplate.updateFirst(query, update, User.class);
        //更新查询返回结果集的所有
        mongoTemplate.updateMulti(query,update,User.class);
    }

    /**
     * 删除操作
     */
    public void delete(Integer id){
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,User.class);
    }



}
