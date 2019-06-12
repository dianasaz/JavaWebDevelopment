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

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StAXParser extends Builder{
    public final static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private String file;

    public StAXParser(String file) {
        this.file = file;
    }

    @Override
    public void buildList(){
        Medicine medicine = null;
        Certificate certificate = null;
        Pharm pharm = null;
        Version version = null;
        Dosage dosage = null;
        Package pack = new Package();
        List<String> analogs = null;
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
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Analogs")) {
                        analogs = new ArrayList<>();
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Analog")){
                        event = eventReader.nextEvent();
                        analogs.add(event.asCharacters().getData());
                    } else if (startElement.getName().getLocalPart().equalsIgnoreCase("Version")) {
                        medicine.setAnalog(analogs);
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
                        Date date = dateFormat.parse(String.valueOf(event.asCharacters().getData()));
                        certificate.setDateOfDelivery(date);
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
                        medicines.add(medicine);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
