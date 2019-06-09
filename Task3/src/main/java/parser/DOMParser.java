package parser;

import entity.Certificate;
import entity.Dosage;
import entity.Group;
import entity.Medicine;
import entity.Package;
import entity.Pharm;
import entity.TypeOfPackage;
import entity.Version;
import entity.VersionType;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {

    public DOMParser(String file) throws ParserConfigurationException, IOException, SAXException {

        File xmlFile = new File(file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);
        document.getDocumentElement();

        List<Medicine> medicins = new ArrayList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Medicine med = new Medicine();
                Version version = new Version();
                Certificate certificate = new Certificate();
                Dosage dosage = new Dosage();
                Package aPackage = new Package();
                Pharm pharm = new Pharm();

                med.setName(element.getAttribute("name"));

                med.setGroup(Group.setGroup(element.getElementsByTagName("Group").item(0).getTextContent()));
                version.setType(VersionType.setVersion(element.getAttribute("versionType")));
                NodeList nodeList1 = element.getChildNodes();

                for (int j = 0; j < nodeList1.getLength(); j++) {
                    Node node1 = nodeList1.item(j);

                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        Element element1 = (Element) node1;

                        pharm.setName(element1.getAttribute("nameOfPharm"));

                        NodeList nodeList2 = element1.getChildNodes();

                        for (int g = 0; g < nodeList1.getLength(); g++) {
                            Node node2 = nodeList2.item(g);

                            if (node2.getNodeType() == Node.ELEMENT_NODE) {
                                Element element2 = (Element) node2;

                                certificate.setNumber(Integer.valueOf(element2.getAttribute("number")));
                                certificate.setDateOfDelivery(Integer.valueOf(element2.getElementsByTagName("Date").item(0).getTextContent()));
                                certificate.setOrganization(element2.getElementsByTagName("Organization").item(0).getTextContent());
                                pharm.setCertificate(certificate);


                            }
                        }

                        med.setVersion(version);
                        version.setPharm(pharm);
                /*NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node cNode = childNodes.item(j);

                    if (cNode instanceof Element) {
                        String content = cNode.getLastChild().getTextContent().trim();
                        NamedNodeMap attributes = cNode.getAttributes();
                        Element element = (Element) cNode;
                        med.setGroup(Group.setGroup(element.getAttribute("Group")));
                        VersionType versionType = VersionType.setVersion(element.getAttribute("versionType"));
                        version.setType(versionType);

                        for (int k = 0; k < cNode.getChildNodes().getLength(); k++) {
                            Element el = (Element) cNode.getChildNodes().item(k);

                            pharm.setName(el.getTextContent());

                            for (int h = 0; h < childNodes.getLength(); h++) {
                                Element ele = (Element) el.getChildNodes().item(h);

                                certificate.setNumber(Integer.valueOf(ele.getAttribute("number")));

                            }/*
                        switch (el){
                            case "Group":{
                               med.setGroup(Group.setGroup(content));
                               break;
                            }
                            case "Version":{
                                VersionType versionType = VersionType.setVersion(attributes.getNamedItem("versionType").getNodeValue());
                                version.setType(versionType);
                            }
                            case "Pharm":{
                                pharm.setName(attributes.getNamedItem("nameOfPharm").getNodeValue());
                                break;
                            }
                            case "Certificate":{
                                certificate.setNumber(Integer.valueOf(attributes.getNamedItem("number").getNodeValue()));
                                break;
                            }
                            case "Date":{
                                certificate.setDateOfDelivery(Integer.valueOf(attributes.getNamedItem("nameOfPharm").getNodeValue()));
                                break;
                            }
                            case "Organization":{
                                certificate.setOrganization(content);
                                break;
                            }
                            case "Dose":{
                                dosage.setDose(Integer.valueOf(content));
                                break;
                            }
                            case "Period":{
                                dosage.setPeriod(Integer.valueOf(content));
                                break;
                            }
                            case "TypeOfPackage":{
                                aPackage.setType(TypeOfPackage.setTypeOfPackage(content));
                                break;
                            }
                            case "Quantity":{
                                aPackage.setQuantity(Integer.valueOf(content));
                                break;
                            }
                            case "Price":{
                                aPackage.setPriceForPackage(Integer.valueOf(content));
                                pharm.setaPackage(aPackage);
                                pharm.setCertificate(certificate);
                                pharm.setDosage(dosage);
                                version.setPharm(pharm);
                                med.setVersion(version);
                                break;
                            }
                        }*/
                        version.setPharm(pharm);
                        med.setVersion(version);
                        medicins.add(med);
                    }

                }
                for (Medicine medic : medicins) {
                    System.out.println(medic);
                }

            }
        }
    }
}
