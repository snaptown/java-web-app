package org.snaptown.converters;

import java.util.List;

public interface IConverter<T, V> {
	V convert(List<T> valueToConvert) throws Exception;

	V convert(T valueToConvert) throws Exception;
}
