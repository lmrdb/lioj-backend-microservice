package com.li.liojbackendquestionservice.controller.inner;

import com.li.liojbackendclient.service.UserFeignClient;
import com.li.liojbackendmodel.entity.User;
import com.li.liojbackenduserservice.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * 该服务仅内部调用,不是给前端的
 */
@RestController("/inner")
public class UserInnerController implements UserFeignClient {


    @Resource
    UserService userService;

    /**
     * 根据id获取用户
     * @param userId
     * @return
     */
    @Override
    @GetMapping("/get/id")
    public User getById(@RequestParam("userId") Long userId){
      return userService.getById(userId);
    };


    /**
     * 根据id获取用户列表
     * @param idList
     * @return
     */
    @Override
    @GetMapping("/get/ids")
    public List<User> listByIds(@RequestParam("idList") Collection<Long> idList){
        return userService.listByIds(idList);
    };




}
