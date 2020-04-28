package aarexer.crud;

import aarexer.crud.dao.GoodDAOImpl;
import aarexer.crud.dao.WarehouseDAO;
import aarexer.crud.dao.WarehouseDAOImpl;
import aarexer.crud.model.Good;
import aarexer.crud.model.Warehouse;
import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Application {
    public static void main(String[] args) throws IOException, SQLException {
        Properties props = new Properties();
        props.load(Application.class.getResourceAsStream("/datasource.properties"));

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(props.getProperty("javabase.jdbc.url"));
        dataSource.setUser(props.getProperty("javabase.jdbc.username"));
        dataSource.setPassword(props.getProperty("javabase.jdbc.password"));
        dataSource.setServerTimezone("UTC");

        GoodDAOImpl goodDAO = new GoodDAOImpl(dataSource);
        WarehouseDAO warehouseDAO = new WarehouseDAOImpl(dataSource);

        System.out.println(goodDAO.findAll());
        System.out.println(warehouseDAO.findAll());

        warehouseDAO.add(new Warehouse("address1"));

        System.out.println(goodDAO.findAll());
        System.out.println(warehouseDAO.findAll());

        goodDAO.add(new Good("Good1", 1L));
        goodDAO.add(new Good("Good2", 1L));

        System.out.println(goodDAO.findAll());
        System.out.println(warehouseDAO.findAll());

        warehouseDAO.removeById(1L);

        System.out.println(goodDAO.findAll());
        System.out.println(warehouseDAO.findAll());
    }
}
