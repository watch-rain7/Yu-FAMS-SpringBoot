package com.example.controller;


import com.example.common.Result;
import com.example.entity.Assets;
import com.example.service.AssetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/asset")
public class AssetController {

    @Autowired
    private AssetService assetService;

    @GetMapping("/recent")
    public Result getRecentAssets() {
        return Result.success(assetService.getRecentAssets());
    }
}
