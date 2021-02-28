package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.Account;

public interface AccountDao {

	//��ѯ���õ��û�����
	List<Account> findAll();
	//����������ѯ
	Account findById(String accountid);
	//�����û���Ϣ
	int creat(Account account);
	//�޸��û���Ϣ
	int modify(Account account);
	//ɾ���û���Ϣ
	int remove(Account account);
}
