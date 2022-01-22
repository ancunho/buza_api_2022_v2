//package com.buza;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.reactive.CorsWebFilter;
//import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
//import org.springframework.web.util.pattern.PathPatternParser;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public CorsWebFilter corsWebFilter() {
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedHeader("*");
//        config.addAllowedOrigin("*");
//        config.setMaxAge(3600L);
//        config.addAllowedMethod("HEAD");
//        config.addAllowedMethod("GET");
//        config.addAllowedMethod("POST");
//        config.addAllowedMethod("OPTIONS");
//        config.addAllowedMethod("DELETE");
//        config.addAllowedMethod("PUT");
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
//        source.registerCorsConfiguration("/**", config);
//
//        return new CorsWebFilter(source);
//
//    }
//
////    private CorsConfiguration buildConfig() {
////        CorsConfiguration corsConfiguration = new CorsConfiguration();
////        corsConfiguration.addAllowedOrigin("*");
////        corsConfiguration.addAllowedHeader("*");
////        corsConfiguration.addAllowedMethod("*");
////        corsConfiguration.addExposedHeader("Authorization");
////        return corsConfiguration;
////    }
////
////    @Bean
////    public CorsFilter corsFilter() {
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/**", buildConfig());
////        return new CorsFilter(source);
////    }
////
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        registry.addMapping("/**")
////                .allowedOrigins("*")
////                .allowCredentials(true)
////                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
////                .maxAge(3600);
////    }
//
//}
