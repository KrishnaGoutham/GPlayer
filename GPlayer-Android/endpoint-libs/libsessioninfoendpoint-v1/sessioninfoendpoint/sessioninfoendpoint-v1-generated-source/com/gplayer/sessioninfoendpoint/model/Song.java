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
 * on 2014-05-18 at 14:24:58 UTC 
 * Modify at your own risk.
 */

package com.gplayer.sessioninfoendpoint.model;

/**
 * Model definition for Song.
 *
 * <p> This is the Java data model class that specifies how to parse/serialize into the JSON that is
 * transmitted over HTTP when working with the sessioninfoendpoint. For a detailed explanation see:
 * <a href="http://code.google.com/p/google-http-java-client/wiki/JSON">http://code.google.com/p/google-http-java-client/wiki/JSON</a>
 * </p>
 *
 * @author Google, Inc.
 */
@SuppressWarnings("javadoc")
public final class Song extends com.google.api.client.json.GenericJson {

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String album;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String artist;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String blobKey;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Float length;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.Boolean songInBlobStore;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String songType;

  /**
   * The value may be {@code null}.
   */
  @com.google.api.client.util.Key
  private java.lang.String title;

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getAlbum() {
    return album;
  }

  /**
   * @param album album or {@code null} for none
   */
  public Song setAlbum(java.lang.String album) {
    this.album = album;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getArtist() {
    return artist;
  }

  /**
   * @param artist artist or {@code null} for none
   */
  public Song setArtist(java.lang.String artist) {
    this.artist = artist;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getBlobKey() {
    return blobKey;
  }

  /**
   * @param blobKey blobKey or {@code null} for none
   */
  public Song setBlobKey(java.lang.String blobKey) {
    this.blobKey = blobKey;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Float getLength() {
    return length;
  }

  /**
   * @param length length or {@code null} for none
   */
  public Song setLength(java.lang.Float length) {
    this.length = length;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.Boolean getSongInBlobStore() {
    return songInBlobStore;
  }

  /**
   * @param songInBlobStore songInBlobStore or {@code null} for none
   */
  public Song setSongInBlobStore(java.lang.Boolean songInBlobStore) {
    this.songInBlobStore = songInBlobStore;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getSongType() {
    return songType;
  }

  /**
   * @param songType songType or {@code null} for none
   */
  public Song setSongType(java.lang.String songType) {
    this.songType = songType;
    return this;
  }

  /**
   * @return value or {@code null} for none
   */
  public java.lang.String getTitle() {
    return title;
  }

  /**
   * @param title title or {@code null} for none
   */
  public Song setTitle(java.lang.String title) {
    this.title = title;
    return this;
  }

  @Override
  public Song set(String fieldName, Object value) {
    return (Song) super.set(fieldName, value);
  }

  @Override
  public Song clone() {
    return (Song) super.clone();
  }

}
