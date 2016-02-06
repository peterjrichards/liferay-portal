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

package com.liferay.portal.kernel.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.bean.AutoEscape;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

/**
 * The base model interface for the ResourceAction service. Represents a row in the &quot;ResourceAction&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This interface and its corresponding implementation {@link com.liferay.portal.model.impl.ResourceActionModelImpl} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link com.liferay.portal.model.impl.ResourceActionImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ResourceAction
 * @see com.liferay.portal.model.impl.ResourceActionImpl
 * @see com.liferay.portal.model.impl.ResourceActionModelImpl
 * @generated
 */
@ProviderType
public interface ResourceActionModel extends BaseModel<ResourceAction>, MVCCModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. All methods that expect a resource action model instance should use the {@link ResourceAction} interface instead.
	 */

	/**
	 * Returns the primary key of this resource action.
	 *
	 * @return the primary key of this resource action
	 */
	public long getPrimaryKey();

	/**
	 * Sets the primary key of this resource action.
	 *
	 * @param primaryKey the primary key of this resource action
	 */
	public void setPrimaryKey(long primaryKey);

	/**
	 * Returns the mvcc version of this resource action.
	 *
	 * @return the mvcc version of this resource action
	 */
	@Override
	public long getMvccVersion();

	/**
	 * Sets the mvcc version of this resource action.
	 *
	 * @param mvccVersion the mvcc version of this resource action
	 */
	@Override
	public void setMvccVersion(long mvccVersion);

	/**
	 * Returns the resource action ID of this resource action.
	 *
	 * @return the resource action ID of this resource action
	 */
	public long getResourceActionId();

	/**
	 * Sets the resource action ID of this resource action.
	 *
	 * @param resourceActionId the resource action ID of this resource action
	 */
	public void setResourceActionId(long resourceActionId);

	/**
	 * Returns the name of this resource action.
	 *
	 * @return the name of this resource action
	 */
	@AutoEscape
	public String getName();

	/**
	 * Sets the name of this resource action.
	 *
	 * @param name the name of this resource action
	 */
	public void setName(String name);

	/**
	 * Returns the action ID of this resource action.
	 *
	 * @return the action ID of this resource action
	 */
	@AutoEscape
	public String getActionId();

	/**
	 * Sets the action ID of this resource action.
	 *
	 * @param actionId the action ID of this resource action
	 */
	public void setActionId(String actionId);

	/**
	 * Returns the bitwise value of this resource action.
	 *
	 * @return the bitwise value of this resource action
	 */
	public long getBitwiseValue();

	/**
	 * Sets the bitwise value of this resource action.
	 *
	 * @param bitwiseValue the bitwise value of this resource action
	 */
	public void setBitwiseValue(long bitwiseValue);

	@Override
	public boolean isNew();

	@Override
	public void setNew(boolean n);

	@Override
	public boolean isCachedModel();

	@Override
	public void setCachedModel(boolean cachedModel);

	@Override
	public boolean isEscapedModel();

	@Override
	public Serializable getPrimaryKeyObj();

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj);

	@Override
	public ExpandoBridge getExpandoBridge();

	@Override
	public void setExpandoBridgeAttributes(BaseModel<?> baseModel);

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge);

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	@Override
	public Object clone();

	@Override
	public int compareTo(
		com.liferay.portal.kernel.model.ResourceAction resourceAction);

	@Override
	public int hashCode();

	@Override
	public CacheModel<com.liferay.portal.kernel.model.ResourceAction> toCacheModel();

	@Override
	public com.liferay.portal.kernel.model.ResourceAction toEscapedModel();

	@Override
	public com.liferay.portal.kernel.model.ResourceAction toUnescapedModel();

	@Override
	public String toString();

	@Override
	public String toXmlString();
}