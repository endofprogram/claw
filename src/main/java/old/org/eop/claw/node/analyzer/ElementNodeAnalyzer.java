package old.org.eop.claw.node.analyzer;

import old.org.eop.claw.node.WNode;
import old.org.eop.claw.node.WNodeAnalyzer;
import old.org.eop.claw.node.element.BigDecimalElement;
import old.org.eop.claw.node.element.BigIntegerElement;
import old.org.eop.claw.node.element.BooleanElement;
import old.org.eop.claw.node.element.ByteElement;
import old.org.eop.claw.node.element.DateElement;
import old.org.eop.claw.node.element.DoubleElement;
import old.org.eop.claw.node.element.FloatElement;
import old.org.eop.claw.node.element.IntegerElement;
import old.org.eop.claw.node.element.LongElement;
import old.org.eop.claw.node.element.ObjectElement;
import old.org.eop.claw.node.element.ShortElement;
import old.org.eop.claw.node.element.StringElement;
import old.org.eop.claw.node.element.TimestampElement;
/**
 * lixinjie 2016-12-26
 */
public class ElementNodeAnalyzer extends WNodeAnalyzer {

	public ElementNodeAnalyzer() {
		super("<", ">");
	}
	
	@Override
	protected WNode buildWnode(String segment) {
		if (wnodeSetting == null || !wnodeSetting.containsKey("type")) {
			return new ObjectElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("BigDecimal".equals(wnodeSetting.get("type"))) {
			return new BigDecimalElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("BigInteger".equals(wnodeSetting.get("type"))) {
			return new BigIntegerElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Boolean".equals(wnodeSetting.get("type"))) {
			return new BooleanElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Byte".equals(wnodeSetting.get("type"))) {
			return new ByteElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Date".equals(wnodeSetting.get("type"))) {
			return new DateElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Double".equals(wnodeSetting.get("type"))) {
			return new DoubleElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Float".equals(wnodeSetting.get("type"))) {
			return new FloatElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Integer".equals(wnodeSetting.get("type"))) {
			return new IntegerElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Long".equals(wnodeSetting.get("type"))) {
			return new LongElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Short".equals(wnodeSetting.get("type"))) {
			return new ShortElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("String".equals(wnodeSetting.get("type"))) {
			return new StringElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Timestamp".equals(wnodeSetting.get("type"))) {
			return new TimestampElement(wnodeName, wnodeType, wnodeSetting);
		}
		if ("Object".equals(wnodeSetting.get("type"))) {
			return new ObjectElement(wnodeName, wnodeType, wnodeSetting);
		}
		return null;
	}

}
