package br.com.siberius.projeto.core.openapi;

import br.com.siberius.projeto.api.exceptionhandler.Problem;
import br.com.siberius.projeto.api.model.UsuarioModel;
import br.com.siberius.projeto.api.openapi.model.PageableModelOpenApi;
import br.com.siberius.projeto.api.openapi.model.UsuariosModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResourceOwnerPasswordCredentialsGrant;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {

        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            //.apis(RequestHandlerSelectors.any())
            .apis(RequestHandlerSelectors.basePackage("br.com.siberius.projeto.api"))
            .paths(PathSelectors.any())
            .build()
            .useDefaultResponseMessages(false)
            //  Configurando de forma global o parametro "campos" do squiggly
//            .globalOperationParameters(Arrays.asList(
//                new ParameterBuilder()
//                    .name("campos")
//                    .description("Nomes das propriedades para filtrar na resposta, separados por vírgula")
//                    .parameterType("query")
//                    .modelRef(new ModelRef("string"))
//                    .build()
//            ))
            .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages())
            .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages())
            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages())
            .additionalModels(typeResolver.resolve(Problem.class))
            .directModelSubstitute(Pageable.class, PageableModelOpenApi.class)
            .alternateTypeRules(AlternateTypeRules.newRule(
                typeResolver.resolve(Page.class, UsuarioModel.class),
                UsuariosModelOpenApi.class))
            .ignoredParameterTypes(ServletWebRequest.class,
                URL.class, URI.class, URLStreamHandler.class, Resource.class,
                File.class, InputStream.class)

            .securitySchemes(Collections.singletonList(securityScheme()))
            .securityContexts(Collections.singletonList(securityContext()))
            .apiInfo(apiInfo())
            .tags(
                    new Tag("Usuários", "Gerencia os usuários"),
                    new Tag("Grupos", "Gerencia os grupos de usuários"),
                    new Tag("Permissões", "Gerencia as permissões de usuários"),
                    new Tag("Recuperar Senha E-mail", "Gera nova senha de acesso e envia para email do usuario"),
                    new Tag("Confirmar Registro E-mail", "Envia um link para o email cadastrado de confirmação do registro"),
                    new Tag("Cidades", "Gerencia os cidades"),
                    new Tag("Estados", "Gerencia os estados"),
                    new Tag("Profissionais", "Gerencia os Profissionais"),
                    new Tag("Pacientes", "Gerencia os Pacientes"),
                    new Tag("Consultas", "Gerencia as Consultas")
                );
    }

    private SecurityScheme securityScheme() {
        return new OAuthBuilder()
            .name("ProjetoOAuth2")
            .grantTypes(grantTypes())
            .scopes(scopes())
            .build();
    }

    private SecurityContext securityContext() {
        SecurityReference securityReference = SecurityReference.builder()
            .reference("ProjetoOAuth2")
            .scopes(scopes().toArray(new AuthorizationScope[0]))
            .build();

        return SecurityContext.builder()
            .securityReferences(Arrays.asList(securityReference))
            .forPaths(PathSelectors.any())
            .build();
    }

    private List<GrantType> grantTypes() {
        return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
    }

    private List<AuthorizationScope> scopes() {
        return Arrays.asList(new AuthorizationScope("READ", "Acesso de leitura"),
            new AuthorizationScope("WRITE", "Acesso de escrita"));
    }

    private List<ResponseMessage> globalGetResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno do servidor")
                .responseModel(new ModelRef("Problema"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build()
        );
    }

    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Requisição inválida (erro do cliente)")
                .responseModel(new ModelRef("Problema"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno no servidor")
                .responseModel(new ModelRef("Problema"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message("Requisição recusada porque o corpo está em um formato não suportado")
                .responseModel(new ModelRef("Problema"))
                .build()
        );
    }

    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Requisição inválida (erro do cliente)")
                .responseModel(new ModelRef("Problema"))
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno no servidor")
                .responseModel(new ModelRef ("Problema"))
                .build()
        );
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Projeto API")
            .description("API aberta para Projeto Inicial - "
                + " Squiggly , ModelMapper , Estrutura de Exceptions e ExceptionHandler, Cors, Swagger, flyway, Conf. com Banco Mysql")
            .version("1")
            .contact(new Contact("Projeto", "https://www.projeto.com.br", "contato@projeto.com.br"))
            .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
            .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

}