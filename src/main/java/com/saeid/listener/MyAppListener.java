package com.saeid.listener;

import com.saeid.util.DbUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;

public class MyAppListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String user = context.getInitParameter("dbuser");
        String pass = context.getInitParameter("dbpass");
        String url = context.getInitParameter("dburl");

        DataSource datasource = DbUtil.getDatasource(user, pass, url);
        context.setAttribute("datasource",datasource);

    }
}
