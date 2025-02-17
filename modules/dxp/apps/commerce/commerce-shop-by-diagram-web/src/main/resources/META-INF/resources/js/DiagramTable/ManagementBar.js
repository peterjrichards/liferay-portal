/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * The contents of this file are subject to the terms of the Liferay Enterprise
 * Subscription License ("License"). You may not use this file except in
 * compliance with the License. You can obtain a copy of the License by
 * contacting Liferay, Inc. See the License for the specific language governing
 * permissions and limitations under the License, including but not limited to
 * distribution rights of the Software.
 */

import {ClayButtonWithIcon} from '@clayui/button';
import {ClayInput} from '@clayui/form';
import ClayManagementToolbar from '@clayui/management-toolbar';
import {debounce} from 'frontend-js-web';
import React, {useRef, useState} from 'react';

function ManagementBar({updateQuery}) {
	const [inputValue, udpateInputValue] = useState('');
	const debouncedUpdateQueryRef = useRef(debounce(updateQuery, 500));

	function handleInputChange({target}) {
		udpateInputValue(target.value);

		debouncedUpdateQueryRef.current(target.value);
	}

	return (
		<ClayManagementToolbar className="border-bottom">
			<ClayManagementToolbar.Search
				onSubmit={(event) => event.preventDefault()}
			>
				<ClayInput.Group>
					<ClayInput.GroupItem>
						<ClayInput
							aria-label={Liferay.Language.get('search')}
							className="form-control input-group-inset input-group-inset-after"
							onChange={handleInputChange}
							type="text"
							value={inputValue}
						/>

						<ClayInput.GroupInsetItem after tag="span">
							<ClayButtonWithIcon
								displayType="unstyled"
								symbol="search"
								type="submit"
							/>
						</ClayInput.GroupInsetItem>
					</ClayInput.GroupItem>
				</ClayInput.Group>
			</ClayManagementToolbar.Search>
		</ClayManagementToolbar>
	);
}

export default ManagementBar;
