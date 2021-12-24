package com.example.pentaho.controller;

import com.example.pentaho.RunningTransformations;
import org.pentaho.di.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class WebController {

    @GetMapping("/runTransformation")
    public String runTransformation() {
        RunningTransformations runningTransformations = new RunningTransformations();

        // run a transformation from the file system
        Trans trans = runningTransformations.runTransformationFromFileSystem(
                "F:/Jayandra/Project/Export PostgreSQL/pentaho/ktr/test.ktr" );

        return "index";
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }
}
