package com.haanhgs.sqlitedatabaseroomdemo.model.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "job_table")
public class Job {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "job_id")
    private int jobId;

    @ColumnInfo(name = "job_name")
    private String jobName;

    public Job(int jobId, String jobName) {
        this.jobId = jobId;
        this.jobName = jobName;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
}
