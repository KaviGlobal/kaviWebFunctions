package com.kavi.web.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

@Component
public class ImageAnalysisService {

	private static String COMPUTER_VISION_SUBSCRIPTION_KEY = "0180fee75e6a4d8e89b5fad55d80ca8c";
	private static String COMPUTER_VISION_ENDPOINT = "https://kaviwebsitecomputervision.cognitiveservices.azure.com/vision/v3.1/generateThumbnail";

	/*
	 * private ComputerVisionClient authenticate(String subscriptionKey, String
	 * endpoint) { return
	 * ComputerVisionManager.authenticate(subscriptionKey).withEndpoint(endpoint); }
	 */

	public HttpEntity generateThumbnail(String url, Integer width, Integer height) {

		/*
		 * 
		 * 
		 * InputStream ipStrm = client.computerVision().generateThumbnail(width, height,
		 * url, null);
		 */

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();

		try {
			URIBuilder uriBuilder = new URIBuilder(COMPUTER_VISION_ENDPOINT);
			uriBuilder.setParameter("width", String.valueOf(width));
			uriBuilder.setParameter("height", String.valueOf(height));
			// uriBuilder.setParameter("smartCropping", null);

			URI uri = uriBuilder.build();
			HttpPost request = new HttpPost(uri);

			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", COMPUTER_VISION_SUBSCRIPTION_KEY);

			StringEntity requestEntity = new StringEntity("{\"url\":\"" + url + "\"}");
			request.setEntity(requestEntity);

			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();

			if (response.getStatusLine().getStatusCode() == 200) {
				return entity;
			}

			httpClient.close();

		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

}
