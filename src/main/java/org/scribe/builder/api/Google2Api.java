package org.scribe.builder.api;

import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;
import org.scribe.utils.Preconditions;

public class Google2Api extends DefaultApi20 {

	private static final String AUTHORIZE_URL = "https://accounts.google.com/o/oauth2/auth?scope=%s&redirect_uri=%s&response_type=code&client_id=%s";
	
	@Override
	public AccessTokenExtractor getAccessTokenExtractor() {
		return new JsonTokenExtractor();
	}
	
	@Override
	public Verb getAccessTokenVerb() {
		return Verb.POST;
	}
	
	@Override
	public String getAccessTokenEndpoint() {
		return "https://accounts.google.com/o/oauth2/token";
	}
	
	@Override
	public String getGrantType() {
		return "authorization_code";
	}

	@Override
	public String getAuthorizationUrl(OAuthConfig config) {
                Preconditions.checkNotNull(config.getScope(), "Must provide a valid scope. Google doesnt support empty scope.");
		return String.format(AUTHORIZE_URL, OAuthEncoder.encode(config.getScope()),OAuthEncoder.encode(config.getCallback()),config.getApiKey());
	}

}
