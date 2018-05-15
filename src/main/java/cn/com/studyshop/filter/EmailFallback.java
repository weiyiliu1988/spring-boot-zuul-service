package cn.com.studyshop.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

/**
 * zuul 服务熔断器
 * 
 * @author weiyiLiu
 *
 */
@Component
public class EmailFallback implements ZuulFallbackProvider {

	/**
	 * 服务级别配置
	 * 
	 */
	@Override
	public String getRoute() {
		return "email-service";
	}

	@Override
	public ClientHttpResponse fallbackResponse() {
		return new ClientHttpResponse() {

			@Override
			public InputStream getBody() throws IOException {
				return new ByteArrayInputStream("email-service服务暂时不可用".getBytes());
			}

			@Override
			public HttpHeaders getHeaders() {
				HttpHeaders headers = new HttpHeaders();
				MediaType mt = new MediaType("application", "json", Charset.forName("UTF-8"));
				headers.setContentType(mt);
				return headers;
			}

			@Override
			public HttpStatus getStatusCode() throws IOException {
				return HttpStatus.SERVICE_UNAVAILABLE;
			}

			@Override
			public int getRawStatusCode() throws IOException {
				return this.getStatusCode().value();
			}

			@Override
			public String getStatusText() throws IOException {
				return this.getStatusCode().getReasonPhrase();
			}

			@Override
			public void close() {
				// TODO Auto-generated method stub

			}

		};
	}

}
