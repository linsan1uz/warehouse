package com.fqf.warehouse.controller;

import com.fqf.warehouse.WarehouseApplication;
import com.fqf.warehouse.entity.*;
import com.fqf.warehouse.page.Page;
import com.fqf.warehouse.service.*;
import com.fqf.warehouse.utils.TokenUtils;
import com.fqf.warehouse.utils.WarehouseConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private StoreService storeService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private ProductTypeService productTypeService;

    @Autowired
    private SupplyService supplyService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private UnitService unitService;

    @Autowired
    private ProductService productService;
    @RequestMapping("/store-list")
    public Result storeList() {
        List<Store> storeList = storeService.queryAllStore();
        return Result.ok(storeList);
    }
    @RequestMapping("/brand-list")
    public Result brandList(){
        List<Brand> brandList = brandService.queryAllBrand();
        return Result.ok(brandList);
    }
    @RequestMapping("/category-tree")
    public Result categoryTree(){
        List<ProductType> typeTreeList = productTypeService.allProductTypeTree();
        return Result.ok(typeTreeList);
    }
    @RequestMapping("/supply-list")
    public Result supplyList(){
        List<Supply> supplies = supplyService.queryAllSupply();
        return Result.ok(supplies);
    }
    @RequestMapping("/place-list")
    public Result placeList(){
        List<Place> placeList = placeService.queryAllPlace();
        return Result.ok(placeList);
    }

    @RequestMapping("/unit-list")
    public Result unitList(){
        List<Unit> unitList = unitService.queryAllUnit();
        return Result.ok(unitList);
    }

    @RequestMapping("/product-page-list")
    public Result productPageList(Page page,Product product){
        page = productService.queryProductPage(page, product);
        return Result.ok(page);
    }
    @Autowired
    private TokenUtils tokenUtils;


    @RequestMapping("/product-add")
    public Result productAdd(@RequestBody Product product, @RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int createBy = currentUser.getUserId();
        product.setCreateBy(createBy);
        Result result = productService.addProduct(product);
        return result;
    }
    /*
    *上传图片的url接口
    *MultipartFile file --表示封装了请求参数名叫file的上传图片
    * file.transferTo 上传的文件保存到磁盘文件的File对象中 --实现文件的上传
    *
    * */
    @Value("${file.upload-path}")
    private String uploadPath;

    @CrossOrigin
    @RequestMapping("/img-upload")
    public Result uploadImage(MultipartFile file){
        /*file.transferTo("static/img/upload"+file.toString());*/
        try {/*
            拿到图片上传的目录路径file对象

            图片上传到的目录路径是个类路径 resource 下的路径
            所以不能直接将类路径封装到file对象中 要经过解析
        */
            //拿到图片上传到的目录路径的file对象
            File uploadDirFile = ResourceUtils.getFile(uploadPath);
            //磁盘路径
            String uploadDirPath = uploadDirFile.getAbsolutePath();
            String filename = file.getOriginalFilename();
            //磁盘文件路径
            String uploadFilePath = uploadDirPath+"\\"+filename;
            System.out.println(uploadFilePath);
            file.transferTo(new File(uploadFilePath));
            return Result.ok("图片上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.err(Result.CODE_ERR_BUSINESS,"图片上传失败");
        }
    }
    @RequestMapping("/state-change")
    public Result stateChange(@RequestBody Product product){
        Result result = productService.updateProductState(product);
        return result;
    }

    @RequestMapping("/product-delete/{productId}")
    public Result deleteProduct(@PathVariable Integer productId){
        Result result = productService.deleteProduct(productId);
        return result;
    }
    @RequestMapping("/product-update")
    public Result updateProduct(@RequestBody Product product,@RequestHeader(WarehouseConstants.HEADER_TOKEN_NAME)String token){
        CurrentUser currentUser = tokenUtils.getCurrentUser(token);
        int updateBy = currentUser.getUserId();
        product.setUpdateBy(updateBy);
        Result result = productService.updateProduct(product);
        return result;
    }
}
