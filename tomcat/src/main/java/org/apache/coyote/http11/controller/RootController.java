package org.apache.coyote.http11.controller;

import org.apache.coyote.http11.request.HttpRequest;
import org.apache.coyote.http11.request.model.ContentType;
import org.apache.coyote.http11.response.HttpResponse;

import java.io.IOException;

public class RootController extends AbstractController {

	@Override
	protected void doGet(final HttpRequest request, final HttpResponse response) throws IOException {
		response.setContentType(ContentType.TEXT_HTML);
		response.forwardBody("Hello world!");
	}
}
