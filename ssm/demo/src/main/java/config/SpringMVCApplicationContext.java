package config;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.GsonBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.AbstractHandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import ga.rugal.ssm.springmvc.multipart.ExtendedMultipartResolver;
import ml.rugal.sshcommon.springmvc.method.annotation.FormModelMethodArgumentResolver;

/**
 * Java based Web context configuration class.
 *
 * @author Rugal Bernstein
 * @since 0.2
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages =
{
    "ga.rugal.ssm.springmvc.controller"
})
public class SpringMVCApplicationContext extends WebMvcConfigurerAdapter
{

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers)
    {
        argumentResolvers.add(new FormModelMethodArgumentResolver());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
    {
        configurer.favorPathExtension(false).favorParameter(false);
        configurer.defaultContentType(MediaType.APPLICATION_JSON);
        configurer.mediaType("json", MediaType.APPLICATION_JSON);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(gsonHttpMessageConverter());
        converters.add(byteArrayHttpMessageConverter());

    }

    private HttpMessageConverter byteArrayHttpMessageConverter()
    {
        ByteArrayHttpMessageConverter messageConverter = new ByteArrayHttpMessageConverter();
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.IMAGE_GIF);
        supportedMediaTypes.add(MediaType.IMAGE_JPEG);
        supportedMediaTypes.add(MediaType.IMAGE_PNG);
        supportedMediaTypes.add(MediaType.valueOf("application/x-bittorrent"));
//        supportedMediaTypes.add(MediaType.ALL);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return messageConverter;
    }

    private HttpMessageConverter gsonHttpMessageConverter()
    {
        GsonHttpMessageConverter messageConverter = new GsonHttpMessageConverter();
        messageConverter.setGson(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        List<MediaType> supportedMediaTypes = new ArrayList<>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        messageConverter.setSupportedMediaTypes(supportedMediaTypes);
        return messageConverter;
    }

//    @Bean
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public HandlerAdapter handlerAdapter()
    {
        RequestMappingHandlerAdapter adapter = new RequestMappingHandlerAdapter();
        return adapter;
    }

    @Bean
    public AbstractHandlerMapping defaultAnnotationHandlerMapping()
    {
        RequestMappingHandlerMapping mapping = new RequestMappingHandlerMapping();
        mapping.setUseSuffixPatternMatch(false);
        //Seems those parameters not important anymore after I use URI as parameter instead
//        mapping.setUrlDecode(false);
//        mapping.setAlwaysUseFullPath(true);
        return mapping;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        //This is a very important interceptor for authentication usage
    }

    @Override
    public void addCorsMappings(CorsRegistry registry)
    {
        registry.addMapping("/**");
    }

    @Bean
    public MultipartResolver multipartResolver()
    {
        ExtendedMultipartResolver cmr = new ExtendedMultipartResolver();
        cmr.setMaxUploadSize(9999999);
        return cmr;
    }

}
