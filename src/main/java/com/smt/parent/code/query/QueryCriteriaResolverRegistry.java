package com.smt.parent.code.query;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 
 * @author DougLei
 */
@Configuration
public class QueryCriteriaResolverRegistry implements WebMvcConfigurer{
	
	@Bean
	public QueryCriteriaResolver queryCriteriaResolver() {
		return new QueryCriteriaResolver();
	}
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) { 
		resolvers.add(queryCriteriaResolver()); // 添加自定义的方法参数解析器
	}
}
