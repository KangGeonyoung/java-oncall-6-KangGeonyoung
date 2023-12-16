package oncall.controller;

import oncall.domain.Date;
import oncall.domain.EmergencyWorkAssignment;
import oncall.domain.Member;

public class OncallController {

    private Date dateSystem;
    private Member memberSystem;
    private EmergencyWorkAssignment assignmentSystem;

    public OncallController() {
        this.dateSystem = new Date();
        this.memberSystem = new Member();
    }

    public void start() {

    }

}
