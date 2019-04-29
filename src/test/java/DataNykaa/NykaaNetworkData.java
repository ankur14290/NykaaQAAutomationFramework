package DataNykaa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class NykaaNetworkData {
    @Id
    @Column
    private String rowId;
    @Column
    private String testcaseid;
    @Column
    private String Question;
    @Column
    private String Reply;

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getTestcaseid() {
        return testcaseid;
    }

    public void setTestcaseid(String testcaseid) {
        this.testcaseid = testcaseid;
    }

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public String getReply() {
        return Reply;
    }

    public void setReply(String reply) {
        Reply = reply;
    }

    }
