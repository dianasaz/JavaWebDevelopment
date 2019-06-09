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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

public class SAXParser {

    public SAXParser(String file) {
        SAXParserFactory factory;
        factory = SAXParserFactory.newInstance();
        javax.xml.parsers.SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
        } catch (
                ParserConfigurationException e) {
            e.printStackTrace();
        } catch (
                SAXException e) {
            e.printStackTrace();
        }

        DefaultHandler handler = new DefaultHandler() {
            List<Medicine> medicins;
            Medicine medicine;
            Certificate certificate;
            Version version;
            Dosage dosage;
            Package aPackage;
            Pharm pharm;

            String elementName;
            String name;
            Group group;
            VersionType versionType;
            String nameOfPharm;
            int number;
            int date;
            String organization;
            int dose;
            int period;
            TypeOfPackage typeOfPackage;
            int quantity;
            double price;
            Attributes attributes;

            @Override
            public void startDocument() throws SAXException {
                medicins = new ArrayList<>();
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
                elementName = qName;
                switch (elementName){
                    case "Medicine":{
                        name = atts.getValue("name");
                        break;
                    }
                    case "Version": {
                        String v = atts.getValue("versionType");
                        versionType = VersionType.setVersion(v);
                        break;
                    }
                    case "Pharm": {
                        nameOfPharm = atts.getValue("nameOfPharm");
                        break;
                    }
                    case "Certificate": {
                        number = Integer.valueOf(atts.getValue("number"));
                        break;
                    }
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String data = new String(ch, start, length);

                data = data.replace("\n", "").trim();
                if (!data.isEmpty()) {
                    switch (elementName) {
                        case "Group":
                            group = Group.setGroup(data);
                            break;
                        case "Date":
                            date = Integer.valueOf(data);
                            break;
                        case "Organization":
                            organization = data;
                            break;
                        case "Dose":
                            dose = Integer.valueOf(data);
                            break;
                        case "Period":
                            period = Integer.valueOf(data);
                            break;
                        case "TypeOfPackage":
                            typeOfPackage = TypeOfPackage.setTypeOfPackage(data);
                            break;
                        case "Quantity":
                            quantity = Integer.valueOf(data);
                            break;
                        case "Price":
                            price = Double.valueOf(data);
                            break;
                    }
                }
            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                if (qName.equalsIgnoreCase("Medicine")) {
                    aPackage = new Package(typeOfPackage, quantity, price);
                    dosage = new Dosage(dose, period);
                    certificate = new Certificate(number, date, organization);
                    pharm = new Pharm(nameOfPharm, certificate, aPackage, dosage);
                    version = new Version(versionType, pharm);
                    medicine = new Medicine(name, group, version);
                    medicins.add(medicine);
                }
            }

            @Override
            public void endDocument() {
                    System.out.println(medicins.toString());
            }
        };

        try {
            saxParser.parse(file, handler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (
                MalformedURLException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
