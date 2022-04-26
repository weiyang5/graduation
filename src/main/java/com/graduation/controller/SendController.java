package com.graduation.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.graduation.dto.UserDTO;
import com.graduation.entity.Favor;
import com.graduation.entity.Resume;
import com.graduation.entity.Send;
import com.graduation.service.*;
import com.graduation.util.RequiresRoles;
import com.graduation.util.Result;
import com.graduation.util.Role;
import com.graduation.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/send")
public class SendController {
    @Autowired
    private SendService sendService;
    @Autowired
    private ResumeService resumeService;

    @Autowired
    private CompanyService companyService;
    @Autowired
    private PostService postService;

    @Autowired
    private StudentService studentService;

    @PostMapping("create")
    public Result create(@RequestBody Map<String,Integer> map){
        UserDTO user = UserHolder.getUser();
        //1、判断用户是否登录
        if(user == null){
            return Result.ok("未登录不能操作");
        }
        Integer postId = map.get("postId");
        Integer companyId = map.get("companyId");

        //2、判断是否重复投递简历
        Send sendParam = new Send();
        sendParam.setStudentId(user.getId());
        sendParam.setPostId(postId);
        sendParam.setCompanyId(companyId);
        LambdaQueryWrapper<Send> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.setEntity(sendParam);
        Send send1 = sendService.getBaseMapper().selectOne(lambdaQueryWrapper);
        if(send1!=null){
            return Result.fail("请勿重复投递简历");
        }

        Send send = new Send();
        send.setPostId(postId);
        send.setCompanyId(companyId);
        send.setSendDate(new Date());
        send.setStatus(0);
        send.setStudentId(user.getId());

        //3、判断简历是否开放
        Resume param = new Resume();
        param.setStudentId(user.getId());
        param.setStatus(1);
        LambdaQueryWrapper<Resume> lambdaQueryWrapper1=new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.setEntity(param);
        Resume resume1 = resumeService.getBaseMapper().selectOne(lambdaQueryWrapper1);
        if(resume1!=null){
            send.setResumeId(resume1.getId());
        }else{
            return Result.fail("请完善简历");
        }
        //4、投递
        boolean save = sendService.save(send);
        if(save){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("delete")
    public Result delete(String ids){
        String[] arr = ids.split(",");
        boolean b = sendService.removeByIds(Arrays.asList(arr));
        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("update")
    public Result update(@RequestBody Send send){
        boolean b = sendService.updateById(send);

        if(b){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @PostMapping("updateStatus")
    public Result updateStatus(@RequestBody Send send){
        LambdaQueryWrapper<Send> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Send::getCompanyId,send.getCompanyId())
                .eq(Send::getResumeId,send.getResumeId())
                .eq(Send::getPostId,send.getPostId());

        boolean update = sendService.update(send, lambdaQueryWrapper);
        if(update){
            return Result.ok();
        }else{
            return Result.fail();
        }
    }

    @GetMapping("detail")
    public Result detail(Integer id){
        return  Result.ok(sendService.getById(id));
    }

    @RequiresRoles(type = Role.COMPANY)
    @PostMapping("query")
    public Map<String,Object> query(@RequestBody HashMap<String, String> jsonString){
        Integer id = UserHolder.getUser().getId();
        List<Map<String,Object>> list=new ArrayList<>();
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        LambdaQueryWrapper<Send> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Send::getCompanyId,id);
        List<Send> sends = sendService.getBaseMapper().selectList(lambdaQueryWrapper);
        sends.forEach(item->{
            Map<String,Object> map=new HashMap<>();
            map.put("id",item.getId());
            map.put("postName",postService.getById(item.getPostId()).getName());
            map.put("companyName",companyService.getById(item.getCompanyId()).getName());
            map.put("studentName",studentService.getById(item.getStudentId()).getName());
            map.put("resumeName",resumeService.getById(item.getResumeId()).getName());
            map.put("sendDate",item.getSendDate());
            map.put("status",item.getStatus());
            list.add(map);
        });
        Page page=new Page(page1,10);
        page.setRecords(list);
        page.setTotal(list.size());
        return Result.ok(page);
    }

    @RequiresRoles(type = Role.STUDENT)
    @PostMapping("student_send")
    public Map<String,Object> student_send(@RequestBody HashMap<String, String> jsonString){
        Integer id = UserHolder.getUser().getId();
        List<Map<String,Object>> list=new ArrayList<>();
        Integer page1;
        if(jsonString.get("page")==null){
            page1=1;
        }else{
            page1 = Integer.valueOf(jsonString.get("page"));
        }
        LambdaQueryWrapper<Send> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Send::getStudentId,id);
        List<Send> sends = sendService.getBaseMapper().selectList(lambdaQueryWrapper);
        sends.forEach(item->{
            Map<String,Object> map=new HashMap<>();
            map.put("id",item.getId());
            map.put("postName",postService.getById(item.getPostId()).getName());
            map.put("companyName",companyService.getById(item.getCompanyId()).getName());
            map.put("resumeName",resumeService.getById(item.getResumeId()).getName());
            map.put("sendDate",item.getSendDate());
            map.put("status",item.getStatus());
            list.add(map);
        });
        Page page=new Page(page1,10);
        page.setRecords(list);
        page.setTotal(list.size());
        return Result.ok(page);
    }
}
