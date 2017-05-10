package old.org.eop.claw;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
/**
 * lixinjie 2016-12-26
 */
public interface IClaw {
	
	BigDecimal getBigDecimal(String path);
	
	BigInteger getBigInteger(String path);
	
	Boolean getBoolean(String path);
	
	Byte getByte(String path);
	
	Date getDate(String path);
	
	Double getDouble(String path);
	
	Float getFloat(String path);
	
	Integer getInteger(String path);
	
	Long getLong(String path);
	
	Short getShort(String path);
	
	String getString(String path);
	
	Timestamp getTimestamp(String path);
	
	<T> List<T> getList(String path);
	
	Object get(String path);
	
	<T> T get(String path, Class<T> targetClass);

	IClaw getClaw(String path);
	
	Object getUnderlyingData();
}
