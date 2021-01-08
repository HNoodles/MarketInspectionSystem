package com.fudan.market_inspection;

import com.fudan.market_inspection.entity.Expert;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) throws Exception {
////        Class.forName("com.h2database:h2:1.4.200");
//        Connection conn = DriverManager.getConnection("jdbc:h2:./data/market_inspection", "sa", "");
//        Statement stmt = conn.createStatement();
//        ResultSet rs = stmt.executeQuery("SELECT * FROM EXPERTS");
//        while (rs.next()) {
//            System.out.println(rs.getInt("id") + rs.getString("NAME"));
//        }
//        conn.close();
        //获取加载配置管理类
        Configuration configuration = new Configuration();
        //不给参数就默认加载hibernate.cfg.xml文件，
        configuration.configure();
        //创建Session工厂对象
        SessionFactory factory = configuration.buildSessionFactory();
        //得到Session对象
        Session session = factory.openSession();
        //使用Hibernate操作数据库，都要开启事务,得到事务对象
//        Transaction transaction = session.getTransaction();
//         开启事务
//        transaction.begin();
        //把对象添加到数据库中
        session.save(new Expert("rose"));
        //提交事务
//        transaction.commit();
        //关闭Session
        session.close();
    }
}
