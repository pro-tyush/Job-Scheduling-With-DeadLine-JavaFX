package Logic;

import java.util.*;

public class JobScheduling {

    private int noOfJobs;
    private List<Job> jobs = new ArrayList<>();

    private int maxDeadline;
    private String[] resultJobSequence;
    private int totalProfit;

    public JobScheduling(int noOfJobs) {
        this.noOfJobs = noOfJobs;
        this.totalProfit = 0;
        this.maxDeadline = -1;
    }

    public void addJob(String jobName, int jobProfit, int jobDeadline) {
        Job job = new Job(jobName, jobProfit, jobDeadline);
        jobs.add(job);
    }

    private void updateMaxDeadline() {
        for (Job j : jobs) { //For-Each
            if (j.getJobDeadline() > maxDeadline) {
                maxDeadline = j.getJobDeadline();
            }
        }
    }

    //JobSequencing Algorithm:
    public String[] getResultJobSequence() {
        Collections.sort(jobs);
        updateMaxDeadline();

        resultJobSequence = new String[maxDeadline];

        for (int i = 0; i < noOfJobs; i++) {
            int currDeadline = jobs.get(i).getJobDeadline() - 1;

            if (resultJobSequence[currDeadline] == null) { //if current Slot Empty
                resultJobSequence[currDeadline] = jobs.get(i).getJobName(); //assign job to slot
                totalProfit += jobs.get(i).getJobProfit(); //add Profit to job
            } else {
                while (currDeadline != -1) {
                    if (resultJobSequence[currDeadline] == null) {
                        resultJobSequence[currDeadline] = jobs.get(i).getJobName();
                        totalProfit += jobs.get(i).getJobProfit();
                        break;
                    }
                    currDeadline--;
                }
            }
        }
        return resultJobSequence;
    }

    public int getTotalProfit() {
        return totalProfit;
    }

}
