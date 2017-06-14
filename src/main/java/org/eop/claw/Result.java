package org.eop.claw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.eop.chassis.util.TypeUtil;
import org.eop.claw.exception.ResultException;

/**
 * @author lixinjie
 * @since 2017-06-05
 */
public class Result implements IResult {
	
	private boolean found;
	private IClaw claw;
	private ResultException exception;
	
	public Result(boolean found, IClaw claw, ResultException exception) {
		this.found = found;
		this.claw = claw;
		this.exception = exception;
	}

	@Override
	public BigDecimal getBigDecimal() {
		return TypeUtil.toBigDecimal(getValue());
	}

	@Override
	public BigInteger getBigInteger() {
		return TypeUtil.toBigInteger(getValue());
	}

	@Override
	public Boolean getBoolean() {
		return TypeUtil.toBoolean(getValue());
	}

	@Override
	public Byte getByte() {
		return TypeUtil.toByte(getValue());
	}

	@Override
	public Date getDate(String format) {
		return TypeUtil.toDate(getValue(), format);
	}

	@Override
	public Double getDouble() {
		return TypeUtil.toDouble(getValue());
	}

	@Override
	public Float getFloat() {
		return TypeUtil.toFloat(getValue());
	}

	@Override
	public Integer getInteger() {
		return TypeUtil.toInteger(getValue());
	}

	@Override
	public Long getLong() {
		return TypeUtil.toLong(getValue());
	}

	@Override
	public Short getShort() {
		return TypeUtil.toShort(getValue());
	}

	@Override
	public String getString() {
		return TypeUtil.toString(getValue());
	}

	@Override
	public Timestamp getTimestamp(String format) {
		return TypeUtil.toTimestamp(getValue(), format);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getValue() {
		if (found) {
			return (T)claw.getUnderlyingData();
		}
		throw exception;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList() {
		T value = getValue();
		if (value instanceof List<?>) {
			return (List<T>)value;
		}
		throw new ResultException("the value " + value + " is not a list, can not use getList() method");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getAsList() {
		T value = getValue();
		if (value instanceof List<?>) {
			return (List<T>)value;
		}
		return Arrays.asList(value);
	}
	
	@Override
	public IClaw getClaw() {
		return claw;
	}

	@Override
	public boolean isFound() {
		return found;
	}

	@Override
	public ResultException getException() {
		return exception;
	}
}
