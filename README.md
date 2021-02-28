**根据智杰课堂petstore视频学习后创做的  
bit的java计算机选修课项目所提交过的**  

**1** **程序的运行环境、安装步骤**

（1）**运行环境**：例如JDK 1.8 

（2）**程序的组成部份**：第三方库mysql-connector-java-8.0.13.jar

（3）**安装步骤**：如何在一台“干净”的计算机上运行你的程序？

?     示例：

?     1） 安装JRE 1.8

?     2）将程序jar文件复制到计算机上

?     3）安装MySQL数据库网址如下：

https://dev.mysql.com/downloads/windows/installer/5.7.htm

4)注册root密码最好设为123456这样就不用改配置文件了

5)用文本编辑器打开db文件下的，复制全部内容                       

6）打开MySQL Command Line Client，将脚本文件输入

7）双击jar文件

**2** **程序开发平台**

?     （1）代码行数：800(纯手写)

?     （2）开发环境：Eclipse IDE for Java DevelopersVersion: Photon Release (4.8.0)

**4** **程序算法说明及面向对象实现技术方案**

（1） 用到数据结构HashMap，查找的时间复杂度为O(1)

（2）数据结构和算法的面向对象实现

实物类四大主类

窗口类的UML

**5** **技术亮点、关键点及其解决方案**

使用MySQL来保存数据，并把**Statement**的数据库操作改为用**PreparedStatement**实现防sql注入

我应用了Martin Fowler分层架构简化版来设计整个程序

大量使用Ps来美化图片

大量的单元测试

遇到的技术难点及对应的解决方案：

**问题描述：**MySQL的使用上面**连接数据库的时候总是报错“时区不同步”**

**最终的解决方案：**`使用gmt+8时区，需要写成GMT%2B`

**问题描述：MySQL中需要用setString绑定参数，但是这个语句不能放进try的括号了进行自动资源管理，但是有了这条语句才能遍历结果集.**

**最终的解决方案:放弃try的自动资源管理，改用传统的try-catch-finally.**

**问题描述：各种的path都报找不到**

**最终的解决方案：把jar包都删了，把connector删的干净些，重新装的**

**问题描述：PreparedStatement 类的setDate方法的第二个参数是sql的强制转换也不成**

**最终的解决方案：先用一个util来获取当前的时间，然后再创建sql的date 实例对象，把util得到的当前时间放入sql的里面。**

**问题描述：多个用户如果同时下订单的话Date不够精确，会产生麻烦。**

**最终的解决方案：用Timestamp来代替Date类。**

**问题描述：**image=ImageIO.*read*(**new** File("images/night.jpg"));这样就一直报错“Can’t read input file ”,到现在为止我也不知道为啥。

**最终的解决方案：根据百度的大佬说的改成这样：**Image image = javax.imageio.ImageIO.*read*(getClass().getResource("/bgpicture/night.jpg"));就成了！why!!!!!!!!!!

**问题描述：没有考虑到购物车上面的购买数量可以用户输入，所以可能输入非法。**

**最终的解决方案:添加异常处理，并告知用户**

 

**6** **简要开发过程**

11月19-22号 确定项目为购买杂货系统，完成需求分析，功能设计，用户可以登录，查询商品，添加到购物车，查看购物车，查看订单（保存在数据库里），下订单，原型设计，命名为CargoStore

1.数据库设计(数据库ER模型如下)

2.架构设计：表示层(Swing)->数据持久层(JDBC)

3.系统设计:

①数据持久层的设计：采用DAO（数据访问对象）设计模式，数据库中的每一个数据表，对应一个DAO对象，每一个DAO对象中有访问数据库的CRUD四类操作

②表示层: 主要使用Swing技术，每一个界面就是一个窗口对象。在表示层中各个窗口是依据原型设计而来的。CargoStore项目,loginFrame用户登录CartFrame购物车窗口和ProductListFrame商品列表窗口，它们有共同的父类MyFrame，MyFrame类是根据自己的项目情况进行的封装.


