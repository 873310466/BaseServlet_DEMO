package com.lemonfish.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Lemonfish
 */
public class BaseServlet extends HttpServlet {
    /**
     * 用一个set去存储我们要转发的目标
     **/
    private static Set<String> set = new HashSet<>();

    // 初始化
    static {
        // 添加需要转发的目标即可
        set.add("hello.jsp");
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        // 1. 这里获取URL或者URI都可以，URI: /lemonfish/UserServlet/login    URL: http://localhost/lemonfish/UserServlet/login
        String requestURI = req.getRequestURI();
        // 2. 获取最后`/`的索引
        int beginIndex = requestURI.lastIndexOf("/");
        // 3. 获取方法名称
        String methodName = requestURI.substring(beginIndex + 1);
        // 4. 判断方法名称是否是转发目标，是 -> return ，否 -> 执行方法
/*        if (set.contains(methodName)) {
            return;
        }*/

        try {
            /***
             * 记住谁调用了“service”方法，this就是谁，
             * 因为我们自己编写的UserServlet继承了BaseServlet
             * 因此，这个service方法是属于UserServlet的
             * 而前端访问的是UserServlet
             * 因此 this 是 UserServlet的一个对象
             *
             * 4. 这里 根据 方法名称 和 方法参数的class类型 ，利用反射获取UserServlet的该方法。
             *
             * 如果大家这两行代码看不太懂，可以先去复习下 反射 的知识。
             ***/
            Method method = this.getClass().getDeclaredMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            // 5. 使用this调用该方法。
            method.invoke(this, req, resp);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
