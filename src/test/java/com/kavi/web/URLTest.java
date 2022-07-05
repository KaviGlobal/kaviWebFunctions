package com.kavi.web;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class URLTest {

	public static void main(String[] args) {

		try {

			byte[] fileContent = FileUtils.readFileToByteArray(new File("C:\\images\\full.jpg"));
			String encodedString = Base64.getEncoder().encodeToString(fileContent);

			URL url = new URL("http://localhost:7071/api/uploadImage");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Content-Length", "0");

			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = encodedString.getBytes();
				os.write(input, 0, input.length);
			}
			
			conn.connect();

			int responseCode = conn.getResponseCode();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
