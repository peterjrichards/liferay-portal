/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.oauth.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.oauth.service.http.OAuthUserServiceSoap}.
 *
 * @author Ivica Cardic
 * @deprecated As of Athanasius (7.3.x), with no direct replacement
 * @generated
 */
@Deprecated
public class OAuthUserSoap implements Serializable {

	public static OAuthUserSoap toSoapModel(OAuthUser model) {
		OAuthUserSoap soapModel = new OAuthUserSoap();

		soapModel.setOAuthUserId(model.getOAuthUserId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setOAuthApplicationId(model.getOAuthApplicationId());
		soapModel.setAccessToken(model.getAccessToken());
		soapModel.setAccessSecret(model.getAccessSecret());

		return soapModel;
	}

	public static OAuthUserSoap[] toSoapModels(OAuthUser[] models) {
		OAuthUserSoap[] soapModels = new OAuthUserSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static OAuthUserSoap[][] toSoapModels(OAuthUser[][] models) {
		OAuthUserSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new OAuthUserSoap[models.length][models[0].length];
		}
		else {
			soapModels = new OAuthUserSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static OAuthUserSoap[] toSoapModels(List<OAuthUser> models) {
		List<OAuthUserSoap> soapModels = new ArrayList<OAuthUserSoap>(
			models.size());

		for (OAuthUser model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new OAuthUserSoap[soapModels.size()]);
	}

	public OAuthUserSoap() {
	}

	public long getPrimaryKey() {
		return _oAuthUserId;
	}

	public void setPrimaryKey(long pk) {
		setOAuthUserId(pk);
	}

	public long getOAuthUserId() {
		return _oAuthUserId;
	}

	public void setOAuthUserId(long oAuthUserId) {
		_oAuthUserId = oAuthUserId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getOAuthApplicationId() {
		return _oAuthApplicationId;
	}

	public void setOAuthApplicationId(long oAuthApplicationId) {
		_oAuthApplicationId = oAuthApplicationId;
	}

	public String getAccessToken() {
		return _accessToken;
	}

	public void setAccessToken(String accessToken) {
		_accessToken = accessToken;
	}

	public String getAccessSecret() {
		return _accessSecret;
	}

	public void setAccessSecret(String accessSecret) {
		_accessSecret = accessSecret;
	}

	private long _oAuthUserId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _oAuthApplicationId;
	private String _accessToken;
	private String _accessSecret;

}