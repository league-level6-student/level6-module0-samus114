package _03_intro_to_authenticated_APIs;

import _03_intro_to_authenticated_APIs.data_transfer_objects.ApiExampleWrapper;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Article;
import _03_intro_to_authenticated_APIs.data_transfer_objects.Source;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec;
import org.springframework.web.reactive.function.client.WebClient.RequestHeadersUriSpec;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import org.springframework.web.util.UriBuilder;

import _01_intro_to_APIs.data_transfer_objects.Result;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class NewsApiTest {

	NewsApi newsApi;
	String apiKey = "98b0026e01924f389df77ddac3f7d0e6";

	@Mock
	WebClient webClientMock;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpecMock; 

    @Mock
    WebClient.ResponseSpec responseSpecMock;
    
    @Mock
    Mono<ApiExampleWrapper> apiExampleWrapperMonoMock;


	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		newsApi = new NewsApi();
		newsApi.setWebClient(webClientMock);
	}

	@Test
    void itShouldGetNewsStoryByTopic() {
        //given
    	String topic =  "Weather";
    	Article article = new Article();
    	List<Article> articles = new ArrayList<Article>();
    	ApiExampleWrapper expectedApiExampleWrapper = new ApiExampleWrapper();
    	
    	expectedApiExampleWrapper.setArticles(articles);

        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriSpecMock.uri((Function<UriBuilder, URI>) any()))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersSpecMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseSpecMock.bodyToMono(ApiExampleWrapper.class))
                .thenReturn(apiExampleWrapperMonoMock);
        when(apiExampleWrapperMonoMock.block())
                .thenReturn(expectedApiExampleWrapper);

        //when
ApiExampleWrapper actualApiExampleWrapper = newsApi.getNewsStoryByTopic(topic);
        System.out.println();
        //then
    	verify(webClientMock, times(1)).get();
    	assertEquals(articles, actualApiExampleWrapper);
    }

	@Test
	void itShouldFindStory() {
		// given
		
		// when

		// then
	}

}