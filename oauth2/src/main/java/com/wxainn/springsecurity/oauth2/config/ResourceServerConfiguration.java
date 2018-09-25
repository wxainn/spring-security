package com.wxainn.springsecurity.oauth2.config;

import com.wxainn.springsecurity.oauth2.security.CustomAccessDecisionManager;
import com.wxainn.springsecurity.oauth2.security.CustomSecurityMetadataSource;
import com.wxainn.springsecurity.oauth2.service.ResourceService;
import com.wxainn.springsecurity.oauth2.service.RoleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 资源服务器配置
 *
 * @author 王晓安
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    private ResourceService resourceService;
    private RoleService roleService;

    private static final String RESOURCE_ID = "MY-RESOURCE-ID";

    public ResourceServerConfiguration(ResourceService resourceService, RoleService roleService) {
        this.resourceService = resourceService;
        this.roleService = roleService;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(RESOURCE_ID)
                .tokenExtractor(new BearerTokenExtractor())
        ;
    }

    @Bean
    AccessDecisionManager accessDecisionManager() {
        return new CustomAccessDecisionManager();
    }

    @Bean
    FilterInvocationSecurityMetadataSource securityMetadataSource() {
        return new CustomSecurityMetadataSource(resourceService, roleService);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http
            .csrf().disable()
//            .anonymous().disable()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                .and()
            .authorizeRequests()
                .anyRequest().authenticated()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O fsi) {
                        fsi.setAccessDecisionManager(accessDecisionManager());
                        fsi.setSecurityMetadataSource(securityMetadataSource());
                        return fsi;
                    }
                })
        ;
        //@formatter:on

    }
}
