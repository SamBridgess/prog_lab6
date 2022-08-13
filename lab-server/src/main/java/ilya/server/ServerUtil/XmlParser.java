package ilya.server.ServerUtil;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public final class XmlParser {
    private XmlParser() {
    }

    /**
     * converts collection to xml file
     * @param manager       manager of collection to convert
     * @param path          path to file
     */
    public static void convertCollectionToXml(CollectionManager manager, String path) {
        try {
            JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(path));
            marshaller.marshal(manager, bufferedOutputStream);
            bufferedOutputStream.close();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * converts xml file to collection
     *
     * @param path          path to file
     * @return              returns updated collection manager
     * @throws JAXBException
     */
    public static CollectionManager convertXmlToCollection(String path) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(CollectionManager.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (CollectionManager) unmarshaller.unmarshal(new File(path));
    }
}
