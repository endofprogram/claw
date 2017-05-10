package old.org.eop.claw.util;

import java.util.List;

import org.dom4j.Element;
import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.TypeUtil;
/**
 * lixinjie 2016-12-26
 */
public final class Dom4jXmlUtil {

	private Dom4jXmlUtil() {
		
	}
	
	public static Element getFirstChild(Element element, String tagName) {
		return element.element(tagName);
	}
	
	public static Element getFirstChild(Element element) {
		List<Element> elementList = TypeUtil.asListType(element.elements());
		if (EmptyUtil.isEmpty(elementList)) {
			return null;
		}
		return ListUtil.getFirst(elementList);
	}
	
	public static Element getLastChild(Element element, String tagName) {
		List<Element> elementList = TypeUtil.asListType(element.elements(tagName));
		if (EmptyUtil.isEmpty(elementList)) {
			return null;
		}
		return ListUtil.getLast(elementList);
	}
	
	public static Element getLastChild(Element element) {
		List<Element> elementList = TypeUtil.asListType(element.elements());
		if (EmptyUtil.isEmpty(elementList)) {
			return null;
		}
		return ListUtil.getLast(elementList);
	}
	
	public static List<Element> getChildren(Element element, String tagName) {
		return TypeUtil.asListType(element.elements(tagName));
	}
	
	public static List<Element> getChildren(Element element) {
		return TypeUtil.asListType(element.elements());
	}
	
	public static List<String> getChildrenText(Element element, String tagName) {
		List<Element> eleList = getChildren(element, tagName);
		List<String> textList = ListUtil.newList(eleList.size());
		for (Element ele : eleList) {
			textList.add(ele.getText());
		}
		return textList;
	}
	
	public static List<String> getChildrenText(Element element) {
		List<Element> eleList = getChildren(element);
		List<String> textList = ListUtil.newList(eleList.size());
		for (Element ele : eleList) {
			textList.add(ele.getText());
		}
		return textList;
	}
	
	public static List<String> getAllFirstChildText(List<Element> elementList, String tagName) {
		List<String> textList = ListUtil.newList(elementList.size());
		for (Element element : elementList) {
			textList.add(getFirstChild(element, tagName).getText());
		}
		return textList;
	}
	
	public static List<Element> getAllFirstChild(List<Element> elementList, String tagName) {
		List<Element> eleList = ListUtil.newList(elementList.size());
		for (Element element : elementList) {
			eleList.add(getFirstChild(element, tagName));
		}
		return eleList;
	}
	
	public static List<String> getAllLastChildText(List<Element> elementList, String tagName) {
		List<String> textList = ListUtil.newList(elementList.size());
		for (Element element : elementList) {
			textList.add(getLastChild(element, tagName).getText());
		}
		return textList;
	}
	
	public static List<Element> getAllLastChild(List<Element> elementList, String tagName) {
		List<Element> eleList = ListUtil.newList(elementList.size());
		for (Element element : elementList) {
			eleList.add(getLastChild(element, tagName));
		}
		return eleList;
	}
	
	public static List<List<Element>> getAllChildren(List<Element> elementList, String tagName) {
		List<List<Element>> elementListList = ListUtil.newList(elementList.size());
		for (Element element : elementList) {
			elementListList.add(getChildren(getFirstChild(element, tagName)));
		}
		return elementListList;
	}
	
	public static List<List<String>> getAllChildrenText(List<Element> elementList, String tagName) {
		List<List<String>> textListList = ListUtil.newList(elementList.size());
		for (Element element : elementList) {
			textListList.add(getChildrenText(getFirstChild(element, tagName)));
		}
		return textListList;
	}
	
	public static String getText(Element element, boolean elementCheck, String defaultValue) {
		if (!elementCheck) {
			return element.getText();
		}
		
		if (element == null) {
			return defaultValue;
		}
		
		if (EmptyUtil.isEmpty(element.getText())) {
			return defaultValue;
		}
		
		return element.getText();
	}
}
