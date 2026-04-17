package com.blackwater.blackwater;

import com.blackwater.config.until.FileUtil;
import com.blackwater.dao.WaterDataDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@SpringBootTest
class BlackwaterApplicationTests {

    @Autowired
    private WaterDataDao waterDataDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private FileUtil fileUtil;


//    @Autowired
//    private MongoTemplate mongoTemplate;
//    @Test
//    void mongoTest() {
//        Book book = new Book();
//        book.setId("2");
//        book.setName("lihuaj");
//        book.setType("]不合适");
//        mongoTemplate.save(book);
//    }
//    @Test
//    void mongodbTest() {
//        List<Book> all = mongoTemplate.findAll(Book.class);
//        System.out.println(all);
//    }
//







    @Test
    void contextLoads() {
        String a = ".png";
        String ext = "." + a.split("\\.")[1];
        String uuid = UUID.randomUUID().toString().replace("-", "");
        System.out.println(uuid);
        String fileName = uuid + ext;
        System.out.println(fileName);
        System.out.println("---------------------------------");

        ApplicationHome applicationHome = new ApplicationHome(this.getClass());
        String path = applicationHome.getDir().getParentFile().getParentFile().getAbsolutePath() +
                "\\com\\blackwater\\A";
        System.out.println(path);
    }



    @Test
    void RedisOne() {
        redisTemplate.opsForValue().set("age", 90);
    }

    @Test
    void RedisTwo() {
//        Query query = new Query(Criteria.where("account").is(""));
//        mongoTemplate.remove(query, User.class);
    }



}
