package cn.com.studyshop;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import cn.com.studyshop.filter.AccessFilter;

@EnableHystrix
@EnableZuulProxy
@SpringCloudApplication
public class ZuulServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ZuulServiceApplication.class).web(true).run(args);
	}

	/**
	 *
	 * IOC
	 * 
	 * @return
	 */
	@Bean
	public AccessFilter accessFilter() {
		return new AccessFilter();
	}

}
