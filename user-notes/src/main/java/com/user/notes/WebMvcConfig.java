//package com.user.notes;
//
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.servlet.ViewResolver;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.spring4.view.ThymeleafViewResolver;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//@EnableWebMvc
//
//public class WebMvcConfig extends WebMvcConfigurerAdapter {
//
//    public WebMvcConfig() {
//        // TODO Auto-generated constructor stub
//    }
//
//    @Override
//    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
//    }
//
//    private ApplicationContext applicationContext;
//
//    public void setApplicationContext(ApplicationContext applicationContext) {
//      this.applicationContext = applicationContext;
//    }
//
//    @Bean
//    public ViewResolver viewResolver() {
//      ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//      resolver.setTemplateEngine(templateEngine());
//      resolver.setCharacterEncoding("UTF-8");
//      return resolver;
//    }
//
//    @Bean
//    public TemplateEngine templateEngine() {
//      SpringTemplateEngine engine = new SpringTemplateEngine();
//      engine.setEnableSpringELCompiler(true);
////    engine.setTemplateResolver(templateResolver());
//      return engine;
//    }
//
////  private ITemplateResolver templateResolver() {
////    ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
//////      resolver.setApplicationContext(applicationContext);
////    resolver.setPrefix("templates/");
////    resolver.setSuffix(".html");
////    resolver.setCacheable(false);
////    resolver.setTemplateMode("HTML5");
////    return resolver;
////  }
//
//}
