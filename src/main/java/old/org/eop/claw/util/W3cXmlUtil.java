package old.org.eop.claw.util;

import java.util.List;

import org.eop.chassis.util.EmptyUtil;
import org.eop.chassis.util.EqualUtil;
import org.eop.chassis.util.ListUtil;
import org.eop.chassis.util.NullUtil;
import org.eop.chassis.util.TypeUtil;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 * lixinjie 2016-12-26
 */
public final class W3cXmlUtil {

	private W3cXmlUtil() {
		
	}
	
	public static Element getFirstChild(Element element, String tagName) {
		NodeList childNodes = element.getChildNodes();
		if (EmptyUtil.isEmpty(childNodes)) {
			return null;
		}
		for (int i = 0, len = childNodes.getLength(); i < len; i++) {
			if (TypeUtil.isW3cElement(childNodes.item(i))) {
				if (childNodes.item(i).getNodeName().equals(tagName)) {
					return TypeUtil.asType(childNodes.item(i));
				}
			}
		}
		return null;
	}
	
	public static Element getFirstChild(Element element) {
		NodeList childNodes = element.getChildNodes();
		if (EmptyUtil.isEmpty(childNodes)) {
			return null;
		}
		for (int i = 0, len = childNodes.getLength(); i < len; i++) {
			if (TypeUtil.isW3cElement(childNodes.item(i))) {
				return TypeUtil.asType(childNodes.item(i));
			}
		}
		return null;
	}
	
	public static Element getLastChild(Element element, String tagName) {
		NodeList childNodes = element.getChildNodes();
		if (EmptyUtil.isEmpty(childNodes)) {
			return null;
		}
		for (int i = (childNodes.getLength() - 1); i >= 0; i--) {
			if (TypeUtil.isW3cElement(childNodes.item(i))) {
				if (childNodes.item(i).getNodeName().equals(tagName)) {
					return TypeUtil.asType(childNodes.item(i));
				}
			}
		}
		return null;
	}
	
	public static Element getLastChild(Element element) {
		NodeList childNodes = element.getChildNodes();
		if (EmptyUtil.isEmpty(childNodes)) {
			return null;
		}
		for (int i = (childNodes.getLength() - 1); i >= 0; i--) {
			if (TypeUtil.isW3cElement(childNodes.item(i))) {
				return TypeUtil.asType(childNodes.item(i));
			}
		}
		return null;
	}
	
	
	
	public static List<Element> getChildren(Element element, String tagName) {
		List<Node> elementList = ListUtil.newList();
		NodeList childNodes = element.getChildNodes();
		if (EmptyUtil.isEmpty(childNodes)) {
			return TypeUtil.asListType(elementList);
		}
		for (int i = 0, len = childNodes.getLength(); i < len; i++) {
			if (TypeUtil.isW3cElement(childNodes.item(i))) {
				if (childNodes.item(i).getNodeName().equals(tagName)) {
					elementList.add(childNodes.item(i));
				}
			}
		}
		return TypeUtil.asListType(elementList);
	}
	
	public static List<Element> getChildren(Element element) {
		List<Node> elementList = ListUtil.newList();
		NodeList childNodes = element.getChildNodes();
		if (EmptyUtil.isEmpty(childNodes)) {
			return TypeUtil.asListType(elementList);
		}
		for (int i = 0, len = childNodes.getLength(); i < len; i++) {
			if (TypeUtil.isW3cElement(childNodes.item(i))) {
				elementList.add(childNodes.item(i));
			}
		}
		return TypeUtil.asListType(elementList);
	}
	
	public static List<Element> getChildrenIfAllSame(Element element) {
		List<Node> elementList = ListUtil.newList();
		NodeList childNodes = element.getChildNodes();
		if (EmptyUtil.isEmpty(childNodes)) {
			return TypeUtil.asListType(elementList);
		}
		String nodeName = null;
		for (int i = 0, len = childNodes.getLength(); i < len; i++) {
			if (TypeUtil.isW3cElement(childNodes.item(i))) {
				if (NullUtil.notNull(nodeName) || EqualUtil.notEqual(childNodes.item(i).getNodeName(), nodeName)) {
					return null;
				}
				elementList.add(childNodes.item(i));
				nodeName = childNodes.item(i).getNodeName();
			}
		}
		return TypeUtil.asListType(elementList);
	}
	
	
	
