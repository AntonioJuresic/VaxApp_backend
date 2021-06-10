package hr.tvz.juresic.vaxapp.quartzjobs;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfiguration {

    @Bean
    public JobDetail getAllVaccinesJobDetail() {
        return JobBuilder
                .newJob(GetAllVaccinesJob.class)
                .withIdentity("getAllVaccinesJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger getAllVaccinesJobTrigger() {
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
                .simpleSchedule()
                .withIntervalInSeconds(10)
                .repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(getAllVaccinesJobDetail())
                .withIdentity("getAllVaccinesJobTrigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

    @Bean
    public Trigger getAllVaccinesJobTriggerForEachMondayAndMondayAtNoon() {

        // https://www.freeformatter.com/cron-expression-generator-quartz.html
        // 0 0 12 ? * MON,FRI *
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 12 ? * MON,FRI *");

        return TriggerBuilder.newTrigger()
                .forJob(getAllVaccinesJobDetail())
                .withIdentity("getAllVaccinesJobTriggerForEachMondayAndMondayAtNoon")
                .startNow()
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
