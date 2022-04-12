package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Channel;
import com.graduation.service.ChannelService;
import com.graduation.util.Result;
import com.graduation.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/channel")
public class ChannelController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ChannelService channelService;
    @PostMapping("create")
    public Result create(@RequestBody Channel channel){
        String token = UserHolder.getUser().getToken();
        UserDTO userDTO = (UserDTO)redisTemplate.opsForValue().get(token);
        channel.setCreateDate(new Date());
        channel.setCreateUser(userDTO.getId());
        if(channel.getParentId() == null){
            channel.setParentId(0);
        }
        boolean b = channelService.save(channel);
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = channelService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Channel channel){
        boolean update = channelService.updateById(channel);

        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(channelService.getById(id));
    }

    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }

        Page<Channel> page=new Page<>(page1,10);
        LambdaQueryWrapper<Channel> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        if(jsonString.get("name")!=null){
            lambdaQueryWrapper.like(Channel::getName,jsonString.get("name"));
        }
        channelService.getBaseMapper().selectPage(page,lambdaQueryWrapper);
        //userService.query().page(page);
        return Result.ok(page);
    }
    @GetMapping("tree")
    public Result tree(){
        List<Channel> list = channelService.getBaseMapper().selectList(null);
        List<Map<String,Object>> mapList = new ArrayList<>();
        list.forEach(channel -> {
            if(channel.getParentId() == 0){
                Map map = new HashMap();
                map.put("id",channel.getId());
                map.put("label",channel.getName());
                if(isChildren(channel.getId(),list)){
                    map.put("children",children(channel.getId(),list));
                }
                mapList.add(map);
            }
        });
        return Result.ok(mapList);
    }
    public List<Map<String,Object>> children(int id,List<Channel> list){
        List<Map<String,Object>> children = new ArrayList<>();
        for (Channel channel : list) {
            if(channel.getParentId() == id){
                Map map = new HashMap();
                map.put("id",channel.getId());
                map.put("label",channel.getName());
                if(isChildren(channel.getId(),list)){//出口
                    map.put("children",children(channel.getId(),list));
                }
                children.add(map);
            }
        }
        return children;
    }

    public boolean isChildren(int id,List<Channel> list){
        boolean flag = false;
        for (Channel channel : list) {
            if(channel.getParentId() == id){
                flag = true;
            }
        }
        return flag;
    }
}
