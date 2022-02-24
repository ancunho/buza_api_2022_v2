package com.buza.server.service.impl;

import com.buza.server.dao.*;
import com.buza.server.dto.TbSpuDto;
import com.buza.server.service.SkuService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@Service
public class SkuServiceImpl implements SkuService {

    @Resource
    private TbSpuMapper tbSpuMapper;

    @Resource
    private TbSpuAttributeMapper tbSpuAttributeMapper;

    @Resource
    private TbAttributeMapper tbAttributeMapper;

    @Resource
    private TbAttributeValueMapper tbAttributeValueMapper;

    @Resource
    private TbSkuMapper tbSkuMapper;

    @Resource
    private TbSkuAttributeMapper tbSkuAttributeMapper;

    @Resource
    private TbCategoryMapper tbCategoryMapper;

    @Resource
    private TbBrandMapper tbBrandMapper;

    public List<TbSpuDto> lstTbSpu(TbSpuDto tbSpuDto) {

        return null;
    }




}
