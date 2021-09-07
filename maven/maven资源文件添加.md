### maven

```xml
    <resources>
      <resource>
        <directory>src/main/resources</directory>
      </resource>
      <resource>
        <directory>src/main/java</directory>
      </resource>
    </resources>
```

build中加入代码

打war包

```xml
	<plugins>
      <plugin>  
        <artifactId>maven-war-plugin</artifactId>   
        <version>2.0.2</version>
      </plugin>
    </plugins>
```

