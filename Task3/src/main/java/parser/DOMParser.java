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

public class DOMParser implements Parser{
    private Document document;

    public DOMParser(){}

    private Certificate createCertificate(NodeList nodes){
        Certificate certificate = new Certificate();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                certificate.setDateOfDelivery(Integer.valueOf(element.getElementsByTagName("Date").item(0).getTextContent()));
                certificate.setOrganization(element.getElementsByTagName("Organization").item(0).getTextContent());
                certificate.setNumber(Integer.valueOf(element.getAttribute("number")));
            }
        }
        return certificate;
    }

    private Dosage createDosage(NodeList nodes){
        Dosage dosage = new Dosage();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                dosage.setPeriod(Integer.valueOf(element.getElementsByTagName("Period").item(0).getTextContent()));
                dosage.setDose(Integer.valueOf(element.getElementsByTagName("Dose").item(0).getTextContent()));
            }
        }
        return dosage;
    }

    private Package createPackage(NodeList nodes){
        Package aPackage = new Package();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                aPackage.setPriceForPackage(Integer.valueOf(element.getElementsByTagName("Price").item(0).getTextContent()));
                aPackage.setQuantity(Integer.valueOf(element.getElementsByTagName("Quantity").item(0).getTextContent()));
                aPackage.setType(TypeOfPackage.setTypeOfPackage(element.getElementsByTagName("TypeOfPackage").item(0).getTextContent()));
            }
        }
        return aPackage;
    }

    private Pharm createPharm(NodeList nodes){
        Pharm pharm = new Pharm();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                pharm.setName(element.getAttribute("nameOfPharm"));
                pharm.setCertificate(createCertificate(document.getElementsByTagName("Certificate")));
                pharm.setDosage(createDosage(document.getElementsByTagName("Dosage")));
                pharm.setaPackage(createPackage(document.getElementsByTagName("Package")));
            }
        }
        return pharm;
    }

    private Version createVersion(NodeList nodes){
        Version version = new Version();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = nodes.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;

                version.setType(VersionType.setVersion(element.getAttribute("versionType")));
                version.setPharm(createPharm(document.getElementsByTagName("Pharm")));
            }
        }
        return version;
    }

    @Override
    public void parse(String file) throws ParserConfigurationException, IOException, SAXException {

        File xmlFile = new File(file);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        builder = factory.newDocumentBuilder();
        document = builder.parse(xmlFile);
        document.getDocumentElement();

        List<Medicine> medicins = new ArrayList<>();
        NodeList nodeList = document.getDocumentElement().getChildNodes();

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);

            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                Medicine med = new Medicine();

                med.setName(element.getAttribute("name"));
                med.setGroup(Group.setGroup(element.getElementsByTagName("Group").item(0).getTextContent()));
                med.setVersion(createVersion(document.getElementsByTagName("Version")));
                medicins.add(med);
            }
        }
    }
}