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

import {
	generateInstanceId,
	generateName,
	generateNestedFieldName,
	parseNestedFieldName,
} from '../../util/repeatable.es';
import {EVENT_TYPES} from '../actions/types.es';
import {PagesVisitor} from '../util/visitors.es';

export const createRepeatedField = (sourceField, repeatedIndex) => {
	const instanceId = generateInstanceId();

	return {
		...sourceField,
		instanceId,
		localizedValue: {},
		name: generateName(sourceField.name, {instanceId, repeatedIndex}),
		nestedFields: (sourceField.nestedFields || []).map((nestedField) => ({
			...nestedField,
			localizedValue: {},
			value: undefined,
		})),
		value: undefined,
	};
};

export const updateNestedFieldNames = (parentFieldName, nestedFields) => {
	return (nestedFields || []).map((nestedField) => {
		const newNestedFieldName = generateNestedFieldName(
			nestedField.name,
			parentFieldName
		);

		return {
			...nestedField,
			name: newNestedFieldName,
			nestedFields: updateNestedFieldNames(
				newNestedFieldName,
				nestedField.nestedFields
			),
			...parseNestedFieldName(newNestedFieldName),
		};
	});
};

export default (state, action) => {
	switch (action.type) {
		case EVENT_TYPES.FIELD_BLUR: {
			const {fieldInstance} = action.payload;
			const pageVisitor = new PagesVisitor(state.pages);

			return {
				pages: pageVisitor.mapFields((field) => {
					const matches =
						field.name === fieldInstance.name &&
						field.required &&
						fieldInstance.value == '';

					return {
						...field,
						displayErrors: !!field.displayErrors || matches,
						focused: matches ? false : field.focused,
					};
				}),
			};
		}
		case EVENT_TYPES.FIELD_FOCUS: {
			const {fieldInstance} = action.payload;
			const pageVisitor = new PagesVisitor(state.pages);

			return {
				pages: pageVisitor.mapFields((field) => {
					const focused = field.name === fieldInstance.name;

					return {
						...field,
						focused,
					};
				}),
			};
		}
		case EVENT_TYPES.FIELD_REMOVED: {
			const pageVisitor = new PagesVisitor(state.pages);

			return {
				pages: pageVisitor.mapColumns((column) => {
					return {
						...column,
						fields: column.fields.filter(
							(field) => field.name !== action.payload
						),
					};
				}),
			};
		}
		case EVENT_TYPES.FIELD_REPEATED: {
			const pageVisitor = new PagesVisitor(state.pages);

			return {
				pages: pageVisitor.mapColumns((column) => {
					const {fields} = column;
					const sourceFieldIndex = fields.reduce(
						(sourceFieldIndex = -1, field, index) => {
							if (field.name === action.payload) {
								sourceFieldIndex = index;
							}

							return sourceFieldIndex;
						},
						-1
					);

					if (sourceFieldIndex > -1) {
						const newFieldIndex = sourceFieldIndex + 1;
						const newField = createRepeatedField(
							fields[sourceFieldIndex],
							newFieldIndex
						);

						let currentRepeatedIndex = 0;

						return {
							...column,
							fields: [
								...fields.slice(0, newFieldIndex),
								newField,
								...fields.slice(newFieldIndex),
							].map((currentField) => {
								if (
									currentField.fieldName ===
									newField.fieldName
								) {
									const name = generateName(
										currentField.name,
										{
											repeatedIndex: currentRepeatedIndex++,
										}
									);

									return {
										...currentField,
										name,
										nestedFields: updateNestedFieldNames(
											name,
											currentField.nestedFields
										),
									};
								}

								return currentField;
							}),
						};
					}

					return column;
				}),
			};
		}
		default:
			return state;
	}
};
