package org.apache.coyote.http11.response;

import camp.nextstep.db.InMemoryUserRepository;
import camp.nextstep.model.User;
import org.apache.coyote.http11.request.Path;
import org.apache.coyote.http11.request.QueryStrings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.NoSuchElementException;

public class ResponseResource {

	private static final Logger log = LoggerFactory.getLogger(ResponseResource.class);

	private final String responseBody;
	private final String fileName;

	private ResponseResource(final String responseBody, String fileName) {
		this.responseBody = responseBody;
		this.fileName = fileName;
	}

	public static ResponseResource of(final Path path) throws IOException {
		if(path.urlPath().equals("/")) {
			String responseBody = createResponseBody("index.html");
			return new ResponseResource(responseBody, "index.html");
		}

		if (path.urlPath().equals("/login")) {
			QueryStrings queryStrings = path.queryStrings();
			String account = queryStrings.getQueryStringValueByKey("account");
			String password = queryStrings.getQueryStringValueByKey("password");
			login(account, password);
			String responseBody = createResponseBody("login.html");
			return new ResponseResource(responseBody, "login.html");
		}

		String responseBody = createResponseBody(path.urlPath());
		return new ResponseResource(responseBody, path.urlPath());
	}

	public String getResponseBody() {
		return responseBody;
	}

	public static void login(String account, String password) {
		final User user = InMemoryUserRepository.findByAccount(account).orElseThrow(NoSuchElementException::new);
		if (user.checkPassword(password)) {
			log.info("user {}", user);
		}
	}

	public String parseExtension() {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private static String createResponseBody(String urlPath) throws IOException {
		URL resource = ResponseResource.class.getClassLoader().getResource("static/" + urlPath);
		String responseBody = new String(Files.readAllBytes(new File(resource.getFile()).toPath()));
		return responseBody;
	}
}
