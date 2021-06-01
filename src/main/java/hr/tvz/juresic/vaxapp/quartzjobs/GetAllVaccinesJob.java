package hr.tvz.juresic.vaxapp.quartzjobs;

import hr.tvz.juresic.vaxapp.vaccine.Vaccine;
import hr.tvz.juresic.vaxapp.vaccine.VaccineDTO;
import hr.tvz.juresic.vaxapp.vaccine.VaccineServiceImplementation;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

public class GetAllVaccinesJob extends QuartzJobBean {
    @Autowired
    private VaccineServiceImplementation vaccineServiceImplementation;

    private String name = "GetAllVaccinesJob";

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<VaccineDTO> vaccineDTOList = vaccineServiceImplementation.findAll();

        System.out.println("=============================================");
        System.out.println("Ovo su trenutno dostupna cjepiva:");

        for(VaccineDTO currentVaccineDTO : vaccineDTOList) {
            if(currentVaccineDTO.getAvailableDoses() > 0)
                System.out.println(currentVaccineDTO.getResearchName() + " - " + currentVaccineDTO.getAvailableDoses());
        }

        System.out.println("=============================================");
    }
}
