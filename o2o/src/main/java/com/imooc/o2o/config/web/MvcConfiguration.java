package com.imooc.o2o.config.web;

import com.google.code.kaptcha.servlet.KaptchaServlet;
import com.imooc.o2o.interceptor.shopadmin.ShopLoginInterceptor;
import com.imooc.o2o.interceptor.shopadmin.ShopPermissonInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.ServletException;

/**
 * Created by Unruly Wind on 2019/3/3/003.
 * 开启Mvc,自动注入spring容器。 WebMvcConfigurerAdapter：配置视图解析器
 * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean
 *
 * @author BlueMelancholy
 * @desc:
 */
@Configuration
// 等价于<mvc:annotation-driven/>
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 静态资源配置
	 *
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/upload/**").addResourceLocations("file:E:/o2oImg/images/upload/");
	}

	/**
	 * 定义默认的请求处理器
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Bean(name = "viewResovler")
	public ViewResolver createViewResovler() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 设置Spring 容器
		viewResolver.setApplicationContext(applicationContext);
		// 取消缓存
		viewResolver.setCache(false);
		viewResolver.setPrefix("/WEB-INF/html/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	/**
	 * 文件上传解析器
	 *
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(20971520);
		return multipartResolver;
	}

	@Value("${kaptcha.border}")
	private String border;

	@Value("${kaptcha.textproducer.font.color}")
	private String fcolor;

	@Value("${kaptcha.image.width}")
	private String width;

	@Value("${kaptcha.textproducer.char.string}")
	private String cString;

	@Value("${kaptcha.image.height}")
	private String height;

	@Value("${kaptcha.textproducer.font.size}")
	private String fsize;

	@Value("${kaptcha.noise.color}")
	private String nColor;

	@Value("${kaptcha.textproducer.char.length}")
	private String clength;

	@Value("${kaptcha.textproducer.font.names}")
	private String fnames;

	/**
	 * 由于web.xml不生效了，需要在这里配置Kaptcha验证码Servlet
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() throws ServletException {
		ServletRegistrationBean servlet = new ServletRegistrationBean(new KaptchaServlet(), "/Kaptcha");
		// 无边框
		servlet.addInitParameter("kaptcha.border", border);
		// 字体颜色
		servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor);
		// 图片宽度
		servlet.addInitParameter("kaptcha.image.width", width);
		// 使用哪些字符生成验证码
		servlet.addInitParameter("kaptcha.textproducer.char.string", cString);
		// 图片高度
		servlet.addInitParameter("kaptcha.image.height", height);
		// 字体大小
		servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);
		// 干扰线的颜色
		servlet.addInitParameter("kaptcha.noise.color", nColor);
		// 字符个数
		servlet.addInitParameter("kaptcha.textproducer.char.length", clength);
		// 字体
		servlet.addInitParameter("kaptcha.textproducer.font.names", fnames);
		return servlet;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		/** 店家管理系统拦截部分 **/
		String interceptPath = "/shopadmin/**";
		// 注册拦截器
		InterceptorRegistration loginIR = registry.addInterceptor(new ShopLoginInterceptor());
		/** shopauthmanagement page **/
		loginIR.excludePathPatterns("/shopadmin/addshopauthmap");
		// 配置拦截的路径
		loginIR.addPathPatterns(interceptPath);
		// 还可以注册其它的拦截器
		InterceptorRegistration permissionIR = registry.addInterceptor(new ShopPermissonInterceptor());
		// 配置拦截的路径
		permissionIR.addPathPatterns(interceptPath);
		// 配置不拦截的路径
		/** shoplist page **/
		permissionIR.excludePathPatterns("/shopadmin/shoplist");
		permissionIR.excludePathPatterns("/shopadmin/getshoplist");
		/** shopregister page **/
		permissionIR.excludePathPatterns("/shopadmin/getshopinitinfo");
		permissionIR.excludePathPatterns("/shopadmin/registershop");
		permissionIR.excludePathPatterns("/shopadmin/shopoperation");
		/** shopmanage page **/
		permissionIR.excludePathPatterns("/shopadmin/shopmanagement");
		permissionIR.excludePathPatterns("/shopadmin/getshopmanagementinfo");
		/** shopauthmanagement page **/
		permissionIR.excludePathPatterns("/shopadmin/addshopauthmap");
	}
}
