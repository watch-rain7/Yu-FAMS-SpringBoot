package com.example.service.impl;


import com.example.entity.Assets;
import com.example.mapper.AssetMapper;
import com.example.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetService {

    @Autowired
    private AssetMapper assetMapper;

    @Override
    public List<Assets> getRecentAssets() {
        return assetMapper.selectRecent(10);
    }
}
