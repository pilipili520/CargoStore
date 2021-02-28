package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.Product;

public interface ProductDao {

	//查询所用的商品数据
		List<Product> findAll();
		//根据主键查询
		Product fingById(String productid);
		//创建商品信息
		int creat(Product  product);
		//修改商品信息
		int modify(Product  product);
		//删除商品信息
		int remove(Product  product);
		//按照类别进行查询
		List<Product> findByCategory(String category);
}
