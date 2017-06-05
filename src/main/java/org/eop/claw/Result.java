package org.eop.claw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
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
		return TypeUtil.toBigDecimal(get());
	}

	@Override
	public BigInteger getBigInteger() {
		return TypeUtil.toBigInteger(get());
	}

	@Override
	public Boolean getBoolean() {
		return TypeUtil.toBoolean(get());
	}

	@Override
	public Byte getByte() {
		return TypeUtil.toByte(get());
	}

	@Override
	public Date getDate(String format) {
		return TypeUtil.toDate(get(), format);
	}

	@Override
	public Double getDouble() {
		return TypeUtil.toDouble(get());
	}

	@Override
	public Float getFloat() {
		return TypeUtil.toFloat(get());
	}

	@Override
	public Integer getInteger() {
		return TypeUtil.toInteger(get());
	}

	@Override
	public Long getLong() {
		return TypeUtil.toLong(get());
	}

	@Override
	public Short getShort() {
		return TypeUtil.toShort(get());
	}

	@Override
	public String getString() {
		return TypeUtil.toString(get());
	}

	@Override
	public Timestamp getTimestamp(String format) {
		return TypeUtil.toTimestamp(get(), format);
	}

	@Override
	public <T> List<T> getList() {
		return TypeUtil.toList(get());
	}

	@Override
	public Object get() {
		if (found) {
			return claw.getUnderlyingData();
		}
		throw exception;
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
