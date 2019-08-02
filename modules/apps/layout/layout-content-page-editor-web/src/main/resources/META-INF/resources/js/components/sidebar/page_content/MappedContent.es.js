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

import ClayButton from '@clayui/button';
import ClayDropDown from '@clayui/drop-down';
import ClayIcon from '@clayui/icon';
import ClayLabel from '@clayui/label';
import PropTypes from 'prop-types';
import React, {useState} from 'react';
import {FRAGMENTS_EDITOR_ITEM_TYPES} from '../../../utils/constants';

const MappedContent = props => {
	const {label, style} = props.status;
	const {editURL, permissionsURL, viewUsagesURL} = props.actions;

	const [active, setActive] = useState(false);

	const sub = (sentence, argument) => sentence.replace('{0}', argument);

	const openWindow = (uri, title) => {
		Liferay.Util.openWindow({
			dialog: {
				destroyOnHide: true,
				modal: true
			},
			dialogIframe: {
				bodyCssClass: 'dialog-with-footer'
			},
			title,
			uri
		});
	};

	const {classNameId, classPK} = props;

	const itemId = `${classNameId}-${classPK}`;

	return (
		<li
			className="fragments-editor__mapped-content"
			data-fragments-editor-item-id={itemId}
			data-fragments-editor-item-type={
				FRAGMENTS_EDITOR_ITEM_TYPES.mappedItem
			}
		>
			<div className="d-flex p-3 flex-column">
				<div className="autofit-col autofit-col-expand">
					<strong className="list-group-title truncate-text">
						{props.title}
					</strong>

					<span className="text-secondary small">{props.name}</span>

					<span className="text-secondary small">
						{props.usagesCount === 1
							? Liferay.Language.get('used-in-1-page')
							: sub(Liferay.Language.get('used-in-x-pages'), [
									props.usagesCount
							  ])}
					</span>

					<ClayLabel
						className="align-self-start mt-2"
						displayType={style}
					>
						{label}
					</ClayLabel>
				</div>

				<ClayDropDown
					active={active}
					onActiveChange={setActive}
					trigger={
						<ClayButton
							className="text-secondary btn-monospaced btn-sm"
							displayType="unstyled"
						>
							<ClayIcon symbol="ellipsis-v" />
						</ClayButton>
					}
				>
					<ClayDropDown.ItemList>
						{editURL && (
							<ClayDropDown.Item href={editURL} key="editURL">
								{Liferay.Language.get('edit')}
							</ClayDropDown.Item>
						)}

						{permissionsURL && (
							<ClayDropDown.Item
								href="javascript:;"
								key="permissionsURL"
								onClick={() =>
									openWindow(
										permissionsURL,
										Liferay.Language.get('permissions')
									)
								}
							>
								{Liferay.Language.get('permissions')}
							</ClayDropDown.Item>
						)}

						{viewUsagesURL && (
							<ClayDropDown.Item
								href="javascript:;"
								key="viewUsagesURL"
								onClick={() =>
									openWindow(
										viewUsagesURL,
										Liferay.Language.get('view-usages')
									)
								}
							>
								{Liferay.Language.get('view-usages')}
							</ClayDropDown.Item>
						)}
					</ClayDropDown.ItemList>
				</ClayDropDown>
			</div>
		</li>
	);
};

MappedContent.propTypes = {
	actions: PropTypes.object,
	name: PropTypes.string.isRequired,
	status: PropTypes.shape({
		label: PropTypes.string,
		style: PropTypes.string
	}),
	title: PropTypes.string.isRequired,
	usagesCount: PropTypes.number.isRequired
};

export {MappedContent};
export default MappedContent;
