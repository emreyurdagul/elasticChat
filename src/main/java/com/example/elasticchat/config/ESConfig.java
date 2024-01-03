package com.example.elasticchat.config;


import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.ssl.SSLContexts;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.net.ssl.SSLContext;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.elasticchat.repository" )
public class ESConfig {


    @Value("${http.server.ssl.trust-store}")
    private Resource trustStore;

    @Value("${http.server.ssl.trust-store-password}")
    private String trustStorePassword;

    @Value("${elastic.client.username}")
    private String elasticUserName;

    @Value("${elastic.client.password}")
    private String elasticPassword;

    @Value("${elastic.host}")
    private String elasticHost;

    @Value("${elastic.port}")
    private String elasticPort;


    @Bean
    public RestClient restClient(){
        try {
            final SSLContext sslContext = SSLContexts.custom()
                    .loadTrustMaterial(trustStore.getURL(), trustStorePassword.toCharArray())
                    .build();
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();

            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(elasticUserName, elasticPassword));

            //Create a client.
            RestClientBuilder builder = RestClient.builder(new HttpHost(elasticHost, Integer.parseInt(elasticPort), "https"))
                    .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                        @Override
                        public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                            return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider).setSSLContext(sslContext);
                        }
                    }).setDefaultHeaders(compatibilityHeaders());
            return builder.build();
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }



    private Header[] compatibilityHeaders() {
        return new Header[]{new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=8"),
                new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=8")};
    }






}
