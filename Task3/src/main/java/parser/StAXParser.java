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
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StAXParser implements Parser{

    public StAXParser() {}

    @Override
    public void parse(String file) throws ParserConfigurationException, IOException, SAXException {
        List<Medicine> medicins = new ArrayList<>();
        Medicine medicine = null;
        Certificate certificate = null;
        Pharm pharm = null;
        Version version = null;
        Dosage dosage = null;
        Package pack = new Package();
        XMLInputFactory factory = XMLInputFactory.newInstance();

        try {
            XMLEventReader eventReader = factory.createXMLEventReader(new FileInputStream(file));

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equalsIgnoreCase("Medicine")) {
                        medicine = new Medicine();
                        Attribute att = startElement.getAttributeByName(new QName("name"));
                        if (att != null) medicine.setName(att.getValue());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Group")) {
                        event = eventReader.nextEvent();
                        medicine.setGroup(Group.setGroup(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Version")) {
                        version = new Version();
                        Attribute atr = startElement.getAttributeByName(new QName("versionType"));
                        version.setType(VersionType.setVersion(atr.getValue()));
                        medicine.setVersion(version);
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Pharm")) {
                        Attribute att = startElement.getAttributeByName(new QName("nameOfPharm"));
                        pharm = new Pharm();
                        if (att != null) pharm.setName(att.getValue());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Certificate")) {
                        Attribute att = startElement.getAttributeByName(new QName("number"));
                        certificate = new Certificate();
                        certificate.setNumber(Integer.valueOf(att.getValue()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Date")) {
                        event = eventReader.nextEvent();
                        certificate.setDateOfDelivery(Integer.valueOf(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Organization")) {
                        event = eventReader.nextEvent();
                        certificate.setOrganization(event.asCharacters().getData());
                        pharm.setCertificate(certificate);
                        version.setPharm(pharm);
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Dosage")){
                        dosage = new Dosage();
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Dose")) {
                        event = eventReader.nextEvent();
                        dosage.setDose(Integer.valueOf(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Period")) {
                        event = eventReader.nextEvent();
                        dosage.setPeriod(Integer.valueOf(event.asCharacters().getData()));
                        pharm.setDosage(dosage);
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Package")){
                        pack = new Package();
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("TypeOfPackage")){
                        event = eventReader.nextEvent();
                        pack.setType(TypeOfPackage.setTypeOfPackage(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Quantity")){
                        event = eventReader.nextEvent();
                        pack.setQuantity(Integer.valueOf(event.asCharacters().getData()));
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Price")){
                        event = eventReader.nextEvent();
                        pack.setPriceForPackage(Integer.valueOf(event.asCharacters().getData()));
                        pharm.setaPackage(pack);
                    }
                }
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals("Medicine")) {
                        medicins.add(medicine);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }
}
