package ru.gpsbox.test.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Schedular {


    @Scheduled(cron = "0 0 10 * * MON,FRI,SAT")
    public void weekDays() {

    }

    @Scheduled(cron = "0 0 10 * * MON-FRI")
    public void dailyScheduleJob() {

    }

    @Scheduled(cron = "0 0/30 * * * *")
    public void everyThirtyMinutes() {
        System.out.println("Runs everyThirtyMinutes RUN " + LocalDateTime.now() + "\n");
    }


    @Scheduled(cron = "0 0/10 * * * *")
    public void everyTenMinutes() {
        System.out.println("Runs everyTenMinutes RUN "+ LocalDateTime.now() + "\n");
    }

    @Scheduled(cron = "0 0/15 * * * *")
    public void everyFivTeenMinutes() {
        System.out.println("Runs everyFivTeenMinutes RUN "+ LocalDateTime.now() + "\n");
    }

    @Scheduled(cron = "0 * * * * *")
    public void everyMinute() {
        System.out.println("Runs everyMinute RUN "+ LocalDateTime.now() + "\n");
    }

    @Scheduled(cron = "0 0/5 * * * *")
    public void everyFiveMinutes() {
        System.out.println("Runs everyFiveMinutes RUN "+ LocalDateTime.now() + "\n");
    }


    @Scheduled(cron = "* * 12  * * ?") // at 12
    @Scheduled(cron = "0 0/3 * * * ?") //every 3 sec
    @Scheduled(cron = "0 0/10 * * * ?") //every 10 sec
    public void complexRun() {
        LocalDateTime time = LocalDateTime.now();
        System.out.println("Pick! Pick!  Pick!  - "+ time.getSecond() + "\n");
    }

}


