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

package com.gplayer.sessioninfoendpoint.model;

/**
 * Model definition for ClientSessionData.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the sessioninfoendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class ClientSessionData extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Integer currentSong;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.util.List<Song> diffList;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Integer getCurrentSong() {
    return currentSong;
  }

  /**
   * @param currentSong currentSong or {@code null} for none
   */
  public ClientSessionData setCurrentSong(java.lang.Integer currentSong) {
    this.currentSong = currentSong;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.util.List<Song> getDiffList() {
    return diffList;
  }

  /**
   * @param diffList diffList or {@code null} for none
   */
  public ClientSessionData setDiffList(java.util.List<Song> diffList) {
    this.diffList = diffList;
    return this;
  }

  @Override
  public ClientSessionData set(String fieldName, Object value) {
    return (ClientSessionData) super.set(fieldName, value);
  }

  @Override
  public ClientSessionData clone() {
    return (ClientSessionData) super.clone();
  }

}