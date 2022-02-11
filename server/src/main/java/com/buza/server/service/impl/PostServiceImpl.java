package com.buza.server.service.impl;

import com.buza.server.dao.TbPostMapper;
import com.buza.server.service.PostService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    @Resource
    private TbPostMapper tbPostMapper;



}
