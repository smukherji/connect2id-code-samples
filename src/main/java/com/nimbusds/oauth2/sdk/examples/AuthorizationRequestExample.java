/**
 * Copyright (C) 2014. All Rights Reserved.
 */
package com.nimbusds.oauth2.sdk.examples;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import com.nimbusds.oauth2.sdk.AuthorizationRequest;
import com.nimbusds.oauth2.sdk.ParseException;
import com.nimbusds.oauth2.sdk.ResponseType;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.SerializeException;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.nimbusds.oauth2.sdk.id.State;

/**
 * 
 * @author <a href="mailto:mukherjisayan@gmail.com">Sayan Mukherji</a>
 *
 */
public class AuthorizationRequestExample {
    private String authEndpoint;
	private String clientID;
	private String state;
	private String redirectUri;
	private String commaSeparatedScopes;
	/**
	 * @param clientID
	 * @param state
	 * @param redirectUri
	 * @param commaSeparatedScopes
	 */
	public AuthorizationRequestExample(String authUri, String clientID, String state, String redirectUri, String commaSeparatedScopes) {
		this.authEndpoint = authUri;
		this.clientID = clientID;
		this.state = state;
		this.redirectUri = redirectUri;
		this.commaSeparatedScopes = commaSeparatedScopes;
	}
	
	public String buildAuthorizationRequest() throws ParseException, URISyntaxException, SerializeException {
		List<String> scopeList = Arrays.asList(this.commaSeparatedScopes.split(","));
		AuthorizationRequest request = new AuthorizationRequest(new URI(this.authEndpoint), 
				                                                ResponseType.parse(ResponseType.Value.CODE.toString()), 
				                                                new ClientID(this.clientID), new URI(this.redirectUri),
                                                                Scope.parse(scopeList),new State(this.state));
		return request.toURI().toString();
	}
	
	public static void main(String[] args) throws Exception {
		final AuthorizationRequestExample example = new AuthorizationRequestExample("https://server.example.com/auth","s6BhdRkqt3", "xyz", "https://client.example.com/cb", "openid,email,profile");
		System.out.println("Authorization Request Output...");
		System.out.println(example.buildAuthorizationRequest());
	}
}
