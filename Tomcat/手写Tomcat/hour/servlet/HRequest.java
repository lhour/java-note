package hour.servlet;

import java.io.IOException;
import java.io.InputStream;

public class HRequest {
    
    private String method;
    private String url;

    public HRequest (InputStream in) throws IOException {
        
        String content = "";
        byte[] buff = new byte[1024];
        int len = 0;
        if((len = in.read(buff)) > 0){
            content = new String(buff,0,len);
        }

        String line = content.split("\\n")[0];
        String[] arr = line.split("\\s");

        this.method = arr[0];
        this.url = arr[1].split("\\?")[0];

    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

}


// GET /_upload/tpl/01/36/310/template310/js/app.js HTTP/1.1
// Host: lib.hitwh.edu.cn
// Connection: keep-alive
// Pragma: no-cache
// Cache-Control: no-cache
// User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.63 Safari/537.36 Edg/93.0.961.47
// Accept: */*
// Referer: http://lib.hitwh.edu.cn/
// Accept-Encoding: gzip, deflate
// Accept-Language: zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6
// Cookie: UM_distinctid=17bcf935489650-0edd361473b556-5734174f-e1000-17bcf93548a7bb; JSESSIONID=AC46B7194BC8C39FBD21379CC4CADE1E; CNZZDATA1256635884=1815461082-1631268860-null%7C1631875621
