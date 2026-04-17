package com.blackwater.blackwater;

import com.blackwater.config.redis.RedisUtil;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringRedisTest {

//    @Autowired
//    RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    private RedisUtil redisUtil;
//    @Autowired
//    private CommentServiceImpl commentService;
    @Test
    void get() {
//        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
//        String age = ops.get("age");
//        System.out.println(age);

    }


//    @Test
//    void set(){
//        ValueOperations ops = redisTemplate.opsForValue();
//        Camera camera = new Camera();
//        camera.setId(12);
//        camera.setStatus("还可以了");
//        camera.setUpdateTime("鱼干");
//        ops.set("camera1",camera);
//        System.out.println(ops.get("camera1"));
//
//    }
    @Test
    void split(){
        String s = new String("Accept what was and what is, and you'll have morepositive energy to pursue what will be");
        String[] s1 = s.split(" ");
        int sum = 0;
        for (int i = 0; i < s1.length; i++) {
            if(s1[i].equals("what")){
                sum++;
            }
        }
        System.out.println(sum);
    }
    @Test
    public void testMaches(){
        String content ="hello world;hello Beijing";
        //String regStr="hello";
        String regStr="hello";
        //如果匹配，就返回true，否则返回false
        boolean matches = Pattern.matches(regStr, content);
        System.out.println("返回结果为："+matches);

    }
    @Test
    public void matcher(){
        String regStr="hello.*.hello.Beijing";
        String content="hello world,hello Beijing";
        Pattern pattern = Pattern.compile(regStr);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()){
            System.out.println("----------------");
            //返回找到目标的起始索引
            System.out.println(matcher.start());
            //返回找到目标的结束索引
            System.out.println(matcher.end());
        }
        System.out.println(matcher.matches());
    }
    @Test
    public void testSaveComment(){
//        Comment comment=new Comment();
//        comment.setArticleid("100000");
//        comment.setContent("测试添加的数据");
//        comment.setCreatedatetime(LocalDateTime.now());
//        comment.setUserid("1003");
//        comment.setNickname("凯撒大帝");
//        comment.setState("1");
//        comment.setLikenum(0);
//        comment.setReplynum(0);
//        commentService.saveComment(comment);
    }
    /**
     * 查询所有数据
     */
    @Test
    public void testFindAll(){
//        List<Comment> list = commentService.findCommentList();
//        System.out.println(list);
    }


    @Test
    public  void testFile(){
        String name = "D:\\video\\aaa.txt";
        File file = new File(name);

    }




}
