web-inf文件夹下资源只能通过servlet看到，不能直接通过地址栏访问

视图解析器,框架会自动接上.jsp,
```xml
<beans:bean id="viewResolver"
        class="org.springframework.web.servlet.view.InternalResourceViewResolver">
        <beans:property name="prefix" value="/WEB-INF/vistas/" />
        <beans:property name="suffix" value=".jsp" />
    </beans:bean>
```