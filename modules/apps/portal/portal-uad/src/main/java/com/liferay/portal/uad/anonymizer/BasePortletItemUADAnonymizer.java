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

package com.liferay.portal.uad.anonymizer;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PortletItem;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.PortletItemLocalService;
import com.liferay.portal.uad.constants.PortalUADConstants;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the portlet item UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link PortletItemUADAnonymizer}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public abstract class BasePortletItemUADAnonymizer
	extends DynamicQueryUADAnonymizer<PortletItem> {

	@Override
	public void autoAnonymize(
			PortletItem portletItem, long userId, User anonymousUser)
		throws PortalException {

		if (portletItem.getUserId() == userId) {
			portletItem.setUserId(anonymousUser.getUserId());
			portletItem.setUserName(anonymousUser.getFullName());

			autoAnonymizeAssetEntry(portletItem, anonymousUser);
		}

		portletItemLocalService.updatePortletItem(portletItem);
	}

	@Override
	public void delete(PortletItem portletItem) throws PortalException {
		portletItemLocalService.deletePortletItem(portletItem);
	}

	@Override
	public Class<PortletItem> getTypeClass() {
		return PortletItem.class;
	}

	protected void autoAnonymizeAssetEntry(
		PortletItem portletItem, User anonymousUser) {

		AssetEntry assetEntry = fetchAssetEntry(portletItem);

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return portletItemLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return PortalUADConstants.USER_ID_FIELD_NAMES_PORTLET_ITEM;
	}

	protected AssetEntry fetchAssetEntry(PortletItem portletItem) {
		return assetEntryLocalService.fetchEntry(
			PortletItem.class.getName(), portletItem.getPortletItemId());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected PortletItemLocalService portletItemLocalService;

}