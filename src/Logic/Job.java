package Logic;

public class Job implements Comparable<Job> {

    private String jobName;
    private int jobProfit;
    private int jobDeadline;


    public Job(String jobName, int jobProfit, int jobDeadline) {
        this.jobName = jobName;
        this.jobProfit = jobProfit;
        this.jobDeadline = jobDeadline;
    }

    public String getJobName() {
        return jobName;
    }

    public int getJobProfit() {
        return jobProfit;
    }

    public int getJobDeadline() {
        return jobDeadline;
    }

    @Override
    public int compareTo(Job o) {
        return o.jobProfit - this.jobProfit;
    }
}