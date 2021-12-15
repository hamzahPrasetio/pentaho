package com.example.pentaho.controller;

import com.example.pentaho.RunningTransformations;
import com.example.pentaho.models.cases_time_series;
import com.example.pentaho.models.statewise;
import com.example.pentaho.models.tested;
import com.example.pentaho.services.dataservices;
import org.pentaho.di.trans.Trans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class WebController {

    @Autowired
    dataservices dataservices;

    @GetMapping("/runTransformation")
    public String runTransformation() {
        RunningTransformations runningTransformations = new RunningTransformations();

        // run a transformation from the file system
        Trans trans = runningTransformations.runTransformationFromFileSystem(
                "F:/Jayandra/Project/Export PostgreSQL/pentaho/ktr/test.ktr" );

        return "index";
    }

    @GetMapping("/")
    public String index(Model model){
        List<statewise> stateWiseList = dataservices.getStatewiseList();
        List<tested> testedList = dataservices.getTestedList();
        List<cases_time_series> caseTimeSeriesList = dataservices.getCaseTimeSeriesList();
        model.addAttribute("stateWiseList", stateWiseList.subList(1, stateWiseList.size()-1));
        model.addAttribute("tested",testedList.get(testedList.size()-1).getTotalSamplesTested());
        model.addAttribute("caseTimeSeries",caseTimeSeriesList.subList(caseTimeSeriesList.size()-15,caseTimeSeriesList.size()-1));
        model.addAttribute("nationalStatus", stateWiseList.get(0));
        return "index";
    }
}
