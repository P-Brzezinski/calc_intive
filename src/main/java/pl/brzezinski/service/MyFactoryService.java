package pl.brzezinski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import pl.brzezinski.config.Configuration;

@Component
public class MyFactoryService {

    @Qualifier(Configuration.FILE_SERVICE)
    private final FileService fileService;

    @Qualifier(Configuration.H2_SERVICE)
    private final H2Service h2Service;

    @Autowired
    public MyFactoryService(FileService fileService, H2Service h2Service) {
        this.fileService = fileService;
        this.h2Service = h2Service;
    }

//    public static DBService getService(String service){
//        if (service.equals(Configuration.FILE_SERVICE)){
//            return fileService;
//        }else {
//            return h2Service;
//        }
//    }
}