	public static List<Element> getW3cElementList(Element element, String tagName) {
		List<Element> children = getChildren(element, tagName);
		if (EmptyUtil.isEmpty(children)) {
			return null;
		}
		for (Element child : children) {
			if (EmptyUtil.isEmpty(getChildren(child))) {
				return null;
			}
		}
		return children;
	}
	
	public static List<List<Element>> getW3cElementListList(Element element, String tagName) {
		List<Element> children = getChildren(element, tagName);
		if (EmptyUtil.isEmpty(children)) {
			return null;
		}
		List<List<Element>> childrenList = ListUtil.newList();
		for (Element child : children) {
			List<Element> eleList = getChildrenIfAllSame(child);
			if (EmptyUtil.isEmpty(eleList)) {
				return null;
			}
			for (Element ele : eleList) {
				if (EmptyUtil.isEmpty(getChildren(ele))) {
					return null;
				}
			}
			childrenList.add(eleList);
		}
		return childrenList;
	}
	
	public static List<List<Object>> getElementListList(Element element, String tagName) {
		List<Element> children = getChildren(element, tagName);
		if (EmptyUtil.isEmpty(children)) {
			return null;
		}
		List<List<Object>> childrenTextList = ListUtil.newList();
		for (Element child : children) {
			List<Element> eleList = getChildrenIfAllSame(child);
			if (EmptyUtil.isEmpty(eleList)) {
				return null;
			}
			List<Object> textList = ListUtil.newList();
			for (Element ele : eleList) {
				if (EmptyUtil.notEmpty(getChildren(ele))) {
					return null;
				}
				textList.add(getText(ele, true, ""));
			}
			childrenTextList.add(textList);
		}
		return childrenTextList;
	}
	
	public static List<List<List<Object>>> getListListList(Element element, String tagName) {
		List<Element> children = getChildren(element, tagName);
		if (EmptyUtil.isEmpty(children)) {
			return null;
		}
		List<List<List<Element>>> childrenListList = ListUtil.newList();
		for (Element child : children) {
			List<Element> eleList = getChildrenIfAllSame(child);
			if (EmptyUtil.isEmpty(eleList)) {
				return null;
			}
			List<List<Element>> childrenList = ListUtil.newList();
			for (Element ele : eleList) {
				List<Element> eList = getChildrenIfAllSame(ele);
				if (EmptyUtil.isEmpty(eList)) {
					return null;
				}
				childrenList.add(eList);
			}
			childrenListList.add(childrenList);
		}
		return TypeUtil.asListListListType(childrenListList);
	}
	
	
	
	public static List<String> getChildrenText(Element element, String tagName) {
		List<Element> eleList = getChildren(element, tagName);
		List<String> textList = ListUtil.newList(eleList.size());
		for (Element ele : eleList) {
			textList.add(ele.getTextContent());
		}
		return textList;
	}
	
	public static List<String> getChildrenText(Element element) {
		List<Element> eleList = getChildren(element);
		List<String> textList = ListUtil.newList(eleList.size());
		for (Element ele : eleList) {
			textList.add(ele.getTextContent());
		}
		return textList;
	}
	
	
	
	public static List<String> getAllFirstChildText(List<Element> elementList, String tagName) {
		List<String> textList = ListUtil.newList(elementList.size());
		for (Element element : elementList) {
			textList.add(getFirstChild(element, tagName).getTextContent());
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
			textList.add(getLastChild(element, tagName).getTextContent());
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
			return element.getTextContent();
		}
		
		if (element == null) {
			return defaultValue;
		}
		
		if (EmptyUtil.isEmpty(element.getTextContent())) {
			return defaultValue;
		}
		return element.getTextContent();
	}
	
	
}
