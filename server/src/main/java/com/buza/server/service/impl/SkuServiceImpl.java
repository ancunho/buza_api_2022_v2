package com.buza.server.service.impl;

import com.buza.server.dao.*;
import com.buza.server.service.SkuService;
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
public class SkuServiceImpl implements SkuService {

    @Resource
    private TbSpuMapper tbSpuMapper;

    @Resource
    private TbSpuSpecMapper tbSpuSpecMapper;

    @Resource
    private TbSpecMapper tbSpecMapper;

    @Resource
    private TbSpecValueMapper tbSpecValueMapper;

    @Resource
    private TbSkuMapper tbSkuMapper;

    @Resource
    private TbSkuSpecMapper tbSkuSpecMapper;

    @Resource
    private TbCategoryMapper tbCategoryMapper;

    @Resource
    private TbBrandMapper tbBrandMapper;



}