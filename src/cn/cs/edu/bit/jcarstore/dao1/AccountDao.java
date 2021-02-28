package cn.cs.edu.bit.jcarstore.dao1;

import java.util.List;

import cn.cs.edu.bit.jcarstore.domain.Account;

public interface AccountDao {

	//查询所用的用户数据
	List<Account> findAll();
	//根据主键查询
	Account findById(String accountid);
	//创建用户信息
	int creat(Account account);
	//修改用户信息
	int modify(Account account);
	//删除用户信息
	int remove(Account account);
}
