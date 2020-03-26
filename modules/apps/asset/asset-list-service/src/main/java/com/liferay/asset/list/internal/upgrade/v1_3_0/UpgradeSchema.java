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

package com.liferay.asset.list.internal.upgrade.v1_3_0;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.list.internal.upgrade.v1_3_0.util.AssetListEntryTable;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Eudaldo Alonso
 */
public class UpgradeSchema extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			AssetListEntryTable.class,
			new AlterTableAddColumn("assetEntryType VARCHAR(255) null"));

		runSQL(
			"create index IX_D604A2E on AssetListEntry (groupId, " +
				"assetEntryType[$COLUMN_LENGTH:75$], ctCollectionId)");

		runSQL(
			"create index IX_CBD041F6 on AssetListEntry (groupId, " +
				"title[$COLUMN_LENGTH:75$], " +
					"assetEntryType[$COLUMN_LENGTH:75$], ctCollectionId)");

		runSQL(
			"update AssetListEntry set assetEntryType = '" +
				AssetEntry.class.getName() + "'");
	}

}