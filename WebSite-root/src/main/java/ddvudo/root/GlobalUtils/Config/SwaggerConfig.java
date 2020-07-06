package ddvudo.root.GlobalUtils.Config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.*;
import springfox.documentation.schema.Example;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiDescription;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Operation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.ApiListingScannerPlugin;
import springfox.documentation.spi.service.contexts.DocumentationContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
	//api接口包扫描路径
	public static final String VERSION = "1.0.0";

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.paths(PathSelectors.any()) // 可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("WebSite接口文档") //设置文档的标题
				.description("WebSite接口和参数文档") // 设置文档的描述
				.version(VERSION) // 设置文档的版本信息-> 1.0.0 Version information
				.termsOfServiceUrl("http://ddvudo.xyz") // 设置文档的License信息->1.3 License information
				.build();
	}

	@Component
	public static class SwaggerAddtion implements ApiListingScannerPlugin {
		/**
		 * Implement this method to manually add ApiDescriptions
		 * 实现此方法可手动添加ApiDescriptions
		 *
		 * @param context - Documentation context that can be used infer documentation context
		 * @return List of {@link ApiDescription}
		 * @see ApiDescription
		 */
		@Override
		//todo Swagger添加自定义登录接口没法用，页面上发请求无响应，后台无报错
		public List<ApiDescription> apply(DocumentationContext context) {
			Multimap<String, Example> parameters = ArrayListMultimap.create();
			parameters.put("username", new Example("admin"));
			parameters.put("password", new Example("admin"));
			Operation usernamePasswordOperation = new OperationBuilder(new CachingOperationNameGenerator())
					.position(100)
					.method(HttpMethod.POST)
					.summary("用户名密码登录")
					.notes("username/password登录")
					.consumes(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 接收参数格式
					.produces(Sets.newHashSet(MediaType.APPLICATION_JSON_VALUE)) // 返回参数格式
					.tags(Sets.newHashSet("登录"))
					.parameters(Arrays.asList(
							new ParameterBuilder()
									.name("用户名和密码")
									.description("用户名和密码")
									.type(new TypeResolver().resolve(String.class))
									.parameterType("body")
									.parameterAccess("access")
									.required(true)
									.scalarExample("{\"username\":\"admin\",\"password\":\"admin\"}")
									.modelRef(new ModelRef("string"))
									.build()
					)).build();

			ApiDescription loginApiDescription = new ApiDescription("login", "/user/login", "登录接口",
					Collections.singletonList(usernamePasswordOperation), false);

			return Collections.singletonList(loginApiDescription);
		}

		/**
		 * 是否使用此插件
		 *
		 * @param documentationType swagger文档类型
		 * @return true 启用
		 */
		@Override
		public boolean supports(DocumentationType documentationType) {
			return DocumentationType.SWAGGER_2.equals(documentationType);
		}
	}
}