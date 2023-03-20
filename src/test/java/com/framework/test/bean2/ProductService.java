package com.framework.test.bean2;

import com.framework.beans.factory.annotation.Autowired;
import com.framework.beans.factory.annotation.Value;
import com.framework.stereotype.Component;

@Component("productService")
public class ProductService implements IProductService{

    @Value("${token}")
    private String token;

    @Autowired
    private ProductDao productDao;

    @Override
    public String queryProductInfo() {
        return productDao.queryUserName("10001")+"."+token;
    }
}
