package com.calculator.coin;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.i18n.SimpleLocaleContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.i18n.LocaleContextResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Configuration
public class LocalizationConfig implements WebMvcConfigurer, LocaleContextResolver {

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/coin");
        source.setDefaultEncoding("UTF-8");
        return source;
    }

    @Override
    public LocaleContext resolveLocaleContext(ServerWebExchange exchange) {
        List<String> languages = Optional.ofNullable(exchange.getRequest().getHeaders().get("lang")).orElse(Arrays.asList("tr"));

        String language = languages.stream()
                .filter(lang -> lang.equalsIgnoreCase("tr") || lang.equalsIgnoreCase("en"))
                .findFirst()
                .orElse("en");

        return new SimpleLocaleContext(new Locale(language));
    }

    @Override
    public void setLocaleContext(ServerWebExchange exchange, LocaleContext localeContext) {
        LocaleContextHolder.setLocale(localeContext.getLocale());
    }
}
