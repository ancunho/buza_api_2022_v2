package com.buza.wechat.controller;

import com.buza.server.common.BaseResponse;
import com.buza.server.dto.BaseRequest;
import com.buza.server.dto.TbPostDto;
import com.buza.server.service.PostService;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/miniapp/post")
public class MiniappPostController {

    @Autowired
    private PostService postService;

    @GetMapping("/test")
    public BaseResponse testpost() {
        return BaseResponse.valueOfSuccessMessage("asdfsadfasdf");
    }

    @PostMapping(value = "/list")
    public BaseResponse getAllTbPostListByTbPost(BaseRequest baseRequest, @RequestBody TbPostDto tbPostDto) {
        PageHelper.startPage(baseRequest.getPage(), baseRequest.getLimit());
        List<TbPostDto> returnData = postService.getAllTbPostListByTbPost(tbPostDto);
        return BaseResponse.valueOfSuccessList(returnData);
    }

}
