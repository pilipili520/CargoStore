/*==============================================================*/
/* DBMS name:      MySQL 8.0                                    */
/* ����� Created on:     2018/11/21 18:47:48                     */
/*==============================================================*/

/* �������ݿ� */
CREATE DATABASE  IF NOT EXISTS  cargostore;

use cargostore;

/* �û��� */
CREATE TABLE IF NOT EXISTS account (
    userid varchar(80) not null,   	 /* �û�Id  */
    password varchar(25)  not null,	 /* �û����� */
    email varchar(80) not null,  	 /* �û�Email */
    name varchar(80) not null, 		 /* �û��� */
    addr varchar(80) not null, 		 /* ��ַ */
    city varchar(80) not  null, 	 /*  ���ڳ��� */
    country varchar(20) not null, 	 /*  ���� */
    phone varchar(80) not null, 	 /*  �绰���� */
PRIMARY KEY (userid));

/* ��Ʒ�� */
CREATE TABLE IF NOT EXISTS product (
    productid varchar(10) not null,	/* ��ƷId */
    category varchar(10) not null,	/* ��Ʒ��� */
    cname varchar(80) null,		/* ��Ʒ������ */
    ename varchar(80) null,		/* ��ƷӢ���� */
    image varchar(20) null,		/* ��ƷͼƬ */
    descn varchar(255) null,		/* ��Ʒ���� */
    listprice decimal(10,2) null, 	/* ��Ʒ�г��� */
    unitcost decimal(10,2) null,	/* ��Ʒ���� */
PRIMARY KEY (productid));

/* ������ */
CREATE TABLE IF NOT EXISTS orders (
    orderid bigint not null,		/* ����Id */
    userid varchar(80) not null,	/* �¶������û�Id */
    orderdate datetime not null,		/* �¶���ʱ�� */
    status int not null default 0,	/* ��������״̬  0������  1�Ѹ��� */    
    amount decimal(10,2) not null,	/* ����Ӧ����� */
PRIMARY KEY (orderid));

/* ������ϸ�� */
CREATE TABLE IF NOT EXISTS ordersdetail (
    orderid bigint not null,		/* ����Id */
    productid varchar(10) not null,	/* ��ƷId */
    quantity int not null,		/* ��Ʒ���� */
    unitcost decimal(10,2) null,	/* ��Ʒ���� */
PRIMARY KEY (orderid, productid));