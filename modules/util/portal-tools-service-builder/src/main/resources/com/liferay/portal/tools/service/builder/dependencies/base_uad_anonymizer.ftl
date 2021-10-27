package ${entity.UADPackagePath}.uad.anonymizer;

import ${apiPackagePath}.model.${entity.name};
import ${apiPackagePath}.service.${entity.name}LocalService;
import ${entity.UADPackagePath}.uad.constants.${entity.UADApplicationName}UADConstants;

import com.liferay.asset.kernel.model.AssetEntry;
import com.liferay.asset.kernel.service.AssetEntryLocalService;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;

import org.osgi.service.component.annotations.Reference;

/**
 * Provides the base implementation for the ${entity.humanName} UAD anonymizer.
 *
 * <p>
 * This implementation exists only as a container for the default methods
 * generated by ServiceBuilder. All custom service methods should be put in
 * {@link ${entity.UADPackagePath}.uad.anonymizer.${entity.name}UADAnonymizer}.
 * </p>
 *
 * @author ${author}
 * @generated
 */
public abstract class Base${entity.name}UADAnonymizer extends DynamicQueryUADAnonymizer<${entity.name}> {

	@Override
	public void autoAnonymize(${entity.name} ${entity.variableName}, long userId, User anonymousUser) throws PortalException {
		<#list entity.UADUserIdColumnNames as uadUserIdColumnName>
			<#assign uadUserIdEntityColumn = entity.getEntityColumn(uadUserIdColumnName) />

					if (${entity.variableName}.get${uadUserIdEntityColumn.methodName}() == userId) {
						<#if entity.UADAutoDelete>
							delete(${entity.variableName});
						<#else>
							<#list entity.UADAnonymizableEntityColumnsMap[uadUserIdColumnName] as uadAnonymizableEntityColumn>
								${entity.variableName}.set${uadAnonymizableEntityColumn.methodName}(anonymousUser.get${textFormatter.format(uadAnonymizableEntityColumn.UADAnonymizeFieldName, 6)}());
							</#list>
						</#if>

						<#if stringUtil.equals(uadUserIdEntityColumn.name, "userId")>
							autoAnonymizeAssetEntry(${entity.variableName}, anonymousUser);
						</#if>
					}
		</#list>

		<#if !entity.UADAutoDelete>
			${entity.variableName}LocalService.update${entity.name}(${entity.variableName});
		</#if>
	}

	@Override
	public void delete(${entity.name} ${entity.variableName}) throws PortalException {
		${entity.variableName}LocalService.${deleteUADEntityMethodName}(${entity.variableName});
	}

	@Override
	public Class<${entity.name}> getTypeClass() {
		return ${entity.name}.class;
	}

	protected void autoAnonymizeAssetEntry(${entity.name} ${entity.variableName}, User anonymousUser) {
		AssetEntry assetEntry = fetchAssetEntry(${entity.variableName});

		if (assetEntry != null) {
			assetEntry.setUserId(anonymousUser.getUserId());
			assetEntry.setUserName(anonymousUser.getFullName());

			assetEntryLocalService.updateAssetEntry(assetEntry);
		}
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return ${entity.variableName}LocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return ${entity.UADApplicationName}UADConstants.USER_ID_FIELD_NAMES_${entity.constantName};
	}

	protected AssetEntry fetchAssetEntry(${entity.name} ${entity.variableName}) {
		return assetEntryLocalService.fetchEntry(${entity.name}.class.getName(), ${entity.variableName}.get${entity.getPKMethodName()}());
	}

	@Reference
	protected AssetEntryLocalService assetEntryLocalService;

	@Reference
	protected ${entity.name}LocalService ${entity.variableName}LocalService;

}