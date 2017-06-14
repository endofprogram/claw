package org.eop.claw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.eop.claw.exception.ResultException;

/**
 * @author lixinjie
 * @since 2017-06-05
 */
public interface IResult {

	BigDecimal getBigDecimal();
	
	BigInteger getBigInteger();
	
	Boolean getBoolean();
	
	Byte getByte();
	
	Date getDate(String format);
	
	Double getDouble();
	
	Float getFloat();
	
	Integer getInteger();
	
	Long getLong();
	
	Short getShort();
	
	String getString();
	
	Timestamp getTimestamp(String format);
	
	<T> T getValue();
	
	<T> List<T> getList();
	
	<T> List<T> getAsList();
	
	IClaw getClaw();
	
	boolean isFound();
	
	ResultException getException();
}
