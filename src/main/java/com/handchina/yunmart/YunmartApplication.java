package com.handchina.yunmart;

import com.handchina.yunmart.core.domain.product.Product;
import com.handchina.yunmart.core.domain.product.ProductCategory;
import com.handchina.yunmart.core.persistence.ProductCategoryRepository;
import com.handchina.yunmart.core.persistence.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@SpringBootApplication
public class YunmartApplication implements CommandLineRunner{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;


    public static void main(String[] args) {
        SpringApplication.run(YunmartApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {
        List<String> categories = Arrays.asList("企业协作管理", "行政办公管理", "差旅管理");
        for (String category : categories) {
            ProductCategory productCategory = new ProductCategory();
            productCategory.setName(category);
            productCategoryRepository.save(productCategory);
        }

        List<String> productNames =
            Arrays.asList("云路天下",
                "teambition",
                "Oracle财务云",
                "麦客",
                "逸创云客服",
                "蚂蚁HR"
                );
        List<String> productDescs = Arrays.asList("针对小型企业管理资源较少的特点，将CRM、ERP和OA平台进行精简和集成，三大功能模块简化后集成一体，既减少了企业人员学习软件难度，也可以缩减人员编制，同时，高度共享的数据大大减少了重复劳动，并让一些..",
            "Teambition是一款出色的项目协作工具，让你可以和同伴共享项目进展，随时沟通，从而轻松完成目标。目前，Teambition 已经被应用于超过十个行业的逾一百万项目中，拥有非常高的媒体评价。..",
            "Oracle是全球一流的企业管理软件服务商，提供ERP、SCM、CRM、HR等多类企业管理软件。近年来，Oracle逐步推出云端的企业管理软件，财务云就是其中之一。财务云不只是把桌面财务系统(R12)..",
            "麦客是一款简单好用的表单制作和联系人管理工具，比word还简单。 麦客以联系人即你的客户为核心，通过表单收集目标和潜在客户的信息，并将信息沉淀成联系人资料为后续跟进打下基础，再以邮件、短信为主要形式..",
            "成都逸创信息技术有限公司于2012年在成都高新区成立，注册资本300万，公司主营基于云和网页的SaaS服务-云客服，为国内中小型企业提供客户支持服务支撑。 逸创 - 安逸的创造 - 用轻松的方式创造..",
            "“蚂蚁HR”更像是一家中国版的Zenpayroll，他们的云服务能够帮助中小企业完成工资计算发放、个税、社保公积金缴纳等工作。“蚂蚁HR”后台系统与社保中心数据打通，HR可以随时提交需求。..");
        for (int i = 0; i < productNames.size(); i++) {
            Product product = new Product();
            product.setProductOID(UUID.nameUUIDFromBytes(productNames.get(i).getBytes()));
            product.setCategory(productCategoryRepository.findOne(1L));
            product.setShortDescription(productDescs.get(i));
            product.setName(productNames.get(i));
            product.setDescription(productDescs.get(i));
            product.setCases(productNames.get(i) + "=======" + productDescs.get(i));
            productRepository.save(product);
        }
    }
}
