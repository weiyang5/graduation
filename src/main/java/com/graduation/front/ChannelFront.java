package com.graduation.front;

import com.graduation.entity.Channel;
import com.graduation.service.ChannelService;
import com.graduation.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/front/channel")
public class ChannelFront {
    @Autowired
    private ChannelService channelService;

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(channelService.getById(id));
    }

    @PostMapping("tree")
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
    public List<Map<String,Object>> children(int id, List<Channel> list){
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
