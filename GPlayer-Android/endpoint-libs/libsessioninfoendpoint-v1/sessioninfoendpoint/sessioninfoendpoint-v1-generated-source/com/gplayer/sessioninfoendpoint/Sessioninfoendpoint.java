/*
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
/*
 * This code was generated by https://code.google.com/p/google-apis-client-generator/
 * (build: 2014-04-15 19:10:39 UTC)
 * on 2014-05-25 at 17:01:47 UTC 
 * Modify at your own risk.
 */

package com.gplayer.sessioninfoendpoint;

/**
 * Service definition for Sessioninfoendpoint (v1).
 *
 * <p>
 * This is an API
 * </p>
 *
 * <p>
 * For more information about this service, see the
 * <a href="" target="_blank">API Documentation</a>
 * </p>
 *
 * <p>
 * This service uses {@link SessioninfoendpointRequestInitializer} to initialize global parameters via its
 * {@link Builder}.
 * </p>
 *
 * @since 1.3
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public class Sessioninfoendpoint extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient {

  // Note: Leave this static initializer at the top of the file.
  static {
    com.google.api.client.util.Preconditions.checkState(
        com.google.api.client.googleapis.GoogleUtils.MAJOR_VERSION == 1 &&
        com.google.api.client.googleapis.GoogleUtils.MINOR_VERSION >= 15,
        "You are currently running with version %s of google-api-client. " +
        "You need at least version 1.15 of google-api-client to run version " +
        "1.16.0-rc of the sessioninfoendpoint library.", com.google.api.client.googleapis.GoogleUtils.VERSION);
  }

  /**
   * The default encoded root URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_ROOT_URL = "https://myapp.appspot.com/_ah/api/";

  /**
   * The default encoded service path of the service. This is determined when the library is
   * generated and normally should not be changed.
   *
   * @since 1.7
   */
  public static final String DEFAULT_SERVICE_PATH = "sessioninfoendpoint/v1/clientsessiondata/";

  /**
   * The default encoded base URL of the service. This is determined when the library is generated
   * and normally should not be changed.
   */
  public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + DEFAULT_SERVICE_PATH;

  /**
   * Constructor.
   *
   * <p>
   * Use {@link Builder} if you need to specify any of the optional parameters.
   * </p>
   *
   * @param transport HTTP transport, which should normally be:
   *        <ul>
   *        <li>Google App Engine:
   *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
   *        <li>Android: {@code newCompatibleTransport} from
   *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
   *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
   *        </li>
   *        </ul>
   * @param jsonFactory JSON factory, which may be:
   *        <ul>
   *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
   *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
   *        <li>Android Honeycomb or higher:
   *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
   *        </ul>
   * @param httpRequestInitializer HTTP request initializer or {@code null} for none
   * @since 1.7
   */
  public Sessioninfoendpoint(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
      com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
    this(new Builder(transport, jsonFactory, httpRequestInitializer));
  }

  /**
   * @param builder builder
   */
  Sessioninfoendpoint(Builder builder) {
    super(builder);
  }

  @Override
  protected void initialize(com.google.api.client.googleapis.services.AbstractGoogleClientRequest<?> httpClientRequest) throws java.io.IOException {
    super.initialize(httpClientRequest);
  }

  /**
   * Create a request for the method "getClientSessionData".
   *
   * This request holds the parameters needed by the the sessioninfoendpoint server.  After setting
   * any optional parameters, call the {@link GetClientSessionData#execute()} method to invoke the
   * remote operation.
   *
   * @param sessionId
   * @param clientId
   * @return the request
   */
  public GetClientSessionData getClientSessionData(java.lang.String sessionId, java.lang.String clientId) throws java.io.IOException {
    GetClientSessionData result = new GetClientSessionData(sessionId, clientId);
    initialize(result);
    return result;
  }

  public class GetClientSessionData extends SessioninfoendpointRequest<com.gplayer.sessioninfoendpoint.model.ClientSessionData> {

    private static final String REST_PATH = "{sessionId}/{clientId}";

    /**
     * Create a request for the method "getClientSessionData".
     *
     * This request holds the parameters needed by the the sessioninfoendpoint server.  After setting
     * any optional parameters, call the {@link GetClientSessionData#execute()} method to invoke the
     * remote operation. <p> {@link GetClientSessionData#initialize(com.google.api.client.googleapis.s
     * ervices.AbstractGoogleClientRequest)} must be called to initialize this instance immediately
     * after invoking the constructor. </p>
     *
     * @param sessionId
     * @param clientId
     * @since 1.13
     */
    protected GetClientSessionData(java.lang.String sessionId, java.lang.String clientId) {
      super(Sessioninfoendpoint.this, "GET", REST_PATH, null, com.gplayer.sessioninfoendpoint.model.ClientSessionData.class);
      this.sessionId = com.google.api.client.util.Preconditions.checkNotNull(sessionId, "Required parameter sessionId must be specified.");
      this.clientId = com.google.api.client.util.Preconditions.checkNotNull(clientId, "Required parameter clientId must be specified.");
    }

    @Override
    public com.google.api.client.http.HttpResponse executeUsingHead() throws java.io.IOException {
      return super.executeUsingHead();
    }

    @Override
    public com.google.api.client.http.HttpRequest buildHttpRequestUsingHead() throws java.io.IOException {
      return super.buildHttpRequestUsingHead();
    }

    @Override
    public GetClientSessionData setAlt(java.lang.String alt) {
      return (GetClientSessionData) super.setAlt(alt);
    }

    @Override
    public GetClientSessionData setFields(java.lang.String fields) {
      return (GetClientSessionData) super.setFields(fields);
    }

    @Override
    public GetClientSessionData setKey(java.lang.String key) {
      return (GetClientSessionData) super.setKey(key);
    }

    @Override
    public GetClientSessionData setOauthToken(java.lang.String oauthToken) {
      return (GetClientSessionData) super.setOauthToken(oauthToken);
    }

    @Override
    public GetClientSessionData setPrettyPrint(java.lang.Boolean prettyPrint) {
      return (GetClientSessionData) super.setPrettyPrint(prettyPrint);
    }

    @Override
    public GetClientSessionData setQuotaUser(java.lang.String quotaUser) {
      return (GetClientSessionData) super.setQuotaUser(quotaUser);
    }

    @Override
    public GetClientSessionData setUserIp(java.lang.String userIp) {
      return (GetClientSessionData) super.setUserIp(userIp);
    }

    @com.google.api.client.util.Key
    private java.lang.String sessionId;

    /**

     */
    public java.lang.String getSessionId() {
      return sessionId;
    }

    public GetClientSessionData setSessionId(java.lang.String sessionId) {
      this.sessionId = sessionId;
      return this;
    }

    @com.google.api.client.util.Key
    private java.lang.String clientId;

    /**

     */
    public java.lang.String getClientId() {
      return clientId;
    }

    public GetClientSessionData setClientId(java.lang.String clientId) {
      this.clientId = clientId;
      return this;
    }

    @Override
    public GetClientSessionData set(String parameterName, Object value) {
      return (GetClientSessionData) super.set(parameterName, value);
    }
  }

  /**
   * Builder for {@link Sessioninfoendpoint}.
   *
   * <p>
   * Implementation is not thread-safe.
   * </p>
   *
   * @since 1.3.0
   */
  public static final class Builder extends com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

    /**
     * Returns an instance of a new builder.
     *
     * @param transport HTTP transport, which should normally be:
     *        <ul>
     *        <li>Google App Engine:
     *        {@code com.google.api.client.extensions.appengine.http.UrlFetchTransport}</li>
     *        <li>Android: {@code newCompatibleTransport} from
     *        {@code com.google.api.client.extensions.android.http.AndroidHttp}</li>
     *        <li>Java: {@link com.google.api.client.googleapis.javanet.GoogleNetHttpTransport#newTrustedTransport()}
     *        </li>
     *        </ul>
     * @param jsonFactory JSON factory, which may be:
     *        <ul>
     *        <li>Jackson: {@code com.google.api.client.json.jackson2.JacksonFactory}</li>
     *        <li>Google GSON: {@code com.google.api.client.json.gson.GsonFactory}</li>
     *        <li>Android Honeycomb or higher:
     *        {@code com.google.api.client.extensions.android.json.AndroidJsonFactory}</li>
     *        </ul>
     * @param httpRequestInitializer HTTP request initializer or {@code null} for none
     * @since 1.7
     */
    public Builder(com.google.api.client.http.HttpTransport transport, com.google.api.client.json.JsonFactory jsonFactory,
        com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      super(
          transport,
          jsonFactory,
          DEFAULT_ROOT_URL,
          DEFAULT_SERVICE_PATH,
          httpRequestInitializer,
          false);
    }

    /** Builds a new instance of {@link Sessioninfoendpoint}. */
    @Override
    public Sessioninfoendpoint build() {
      return new Sessioninfoendpoint(this);
    }

    @Override
    public Builder setRootUrl(String rootUrl) {
      return (Builder) super.setRootUrl(rootUrl);
    }

    @Override
    public Builder setServicePath(String servicePath) {
      return (Builder) super.setServicePath(servicePath);
    }

    @Override
    public Builder setHttpRequestInitializer(com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
      return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
    }

    @Override
    public Builder setApplicationName(String applicationName) {
      return (Builder) super.setApplicationName(applicationName);
    }

    @Override
    public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
      return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
    }

    @Override
    public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
      return (Builder) super.setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
    }

    @Override
    public Builder setSuppressAllChecks(boolean suppressAllChecks) {
      return (Builder) super.setSuppressAllChecks(suppressAllChecks);
    }

    /**
     * Set the {@link SessioninfoendpointRequestInitializer}.
     *
     * @since 1.12
     */
    public Builder setSessioninfoendpointRequestInitializer(
        SessioninfoendpointRequestInitializer sessioninfoendpointRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(sessioninfoendpointRequestInitializer);
    }

    @Override
    public Builder setGoogleClientRequestInitializer(
        com.google.api.client.googleapis.services.GoogleClientRequestInitializer googleClientRequestInitializer) {
      return (Builder) super.setGoogleClientRequestInitializer(googleClientRequestInitializer);
    }
  }
}