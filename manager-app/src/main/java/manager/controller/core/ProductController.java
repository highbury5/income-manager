package manager.controller.core;


import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import manager.core.constant.CommonConstant;
import manager.core.message.CommonFailureMessage;
import manager.entity.Product;
import manager.entity.ProductCategory;
import manager.entity.ProductTag;
import manager.entity.dto.ProductDto;
import manager.service.ProductCategoryService;
import manager.service.ProductService;
import manager.service.ProductTagService;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 产品表 前端控制器
 * </p>
 *
 * @author huangduoxiao
 * @since 2020-02-02
 */
@Slf4j
@RestController
@RequestMapping("/api/product")
@Api(tags="产品管理")
public class ProductController{


    @Resource
    ProductCategoryService productCategoryService;

    @Resource
    ProductTagService productTagService;

    @Resource
    ProductService productService;

    @ApiOperation(value = "新增产品类别")
    @PostMapping("/add_category")
    public void addCategory(@RequestBody ProductCategory productCategory){
        log.info("ProductController -> addCategory");
        //TODO 防止重复添加
        productCategoryService.save(productCategory);
    }

    @ApiOperation(value = "查询产品类别列表")
    @GetMapping("/list_category")
    public List<ProductCategory> listCategory(){
        log.info("ProductController -> listCategory");
        List<ProductCategory> productCategories = productCategoryService.list();
        return productCategories;
    }

    @ApiOperation(value = "新增产品标签")
    @PostMapping("/add_tag")
    public void addTag(@RequestBody ProductTag productTag){
        log.info("ProductController -> addTag");
        //TODO 防止重复添加
        productTagService.save(productTag);
    }

    @ApiOperation(value = "查询产品标签列表")
    @GetMapping("/list_tag")
    public List<ProductTag> listTag(){
        log.info("ProductController -> listTag");
        List<ProductTag> productTags = productTagService.list();
        return productTags;
    }

    @ApiOperation(value = "新增产品")
    @PostMapping("/add")
    public void addProduct(@RequestBody ProductDto productDto){
        log.info("ProductController -> addProduct");
        //TODO 检查产品类别、产品标签是否存在
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        product.setTags(tagsToStr(productDto.getTags()));
        productService.save(product);
    }

    @ApiOperation(value = "修改产品")
    @PostMapping("/update")
    public void updateProduct(@RequestBody ProductDto productDto){
        log.info("ProductController -> updateProduct");
        Product product = new Product();
        BeanUtils.copyProperties(productDto,product);
        product.setTags(tagsToStr(productDto.getTags()));
        if(!productService.updateById(product)){
            throw CommonFailureMessage.UPDATE_FAILURE.toBizException();
        }
    }

    //TODO 待优化
    @ApiOperation(value = "查询产品列表")
    @GetMapping("/list")
    public IPage<ProductDto> listProduct(@RequestParam Long currentpage){
        log.info("ProductController -> listProduct");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, CommonConstant.PAGE_SIZE);
        IPage result = productService.page(page);
        List<Product> products = result.getRecords();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products){
            Long[] tags = tagStrToLong(product.getTags());
            String[] tagsName = getTagsName(tags);
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDto.setTags(tags);
            productDto.setTagsName(tagsName);
            productDto.setCategoryName(getCategoryName(product.getCategory()));
            productDtos.add(productDto);
        }
        page.setRecords(productDtos);
        return  page;
    }

    @ApiOperation(value = "查询有效产品列表简版")
    @GetMapping("/list/simple_effective")
    public List<Product> listsimpleEffectiveProduct(@RequestParam(required = false) String name){
        log.info("ProductController -> listsimpleEffectiveProduct");
        return productService.listSimpleEffectiveProduct(name);
    }

    //TODO 待优化
    @ApiOperation(value = "查询有效产品列表")
    @GetMapping("/list/effective")
    public IPage<ProductDto> listEffectiveProduct(@RequestParam Long currentpage,@RequestParam Long size){
        log.info("ProductController -> listEffectiveProduct");
        if(currentpage < 1 || null == currentpage){
            throw CommonFailureMessage.ARGUMENT_NOT_VALID.toBizException();
        }
        Page page = new Page(currentpage, (size==0?CommonConstant.PAGE_SIZE:size));
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status","0");
        IPage result = productService.page(page,queryWrapper);
        List<Product> products = result.getRecords();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product : products){
            Long[] tags = tagStrToLong(product.getTags());
            String[] tagsName = getTagsName(tags);
            ProductDto productDto = new ProductDto();
            BeanUtils.copyProperties(product,productDto);
            productDto.setTags(tags);
            productDto.setTagsName(tagsName);
            productDto.setCategoryName(getCategoryName(product.getCategory()));
            productDtos.add(productDto);

        }
        page.setRecords(productDtos);
        return  page;
    }



    @ApiOperation(value = "查询产品详情")
    @GetMapping("/get/{id}")
    public ProductDto getChannel(@PathVariable Long id){
        log.info("ProductController -> getProduct");
        Product product = productService.getById(id);
        if(product == null){
            throw CommonFailureMessage.READ_FAILURE.toBizException();
        }
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        productDto.setTags(tagStrToLong(product.getTags()));
        return productDto;
    }


    public String tagsToStr(Long[] tags){
        if(tags == null || tags.length == 0){
            return "";
        }
        StringBuffer tagStr = new StringBuffer();
        for(Long tag : tags){
            tagStr.append(tag + ",");
        }
        if(tagStr.length() > 0){
            tagStr.deleteCharAt(tagStr.length()-1);
        }
        return tagStr.toString();
    }


    public Long[] tagStrToLong(String tagStr){
        Long[] tags = new Long[]{};
        if(StringUtils.isEmpty(tagStr)){
            return tags;
        }
        String str[] = tagStr.split(",");
        tags = new Long[str.length];
        for(int i=0;i<str.length;i++){
            tags[i] = Long.parseLong(str[i]);
        }
        return tags;
    }

    public String[] getTagsName(Long[] tags){
        if(tags == null){
            return null;
        }
        String[] tagsName = new String[tags.length];
        if(tags.length == 0){
            return tagsName;
        }
        int index = 0;
        for(Long tag : tags){
            ProductTag pt = productTagService.getById(tag);
            tagsName[index] = pt.getName();
            index++;
        }
        return tagsName;
    }

    public String getCategoryName(Long categoryId){
        return productCategoryService.getById(categoryId).getName();
    }

}
