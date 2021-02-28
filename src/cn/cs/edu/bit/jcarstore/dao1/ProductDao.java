package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.Product;

public interface ProductDao {

	//��ѯ���õ���Ʒ����
		List<Product> findAll();
		//����������ѯ
		Product fingById(String productid);
		//������Ʒ��Ϣ
		int creat(Product  product);
		//�޸���Ʒ��Ϣ
		int modify(Product  product);
		//ɾ����Ʒ��Ϣ
		int remove(Product  product);
		//���������в�ѯ
		List<Product> findByCategory(String category);
}
